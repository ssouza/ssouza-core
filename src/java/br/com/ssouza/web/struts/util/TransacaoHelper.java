package br.com.ssouza.web.struts.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.ssouza.util.Constantes;

/**
 * Classe que define as configura��es da sess�o e da transa��o do Hibernate.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class TransacaoHelper {

	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> transactionThreadLocal = new ThreadLocal<Transaction>();

	/**
	 * Construtor
	 */
	static {
		try {
			sessionFactory = new Configuration().configure(Constantes.FILE_HIBERNATE_CONFIG).buildSessionFactory();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * M�todo que cria uma sess�o.
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		if (sessionThreadLocal.get() == null) {
			Session session = sessionFactory.openSession();
			sessionThreadLocal.set(session);
		}
		return (Session) sessionThreadLocal.get();
	}

	/**
	 * M�todo que fecha a sess�o, caso ela ainda esteja aberta.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) sessionThreadLocal.get();
		if (session != null && session.isOpen()) {
			sessionThreadLocal.set(null);
			session.close();
		}
	}

	/**
	 * M�todo que inicializa uma transa��o.
	 * 
	 * @throws HibernateException
	 */
	public static void beginTransaction() throws HibernateException {
		Transaction transaction = getSession().beginTransaction();
		transactionThreadLocal.set(transaction);
	}

	/**
	 * M�todo que verifica se a transa��o est� ativa.
	 * 
	 * @return <code>true</code> se a transa��o estiver ativa.<br>
	 *         <code>false</code> caso contr�rio.
	 */
	public static boolean isActive() {
		Transaction transaction = getSession().beginTransaction();
		return transaction.isActive();
	}

	/**
	 * M�todo que efetiva uma transa��o.
	 * 
	 * @throws HibernateException
	 */
	public static void commitTransaction() throws HibernateException {
		Transaction transaction = (Transaction) transactionThreadLocal.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.commit();
			transactionThreadLocal.set(null);
		}
	}

	/**
	 * M�todo que desfaz uma transa��o.
	 * 
	 * @throws HibernateException
	 */
	public static void rollbackTransaction() throws HibernateException {
		Transaction transaction = (Transaction) transactionThreadLocal.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.rollback();
			transactionThreadLocal.set(null);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
