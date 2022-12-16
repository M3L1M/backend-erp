package com.melim.backenderp.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class InformacoesItensPedidoDTO {
	private String descricao;
	private BigDecimal preco;
	private Integer quantidade;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		return Objects.hash(descricao, preco, quantidade);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacoesItensPedidoDTO other = (InformacoesItensPedidoDTO) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(preco, other.preco)
				&& Objects.equals(quantidade, other.quantidade);
	}
	@Override
	public String toString() {
		return "InformacoesItensPedidoDTO [descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ "]";
	}
	public InformacoesItensPedidoDTO(String descricao, BigDecimal preco, Integer quantidade) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public InformacoesItensPedidoDTO() {
		super();
	}
	
	
	
	
}
