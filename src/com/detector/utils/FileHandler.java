/**
 * 
 */
package com.tripadvisor.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author anjali
 *
 */
//class that does file handling for the project
public class FileHandler {
	
	//opens a file and returns the reader object
	public BufferedReader openFile(String fileName){
		File file = new File(fileName);
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			return reader;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//closes the file given the reader object
	public void closeFile(BufferedReader reader){
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
