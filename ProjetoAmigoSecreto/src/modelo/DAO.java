package modelo;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

public class DAO {
	
	static ObjectContainer manager ;
	
	
	
	public ObjectContainer DAO() {

		ClientConfiguration config = Db4oClientServer. newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		//conexao: usar o ip do computador servidor (ver ipconfig) e qualquer porta
		return manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");	
		//manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");	

		
	}

}
