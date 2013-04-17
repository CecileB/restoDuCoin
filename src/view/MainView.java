package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;

import manager.RestoManager;
import model.Restaurant;
import config.RestoParser;

public class MainView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 2597962974368580986L;
	private int debut;
	private int pas;
	private int fin;
	
	public MainView(int debut, int fin){
    	this.setTitle("Restos du Coin"); //Dï¿½finit un titre pour notre fenï¿½tre
    	this.setSize(600, 500);    //Dï¿½finit sa taille : 400 pixels de large et 100 pixels de haut
    	this.setLocationRelativeTo(null);     //Nous demandons maintenant ï¿½ notre objet de se positionner au centre
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Termine le processus lorsqu'on clique sur la croix rouge
    	this.setResizable(false); //Fenï¿½tre non modifiable
    	this.setAlwaysOnTop(false); //Fenï¿½tre toujours au dessus
		//if((this.fin - this.debut) != 15) { this.debut = 0; this.fin = 15;}
		this.debut = debut;
		this.pas = 15;
		this.setContentPane(this.initContentPane());
    }
	
	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}

	//JPanel de base
	private Panneau initContentPane() {
		Panneau panel = new Panneau(); //Panneau Principal
		Panneau grille = new Panneau(); //Liste des restaurants dans le panneau central
		panel.setLayout(new BorderLayout());
		int nb = 15;
		grille.setLayout(new GridLayout(nb, 1));
		panel.add(grille,BorderLayout.CENTER);
        //panel.setLayout(new GridLayout);
		
		Panneau nord_p = new Panneau(Color.ORANGE);
		nord_p.setLayout(new FlowLayout());
		RestoManager restoMng = new RestoManager();
		long nbTotal = restoMng.nb_getAll();
		this.fin = this.pas + this.debut;
		JLabel l1 = new JLabel("Restaurants du coin " + this.debut + " - " + this.fin +  "sur" + nbTotal);
		nord_p.add(l1);
        panel.add(nord_p, BorderLayout.NORTH); //NORD
        panel.add(new Panneau(Color.CYAN), BorderLayout.WEST);  //OUEST
        panel.add(new Panneau(Color.RED),BorderLayout.SOUTH); //SUD
        panel.add(new Panneau(Color.BLACK),BorderLayout.EAST); //EST
        Bouton bt_delete = new Bouton("ADD");
        Bouton bt_update = new Bouton("UPDATE");
        Bouton bt_prec = new Bouton("PREC");
        Bouton bt_next = new Bouton("NEXT");
        Panneau validation = new Panneau();
        validation.setLayout(new FlowLayout());
        validation.add(bt_delete);
        validation.add(bt_update);
        grille.add(validation);
        bt_delete.addActionListener(this);
        bt_update.addActionListener(this);
        bt_prec.addActionListener(this);
        bt_next.addActionListener(this);
       
       if(this.getDebut() <= 0) {bt_prec.setForeground(Color.GRAY); bt_prec.setEnabled(false);}
       if(this.fin >= nbTotal) { bt_prec.setForeground(Color.GRAY);bt_next.setEnabled(false);}
       
		//AJOUT DES DONNEES
		Iterator<Restaurant> iter = restoMng.getAll(this.getDebut());
		System.out.println(this.getDebut() + " - " + this.fin);
		
        //On ajoute le bouton au content pane de la JFrame
		if(iter.hasNext()){
	        while(iter.hasNext()){
	        	Restaurant resto2 = (Restaurant) iter.next();
	        	String nom_resto = resto2.toString();
	        	Long id = resto2.getId();
	        	Bouton b_ville = new Bouton(nom_resto,id);
	        	b_ville.addActionListener(this);
	        	grille.add(b_ville);
	        	System.out.println(nom_resto + " - " + id);
	        }
		}
		else {
			JLabel text_bdd_vide = new JLabel("Application non mise à jour, veuillez recharger la base de données en cliquant sur update");
			grille.add(text_bdd_vide);
		}

        //grille.add(label1);
		grille.add(bt_prec);
		grille.add(bt_next);
        return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	Bouton source = (Bouton) e.getSource();
		switch(source.getType()){
		case 2: {	//ADD
					DetailView view = new DetailView();
					view.setVisible(true);
					this.setVisible(false);
		} break; 
		case 5: {	//Mise ï¿½ jour des donnï¿½es
					RestoParser r = new RestoParser(); 
					try {
						r.execute();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					MainView view = new MainView(this.getDebut(),fin);
					view.setVisible(true);
					this.dispose(); 
		}break;
		case 6: { //FICHE DETAIL RESTAURANT
			DetailView view = new DetailView(source.getId()); //Fiche de dï¿½tail d'un restaurant
			view.setVisible(true);
			this.setVisible(false);
		}
		break;
		case 7: { //PREC
			int begin = this.getDebut() - 15;
			int end = this.getFin() - 15;
			if(begin < 0) begin = 0;
			MainView view = new MainView(begin,end); //Fiche de dï¿½tail d'un restaurant
			System.out.println( begin + " - " + end);
			view.setVisible(true);
			this.setVisible(false);			
		}
		break;
		case 8: { //NEXT
			int begin = this.getDebut() + 15;
			int end = this.getFin() + 15;
			if(begin < 0) begin = 0;
			System.out.println( begin + " - " + end);
			MainView view = new MainView(begin, end); //Fiche de dï¿½tail d'un restaurant
			view.setVisible(true);
			this.setVisible(false);
		}
		break;
	}
		
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}
}
