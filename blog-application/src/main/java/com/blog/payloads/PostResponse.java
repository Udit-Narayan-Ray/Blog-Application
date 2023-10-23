package com.blog.payloads;

import java.util.List;

public class PostResponse {

	private List<PostDTO> postDTOs;

	private int pageSize;

	private int pageNumber;

	private int totalPage;

	private long totalElement;

	private boolean isLast;

	public List<PostDTO> getPostDTOs() {
		return postDTOs;
	}

	public void setPostDTOs(List<PostDTO> postDTOs) {
		this.postDTOs = postDTOs;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public PostResponse() {
		// TODO Auto-generated constructor stub
	}

	public PostResponse(List<PostDTO> postDTOs, int pageSize, int pageNumber, int totalPage, long totalElement,
			boolean isLast) {
		super();
		this.postDTOs = postDTOs;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.totalPage = totalPage;
		this.totalElement= totalElement;
		this.isLast = isLast;
	}

	@Override
	public String toString() {
		return "PostResponse [postDTOs=" + postDTOs + ", pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", totalPage=" + totalPage + ", totalElement=" + totalElement+ ", isLast=" + isLast + "]";
	}

}
