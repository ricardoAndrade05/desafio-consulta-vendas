package com.devsuperior.dsmeta.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SallerDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(obj.id,obj.date,obj.amount,obj.seller.name) " 
		 + "FROM Sale obj " 
		 + "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:nome,'%'))  " 
		 + "AND obj.date BETWEEN :dataInicial AND :dataFinal")
	Page<SaleDTO> relatorio(String nome, LocalDate dataInicial, LocalDate dataFinal, Pageable pageable);
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SallerDTO(obj.seller.name,SUM(obj.amount)) "
		 + "FROM Sale obj "
		 + "WHERE obj.date BETWEEN :dataInicial AND :dataFinal "
		 + "GROUP BY obj.seller.name")
	Page<SallerDTO> sumario(LocalDate dataInicial, LocalDate dataFinal, Pageable pageable);

}
