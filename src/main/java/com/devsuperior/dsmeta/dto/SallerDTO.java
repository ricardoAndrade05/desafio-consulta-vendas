package com.devsuperior.dsmeta.dto;

public class SallerDTO {
	
	private String sellerName;
	private Double total;
	
	public SallerDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}

}
