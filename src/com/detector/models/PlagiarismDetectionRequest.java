/**
 * 
 */
package com.tripadvisor.models;

/**
 * @author anjali
 *
 */
public class PlagiarismDetectionRequest {

	private String synonymFile;
	private String primaryFile;
	private String secondaryFile;
	private int tupleNum;
	
	public PlagiarismDetectionRequest(String syn, String file1, String file2){
		this.synonymFile = syn;
		this.primaryFile = file1;
		this.secondaryFile = file2;
		this.tupleNum = 3;
	}
	
	public PlagiarismDetectionRequest(String syn, String file1, String file2, int n){
		this.synonymFile = syn;
		this.primaryFile = file1;
		this.secondaryFile = file2;
		this.tupleNum = n;
	}
	
	public String getSynonymFile() {
		return synonymFile;
	}
	public void setSynonymFile(String synonymFile) {
		this.synonymFile = synonymFile;
	}
	public String getPrimaryFile() {
		return primaryFile;
	}
	public void setPrimaryFile(String primaryFile) {
		this.primaryFile = primaryFile;
	}
	public String getSecondaryFile() {
		return secondaryFile;
	}
	public void setSecondaryFile(String secondaryFile) {
		this.secondaryFile = secondaryFile;
	}

	public int getTupleNum() {
		return tupleNum;
	}

	public void setTupleNum(int tupleNum) {
		this.tupleNum = tupleNum;
	}
	
	
}
