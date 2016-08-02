package com.xyinc.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.xyinc.model.PontoInteresse;
import com.xyinc.model.PontoInteresseNamedQuery;
import com.xyinc.service.BaseManager;
import com.xyinc.service.IPontoInteresseService;
import com.xyinc.service.XyIncException;

public class PontoInteresseService extends BaseManager implements IPontoInteresseService, Serializable {

	private static final long serialVersionUID = 1L;

	@Transactional
	public void incluir(PontoInteresse pontoInteresse) {
		if (validar(pontoInteresse)) {
			create(pontoInteresse);
		}
	}

	@Transactional
	public void alterar(PontoInteresse pontoInteresse) {
		if (validar(pontoInteresse)) {
			update(pontoInteresse);
		}
	}

	@Transactional
	public void remover(PontoInteresse pontoInteresse) {
		remove(buscarPeloId(pontoInteresse.getId()));
	}

	@Transactional(readOnly = true)
	public Collection<PontoInteresse> listarTodos() {
		return criarQueryNomeada(PontoInteresseNamedQuery.LISTAR_TODOS, PontoInteresse.class).getResultList();
	}

	@Transactional(readOnly = true)
	public PontoInteresse buscarPeloId(Long id) {
		return find(PontoInteresse.class, id);
	}

	@Transactional(readOnly = true)
	public Collection<PontoInteresse> listarPorProximidade(Integer coordenadaX, Integer coordenadaY, Integer limiteDistancia) {
		TypedQuery<PontoInteresse> q = criarQueryNomeada(PontoInteresseNamedQuery.LISTAR_POR_APROXIMACAO, PontoInteresse.class);
		q.setParameter("coordenadaX", coordenadaX + limiteDistancia);
		q.setParameter("coordenadaY", coordenadaY + limiteDistancia);
		return q.getResultList();
	}

	private boolean validar(PontoInteresse pontoInteresse) {

		if (pontoInteresse.getNome() == null || pontoInteresse.getNome().isEmpty()) {
			throw new XyIncException("O nome deve ser informado.");
		}

		if (pontoInteresse.getCoordenadaX() == null || pontoInteresse.getCoordenadaX() < 0) {
			throw new XyIncException("A coordenada X deve ser informada e não pode ser negativa.");
		}

		if (pontoInteresse.getCoordenadaY() == null || pontoInteresse.getCoordenadaY() < 0) {
			throw new XyIncException("A coordenada Y deve ser informada e não pode ser negativa.");
		}

		return true;
	}
}
