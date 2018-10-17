package exo7;

import exo7.VueLigneBrisee2;
import exo7.ControleurLigneBrisee2;
import javax.swing.JFrame;

public class LigneBriseeGUI2 extends JFrame {

	public LigneBriseeGUI2() {
		super("Ligne brisée MVC");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		LigneBriseeModele2 modele = new LigneBriseeModele2();
		ControleurLigneBrisee2 controleur = new ControleurLigneBrisee2(modele);
		VueLigneBrisee2 vue = new VueLigneBrisee2(modele, controleur);
		this.setContentPane(vue);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> { 
				new LigneBriseeGUI2(); 
		}); 

	}

}
