package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Restaurant")
public class Restaurant{
	
	private Long id;
	private String nom;
	private String specialite;
	private String adresse;
	private String codePostal;
	private String ville;
	
	
	public Restaurant(String nom, String specialite, String adresse,String codePostal,String ville){
		this.nom = nom;
		this.specialite = specialite;
		this.adresse = adresse;
		this.codePostal  = codePostal;
		this.ville = ville;
	}
	public Restaurant(){
		
	}
	public boolean initialise(){
		if( (this.nom.length() == 0) || 
			(this.specialite.length() == 0) ||
			(this.adresse.length() == 0) ||
			(this.codePostal).length() == 0 ||
			(this.ville.length() == 0)) return false;
		else return true;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="nom")
	public String getNom() {
		return nom;
	}
	public boolean setNom(String nom) {
		if(nom != ""){this.nom = nom; return true;}
		else return false;
	}
	
	@Column(name="specialite")
	public String getSpecialite() {
		return specialite;
	}
	
	public boolean setSpecialite(String specialite) {
		if(specialite != ""){this.specialite = specialite; return true;}
		else return false;
	}
	
	@Column(name="adresse")
	public String getAdresse() {
		return adresse;
	}

	public boolean setAdresse(String adresse) {
		if(adresse != ""){this.adresse = adresse; return true;}
		else return false;
	}
	
	@Column(name="code_postal")
	public String getCodePostal() {
		return codePostal;
	}
	public boolean setCodePostal(String cpResto) {
		if(cpResto != ""){this.codePostal = cpResto; return true;}
		else return false;
	}
	
	@Column(name="ville")
	public String getVille() {
		return ville;
	}
	public boolean setVille(String ville) {
		if(ville != ""){this.ville = ville; return true;}
		else return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nom;
	}
}
