package com.atticus.hr.domain;

public class PagerModel {
	private int buttonsToShow=5;
	private int startPage;
	private int endPage;

	public PagerModel(int totalPages, int currentPage, int buttonsToShow) {
		setButtonsToShow(buttonsToShow);
		
		int halfPagesToShow= getButtonsToShow()/2;
		
		if(totalPages <= getButtonsToShow()) {
			setStartPage(1);
			setEndPage(totalPages);
		}
		else if(currentPage - halfPagesToShow <= 0) {
			setStartPage(1);
			setEndPage(getButtonsToShow());
		}
		else if(currentPage + halfPagesToShow ==totalPages) {
			setStartPage(currentPage- halfPagesToShow);
			setEndPage(totalPages);
		}
		else if(currentPage + halfPagesToShow > totalPages) {
			setStartPage(currentPage - halfPagesToShow + 1);
			setEndPage(totalPages);
		}
		else {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(totalPages);
		}
	}
	
	public int getButtonsToShow() {
		return this.buttonsToShow;
	}
	public void setButtonsToShow(int buttonsToShow) {
		this.buttonsToShow=buttonsToShow;
	}
	public int getStartPage() {
		return this.startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage=startPage;
	}
	public int getEndPage() {
		return this.endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage=endPage;
	}
	
	@Override
	public String toString() {
	return "Pager [startPage=" + startPage + ", end-Page=" + endPage + "]";
	}
}
