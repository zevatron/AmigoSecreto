package modelo;

import java.util.Date;

public class Mensagem {
	
//	private Pessoa remetente,destinatario;
	private String assunto,mensagem;
	private Date data;
	private boolean lida=false;
	
	public Mensagem (String assunto, String mensagem) {
		super();
//		this.remetente = remetente;
//		this.destinatario = destinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.data = new Date();
		
	}
	

	@Override
	public String toString() {
		return data+" - " + assunto;
	}



	public Mensagem() {
		super();
		// TODO Auto-generated constructor stub
	}



	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}


	public String getAssunto() {
		return assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Date getData() {
		return data;
	}
	
	


}
