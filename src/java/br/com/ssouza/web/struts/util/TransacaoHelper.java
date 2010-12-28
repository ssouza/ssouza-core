package br.com.ssouza.web.struts.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.ssouza.util.Constantes;

/**
 * Classe que define as configurações da sessão e da transação do Hibernate.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
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
	 * Método que cria uma sessão.
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
	 * Método que fecha a sessão, caso ela ainda esteja aberta.
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
	 * Método que inicializa uma transação.
	 * 
	 * @throws HibernateException
	 */
	public static void beginTransaction() throws HibernateException {
		Transaction transaction = getSession().beginTransaction();
		transactionThreadLocal.set(transaction);
	}

	/**
	 * Método que verifica se a transação está ativa.
	 * 
	 * @return <code>true</code> se a transação estiver ativa.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isActive() {
		Transaction transaction = getSession().beginTransaction();
		return transaction.isActive();
	}

	/**
	 * Método que efetiva uma transação.
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
	 * Método que desfaz uma transação.
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
