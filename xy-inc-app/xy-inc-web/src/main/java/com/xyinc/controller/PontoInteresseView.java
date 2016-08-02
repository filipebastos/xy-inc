package com.xyinc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.xyinc.helper.PontoInteresseHelper;
import com.xyinc.model.PontoInteresse;
import com.xyinc.service.IPontoInteresseService;
import com.xyinc.service.ServiceLocator;
import com.xyinc.service.XyIncException;
import com.xyinc.util.FacesMessages;
import com.xyinc.util.FacesMessages.TipoMensagem;

@ManagedBean
@ViewScoped
public class PontoInteresseView implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient IPontoInteresseService service = ServiceLocator.getInstance().getPontoInteresseService();

	private List<PontoInteresse> list;

	private PontoInteresse pontoInteresse;

	private PontoInteresseHelper helper;
	
	public PontoInteresseView() {
		reset();
		listarTodos();
	}
	
	public void reset() {
		helper = new PontoInteresseHelper();
		pontoInteresse = new PontoInteresse();
	}

	public void listarTodos() {
		list = new ArrayList<PontoInteresse>(service.listarTodos());
	}

	public void salvar() {

		try {

			if (pontoInteresse.getId() == null) {
				service.incluir(pontoInteresse);
			} else {
				service.alterar(pontoInteresse);
			}
			FacesMessages.apresentarMensagem(TipoMensagem.INFO, "Ponto de interesse salvo com sucessso!");
			RequestContext.getCurrentInstance().execute("PF('dlgCadastrar').hide()");

			reset();
			listarTodos();

		} catch (XyIncException e) {
			FacesMessages.apresentarMensagem(TipoMensagem.ERRO, e.getMessage());
		}
	}

	public void excluir() {

		try {

			service.remover(pontoInteresse);
			FacesMessages.apresentarMensagem(TipoMensagem.INFO, "Ponto de interesse exlcuído com sucessso!");

			reset();
			listarTodos();

		} catch (XyIncException e) {
			FacesMessages.apresentarMensagem(TipoMensagem.ERRO, e.getMessage());
		}
	}

	public void filtar() {
		list = new ArrayList<>(service.listarPorProximidade(helper.getCoordX(), helper.getCoordY(), helper.getMaxDist()));		
	}

	public List<PontoInteresse> getList() {
		return list;
	}

	public void setList(List<PontoInteresse> list) {
		this.list = list;
	}

	public PontoInteresse getPontoInteresse() {
		return pontoInteresse;
	}

	public void setPontoInteresse(PontoInteresse pontoInteresse) {
		this.pontoInteresse = pontoInteresse;
	}

	public PontoInteresseHelper getHelper() {
		return helper;
	}

	public void setHelper(PontoInteresseHelper helper) {
		this.helper = helper;
	}

}
