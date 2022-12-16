package com.melim.backenderp.model.entity;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "produtoservico_pedido",schema = "senior")
public class ItensPedido {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "id_produto_servico")
	private ProdutoServico produtoServico;
	
	@Column(name="quantidade")
	private Integer quantidade;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ProdutoServico getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(ProdutoServico produtoServico) {
		this.produtoServico = produtoServico;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	

	@Override
	public String toString() {
		return "ItensPedido [id=" + id + ", pedido=" + pedido + ", produtoServico=" + produtoServico + ", quantidade="
				+ quantidade + "]";
	}

	public ItensPedido(UUID id, Pedido pedido, ProdutoServico produtoServico, Integer quantidade) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.produtoServico = produtoServico;
		this.quantidade = quantidade;
	}

	public ItensPedido() {
		super();
	}

	
	
}
