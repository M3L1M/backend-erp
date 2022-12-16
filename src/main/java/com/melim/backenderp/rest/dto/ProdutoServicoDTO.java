package com.melim.backenderp.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoServicoDTO {
	private String descricao;
	private String tipo;
	private String status;
	private BigDecimal preco;
	private Integer quantidade;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public int hashCode() {
		return Objects.hash(descricao, preco, quantidade, status, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoServicoDTO other = (ProdutoServicoDTO) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(preco, other.preco)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(status, other.status)
				&& Objects.equals(tipo, other.tipo);
	}
	@Override
	public String toString() {
		return "ProdutoServicoDTO [descricao=" + descricao + ", tipo=" + tipo + ", status=" + status + ", preco="
				+ preco + ", quantidade=" + quantidade + "]";
	}
	public ProdutoServicoDTO(String descricao, String tipo, String status, BigDecimal preco, Integer quantidade) {
		super();
		this.descricao = descricao;
		this.tipo = tipo;
		this.status = status;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public ProdutoServicoDTO() {
		super();
	}
	
	
	
	
}
