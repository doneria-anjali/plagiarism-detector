/**
 * 
 */
package com.tripadvisor.application;

import java.util.Scanner;

import com.tripadvisor.controller.PlagiarismDetector;
import com.tripadvisor.error.ErrorConstants;
import com.tripadvisor.models.PlagiarismDetectionRequest;

/**
 * @author anjali
 *
 */
public class PlagiarismDetectorTest {

	/*Possible Test Case:
	 * 1. File names/paths wrong - throws FileNotFoundException
	 * 2. Either of the 3 files is empty - prints the relevant error message
	 * 3. repetitive synonyms
	 * 4. All files have content and content can be found in files under test folder for further testing
	 * */
	
	public static void main(String[] args) {
		String synonymFileName = null; 
				//directory + "synonyms.txt";
		String primaryFileName = null; 
				//directory + "file1.txt";
		String secondaryFileName = null; 
				//directory + "file2.txt";
		int tupleNum = 0;
		
		//take command line input from user
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the location of synonym file:");
		synonymFileName = scanner.nextLine().trim();
		if(synonymFileName.isEmpty())
			System.out.println(ErrorConstants.synonym_file);
		
		System.out.println("Enter the location of file1:");
		primaryFileName = scanner.nextLine().trim();
		if(primaryFileName.isEmpty())
			System.out.println(ErrorConstants.primary_file);
		
		System.out.println("Enter the location of file2:");
		secondaryFileName = scanner.nextLine().trim();
		if(secondaryFileName.isEmpty())
			System.out.println(ErrorConstants.secondary_file);
		
		System.out.println("Enter the N-tuple to be checked upon:");
		String tupleN = scanner.nextLine();
		if(!tupleN.trim().isEmpty())
			tupleNum = Integer.parseInt(tupleN);
		
		PlagiarismDetectionRequest request = null;
		if(tupleNum > 0)
			request = new PlagiarismDetectionRequest(synonymFileName, primaryFileName, secondaryFileName, tupleNum);
		else
			request = new PlagiarismDetectionRequest(synonymFileName, primaryFileName, secondaryFileName);
		
		System.out.println(new PlagiarismDetector().detectPlagiarismPercentage(request) + "%");
	}
}
