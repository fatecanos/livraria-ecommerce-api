package com.fatec.livrariaecommerce.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Cliente extends Pessoa {
	@Id
	@SequenceGenerator(
			name="clientes_sequence",
			sequenceName="clientes_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "clientes_sequence"
	)
	private int id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_TIPO_CLIENTE"))
	@Transient
	private TipoCliente tipoCliente;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_USUARIO"))
	@Transient
	private Usuario usuario;

	public Cliente() {

	}

	public Cliente(String nome, String sobrenome, LocalDate dataNascimento) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*

	@OneToMany
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ENDERECO"))
	private List<Endereco> enderecos;
	*/


}
