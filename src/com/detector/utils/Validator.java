/**
 * 
 */
package com.tripadvisor.utils;

import com.tripadvisor.error.ErrorConstants;
import com.tripadvisor.models.PlagiarismDetectionRequest;

/**
 * @author anjali
 *
 */
public class Validator {
	
	public String validateRequest(PlagiarismDetectionRequest request){
		//check if the correct paths are given else output error and tell user what to do
		String error = "";
		if(request.getSynonymFile() == null)
			error += ErrorConstants.synonym_file;
		if(request.getPrimaryFile() == null)
			error += ErrorConstants.primary_file;
		if(request.getSecondaryFile() == null)
			error += ErrorConstants.secondary_file;
		
		return error;
	}

}
