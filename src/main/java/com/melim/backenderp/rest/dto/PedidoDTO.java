package com.melim.backenderp.rest.dto;

import java.util.List;
import java.util.Objects;

public class PedidoDTO {
	private Integer desconto;
	private List<ItensPedidoDTO> itensPedido;
	private String situacao;
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public List<ItensPedidoDTO> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItensPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(desconto, itensPedido, situacao);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDTO other = (PedidoDTO) obj;
		return Objects.equals(desconto, other.desconto) && Objects.equals(itensPedido, other.itensPedido)
				&& Objects.equals(situacao, other.situacao);
	}
	@Override
	public String toString() {
		return "PedidoDTO [desconto=" + desconto + ", itensPedido=" + itensPedido + ", situacao=" + situacao + "]";
	}
	public PedidoDTO(Integer desconto, List<ItensPedidoDTO> itensPedido, String situacao) {
		super();
		this.desconto = desconto;
		this.itensPedido = itensPedido;
		this.situacao = situacao;
	}
	public PedidoDTO() {
		super();
	}
	
	
	
	
	
	
}
