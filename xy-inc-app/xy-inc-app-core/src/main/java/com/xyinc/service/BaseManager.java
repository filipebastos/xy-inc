package com.xyinc.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.BeanUtils;

public class BaseManager {

	protected EntityManager entityManager;

	protected BaseManager() {

	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected <T> T get(Class<T> clazz, Number id) {
		return entityManager.find(clazz, id);
	}

	protected void copyProperties(Object dest, Object src) {
		copyProperties(dest, src, new String[0]);
	}

	protected void copyPropertiesSemId(Object dest, Object src) {
		copyProperties(dest, src, new String[] { "id" });
	}

	protected void copyProperties(Object dest, Object src, String[] excludes) {
		BeanUtils.copyProperties(src, dest, excludes);
	}

	protected <T> void create(T entity) {
		try {
			entityManager.persist(entity);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new XyIncException(e);
		}
	}

	protected <T> TypedQuery<T> createNamedQuery(String q, Class<T> clazz) {
		return entityManager.createNamedQuery(q, clazz);
	}

	protected <T> T update(T entity) {
		try {
			entity = entityManager.merge(entity);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new XyIncException(e);
		}

		return entity;
	}

	protected <T> void remove(T entity) {
		try {
			entityManager.remove(entity);
		} catch (IllegalArgumentException e) {
			throw new XyIncException(e);
		}
	}

	protected <T> boolean contains(T entity) {
		return entityManager.contains(entity);
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	protected <T> TypedQuery<T> createCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery);
	}

	protected Metamodel getMetamodel() {
		return entityManager.getMetamodel();
	}
}