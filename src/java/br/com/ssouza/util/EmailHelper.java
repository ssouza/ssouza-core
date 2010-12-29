package br.com.ssouza.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.Flags.Flag;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import br.com.ssouza.exception.SystemException;

/**
 * Classe que define o envio de e-mail.
 * 
 * mail.host=smtp.ssouza.com.br
 * 
 * mail.port=587
 * 
 * mail.user=contato@ssouza.com.br
 * 
 * mail.pwd=******
 * 
 * mail.ssl=false
 * 
 * mail.authentication=true
 * 
 * mail.delay=10
 * 
 * mail.copy.sent.itens=true
 * 
 * mail.imap.host=imap.ssouza.com.br
 * 
 * mail.imap.port=993
 * 
 * mail.sent.itens.folder=Itens Enviados
 * 
 * mail.charset=ISO-8859-1
 * 
 * mail.content.type=text/plain
 * 
 * mail.sender.name=Sérgio Souza
 * 
 * mail.sender.mail=contato@ssouza.com.br
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class EmailHelper {

	private Properties properties = new Properties();

	private String host;
	private int port;
	private String username;
	private String password;
	private boolean ssl = Boolean.FALSE;
	private boolean authentication = Boolean.FALSE;
	private String contentType = Constantes.CONTENT_TYPE_TEXT;
	private String charset = Constantes.CHARSET_ISO;
	private int delay = 10;
	private String imapHost;
	private int imapPort;
	private String imapProtocol;

	private boolean copyToSentItens = Boolean.TRUE;
	private String folderSentItens;

	private String subject;
	private InternetAddress sender;
	private Set<InternetAddress> recipients = new HashSet<InternetAddress>();

	private Session session;

	private static final int timeout = 45000;

	public EmailHelper(String baseName, byte[] key) throws Exception {

		ResourceBundleHelper bundleHelper = ResourceBundleHelper.getInst(baseName);

		host = bundleHelper.getString("mail.host");

		port = bundleHelper.getInt("mail.port");

		username = bundleHelper.getString("mail.user");

		password = CriptografiaHelper.getInst(key).decrypt(bundleHelper.getString("mail.pwd"));

		ssl = bundleHelper.getBoolean("mail.ssl");

		authentication = bundleHelper.getBoolean("mail.authentication");

		copyToSentItens = bundleHelper.getBoolean("mail.copy.sent.itens");

		folderSentItens = bundleHelper.getString("mail.sent.itens.folder");

		delay = bundleHelper.getInt("mail.delay");

		charset = bundleHelper.getString("mail.charset");

		imapHost = bundleHelper.getString("mail.imap.host");

		imapPort = bundleHelper.getInt("mail.imap.port");

		imapProtocol = bundleHelper.getBoolean("mail.imap.ssl") ? "imaps" : "imap";

		contentType = bundleHelper.getString("mail.content.type");

		sender = new InternetAddress(bundleHelper.getString("mail.sender.mail"), bundleHelper
				.getString("mail.sender.name") == null ? "" : bundleHelper.getString("mail.sender.name"), charset);
	}

	public EmailHelper contentType(String contentType) {

		this.contentType = contentType;
		return this;
	}

	public EmailHelper subject(String subject) {

		this.subject = subject;
		return this;
	}

	public EmailHelper recipients(String[][] recipients) throws UnsupportedEncodingException {

		Set<InternetAddress> list = new HashSet<InternetAddress>();

		for (String[] recipient : recipients) {
			InternetAddress internetAddress = new InternetAddress(recipient[1], recipient[0], charset);
			list.add(internetAddress);
		}

		this.recipients = list;
		return this;
	}

	public boolean dispache(String message) throws Exception {

		return dispacheWithDebug(message, null);
	}

	public boolean dispacheWithDebug(String message, PrintWriter writer) throws Exception {

		validateParameters();

		prepareConnection();

		MimeMessage mimeMessage = new MimeMessage(session);

		mimeMessage.setHeader("Content-Type", contentType + "; charset=" + charset);
		mimeMessage.setSentDate(DataHelper.getDataAtual().getTime());
		mimeMessage.setFrom(sender);
		mimeMessage.setSender(sender);
		mimeMessage.setReplyTo(new Address[] { sender });
		mimeMessage.setSubject(subject, charset);

		if (Constantes.CONTENT_TYPE_TEXT.equalsIgnoreCase(contentType)) {
			mimeMessage.setText(message, charset);
		} else {
			mimeMessage.setContent(message, contentType + "; charset=" + charset);
		}

		for (InternetAddress recipient : recipients) {

			mimeMessage.setRecipient(RecipientType.TO, recipient);

			try {

				if (authentication) {

					Transport transport = session.getTransport(getProtocol());

					transport.connect(host, port, username, password);

					if (!transport.isConnected()) {
						throw new SystemException("Não foi possível efetuar conexão com o servidor SMTP.");
					}

					transport.sendMessage(mimeMessage, new Address[] { recipient });

				} else {
					Transport.send(mimeMessage, new Address[] { recipient });
				}

				if (copyToSentItens) {

					Store store = session.getStore(imapProtocol);
					store.connect(imapHost, imapPort, username, password);

					Folder folder = store.getDefaultFolder();

					if (folder == null || StringUtils.isBlank(folder.getFullName())) {
						folder = store.getFolder(folderSentItens);
					}

					folder.open(Folder.READ_WRITE);
					folder.appendMessages(new Message[] { mimeMessage });

					mimeMessage.setFlag(Flag.SEEN, true);

					store.close();
				}

				if (writer != null) {
					writer.print(recipient + " - Ok\n");
					writer.flush();
				}

			} catch (Exception e) {
				if (writer != null) {
					writer.print(recipient + " - Erro (" + e.getMessage() + ")\n");
					writer.flush();
				}
				LogHelper.getInstance().error("Erro na tentativa de envio de e-mail!", e);
			}

			if (delay > 0) {
				Thread.sleep(delay);
			}
		}

		return true;
	}

	public String processTemplate(String templateFolder, String templateFile, VelocityContext velocityContext)
			throws Exception {

		StringWriter writer = new StringWriter();

		prepareTemplate(templateFolder);

		Template template = Velocity.getTemplate(templateFile);
		template.merge(velocityContext, writer);

		return Constantes.CONTENT_TYPE_TEXT.equalsIgnoreCase(contentType) ? writer.toString().replaceAll("<br>", "\n")
				: writer.toString().replaceAll("\n", "").replaceAll("\r", "");
	}

	private void prepareTemplate(String templateFolder) throws Exception {

		Properties properties = new Properties();
		properties.setProperty("file.resource.loader.path", templateFolder);

		Velocity.init(properties);
	}

	private void prepareConnection() {

		String hostProperty = getHostProperty(ssl);
		String portProperty = getPortProperty(ssl);
		String authenticationProperty = getAuthenticationProperty(ssl);
		String timeoutProperty = getTimeoutProperty(ssl);
		String connectionTimeoutProperty = getConnectionTimeoutProperty(ssl);

		properties.put("mail.transport.protocol", getProtocol());
		properties.put(hostProperty, host);
		properties.put(portProperty, port);
		properties.put(timeoutProperty, timeout);
		properties.put(connectionTimeoutProperty, timeout);

		if (authentication) {
			properties.put(authenticationProperty, authentication);
		}

		if (authentication) {
			session = Session.getInstance(properties, new SSSMTPAuthenticator(username, password));
		} else {
			session = Session.getInstance(properties);
		}
	}

	private String getProtocol() {

		return ssl ? "smtps" : "smtp";
	}

	private String getHostProperty(boolean ssl) {

		return ssl ? "mail.smtps.host" : "mail.smtp.host";
	}

	private String getPortProperty(boolean ssl) {

		return ssl ? "mail.smtps.port" : "mail.smtp.port";
	}

	private String getAuthenticationProperty(boolean ssl) {

		return ssl ? "mail.smtps.auth" : "mail.smtp.auth";
	}

	private String getTimeoutProperty(boolean ssl) {

		return ssl ? "mail.smtps.timeout" : "mail.smtp.timeout";
	}

	private String getConnectionTimeoutProperty(boolean ssl) {

		return ssl ? "mail.smtps.connectiontimeout" : "mail.smtp.connectiontimeout";
	}

	private void validateParameters() {

		if (StringUtils.isBlank(host)) {
			throw new SystemException("Host is required.");
		}

		if (port <= 0) {
			throw new SystemException("Port can't be less than or equal to zero.");
		}

		if (imapPort <= 0) {
			throw new SystemException("IMAP Port can't be less than or equal to zero.");
		}

		if (delay < 0) {
			throw new SystemException("Delay can't be less than to zero.");
		}

		if (sender == null) {
			throw new SystemException("Sender is required.");
		}

		if (copyToSentItens && StringUtils.isBlank(folderSentItens)) {
			throw new SystemException("Folder 'Sent Itens' is required.");
		}

		if (StringUtils.isBlank(charset)) {
			charset = Constantes.CHARSET_ISO;
		}

		if (StringUtils.isBlank(contentType)) {
			contentType = Constantes.CONTENT_TYPE_TEXT;
		}

		if (StringUtils.isBlank(subject)) {
			throw new SystemException("Subject is required.");
		}

		if (recipients.isEmpty()) {
			throw new SystemException("Recipients is required.");
		}
	}

}

/**
 * Classe que define a autenticação SMTP.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
class SSSMTPAuthenticator extends javax.mail.Authenticator {

	private String username;
	private String password;

	/**
	 * Construtor
	 * 
	 * @param username
	 * @param password
	 */
	SSSMTPAuthenticator(String username, String password) {

		this.username = username;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(username, password);
	}

}