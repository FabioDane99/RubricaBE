package com.jac.javadb.domain.dto.response;

import java.util.ArrayList;
import java.util.List;

public class PaginationContattiDTO {


	private List<ContattoResponseDTO> content= new ArrayList<>();
	
	private int pageNumber; // me lo da in input
	private boolean lastPage; // if totalPages-pageNumber
	private int totalPages; // numero di elementi nel database / page size
	private int totalElements;  // numero di elementi nel database
	private int pageSize; // input
	
	
	public List<ContattoResponseDTO> getContent() {
		return content;
	}
	public void setContent(List<ContattoResponseDTO> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
