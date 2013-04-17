package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener {

	private static final long serialVersionUID = 5883590746938182936L;
	private int type;
	private long id;
	
	public Bouton(String s1,long i){
		super(s1);
		this.addMouseListener(this);
		this.id = i;
		this.type = 6;
	}
	public Bouton(String s1){
		super(s1);
		switch(s1){
			case "DELETE":{ type = 1; customiseIco(1); }break;
			case "ADD": type = 2; customiseIco(2);break;
			case "RETURN": type = 3;customiseIco(3);break;
			case "SAVE": type = 4;customiseIco(4);break;
			case "UPDATE": type = 5;customiseIco(5);break;
			case "MODIFY": type = 6;customiseIco(6);break;
			case "PREC": type = 7;break;
			case "NEXT": type = 8;break;
		}
		this.addMouseListener(this);
	}
	public long getId(){
		return this.id;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void customiseIco(int type){
		switch(type){
		case 1:{} break;
		case 2:{} break;
		case 3:{} break;
		case 4:{} break;
		case 5:{} break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	public int getType() {
		return this.type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
