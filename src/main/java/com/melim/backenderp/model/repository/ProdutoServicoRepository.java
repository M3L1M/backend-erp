package com.melim.backenderp.model.repository;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.melim.backenderp.model.entity.ProdutoServico;
@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Integer>, PagingAndSortingRepository<ProdutoServico, Integer>{

	Optional<ProdutoServico> findById(UUID uuid);
	
}
