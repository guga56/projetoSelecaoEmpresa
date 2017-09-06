package control;

import org.jcommon.encryption.SimpleMD5;

import entity.Usuario;

public class Criptografia {

	public static void criptografia(Usuario u){
		SimpleMD5 md5 = new SimpleMD5(u.getSenha(), "coti@informatica123");
		u.setSenha(md5.toHexString());
	}
	
	public static void main(String[] args) {
		Usuario u = new Usuario();
		u.setSenha("123");
		Criptografia.criptografia(u);
		System.out.println(u.getSenha());
	}
}
