package com.melim.backenderp.rest.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.melim.backenderp.model.entity.ProdutoServico;
import com.melim.backenderp.model.enums.Status;
import com.melim.backenderp.model.enums.Tipo;
import com.melim.backenderp.rest.dto.ProdutoServicoDTO;
import com.melim.backenderp.service.ProdutoServicoService;

@RestController
@RequestMapping("/api/produto-servico")
public class ProdutoServicoController {
	private ProdutoServicoService service;

	public ProdutoServicoController(ProdutoServicoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public ProdutoServico save(@RequestBody ProdutoServicoDTO dto) {
		ProdutoServico produtoServico=converte(dto);
		return service.save(produtoServico);
		
	}
	@GetMapping("/{id}")
	public ProdutoServico obterProdutoServicoPorId(@PathVariable UUID id) {
		return service.obterProdutoPorId(id);
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable UUID id,@RequestBody ProdutoServicoDTO dto) {
		ProdutoServico produtoServico=converte(dto);
		service.update(id, produtoServico);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		service.delete(id);
	}
	
	@GetMapping
	public Page<ProdutoServico> findAll(Pageable pageable,
			@RequestParam(value = "tipo", required = false) String tipo){
		ProdutoServico filtro=new ProdutoServico();
		System.out.println(tipo);
		if(tipo!=null) {
			filtro.setTipo(Tipo.valueOf(tipo));
		}
		
		
		return service.findAll(pageable,filtro);
	}

	private ProdutoServico converte(ProdutoServicoDTO dto) {
		ProdutoServico produtoServico=new ProdutoServico();
		produtoServico.setDescricao(dto.getDescricao());
		produtoServico.setPreco(dto.getPreco());
		produtoServico.setTipo(Tipo.valueOf(dto.getTipo()));
		if(produtoServico.getTipo().equals(Tipo.SERVICO)) {
			produtoServico.setQuantidade(1);
		}else {
			produtoServico.setQuantidade(dto.getQuantidade());
		}
		produtoServico.setStatus(Status.valueOf(dto.getStatus()));
		
		
		return produtoServico;
	}
}
