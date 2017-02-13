package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Grupo {
	
	private String titulo;
	private Pessoa admin;
	
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private ArrayList<Pessoa> pessoasEmbaralhadas = new ArrayList<Pessoa>();
	
	
	
	public Grupo(String titulo, Pessoa admin) {
		super();
		this.titulo = titulo;
		this.admin = admin;
	}
	
	public void adicionarPessoa(Pessoa p){
		pessoas.add(p);
		pessoasEmbaralhadas.add(p);
	}
	public void removerPessoa(Pessoa p){
		pessoas.remove(p);
		pessoasEmbaralhadas.remove(p);
	}
	
	public Pessoa localizarPessoa(String email){
		for(Pessoa p : pessoas){
			if(p.getEmail().equals(email))
				return p;
		}
		return null;
	}
	
	public Pessoa getAdmin() {
		return admin;
	}
	public void setAdmin(Pessoa admin) {
		this.admin = admin;
	}
	public void embaralhar(){
				
		Collections.shuffle(pessoasEmbaralhadas);
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public ArrayList<Pessoa> getPessoasEmbaralhadas() {
		return pessoasEmbaralhadas;
	}

	public void setPessoasEmbaralhadas(ArrayList<Pessoa> pessoasEmbaralhadas) {
		this.pessoasEmbaralhadas = pessoasEmbaralhadas;
	}
	
	
	

}
