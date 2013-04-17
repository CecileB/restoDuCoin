package manager;

import java.util.Iterator;
import java.util.List;

import model.Restaurant;

import org.hibernate.Session;

import util.HibernateUtil;


public class RestoManager {
	
	//private static RestoManager instance = new RestoManager();
	public RestoManager(){
		
	}
	/*
	public static RestoManager getInstance(){
		return instance;
	}
	*/
	
	public boolean addResto(Restaurant r){
        boolean validate = true;
		if(r.getNom().length() < 2 ) validate = false;
		if( (r.getCodePostal().length() != 5) ) validate = false;
		if( (r.getAdresse().length() < 1) ) validate = false;
		if( (r.getSpecialite().length() < 1) ) validate = false;
		if( (r.getVille().length() < 1) ) validate = false;
		
		if(validate){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save( r );
		
		session.getTransaction().commit();
		session.close();
		} 
		return validate;
	}
	
	
	public boolean save(Restaurant resto){
        boolean validate = true;
		if(resto.getNom().length() < 2 ) validate = false;
		if( (resto.getCodePostal().length() != 5) ) validate = false;
		if( (resto.getAdresse().length() < 1) ) validate = false;
		if( (resto.getSpecialite().length() < 1) ) validate = false;
		if( (resto.getVille().length() < 1) ) validate = false;
		
		if(validate){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
	
			session.update(resto);
			   
			session.getTransaction().commit();
			session.close();
		}
		return validate;
	}
	
	public void delete(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Restaurant resto = this.get(id);
		session.delete( resto );
		
		session.getTransaction().commit();
		session.close();
	}
	
	public Restaurant get ( Long id ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Restaurant resto = (Restaurant) session.get(Restaurant.class, new Long(id));
		
		session.getTransaction().commit();
		session.close();
		
		return resto;
	} 
	
	public Iterator<Restaurant> getAll (int debut) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
        Iterator<Restaurant> i = session.createQuery("FROM Restaurant ")
        		.setFirstResult(debut)
        		.setMaxResults(15)
    			.list()
    			.iterator(); 
		
		session.getTransaction().commit();
		session.close();
		
		return i;
	}
	public List<String> getSpecialites(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
        List<String> specialites =  session.createQuery("select distinct specialite FROM Restaurant ORDER BY SPECIALITE")
    			.list();
		
		session.getTransaction().commit();
		session.close();
		
		return specialites;		
	}
	public List<String> getVilles(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
        List<String> villes =  session.createQuery("select distinct ville FROM Restaurant ")
    			.list();
		
		session.getTransaction().commit();
		session.close();
		
		return villes;		
	}
	public long nb_getAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
        long i = (long)session.createQuery("SELECT count(*) FROM Restaurant ").uniqueResult(); 
		
		session.getTransaction().commit();
		session.close();
		
		return i;
	}
}
