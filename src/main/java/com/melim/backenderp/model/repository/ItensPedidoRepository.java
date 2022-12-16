package com.melim.backenderp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.melim.backenderp.model.entity.ItensPedido;
import com.melim.backenderp.model.entity.ProdutoServico;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido,Integer>,PagingAndSortingRepository<ItensPedido,Integer>{

	boolean existsByProdutoServico(ProdutoServico produtoServico);


}
