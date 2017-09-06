package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import control.Criptografia;
import entity.Cliente;
import entity.Usuario;

public class ClienteDao {

	Session session;
	Transaction transaction;
	Criteria criteria;
	Query query;
	
	public void create(Usuario u, Cliente c) throws Exception{
	    Criptografia.criptografia(u);	
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		u.setCliente(c);
		c.setUsuario(u);
		session.save(c);
		session.save(u);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(){
		session = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> listaCliente = session.createQuery("from Cliente").list();
		session.close();
		return listaCliente;
	}
	
	public void delete(Cliente c) throws Exception{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.delete(c);
		transaction.commit();
		session.close();
	}
	
	public void update(Cliente c) throws Exception{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.update(c);
		transaction.commit();
		session.close();
	}
	
	public Cliente findByCode(Integer cod) throws Exception{
		session = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = (Cliente) session.get(Cliente.class, cod);
		session.close();
		return cliente;
	}
}
