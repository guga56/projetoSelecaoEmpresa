package persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import control.Criptografia;
import entity.Usuario;

public class UsuarioDao {

	Session session;
	Transaction transaction;
	Criteria criteria;
	Query query;
	
	public void create(Usuario u) throws Exception{
		Criptografia.criptografia(u);
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(u);
		transaction.commit();
		session.close();
	}
	
	public Usuario login(Usuario u){
		Criptografia.criptografia(u);
		session = HibernateUtil.getSessionFactory().openSession();
		query = session.createQuery("from Usuario u where u.login=:param1 and u.senha=:param2");
		query.setString("param1", u.getLogin());
		query.setString("param2", u.getSenha());
		Usuario resp = (Usuario) query.uniqueResult();
		session.close();
		return resp;
	}
}
