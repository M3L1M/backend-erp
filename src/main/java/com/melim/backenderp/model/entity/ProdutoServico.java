package com.melim.backenderp.model.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melim.backenderp.model.enums.Status;
import com.melim.backenderp.model.enums.Tipo;


@Entity
@Table(name="produto_servico",schema = "senior")
public class ProdutoServico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",updatable = false, unique = true, nullable = false)
	private UUID id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Column(name="preco", precision = 16, scale = 2)
	private BigDecimal preco;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, preco, quantidade, status, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoServico other = (ProdutoServico) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(preco, other.preco) && Objects.equals(quantidade, other.quantidade)
				&& status == other.status && tipo == other.tipo;
	}

	public ProdutoServico(UUID id, String descricao, Tipo tipo, BigDecimal preco, Integer quantidade, Status status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
		this.quantidade = quantidade;
		this.status = status;
	}

	public ProdutoServico() {
		super();
	}

	@Override
	public String toString() {
		return "ProdutoServico [id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", preco=" + preco
				+ ", quantidade=" + quantidade + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
