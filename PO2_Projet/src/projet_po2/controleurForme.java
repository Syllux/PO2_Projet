/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class controleurForme implements AutreEventListener {

    private modeleForme modele;   
    public controleurForme(modeleForme modele) {
        this.modele = modele;
    }

    public void actionADeclancher(AutreEvent event) {
        
        if (event.getDonnee() instanceof ArrayList) {
            Color couleurForme = new Color(0,0,0);
            System.out.println("actionADeclancher add");
            List listePoints = new ArrayList<>((ArrayList) event.getDonnee());
            String nomForme = new String();
           
            int nombre = listePoints.size();
            for (int i = 0; i < nombre; i++) {
                if (listePoints.get(i) instanceof String) {
                    if (listePoints.get(i).equals("Rectangle")) {
                        nomForme = "Rectangle";
                    } else if (listePoints.get(i).equals("Ligne Brisée")) {
                        nomForme = "Ligne Brisée";
                    }
                    System.out.println(nomForme);
                } else if (listePoints.get(i) instanceof Color) {
                    couleurForme = (Color) listePoints.get(i);
                }
            }
            // On vérifie quelle forme a été choisie avant la création de la forme

//              System.out.println(listePoints.get(4));
//              System.out.println(listePoints.get(5));
//              System.out.println(listePoints.get(6));             
            //Color couleurForme= new Color ((int)listePoints.get(1),(int)listePoints.get(2),(int)listePoints.get(3));
            
            Forme nouvelleForme = new Forme(listePoints, nomForme, couleurForme);
            modele.addForme(nouvelleForme);
        } else if (event.getDonnee() instanceof Integer) {
            System.out.println("actionADeclancher del");
            int pos = (Integer) event.getDonnee();
            modele.removeForme(pos);
        }
    }

//    @Override
//    public void actionADeclancher(AutreEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
