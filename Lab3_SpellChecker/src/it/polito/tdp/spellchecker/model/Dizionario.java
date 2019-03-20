package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Dizionario {

	private List<String> parole = new ArrayList<String>();
	
	public void loadDictionary(String language) {
		
		try{ 
			BufferedReader br = new BufferedReader(new FileReader("rsc/"+language+".txt")); 
			String word; 
			while((word = br.readLine()) != null) { 
				parole.add(word.toLowerCase());
			} 
			br.close();; 
		  } catch (IOException e){ 
			  System.out.println("Errore nella lettura da file"); 
			  } 		
	}
	
	public List<RichWord> spellCheckText(List<String> stringa){
		
		List<RichWord> paroleEsatte = new ArrayList<RichWord>(); 
		for(String s: stringa) {
			if(parole.contains(s))
				paroleEsatte.add(new RichWord(s,true));
			else paroleEsatte.add(new RichWord(s,false));
		}
		
		return paroleEsatte;
	}
	
	
}
