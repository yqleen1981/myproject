package com.tds.vo;

public class MarketData {
	
	//Ʒ�ֱ���
	private String productId;
	//��ǰ�۸�
	private Long currentPrice;
	//��ǰ����
	private int currentLine;
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Long getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Long currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getCurrentLine() {
		return currentLine;
	}
	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}
}
