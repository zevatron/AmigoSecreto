package modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Senha {
	
		
	public static String converterSenhaHash (String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
		StringBuilder hexString;
		byte messageDigest[]=algorithm.digest(senha.getBytes("UTF-8"));
		 hexString = new StringBuilder();
		for(byte b : messageDigest){
			hexString.append(String.format("%02X",0xFF & b));
		}
		
		 return hexString.toString();
	}
}
