package com.xyinc.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class BaseManager {

	@PersistenceContext
	private EntityManager entityManager;

	protected BaseManager() {

	}

	protected <T> void create(T object) {
		entityManager.persist(object);
	}

	protected <T> T update(T object) {
		return entityManager.merge(object);
	}

	protected <T> T find(Class<T> tipo, Number id) {
		return entityManager.find(tipo, id);
	}

	protected <T> void remove(T object) {
		entityManager.remove(object);
	}

	protected <T> TypedQuery<T> criarQueryNomeada(String query, Class<T> tipo) {
		return entityManager.createNamedQuery(query, tipo);
	}

	protected Query createJPQL(String query) {
		return entityManager.createQuery(query);
	}

}