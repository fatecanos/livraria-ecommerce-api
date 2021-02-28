package com.fatec.livrariaecommerce.domain;

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
	private LocalDate timeStamp;
	private boolean isAtivo;

	@OneToOne(cascade = CascadeType.ALL)
	private TipoCliente tipoCliente;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Documento> documentos;

	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

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

	@Override
	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public boolean isAtivo() {
		return isAtivo;
	}

	@Override
	public void setAtivo(boolean ativo) {
		isAtivo = ativo;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Override
	public List<Documento> getDocumentos() {
		return documentos;
	}

	@Override
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
