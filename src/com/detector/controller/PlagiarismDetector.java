/**
 * 
 */
package com.tripadvisor.controller;

import com.tripadvisor.models.PlagiarismDetectionRequest;
import com.tripadvisor.service.PlagiarismDetectionService;
import com.tripadvisor.utils.Validator;

/**
 * @author anjali
 *
 */
public class PlagiarismDetector {
	
	private PlagiarismDetectionService service = new PlagiarismDetectionService();
	
	public int detectPlagiarismPercentage(PlagiarismDetectionRequest request){
		//validate input request for files otherwise throw error
		String validationMessage = new Validator().validateRequest(request);
		if(validationMessage.isEmpty()){
			return service.detectPlagiarismFromPrimaryFile(request);
		}
		else
			System.out.println(validationMessage);
		
		return -1;
	}

}
