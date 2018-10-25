/**
 * 
 */
package com.tripadvisor.models;

/**
 * @author anjali
 *
 */
public class PlagiarismResponse {

	private int overlappingCount;
	private int totalCount;
	
	private int percentage;

	public int getOverlappingCount() {
		return overlappingCount;
	}

	public void setOverlappingCount(int overlappingCount) {
		this.overlappingCount = overlappingCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
}
