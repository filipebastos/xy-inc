package com.xyinc.service;

import java.util.Collection;

import com.xyinc.model.PontoInteresse;

public interface IPontoInteresseService {

	public void incluir(PontoInteresse pontoInteresse);

	public void alterar(PontoInteresse pontoInteresse);

	public void remover(PontoInteresse pontoInteresse);

	public Collection<PontoInteresse> listarTodos();

	public Collection<PontoInteresse> listarPorProximidade(Integer coordenadaX, Integer coordenadaY, Integer limiteDistancia);

}
