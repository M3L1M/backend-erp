package com.melim.backenderp.model.entity;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.melim.backenderp.model.enums.Situacao;

@Entity
@Table(name = "pedido",schema = "senior")
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
	private UUID id;
	
	@Column(name="data_pedido")
	private LocalDate dataPedido;
	
	@Column(name="preco", precision = 16, scale = 2)
	private BigDecimal total;
	
	@Column(name="desconto")
	private Integer desconto;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	
	@OneToMany(mappedBy = "pedido")
	private List<ItensPedido> itensPedido;


	public Pedido(UUID id, LocalDate dataPedido, BigDecimal total, Integer desconto, Situacao situacao,
			List<ItensPedido> itensPedido) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.total = total;
		this.desconto = desconto;
		this.situacao = situacao;
		this.itensPedido = itensPedido;
	}


	public Pedido() {
		super();
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public LocalDate getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Integer getDesconto() {
		return desconto;
	}


	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}


	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}


	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", total=" + total + ", desconto=" + desconto
				+ ", situacao=" + situacao + ", itensPedido=" + itensPedido + "]";
	}


	

	
	
}
