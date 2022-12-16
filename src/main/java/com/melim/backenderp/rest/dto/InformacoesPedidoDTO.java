package com.melim.backenderp.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;




public class InformacoesPedidoDTO{
	private UUID id;
	private BigDecimal total;
	private LocalDate dataPedido;
	private List<InformacoesItensPedidoDTO> itensPedido;
	private String situacao;
	
	public UUID getId() {
		return id;
	}
	
	
	public void setId(UUID id) {
		this.id = id;
	}
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	public List<InformacoesItensPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<InformacoesItensPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return "InformacoesPedidoDTO [id=" + id + ", total=" + total + ", dataPedido=" + dataPedido + ", itensPedido="
				+ itensPedido + ", situacao=" + situacao + "]";
	}
	public InformacoesPedidoDTO(UUID id, BigDecimal total, LocalDate dataPedido,
			List<InformacoesItensPedidoDTO> itensPedido, String situacao) {
		super();
		this.id = id;
		this.total = total;
		this.dataPedido = dataPedido;
		this.itensPedido = itensPedido;
		this.situacao = situacao;
	}
	public InformacoesPedidoDTO() {
		super();
	}
	
	
}
