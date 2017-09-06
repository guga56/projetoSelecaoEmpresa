package manager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import persistence.UsuarioDao;
import entity.Usuario;

@ManagedBean(name="mb")
@SessionScoped
public class ManagerBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usuarioLogado;
	private String senhaConfirm;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
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
	
	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}

	public void cadastrarUsuario(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			if(senhaConfirm.equalsIgnoreCase(usuario.getSenha())){
				new UsuarioDao().create(usuario);
				usuario = new Usuario();
				fc.addMessage("form1", new FacesMessage("Dados Gravados!!"));
			}else{
				fc.addMessage("form1", new FacesMessage("Senhas Invalidas!"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String logar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			
			usuarioLogado = new UsuarioDao().login(usuario);
			if(usuarioLogado!=null){
				session.setAttribute("usuarioLogado", usuarioLogado);
				fc.addMessage("form3", new FacesMessage("usuario Logado com sucesso!"));
				return "adm/logado.jsf";
			}else{
				session.setAttribute("usuarioLogado", null);
			}
			fc.addMessage("form2", new FacesMessage("Login Invalido!!"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public void verificarLogin(){
		try{
			
			if(usuarioLogado ==null){
				usuario = new Usuario();
				FacesContext.getCurrentInstance().getExternalContext().redirect("../sistema.jsf");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String logout(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.invalidate();
			fc.addMessage("form2", new FacesMessage("Logout Realizado com sucesso!!"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return "../sistema.jsf";
	}
}
