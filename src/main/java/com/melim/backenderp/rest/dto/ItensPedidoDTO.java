package com.melim.backenderp.rest.dto;

import java.util.Objects;
import java.util.UUID;

public class ItensPedidoDTO {
	
	private UUID produtoServico;
	private Integer quantidade;
	public UUID getProdutoServico() {
		return produtoServico;
	}
	public void setProdutoServico(UUID produtoServico) {
		this.produtoServico = produtoServico;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public int hashCode() {
		return Objects.hash(produtoServico, quantidade);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensPedidoDTO other = (ItensPedidoDTO) obj;
		return Objects.equals(produtoServico, other.produtoServico) && Objects.equals(quantidade, other.quantidade);
	}
	@Override
	public String toString() {
		return "ItensPedidoDTO [produtoServico=" + produtoServico + ", quantidade=" + quantidade + "]";
	}
	public ItensPedidoDTO(UUID produtoServico, Integer quantidade) {
		super();
		this.produtoServico = produtoServico;
		this.quantidade = quantidade;
	}
	public ItensPedidoDTO() {
		super();
	}
	
	
	
	
	
	
	
	
}
