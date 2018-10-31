/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class controleurForme implements  AutreEventListener {
	private modeleForme modele;
	public controleurForme(modeleForme modele) {
		this.modele = modele;
	}


	public void actionADeclancher(AutreEvent event) {
	  if (event.getDonnee() instanceof List)  {   System.out.println("actionADeclancher add");
			  List<Paire<Integer>> listePoints = (List<Paire<Integer>>) event.getDonnee(); 
                          //Le "rectangle" c'ést pour tester
                          Forme nouvelleforme = new Forme(listePoints,"rectangle");                        
			  modele.addForme(nouvelleforme);                         
		  } else if (event.getDonnee() instanceof Integer) {   System.out.println("actionADeclancher del");
			  int pos = (Integer) event.getDonnee();
			  modele.removeForme(pos);
		  }
	}


//    @Override
//    public void actionADeclancher(AutreEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
