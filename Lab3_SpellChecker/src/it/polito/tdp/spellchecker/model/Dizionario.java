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
	
	/*public List<RichWord> spellCheckText(List<String> stringa){
		
		List<RichWord> paroleEsatte = new ArrayList<RichWord>(); 
		for(String s: stringa) {
			if(parole.contains(s))
				paroleEsatte.add(new RichWord(s,true));
			else paroleEsatte.add(new RichWord(s,false));
		}
		
		return paroleEsatte;
	}*/
	
	public List<RichWord> spellCheckTextLinear(List<String> stringa){
		
		boolean h;
		List<RichWord> paroleSalvate = new ArrayList<RichWord>();
		for(String s: stringa) {
			h=false;
			for(String n:parole) {
				if(n.equals(s)) {
					h=true;
				}
			}
			paroleSalvate.add(new RichWord(s,h));
		}	
		return paroleSalvate;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> stringa){
		
		List<RichWord> paroleSalvate = new ArrayList<RichWord>();
		int mediana=0;
		boolean h=false;
		
		for(String s: stringa) {
			
			int min=0;
			int max= parole.size()-1;
			h=false;
			
			while(min <= max) {
				
				mediana = (min+max)/2;
				if(s.compareTo(parole.get(mediana))==0) {
					h=true;
					break;
				} else if(s.compareTo(parole.get(mediana)) < 0 )
						max=mediana -1;
					   else min = mediana +1;
			}
			paroleSalvate.add(new RichWord(s,h));
		}
		return paroleSalvate;
	}
	
	public void pulisciDizionario() {
		parole.clear();
	}
	
	
}
