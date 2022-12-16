package com.melim.backenderp.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.melim.backenderp.exception.RegraNegocioException;
import com.melim.backenderp.model.entity.ItensPedido;
import com.melim.backenderp.model.entity.Pedido;
import com.melim.backenderp.model.entity.ProdutoServico;
import com.melim.backenderp.model.enums.Situacao;
import com.melim.backenderp.model.enums.Status;
import com.melim.backenderp.model.enums.Tipo;
import com.melim.backenderp.model.repository.ItensPedidoRepository;
import com.melim.backenderp.model.repository.PedidoRepository;
import com.melim.backenderp.model.repository.ProdutoServicoRepository;
import com.melim.backenderp.rest.dto.InformacoesItensPedidoDTO;
import com.melim.backenderp.rest.dto.InformacoesPedidoDTO;
import com.melim.backenderp.rest.dto.ItensPedidoDTO;
import com.melim.backenderp.rest.dto.PedidoDTO;
import com.melim.backenderp.service.PedidoService;


@Service
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository repository;
	private final ProdutoServicoRepository prodServRepository;
	private final ItensPedidoRepository itensPedidoRepository;
	
	public PedidoServiceImpl(PedidoRepository repository, ProdutoServicoRepository prodServRepository,
			ItensPedidoRepository itensPedidoRepository) {
		super();
		this.repository = repository;
		this.prodServRepository = prodServRepository;
		this.itensPedidoRepository = itensPedidoRepository;
	}

	@Override
	@Transactional
	public Pedido save(PedidoDTO dto) {
		Pedido pedido=new Pedido();
		pedido.setDataPedido(LocalDate.now());
		pedido.setDesconto(dto.getDesconto());
		pedido.setSituacao(Situacao.valueOf(dto.getSituacao()));
		
		List<ItensPedido> listaItensPedido=converterSave(pedido,dto.getItensPedido());
		System.out.println("Tamanho: "+dto.getItensPedido().size());
		double precoTotalServico=0.00;
		double precoTotalProduto=0.00;
		for(int i=0;i<listaItensPedido.size();i++) {
			
			if(listaItensPedido.get(i).getProdutoServico().getTipo()==Tipo.PRODUTO) {
				precoTotalProduto+=listaItensPedido.get(i).getProdutoServico().getPreco().doubleValue();
			}else {
				precoTotalServico+=listaItensPedido.get(i).getProdutoServico().getPreco().doubleValue();
			}
			
		}
		
		if(pedido.getSituacao()==Situacao.FECHADO && pedido.getDesconto()!=0) {
			throw new RegraNegocioException("Não é permitido dar desconto em um pedido fechado");
		}
		
		precoTotalProduto=precoTotalProduto-((pedido.getDesconto().doubleValue()/100)*precoTotalProduto);
		pedido.setTotal(new BigDecimal(precoTotalProduto+precoTotalServico));
		
		repository.save(pedido);
		itensPedidoRepository.saveAll(listaItensPedido);
		
		return pedido;
	}

	@Override
	public InformacoesPedidoDTO obterPedidoPorId(UUID id) {
		return repository
				.obterItensPeloId(id)
				.map(entity-> converterObterInformacoesPedidoDTO(entity)
						).orElseThrow(() ->
		                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
				
	}

	
	@Override
	@Transactional
	public void delete(UUID id) {
		repository
			.findById(id)
			.map(entity-> {
				itensPedidoRepository.deleteAll(entity.getItensPedido());
				repository.delete(entity);
				return Void.TYPE;
			}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não cadastrado na base de dados"));
		
	}
	

	@Override
	@Transactional
	public void update(UUID id, PedidoDTO dto) {
		repository
			.findById(id)
			.map(entity -> {
				
				entity.setDesconto(dto.getDesconto());
				entity.setSituacao(Situacao.valueOf(dto.getSituacao()));
				List<ItensPedido> listaItensPedido=converterSave(entity,dto.getItensPedido());
				
				
				double precoTotalServico=0.00;
				double precoTotalProduto=0.00;
				for(int i=0;i<listaItensPedido.size();i++) {
					
					if(listaItensPedido.get(i).getProdutoServico().getTipo()==Tipo.PRODUTO) {
						precoTotalProduto+=listaItensPedido.get(i).getProdutoServico().getPreco().doubleValue();
					}else {
						precoTotalServico+=listaItensPedido.get(i).getProdutoServico().getPreco().doubleValue();
					}
					
				}
				
				if(entity.getSituacao()==Situacao.FECHADO && entity.getDesconto()!=0) {
					throw new RegraNegocioException("Não é permitido dar desconto em um pedido fechado");
				}
				
				precoTotalProduto=precoTotalProduto-((entity.getDesconto().doubleValue()/100)*precoTotalProduto);
				entity.setTotal(new BigDecimal(precoTotalProduto+precoTotalServico));
				
				repository.save(entity);
				itensPedidoRepository.saveAll(listaItensPedido);
				return entity;
				
			}).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não cadastrado na base de dados"));
		
	}


	@Override
	@Transactional
	public Page<Pedido> findAll(Pageable pageable,Pedido filtro) {
		List<ItensPedido> itensPedidos  = itensPedidoRepository.findAll();
		
		filtro.setItensPedido(itensPedidos);
		
		Example<Pedido> example=Example.of(filtro,ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example,pageable);
	}

	






	private List<ItensPedido> converterSave(Pedido pedido, List<ItensPedidoDTO> itensPedidoDTO) {
		if(itensPedidoDTO.isEmpty()) {
			throw new RegraNegocioException("A lista de produtos esta vazia");
		}
		
		return itensPedidoDTO
				.stream()
				.map(dto -> {
					UUID idProdutoServico=dto.getProdutoServico();
					ProdutoServico produtoServico=prodServRepository
							.findById(idProdutoServico)
							.orElseThrow(
									()-> new RegraNegocioException("ID do produto invalido"));
					
					
					ItensPedido itensPedido=new ItensPedido();
					itensPedido.setQuantidade(dto.getQuantidade());
					itensPedido.setPedido(pedido);
					itensPedido.setProdutoServico(produtoServico);
					Integer quantidade=produtoServico.getQuantidade()-dto.getQuantidade();
					produtoServico.setQuantidade(quantidade);
					
					if(produtoServico.getStatus().equals(Status.INATIVO)) {
						throw new RegraNegocioException("O produto '"+produtoServico.getDescricao()+"' esta INATIVO");
					}
					prodServRepository.save(produtoServico);
					return itensPedido;
				}).collect(Collectors.toList());
	}


	private InformacoesPedidoDTO converterObterInformacoesPedidoDTO(Pedido pedido) {
		InformacoesPedidoDTO dto=new InformacoesPedidoDTO();
		dto.setId(pedido.getId());
		dto.setTotal(pedido.getTotal());
		dto.setDataPedido(pedido.getDataPedido());
		dto.setItensPedido(converterList(pedido.getItensPedido()));
		dto.setSituacao(pedido.getSituacao().toString());
		
		return dto;
		
	}

	private List<InformacoesItensPedidoDTO> converterList(List<ItensPedido> itensPedido) {
		if(CollectionUtils.isEmpty(itensPedido)) {
			return Collections.emptyList();
		}
		return itensPedido
				.stream()
				.map(item -> {
					InformacoesItensPedidoDTO dto=new InformacoesItensPedidoDTO();
					dto.setDescricao(item.getProdutoServico().getDescricao());
					dto.setPreco(item.getProdutoServico().getPreco());
					dto.setQuantidade(item.getQuantidade());
					return dto;
				}).collect(Collectors.toList());
	}

	


	





	
	
}
