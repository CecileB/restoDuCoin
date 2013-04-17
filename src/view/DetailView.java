package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import manager.RestoManager;
import model.Restaurant;

public class DetailView extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7228666061865938681L;
	private Long id;
	private RestoManager restoMng = new RestoManager();
	Bouton bt_retour = new Bouton("RETOUR");
	Bouton bt_delete = new Bouton("DELETE");
	private JTextField c_nom = new JTextField();
	private JComboBox<String> c_spec = new JComboBox<String>();
	private JTextField c_adresse = new JTextField();
	private JTextField c_codePostal = new JTextField();
	private JComboBox<String> c_ville = new JComboBox<String>();
	private JLabel msg_erreur = new JLabel("Erreur dans un de vos champs");
	
	
	public DetailView(){
    	this.setTitle("Détail d'un restaurant"); //Définit un titre pour notre fenêtre
    	this.setSize(600, 500);    //Définit sa taille : 400 pixels de large et 100 pixels de haut
    	this.setLocationRelativeTo(null);     //Nous demandons maintenant à notre objet de se positionner au centre
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Termine le processus lorsqu'on clique sur la croix rouge
    	this.setResizable(false); //Fenêtre non modifiable
    	this.setAlwaysOnTop(false); //Fenêtre toujours au dessus
		this.setContentPane(this.initContentPane());
		this.msg_erreur.setForeground(Color.RED);
    }
	public DetailView(long id){
    	this.setTitle("Détail d'un restaurant"); //Définit un titre pour notre fenêtre
    	this.setSize(600, 500);    //Définit sa taille : 400 pixels de large et 100 pixels de haut
    	this.setLocationRelativeTo(null);     //Nous demandons maintenant à notre objet de se positionner au centre
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Termine le processus lorsqu'on clique sur la croix rouge
    	this.setResizable(false); //Fenêtre non modifiable
    	this.setAlwaysOnTop(false); //Fenêtre toujours au dessus
		this.setContentPane(this.initContentPane(id));
		this.msg_erreur.setForeground(Color.RED);
		this.id = id;
    }
	private Panneau initContentPane(long id) {
		Panneau panel = new Panneau();
		Panneau nord_p = new Panneau(Color.ORANGE);
		Panneau center_p = new Panneau();
		
		panel.setLayout(new BorderLayout());
		nord_p.setLayout(new FlowLayout());
		center_p.setLayout(new FlowLayout());
        
		JLabel l1 = new JLabel("Fiche de détail d'un restaurant");
		nord_p.add(l1);
		
		//FORMULAIRE DETAIL RESTAURANT 
		JLabel label1 = new JLabel("Nom");
		JLabel label2 = new JLabel("Spécialité");
		JLabel label3 = new JLabel("Adresse");
		JLabel label4 = new JLabel("Code Postal");
		JLabel label5 = new JLabel("Ville");
		
		Restaurant resto = this.restoMng.get(new Long(id));
		
		this.c_nom = new JTextField(resto.getNom());
		this.c_nom.setPreferredSize(new Dimension(200, 30));
		center_p.add(label1);
		this.c_nom.setLocation(100, 100);
		center_p.add(this.c_nom);
		
		List<String> tabSpecialites = this.restoMng.getSpecialites();
		String[] a = new String[tabSpecialites.size()];
		tabSpecialites.toArray(a);
		this.c_spec = new JComboBox<String>(a);
		for(int i = 0;i< a.length;i++){
			if(a[i] == resto.getSpecialite()){
				this.c_spec.setSelectedIndex(i);
			}
		}
		c_spec.setPreferredSize(new Dimension(200,20));
	    center_p.add(label2);
	    center_p.add(this.c_spec);
	   
		this.c_adresse = new JTextField(resto.getAdresse());
		c_adresse.setPreferredSize(new Dimension(150, 30));
		center_p.add(label3);
		center_p.add(this.c_adresse);
		
		this.c_codePostal = new JTextField(resto.getCodePostal());
		c_adresse.setPreferredSize(new Dimension(200, 30));
		center_p.add(label4);
		center_p.add(this.c_codePostal);
		
		String[] tabVilles = {"paris","lyon","marseille","biarritz","orleans","lyon"};
		this.c_ville = new JComboBox<String>(tabVilles);
		for(int i = 0; i < tabVilles.length; i++){
			if(tabVilles[i].toString() == resto.getVille()) 
				this.c_ville.setSelectedIndex(i);
		}
		c_spec.setPreferredSize(new Dimension(200,20));
	    center_p.add(label5);
	    center_p.add(c_ville); 
	    this.msg_erreur.setVisible(false);
	    center_p.add(this.msg_erreur);
	    
		
		Bouton bt_retour = new Bouton("RETURN");
		Bouton bt_modify = new Bouton("SAVE");
		Bouton bt_delete = new Bouton("DELETE");
		bt_retour.addActionListener(this);
		bt_modify.addActionListener(this);
		bt_delete.addActionListener(this);
		center_p.add(bt_modify);
		center_p.add(bt_retour);
		center_p.add(bt_delete);
		
		//Formulaire de détail d'un restaurant
        panel.add(nord_p, BorderLayout.NORTH); //NORD
        panel.add(new Panneau(Color.CYAN), BorderLayout.WEST);  //OUEST
        panel.add(new Panneau(Color.RED),BorderLayout.SOUTH); //SUD		
        
        panel.add(center_p,BorderLayout.CENTER); //SUD
        panel.add(new Panneau(Color.BLACK),BorderLayout.EAST); //SUD
        
		return panel;
	}
	//JPanel de base
	private Panneau initContentPane() {
		Panneau panel = new Panneau();
		Panneau nord_p = new Panneau(Color.ORANGE);
		Panneau center_p = new Panneau();
		
		panel.setLayout(new BorderLayout());
		nord_p.setLayout(new FlowLayout());
		center_p.setLayout(new FlowLayout());
		
		JLabel l1 = new JLabel("Fiche de détail d'un restaurant");
		nord_p.add(l1);
		
		//FORMULAIRE DETAIL RESTAURANT 
		JLabel label1 = new JLabel("Nom");
		JLabel label2 = new JLabel("Spécialité");
		JLabel label3 = new JLabel("Adresse");
		JLabel label4 = new JLabel("Code Postal");
		JLabel label5 = new JLabel("Ville");
		
		this.c_nom = new JTextField();
		this.c_nom.setPreferredSize(new Dimension(150, 30));
		center_p.add(label1);
		center_p.add(this.c_nom);
		 
		List<String> tabSpecialites = this.restoMng.getSpecialites();
		String[] a = new String[tabSpecialites.size()];
		tabSpecialites.toArray(a);
		this.c_spec = new JComboBox<String>(a);
		c_spec.setPreferredSize(new Dimension(200,20));
	    center_p.add(label2);
	    center_p.add(this.c_spec);
	    
	    this.c_adresse = new JTextField();
	    this.c_adresse.setPreferredSize(new Dimension(150, 30));
		center_p.add(label3);
		center_p.add(this.c_adresse);
		
		this.c_codePostal = new JTextField();
		this.c_codePostal.setPreferredSize(new Dimension(150, 30));
		center_p.add(label4);
		center_p.add(this.c_codePostal);
		
		List<String> tabVilles = this.restoMng.getVilles();
		String[] v = new String[tabVilles.size()];
		tabVilles.toArray(v);
		this.c_ville = new JComboBox<String>(v);
		this.c_ville.setPreferredSize(new Dimension(200,20));
	    center_p.add(label5);
	    center_p.add(this.c_ville);
		
		Bouton bt_retour = new Bouton("RETURN");
		Bouton bt_save = new Bouton("MODIFY");
		bt_retour.addActionListener(this);
		bt_save.addActionListener(this);
		center_p.add(bt_save);
		center_p.add(bt_retour);
		this.msg_erreur.setVisible(false);
		center_p.add(this.msg_erreur);
		
		//Formulaire de détail d'un restaurant
        panel.add(nord_p, BorderLayout.NORTH); //NORD
        panel.add(new Panneau(Color.CYAN), BorderLayout.WEST);  //OUEST
        panel.add(new Panneau(Color.RED),BorderLayout.SOUTH); //SUD		
        
        panel.add(center_p,BorderLayout.CENTER); //SUD
        panel.add(new Panneau(Color.BLACK),BorderLayout.EAST); //SUD
        
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Bouton source = (Bouton) e.getSource();
		switch(source.getType()){
		case 1: { //DELETE
            Restaurant resto = this.restoMng.get(this.id);
            System.out.println(this.id + "- " + resto.getNom());
            restoMng.delete(this.id);
     
			MainView view = new MainView(0,15);
			view.setVisible(true);
			this.dispose(); 
		} break; 
		case 3: { //RETURN
				MainView view = new MainView(0,15);
				view.setVisible(true);
				this.dispose(); 
			} break; 
		case 4: { //SAVE donc modifier un restaurant deja crée
				System.out.println();
				Restaurant r = this.restoMng.get(this.id);
				r.setNom(this.c_nom.getText());
				r.setAdresse(this.c_adresse.getText());
				r.setCodePostal(this.c_codePostal.getText());
				r.setSpecialite(this.c_spec.getSelectedItem().toString());
				r.setVille(this.c_ville.getSelectedItem().toString());
				boolean b2 = this.restoMng.save(r);
				if(b2){
					MainView view = new MainView(0,15);
					view.setVisible(true);
					this.dispose();
				}else {
						this.msg_erreur.setVisible(true);
				}
		} break; 
		case 6: { //MODIFY donc ajouter un nouveau restaurant
				Restaurant r = new Restaurant();
				r.setNom(this.c_nom.getText());
				r.setAdresse(this.c_adresse.getText());
				r.setCodePostal(this.c_codePostal.getText());
				r.setSpecialite(this.c_spec.getSelectedItem().toString());
				r.setVille(this.c_ville.getSelectedItem().toString());
				boolean b2 = this.restoMng.addResto(r);
				if(b2){
					MainView view = new MainView(0,15);
					view.setVisible(true);
					this.dispose();
				}
				else {
					this.msg_erreur.setVisible(true);
				}
		} break; 
	}
		
	}

}
