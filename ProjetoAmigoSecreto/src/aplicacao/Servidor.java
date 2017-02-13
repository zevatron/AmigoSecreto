package aplicacao;
/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 * teste cliente servidor com db4o
 */
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

public class Servidor implements Runnable{
	private ObjectServer servidor;
	private Thread thread ;
	
	public Servidor() {	

		if(new File("bancoservidor.db4o").exists()){
			Scanner teclado = new Scanner(System.in);
			String opcao = " ";
			do{
				System.out.print(" Voc� deseja apagar o banco de dados? (s/n) Opc�o: ");
				opcao = teclado.nextLine().toUpperCase();
			}while(opcao.compareTo("S") != 0 && opcao.compareTo("N") != 0);
			if(opcao.compareTo("S")==0){
				new File ("bancoservidor.db4o").delete(); //apagar o arquivo
				System.out.println("banco de dados foi apagado");
			}
			teclado.close();
		}

		
		ServerConfiguration config = Db4oClientServer.newServerConfiguration() ;
		config.common().messageLevel(0);   //0,1,2,3,4
		servidor = Db4oClientServer.openServer(config, "bancoservidor.db4o",34000);

		// cadastrar usuarios
		servidor.grantAccess("usuario1","senha1");
		servidor.grantAccess("usuario2","senha2");
		servidor.grantAccess("usuario3","senha3");
		servidor.grantAccess("usuario4","senha4");
		servidor.grantAccess("usuario5","senha5");
		servidor.grantAccess("usuario6","senha6");
		servidor.grantAccess("usuario7","senha7");
		servidor.grantAccess("usuario8","senha8");
		servidor.grantAccess("usuario9","senha9");
		servidor.grantAccess("usuario10","senha10");
		servidor.grantAccess("usuario11","senha11");
		servidor.grantAccess("usuario12","senha12");
		servidor.grantAccess("usuario13","senha13");
		servidor.grantAccess("usuario14","senha14");
		servidor.grantAccess("usuario15","senha15");

		long databaseSize = servidor.ext().objectContainer().ext().systemInfo().totalSize();
		System.out.println("Database size: "+databaseSize);
		System.out.println("Servidor inicializado com sucesso: ");
		thread = new Thread(this);
		thread.start();		
	}

	public void run() {
			System.out.println("\nservidor pronto! ");

			System.out.println("aguardando requisi��o....  ");
			JOptionPane.showMessageDialog(null, "execute a aplica��o cliente \ntecle ok para encerrar o servidor");
			close();
	}
	
	public void close() {
		System.out.println("\nFechando o servidor. ");
		servidor.close();
		thread.interrupt();	
	}
	
	public static void main(String[] args) {
		new Servidor();	
	}


}
