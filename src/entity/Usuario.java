package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
	private Integer idUsuario;
	@Column
    private String login;
	@NotEmpty(message="Digite a Senha")
	@Column
	private String senha;
	@OneToOne(mappedBy="usuario", targetEntity=Cliente.class, cascade={CascadeType.ALL})
	private Cliente cliente;
	
	public Usuario() {
		
	}

	public Usuario(Integer idUsuario, String login, String senha) {
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "login=" + login;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
