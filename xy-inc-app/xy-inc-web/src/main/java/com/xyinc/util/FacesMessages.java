package com.xyinc.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class FacesMessages {

	public static enum TipoMensagem {
		ALERTA, INFO, ERRO, FATAL;
	}

	public FacesMessages() {

	}

	public static void apresentarMensagem(String titulo, String descricao) {
		apresentarMensagem(titulo, descricao, null);
	}

	public static void apresentarMensagem(String titulo, String descricao, String clientId) {
		apresentarMensagem(TipoMensagem.INFO, titulo, descricao, clientId);
	}

	public static void apresentarMensagem(TipoMensagem tipo, String mensagem) {
		apresentarMensagem(tipo, null, mensagem, null);
	}

	public static void apresentarMensagem(TipoMensagem tipo, String mensagem, String clientId) {
		apresentarMensagem(tipo, null, mensagem, clientId);
	}

	public static void apresentarMensagem(TipoMensagem tipo, String resumo, String mensagem, String clientId) {

		Severity tipoMensagem;
		switch (tipo) {
			case ALERTA:
				tipoMensagem = FacesMessage.SEVERITY_WARN;
				break;
			case ERRO:
				tipoMensagem = FacesMessage.SEVERITY_ERROR;
				break;
			case FATAL:
				tipoMensagem = FacesMessage.SEVERITY_FATAL;
				break;
			default:
				tipoMensagem = FacesMessage.SEVERITY_INFO;
		}

		FacesMessage msg = null;
		if (resumo == null) {
			msg = new FacesMessage(tipoMensagem, mensagem, mensagem);
		} else {
			msg = new FacesMessage(tipoMensagem, resumo, mensagem);
		}

		FacesContext.getCurrentInstance().addMessage(clientId, msg);
	}
}
