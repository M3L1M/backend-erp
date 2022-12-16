package com.melim.backenderp.service.impl;


import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.melim.backenderp.exception.RegraNegocioException;
import com.melim.backenderp.model.entity.ProdutoServico;
import com.melim.backenderp.model.repository.ItensPedidoRepository;
import com.melim.backenderp.model.repository.ProdutoServicoRepository;
import com.melim.backenderp.service.ProdutoServicoService;

@Service
public class ProdutoServicoServiceImpl implements ProdutoServicoService {
	
	private final ProdutoServicoRepository repository;
	private final ItensPedidoRepository itensPedidoRepository;
	
	

	public ProdutoServicoServiceImpl(ProdutoServicoRepository repository, ItensPedidoRepository itensPedidoRepository) {
		super();
		this.repository = repository;
		this.itensPedidoRepository = itensPedidoRepository;
	}

	@Override
	@Transactional
	public ProdutoServico save(ProdutoServico produtoServico) {
		//produtoServico.setId(UUID.randomUUID());
		return repository.save(produtoServico);
	}

	@Override
	public ProdutoServico obterProdutoPorId(UUID id) {
		System.out.println(id);
		return repository
				.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não cadastrado na base de dados"));
	}

	@Override
	@Transactional
	public void update(UUID id, ProdutoServico produtoServico) {
		repository
			.findById(id)
			.map(entity->{
				produtoServico.setId(entity.getId());
				
				return repository.save(produtoServico);
			}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não cadastrado na base de dados"));
		
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		repository
			.findById(id)
			.map(entity->{
				
				boolean existe=itensPedidoRepository.existsByProdutoServico(entity);
				if(existe==true) {
					throw new RegraNegocioException("Este produto/servico esta associado a um pedido");
				}
				
				
				repository.delete(entity);
				return Void.TYPE;
			}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não cadastrado na base de dados"));
		
	}

	@Override
	@Transactional
	public Page<ProdutoServico> findAll(Pageable pageable,ProdutoServico filtro) {
		Example<ProdutoServico> example=Example.of(filtro,ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example,pageable);
	}

	
	

}
