package manager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import control.Criptografia;
import persistence.ClienteDao;
import entity.Cliente;
import entity.Usuario;

@ManagedBean(name="mbcliente")
@SessionScoped
public class ManagerBeanCliente implements Serializable{
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Usuario usuario;
	private Usuario usuarioLogado;
	private DataModel listaCliente;
	private Cliente clienteAltera;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public DataModel getListaCliente() {
		listaCliente = new ListDataModel( new ClienteDao().findAll() );
		return listaCliente;
	}

	public void setListaCliente(DataModel listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Cliente getClienteAltera() {
		return clienteAltera;
	}

	public void setClienteAltera(Cliente clienteAltera) {
		this.clienteAltera = clienteAltera;
	}

	public void cadastrarCliente(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			
			session.setAttribute("usuario", usuario);
			session.setAttribute("cliente", cliente);
	            
	        new ClienteDao().create(usuario, cliente);
	        Criptografia.criptografia(usuario);
			fc.addMessage("form4", new FacesMessage("Dados Gravados!"));
	        usuario = new Usuario();
	        cliente = new Cliente();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void excluir(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			new ClienteDao().delete((Cliente) listaCliente.getRowData());
			
			fc.addMessage("form5", new FacesMessage("Dados Excluidos!"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void alterar1(){
		clienteAltera = (Cliente) listaCliente.getRowData();
	}
	
	public void alterar2(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			new ClienteDao().update(clienteAltera);
			
			fc.addMessage("form5", new FacesMessage("Dados Alterados!"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
