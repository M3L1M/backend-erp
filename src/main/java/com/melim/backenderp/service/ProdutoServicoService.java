package com.melim.backenderp.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.melim.backenderp.model.entity.ProdutoServico;

public interface ProdutoServicoService {	
	ProdutoServico save(ProdutoServico produtoServico);
	ProdutoServico obterProdutoPorId(UUID Id);
	void update(UUID id,ProdutoServico produtoServico);
	void delete(UUID id);
	Page<ProdutoServico> findAll(Pageable pageable,ProdutoServico filtro);
	
}
