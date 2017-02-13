package fachada;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import modelo.Pessoa;
import modelo.Grupo;
import modelo.Mensagem;
import modelo.Senha;

public class Sistema {
	
	private static Pessoa logado=null;
	
	private static Grupo grupoSelecionado=null;
	
	private static ObjectContainer manager;
	
	public static Pessoa getLogado(){
		return logado;
	}
	
	
	public static Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public static void setGrupoSelecionado(Grupo grupoSelecionado) {
		Sistema.grupoSelecionado = grupoSelecionado;
	}

	public static void conectar() throws Exception{
		
		ClientConfiguration config = Db4oClientServer. newClientConfiguration( ) ;
		config.common().messageLevel(1);   //0,1,2,3,4
		config.common().objectClass(Pessoa.class).cascadeOnUpdate(true);;
		config.common().objectClass(Pessoa.class).cascadeOnActivate(true);
		config.common().objectClass(Pessoa.class).cascadeOnDelete(false);
		config.common().objectClass(Grupo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Grupo.class).cascadeOnActivate(true);
		config.common().objectClass(Grupo.class).cascadeOnDelete(false);
		config.common().objectClass(Mensagem.class).cascadeOnUpdate(true);;
		config.common().objectClass(Mensagem.class).cascadeOnActivate(true);
		config.common().objectClass(Mensagem.class).cascadeOnDelete(false);
		//conexao: usar o ip do computador servidor (ver ipconfig) e qualquer porta
		manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario2","senha2");	
		//manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");
	}
	
	public static void desconectar(){
		manager.close();
	}
	
	
	public static Pessoa localizarPessoa(String email){
		Query q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("email").constrain(email);
		List<Pessoa> resultados = q.execute();
//		for(Pessoa p: resultados)
//			System.out.println(p);
		if (resultados.isEmpty())
			return null;
		else
			return resultados.get(0);
	}
	public static Pessoa localizarPessoaNoGrupo(String email){
		Query q = manager.query();
		q.constrain(Grupo.class);
		q.descend("pessoas").descend("email").constrain(email);
		List<Pessoa> resultados = q.execute();
//		for(Pessoa p: resultados)
//			System.out.println(p);
		if (resultados.isEmpty())
			return null;
		else
			return resultados.get(0);
	}
	
	public static Grupo localizarGrupo(String titulo){
		Query q = manager.query();
		q.constrain(Grupo.class);
		q.descend("titulo").constrain(titulo).like();
		List<Grupo> resultados = q.execute();
//		for(Pessoa p: resultados)
//			System.out.println(p);
		if (resultados.isEmpty())
			return null;
		else
			return resultados.get(0);
	}
	
	
	public static Pessoa cadastrarPessoa(String nome, String email, String senha) throws Exception{
			
			Pessoa p = localizarPessoa(email);
			if(p!=null)
				throw new Exception("Pessoa já cadastrada com o email -> "+email);
			p = new Pessoa(nome, email, senha);
			manager.store(p);
			manager.commit();
			return p;
			
		}
	
	public static Grupo criarGrupo(String titulo) throws Exception{
		
		if (logado==null)
			throw new Exception("É necessário fazer login para criar grupos...");
		
		Grupo g = localizarGrupo(titulo);
		if (g!=null)
			throw new Exception("Grupo já existe com esse nome -> "+titulo +" tente outro nome.");
		g = new Grupo(titulo, Sistema.localizarPessoa(logado.getEmail()));
		manager.store(g);
		manager.commit();
		return g;
	}
	public static void adicionarPessoaGrupo(Pessoa p){
		grupoSelecionado.adicionarPessoa(p);
		p.adicionarGrupo(grupoSelecionado);
		manager.store(grupoSelecionado);
		manager.store(p);
		manager.commit();
	}
	public static void removerPessoaGrupo(Grupo g,Pessoa p){
		
	}
	public static void removerGrupo(Grupo g){
		for( Pessoa p : grupoSelecionado.getPessoas()){
			
		}
		manager.delete(grupoSelecionado);
		
	}
	public static void enviarMensagem(Mensagem m){
		logado.getAmigo().adicionarCxEntrada(m);
		logado.adicionarCxSaida(m);
		manager.store(logado);
		manager.commit();
	}
	public static void responderMensagem(Mensagem responder){
//		localizarQuemMeTirou(logado.getAmigo().getEmail()).adicionarCxEntrada(responder);
		logado.adicionarCxSaida(responder);
		manager.store(logado);
		manager.commit();
	}
	public static Pessoa localizarQuemMeTirou(Pessoa p){
		Query q = manager.query();
		q.constrain(Pessoa.class);
//		q.descend("listaGrupos").constrain(grupoSelecionado);
		q.descend("amigo").constrain(p);
		List<Pessoa> resultados = q.execute();
//		for(Pessoa p: resultados)
//			System.out.println(p);
		if (resultados.isEmpty())
			return null;
		else
			return resultados.get(0);
	}
	public static List<Mensagem> listarMensagensEntrada(){
		
		return logado.getCaixaDeEntrada();
		
	}
	
	public static void atualizarGrupo(Grupo g){
		manager.store(g);
		manager.commit();
	}
	public static void atualizarPessoa(Pessoa p){
		manager.store(p);
		manager.commit();
	}
	
	public static List<Grupo> listarGruposParticipando(Pessoa logado){
		Query q = manager.query();
		q.constrain(Grupo.class);
		q.descend("pessoas").constrain(logado);
		List<Grupo> restultados =  q.execute();
		return restultados;
		
	}
	
	public static List<Grupo> listarGruposAdmin(Pessoa logado){
		
		Query q = manager.query();
		q.constrain(Grupo.class);
		q.descend("admin").constrain(logado);
		List<Grupo> restultados =  q.execute();
		
		return restultados;
	}
	
	public static List<Pessoa> listarPessoas(){
		Query q = manager.query();
		q.constrain(Pessoa.class);
//		q.descend("email").constrain(email);
		List<Pessoa> resultados = q.execute();
//		for(Pessoa p: resultados)
//			System.out.println(p);
		if (resultados.isEmpty())
			return null;
		else
			return resultados;
	}
	
	
	
	public static Pessoa login(String email,String senha) throws Exception{
		Pessoa p;
		if(logado!=null)
			throw new Exception("já existe uma pessoa logada");
		p = localizarPessoa(email);
		if(p==null)
			throw new Exception("Erro: email("+email+") invalido.");
		if(p.getSenhaHash().equals(Senha.converterSenhaHash(senha))){
			logado = p;
//			gruposamigosecreto=listarGrupos(logado);
//			for(Grupo g : gruposamigosecreto){
//				System.out.println(g.getTitulo());
//			}
		}
		else 
			throw new Exception("Erro: senha invalida.");
		return logado;
	}
	
	public static String logoff() throws Exception{
		if (logado==null)
			throw new Exception("Não existe pessoas logado");
		else
			logado=null;
		return "Obrigado, volte sempre que desejar!";
//			System.out.println("Obrigado, volte sempre que desejar!");
			
	}

}
