package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SallerDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDTO>> getReport(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "")String maxDate,Pageable pageable) {
		Page<SaleDTO> page = service.relatorio(name,minDate,maxDate,pageable);
		return ResponseEntity.ok(page);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SallerDTO>> getSummary(
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "")String maxDate,Pageable pageable) {
		Page<SallerDTO> page = service.sumario(minDate,maxDate,pageable);
		return ResponseEntity.ok(page);
	}
}
