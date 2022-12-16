package com.melim.backenderp.rest.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.melim.backenderp.model.entity.Pedido;
import com.melim.backenderp.model.enums.Situacao;
import com.melim.backenderp.rest.dto.InformacoesPedidoDTO;
import com.melim.backenderp.rest.dto.PedidoDTO;
import com.melim.backenderp.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;

	public PedidoController(PedidoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido save(@RequestBody PedidoDTO dto) {
		return service.save(dto);
	}
	
	@GetMapping("/{id}")
	public InformacoesPedidoDTO obterPedidoPorId(@PathVariable UUID id) {
		return service.obterPedidoPorId(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable UUID id,@RequestBody PedidoDTO dto) {
		service.update(id, dto);
	}
	
	@GetMapping
	public Page<Pedido> findAll(Pageable pageable,
			@RequestParam(value = "situacao", required = false) String situacao){
		Pedido filtro=new Pedido();
		if(situacao!=null) {
			filtro.setSituacao(Situacao.valueOf(situacao));
		}
		
		return service.findAll(pageable,filtro);
	}
	
	
	
	
}
