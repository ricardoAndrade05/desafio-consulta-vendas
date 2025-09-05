package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleDTO> relatorio(String nome, String minDate, String maxDate, Pageable pageable) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFinal;
		LocalDate dataInicial;
		dataFinal = retornaDataFinal(maxDate, formatter);
		dataInicial = retornaDataInicial(minDate,formatter, dataFinal);
		Page<SaleDTO> relatorio = repository.relatorio(nome,dataInicial,dataFinal,pageable);
		return relatorio;
		
	}

	private LocalDate retornaDataInicial(String minDate, DateTimeFormatter formatter, LocalDate dataFinal) {
		LocalDate dataInicial;
		if (minDate.equals("")) {
			dataInicial = dataFinal.minusYears(1L);
		} else {
			dataInicial = LocalDate.parse(minDate, formatter);
		}
		return dataInicial;
	}

	private LocalDate retornaDataFinal(String maxDate, DateTimeFormatter formatter) {
		LocalDate dataFinal;
		if (maxDate.equals("")) {
			dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else {
			dataFinal = LocalDate.parse(maxDate, formatter);
		}
		return dataFinal;
	}
}
