package br.com.ssouza.web.spring.persistence.repository;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import br.com.ssouza.exception.ApplicationException;
import br.com.ssouza.persistente.SimplesObjetoPersistente;

public interface SpringGenericRepository<T extends SimplesObjetoPersistente> {

	List<T> findAll(Criterion criterion, Order[] orders);

	T save(T object);

	List<T> findAll(String[][] createAlias, Criterion criterion, Order[] orders);

	T find(Long id);

	boolean exist(String[][] createAlias, Criterion criterion);

	boolean exist(Criterion criterion);

	void evict(T object);

	void delete(T object) throws ApplicationException;

	Long total(Criterion criterion);

	Long total(String[][] arrayAlias, Criterion criterion);

}