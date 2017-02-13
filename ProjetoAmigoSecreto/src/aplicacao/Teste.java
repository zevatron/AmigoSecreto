package aplicacao;


import java.util.List;

import fachada.Sistema;
import modelo.Grupo;
import modelo.Mensagem;
import modelo.Pessoa;


public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Sistema.conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Sistema.cadastrarPessoa("José","jose", "123");
			Sistema.cadastrarPessoa("Pacola","pacola", "123");
			Sistema.cadastrarPessoa("José Pacola","jose.pacola@academico.ifpb.edu.br", "123");
			Sistema.cadastrarPessoa("Ricardo","ricardo", "123");
			Sistema.cadastrarPessoa("José Ricardo","zevatron@zevatron.com.br", "123");
			Sistema.cadastrarPessoa("Bettini","bettini", "123");
			Sistema.cadastrarPessoa("José Bettini","bettinipacola@gmail.com", "123");
			
			
		} catch (Exception e) {
			// TODO 	Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
//			Sistema.login("zevatron@zevatron.com.br", "123");
			Sistema.login("ricardo", "123");
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			Sistema.criarGrupo("2015.1");
			Sistema.criarGrupo("2015.2");
			Sistema.criarGrupo("Família2016");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//testando pessoa que esta logada
		System.out.println("User localizado-> "+Sistema.localizarPessoa("zevatron@zevatron.com.br"));
		//testado localizar grupo q começa com a letra f
		System.out.println("Grupo localizado-> "+Sistema.localizarGrupo("f").getTitulo());
		//listando todas os grupos que admin está participado
//		for(Grupo g : Sistema.listarGruposAdmin(Sistema.localizarPessoa("zevatron@zevatron.com.br")))
//			System.out.println(g.getTitulo());
		separador();

		//listando todas as pessoas cadastradas no sistema
		System.out.println("Pessoas cadastradas no Sistema");
		for(Pessoa p : Sistema.listarPessoas())
			System.out.println(p);
		
		Sistema.setGrupoSelecionado(Sistema.localizarGrupo("f"));
		System.out.println("Grupo selecionado-> "+Sistema.getGrupoSelecionado().getTitulo());

		//Adicionando todas as pessoas ao grupo selecionados para participar do amigo secreto
//		for(Pessoa p : Sistema.listarPessoas()){
//			Sistema.adicionarPessoaGrupo(p);
//			System.out.println("Adcionando-> "+p+" ao Grupo-> "+Sistema.getGrupoSelecionado().getTitulo());	
//		}
		Sistema.adicionarPessoaGrupo(Sistema.localizarPessoa("zevatron@zevatron.com.br"));
		
		separador();

		List<Pessoa> pessoas = Sistema.listarPessoas();
		System.out.println("Pessoas cadastradas no Sistema");
		for(Pessoa p : pessoas)
			System.out.println(p);

		
//		for(Pessoa p : pessoas)
//			Sistema.adicionarPessoaGrupo(p);
		
		separador();
		
		for (Pessoa p : Sistema.localizarGrupo(Sistema.getGrupoSelecionado().getTitulo()).getPessoas())
			System.out.println("Pessoas do grupo selecionado-> "+p);
		separador();
		
		for(Grupo g : Sistema.listarGruposParticipando(Sistema.getLogado())){
			System.out.println("Participando do grupo-> "+g.getTitulo());
		}
		separador();
//		Sistema.getGrupoSelecionado().embaralhar();
//		Sistema.atualizarGrupo(Sistema.getGrupoSelecionado());
//		System.out.println("Grupo embaralhado");
//		for (Pessoa p : Sistema.getGrupoSelecionado().getPessoasEmbaralhadas())
//			System.out.println(p);
		
		separador();
		
//		Sistema.getLogado().setAmigo(Sistema.getGrupoSelecionado().getPessoasEmbaralhadas().get(0));
//		Sistema.getGrupoSelecionado().getPessoasEmbaralhadas().remove(Sistema.getLogado().getAmigo());
//		Sistema.atualizarPessoa(Sistema.getLogado());
//		Sistema.atualizarGrupo(Sistema.getGrupoSelecionado());
		System.out.println("Seu amigo secreto é -> "+Sistema.getLogado().getAmigo().getNome());
		
		separador();
		
//		Mensagem m = new Mensagem("Assunto da mensagem", "Texto da mensagem.");
//		Sistema.enviarMensagem(m);
//		Sistema.atualizarPessoa(Sistema.getLogado());
		
//		for(Mensagem msg : Sistema.getLogado().getCaixaDeEntrada())
//			System.out.println(msg);
//		
//		Sistema.responderMensagem(new Mensagem("lista de presentes", "Gostaria de ganhar um relógio..."));
//		
//		
		separador();
		for(Mensagem msg : Sistema.getLogado().getCaixadeSaida())
			System.out.println(msg);
		separador();

		System.out.println("Quem-> "+Sistema.localizarQuemMeTirou(Sistema.getLogado()));
		
			
		Sistema.desconectar();
		
		try {
			Sistema.logoff();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	private static void separador() {
		System.out.println("----------------------------------");
	}

}
