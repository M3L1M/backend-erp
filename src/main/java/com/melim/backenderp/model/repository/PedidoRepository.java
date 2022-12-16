package com.melim.backenderp.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.melim.backenderp.model.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>,PagingAndSortingRepository<Pedido, Integer>{
	
	@Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.itensPedido WHERE p.id= :id")
	Optional<Pedido> obterItensPeloId(@Param("id")UUID id);

	Optional<Pedido> findById(UUID id);

	Page<Pedido> findBySituacao(Example<Pedido> example, Pageable pageable);

	
}
