package view;

import java.awt.Component;

import javax.swing.JFrame;

public class FenetreManager extends Component {

	private static final long serialVersionUID = -7584532543491404505L;
	JFrame f_main = new JFrame();
	JFrame f_detail = new JFrame();
	JFrame f_plusdetail = new JFrame();
	
	public FenetreManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JFrame getF_main() {
		return f_main;
	}

	public void setF_main(JFrame f_main) {
		this.f_main = f_main;
	}

	public JFrame getF_detail() {
		return f_detail;
	}

	public void setF_detail(JFrame f_detail) {
		this.f_detail = f_detail;
	}

	public JFrame getF_plusdetail() {
		return f_plusdetail;
	}

	public void setF_plusdetail(JFrame f_plusdetail) {
		this.f_plusdetail = f_plusdetail;
	}
	
	
}
