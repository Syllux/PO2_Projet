
package projet_po2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class controleurForme implements AutreEventListener {

    private modeleForme modele;

    public controleurForme(modeleForme modele) {
        this.modele = modele;
    }

    public void actionADeclancher(AutreEvent event) {

        if (event.getDonnee() instanceof ArrayList) {
            Color couleurForme = new Color(0, 0, 0);
            System.out.println("Controleur add");
            List listePoints = new ArrayList<>((ArrayList) event.getDonnee());
            String nomForme = new String();
            Boolean ajoutForme = true;

            int nombre = listePoints.size();
            for (int i = 0; i < nombre; i++) {
                if (listePoints.get(0) instanceof String) {
                    if (listePoints.get(0).equals("Rectangle")) {
                        nomForme = "Rectangle";
                    } else if (listePoints.get(0).equals("Ligne Brisée")) {
                        nomForme = "Ligne Brisée";

                    }
                      else if (listePoints.get(0).equals("Add Ligne")) {
                        System.out.println("Modif");
                        modele.ajoutCordoonneeForme((Integer)listePoints.get(1), (Paire)listePoints.get(2));

                        i = nombre;
                        ajoutForme = false;
                    } else if (listePoints.get(0).equals("Swap")){
                        System.out.println("Swap controleur");
                        modele.swapForme((int) listePoints.get(1), (int) listePoints.get(2));
                        ajoutForme = false;
                    }
                }
                if (listePoints.get(i) instanceof Color) {
                    couleurForme = (Color) listePoints.get(i);
                }
            }
            // On vérifie quelle forme a été choisie avant la création de la forme
            if (ajoutForme == true) {
                Forme nouvelleForme = new Forme(listePoints, nomForme, couleurForme);
                modele.addForme(nouvelleForme);
            }
        } else if (event.getDonnee() instanceof Integer) {
            System.out.println("Controleur del");
            int pos = (Integer) event.getDonnee();
            modele.removeForme(pos);
        }
    }
}
