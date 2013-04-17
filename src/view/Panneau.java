package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
  
@SuppressWarnings("unused")
public class Panneau extends JPanel {

	private static final long serialVersionUID = -2029374437722116290L;
	public Panneau(Color c){
		this.setBackground(c);
	}
	public Panneau(){
	}
	/*
  public void paintComponent(Graphics g){
    try {
      Image img = ImageIO.read(new File("C:/Users/Administrateur/Pictures/52f4b1e5.gif"));
      g.drawImage(img,0,0,this);
      //Pour une image de fond
      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }              
  }   */         
}