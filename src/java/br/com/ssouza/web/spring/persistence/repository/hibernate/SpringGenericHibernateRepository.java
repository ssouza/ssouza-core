package br.com.ssouza.web.spring.persistence.repository.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.ssouza.exception.ApplicationException;
import br.com.ssouza.persistente.SimplesObjetoPersistente;
import br.com.ssouza.web.spring.persistence.repository.SpringGenericRepository;

public abstract class SpringGenericHibernateRepository<T extends SimplesObjetoPersistente> extends HibernateDaoSupport
		implements SpringGenericRepository<T> {

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public SpringGenericHibernateRepository() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void delete(T object) throws ApplicationException {
		try {
			getSession().delete(object);
			getSession().flush();

		} catch (ConstraintViolationException c) {
			throw new ApplicationException("Registro associado a um ou mais processo(s)", c);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void evict(T object) {
		getSession().evict(object);
	}

	@Override
	public boolean exist(Criterion criterion) {
		return exist(null, criterion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exist(String[][] createAlias, Criterion criterion) {

		Criteria criteria = getSession().createCriteria(clazz);

		if (!ArrayUtils.isEmpty(createAlias)) {
			for (String[] a : createAlias) {
				if (NumberUtils.isDigits(a[2])) {
					criteria.createAlias(a[0], a[1], Integer.valueOf(a[2]));
				} else {
					criteria.createAlias(a[0], a[1]);
				}
			}
		}

		if (criterion != null) {
			criteria.add(criterion);
		}

		List<T> list = criteria.list();

		if (list.isEmpty()) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Long id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String[][] createAlias, Criterion criterion, Order[] orders) {

		Criteria criteria = getSession().createCriteria(clazz);

		if (!ArrayUtils.isEmpty(createAlias)) {
			for (String[] a : createAlias) {
				if (NumberUtils.isDigits(a[2])) {
					criteria.createAlias(a[0], a[1], Integer.valueOf(a[2]));
				} else {
					criteria.createAlias(a[0], a[1]);
				}
			}
		}

		if (criterion != null) {
			criteria.add(criterion);
		}

		if (!ArrayUtils.isEmpty(orders)) {
			for (Order order : orders) {
				criteria.addOrder(order.ignoreCase());
			}
		}

		return criteria.list();
	}

	@Override
	public List<T> findAll(Criterion criterion, Order[] orders) {
		return this.findAll(null, criterion, orders);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T object) {
		T obj = (T) getSession().merge(object);
		getSession().flush();
		return obj;
	}

	@Override
	public Long total(String[][] arrayAlias, Criterion criterion) {

		Criteria criteria = getSession().createCriteria(clazz);
		criteria.setProjection(Projections.rowCount());

		if (criterion != null) {
			criteria.add(criterion);
		}

		if (arrayAlias != null && arrayAlias.length != 0) {
			for (String[] alias : arrayAlias) {
				criteria.createAlias(alias[0], alias[1]);
			}
		}

		return (Long) criteria.uniqueResult();
	}

	@Override
	public Long total(Criterion criterion) {
		return total(null, criterion);
	}

}