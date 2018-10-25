/**
 * 
 */
package com.tripadvisor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tripadvisor.error.ErrorConstants;
import com.tripadvisor.models.PlagiarismDetectionRequest;
import com.tripadvisor.utils.FileHandler;
import com.tripadvisor.utils.Validator;

/**
 * @author anjali
 *
 */
public class PlagiarismDetectionService {

	private Map<String, Set<String>> synonymsMap = new HashMap<String, Set<String>>();
	private FileHandler fileHandler = new FileHandler();
	
	public int detectPlagiarismFromPrimaryFile(PlagiarismDetectionRequest params){
		int overlappingContent = 0;
		
		this.populateSynonymsMap(params.getSynonymFile());
		List<String> primaryFileTuples = this.createNTuples(params.getPrimaryFile(), params.getTupleNum());
		List<String> secFileTuples = this.createNTuples(params.getSecondaryFile(), params.getTupleNum());
		
		if(!synonymsMap.isEmpty() && !primaryFileTuples.isEmpty() && !secFileTuples.isEmpty()){
			if(primaryFileTuples.size() != secFileTuples.size())
				return -1;
			
			int totalContentPrimaryFile = primaryFileTuples.size();
			
			for(int ind = 0; ind < primaryFileTuples.size(); ind++){
				String primary = primaryFileTuples.get(ind);
				String secondary = secFileTuples.get(ind);
				//System.out.print(ind + " " + primary + "," + secondary + "\n");
				int count = this.countPlagiarism(primary, secondary);
				overlappingContent += count;
			}
			
			if(totalContentPrimaryFile > 0)
				return Math.round((overlappingContent*100)/totalContentPrimaryFile);
			
			
		}
		else{
			System.out.println(ErrorConstants.not_empty);
		}
		
		return -1;
	}
	
	/*this function takes the primary and secondary tuple strings and compares them for similarity*/
	private int countPlagiarism(String primaryTuple, String secTuple) {
		String[] primaryTupleArr = primaryTuple.split(" ");
		String[] secTupleArr = secTuple.split(" ");
		
		//System.out.println(primaryTuple);
		//System.out.println(secTuple);
		for(int i = 0; i < primaryTupleArr.length; i++){
			//System.out.println(primaryTupleArr[i] + " " + secTupleArr[i]);
			if(!primaryTupleArr[i].equalsIgnoreCase(secTupleArr[i])){
				if(synonymsMap.containsKey(primaryTupleArr[i]) 
						&& synonymsMap.get(primaryTupleArr[i]).contains(secTupleArr[i]))
					continue;
				else
					return 0;
			}
		}
		
		return 1;
	}

	public void populateSynonymsMap(String synonymFile){
		BufferedReader reader = fileHandler.openFile(synonymFile);
		
		if(reader != null){
			String st;
			try {
				while((st = reader.readLine()) != null){
					String[] stArr = st.split(" ");		//assuming file is single space separated
					for(int i = 0; i < stArr.length; i++){
						synonymsMap.put(stArr[i], this.getSynonyms(stArr, i));
					}
				}
				
				//this.printMap();
				fileHandler.closeFile(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	private void printMap(){
		for(String key: synonymsMap.keySet()){
			Set<String> list = synonymsMap.get(key);
			System.out.print(key + "->");
			for(String element : list)
				System.out.print(element + " ");
			System.out.println();
		}
	}
	
	private Set<String> getSynonyms(String[] stArr, int i) {
		Set<String> list = new HashSet<String>();
		if(synonymsMap.containsKey(stArr[i]))
			list = synonymsMap.get(stArr[i]);
		for(String str : stArr){
			if(!str.equalsIgnoreCase(stArr[i]))
				list.add(str);
		}
		return list;
	}

	public List<String> createNTuples(String fileName, int tupleNum){
		List<String> tuples = new ArrayList<String>();
		
		BufferedReader reader = fileHandler.openFile(fileName);
		
		if(reader != null){
			String st;
			StringBuilder aggregatedStr = new StringBuilder();
			try {
				while((st = reader.readLine()) != null){
					st = this.cleanSentences(st);
					aggregatedStr.append(st);
				}
				st = aggregatedStr.toString();
				
				String[] stArr = st.split(" ");		//assuming file is single space separated
				int start = 0;
				while(start < stArr.length){
					int last = start + tupleNum - 1;
					/*we ignore the combination of substring that cannot form the N-tuple, 
					 * mostly this happens towards the end of the sentence
					*/
					if(last < stArr.length){
						int count = 0;
						StringBuilder sb = new StringBuilder();
						while(count < tupleNum && (count+start) < stArr.length){
							sb.append(stArr[count + start]);
							sb.append(" ");
							count++;
						}
						start += 1;
						tuples.add(sb.toString().trim());
					}
					else
						break;
				}
				
				fileHandler.closeFile(reader);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tuples;
	}

	private String cleanSentences(String str){
		str = str.replace(".", "");
		str = str.replace(",", "");
		
		return str;
	}
}
