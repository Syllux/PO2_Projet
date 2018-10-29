/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;
import javax.swing.JFrame;

public class Projet_PO2 extends JFrame {

	public Projet_PO2() {
		super("Projet_PO2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		modeleForme modele = new modeleForme();
		controleurForme controleur = new controleurForme(modele);
		Vue vue = new Vue(modele, controleur);
		this.setContentPane(vue);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> { 
				new Projet_PO2(); 
		}); 

	}

}
