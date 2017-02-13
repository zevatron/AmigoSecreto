package modelo;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Pessoa {
	private String nome;
	private String email;
	private String senhaHash;
	private Pessoa amigo;
	private ArrayList<Mensagem> caixaDeEntrada = new ArrayList<Mensagem>();
	private ArrayList<Mensagem> caixadeSaida = new ArrayList<Mensagem>();
	private ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Mensagem> getCaixaDeEntrada() {
		return caixaDeEntrada;
	}
	public void setCaixaDeEntrada(ArrayList<Mensagem> caixaDeEntrada) {
		this.caixaDeEntrada = caixaDeEntrada;
	}
	public ArrayList<Mensagem> getCaixadeSaida() {
		return caixadeSaida;
	}
	public void setCaixadeSaida(ArrayList<Mensagem> caixadeSaida) {
		this.caixadeSaida = caixadeSaida;
	}
	public ArrayList<Grupo> getListaGrupos() {
		return listaGrupos;
	}
	public void setListaGrupos(ArrayList<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	public void adicionarGrupo(Grupo g){
		listaGrupos.add(g);
	}
	public void removerGrupo(Grupo g){
		listaGrupos.remove(g);
	}
	public void adicionarCxEntrada(Mensagem m){
		caixaDeEntrada.add(m);
	}
public void adicionarCxSaida(Mensagem m){
		caixadeSaida.add(m);
	}
	public String getSenhaHash() {
		return senhaHash;
	}
	public void setSenhaHash(String senha) {
		try {
			this.senhaHash = Senha.converterSenhaHash(senha);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Pessoa getAmigo() {
		return amigo;
	}
	public void setAmigo(Pessoa amigo) {
		this.amigo = amigo;
	}
	public Pessoa(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		setSenhaHash(senha);
	}
	@Override
	public String toString() {
		return nome + " - " + email;
	}
	
	
	
	
	

}
