package com.melim.backenderp.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.melim.backenderp.model.entity.Pedido;
import com.melim.backenderp.rest.dto.InformacoesPedidoDTO;
import com.melim.backenderp.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido save(PedidoDTO dto);
	InformacoesPedidoDTO obterPedidoPorId(UUID id);
	void delete(UUID id);
	void update(UUID id,PedidoDTO dto);
	Page<Pedido> findAll(Pageable pageable,Pedido filtro);
}
