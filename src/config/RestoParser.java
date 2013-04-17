package config;

import java.io.IOException;

import manager.RestoManager;
import model.Restaurant;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@SuppressWarnings("unused")
public class RestoParser {
	
	private String[] tabVille = {"lyon","biarritz"};
	private int statusCode; 
	private int page;

	
	public void execute() throws IOException{

		int i = 1;
		int j = 0;
		int k = 0;
		
		for(k = 0; k < tabVille.length; k++){
		 // Tant qu'il existe des pages de resultats
	    do {		
		
	    	j = 0;
	    ConfigSite.setUrl("http://www.cityvox.fr/restaurants_"+ tabVille[k] +"/Liste?searchPage=" +i);
	    	
		// Connexion au ConfigSite
	    Connection conn = Jsoup.connect(ConfigSite.getUrl()).timeout(30000);
	    
		statusCode = conn.execute().statusCode();
		
		System.out.println("\tFetching " + ConfigSite.getUrl() + "\n");
		
		// Extraction des données du ConfigSite
		Document doc = conn.get(); 
		
		// Récupération du nb de liens suivants
		Elements pageSuivante = doc.select("a.next");
		page = pageSuivante.size();	
		//System.out.println(page);
		
		// Récupération des noms de resto
		Elements title = doc.select("h3").append(" ;"); 
		String name = title.text();

		// Ajout des noms dans un tableau
		String[] data = name.split(";");

	    // Récupération du genre et l'adresse
		 for(  Element table : doc.select("table.BLOK")) {
		   
		       for( Element row : table.select("tr")) {
		    	   
		    	   Elements genreResto = row.select("div.genre");
		    	   Elements adresseResto = row.select("span[itemprop = streetAddress]");
		    	   
		    	   Elements cpResto = row.select("span[itemprop = postalCode]");
		    	   Elements villeResto = row.select("span[itemprop = addressLocality]");
		    	   
		           String ligne =  data[j] + " " + genreResto.text() +  adresseResto.text() +  cpResto.text() + villeResto.text();
		           
		           Restaurant resto; 
		           resto = new Restaurant(data[j],genreResto.text(),adresseResto.text(),cpResto.text(),villeResto.text());
		           RestoManager resto2 = new RestoManager();
		           if(resto.initialise())
		        	   resto2.addResto(resto);
		     
		           
		           /*
		           int first = 0;
		           if(first == 0){
		        	   resto2.modify();
		           first = 1;
		           } */
		           

		           // Suppression des doublons

	                j++; 
		       }  	  
		      
	}
		
	i++;
		
			}while( (page > 0) && (statusCode == 200) );	
	    
		}
		
		
	}
	
}


