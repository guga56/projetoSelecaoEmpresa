package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
	private Integer idCliente;
    @NotEmpty(message="Nome não pode ficar em branco")
	@Column(length=40, nullable=false)
    private String nome;
    @NotEmpty(message="email não pode ficar em branco")
	@Column(length=20, nullable=false)
	private String email;
	@Column(length=40, nullable=false)
	@NotEmpty(message="endereço não pode ficar em branco")
	private String endereco;
	@Column(length=40, nullable=false)
	private String cidade;
	@Column
	private String estado;
	@NotEmpty(message="Cep não pode ficar em branco")
	@Column(length=9, nullable=false)
	private String cep;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@OneToOne
	@JoinColumn(name="id_usuario", unique=true)
	private Usuario usuario;
	
	public Cliente() {
		
	}

	public Cliente(Integer idCliente, String nome, String email,
			String endereco, String cidade, String estado, String cep,
			Date dataNascimento) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.dataNascimento = dataNascimento;
	}
    
 	public Cliente(Integer idCliente, String nome, String email,
			String endereco, String cidade, String estado, String cep,
			Date dataNascimento, Usuario usuario) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.dataNascimento = dataNascimento;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome
				+ ", email=" + email + ", endereco=" + endereco + ", cidade="
				+ cidade + ", estado=" + estado + ", cep=" + cep
				+ ", dataNascimento=" + dataNascimento + "]";
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
