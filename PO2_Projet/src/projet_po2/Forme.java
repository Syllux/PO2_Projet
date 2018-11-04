
package projet_po2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Forme {

    private String typeForme;
    private List<Paire<Integer>> listePoints = new ArrayList<>();
    private Color couleurForme;
    
    public Forme() {
        this.listePoints = null;
        this.typeForme = "";
    }

    public Forme(List<Paire<Integer>> liste, String type,Color couleur) {
        // On récupère que les variables de type Paire dans la liste
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) instanceof Paire)
                this.listePoints.add(liste.get(i));          
        }
        this.typeForme = type;
        this.couleurForme= couleur;
    }

    // Constructeur par recopie
    public Forme(Forme forme){
        this.listePoints = forme.getList();
        this.typeForme = forme.getType();
    }
    
    public String toString() {
        return typeForme.toString() + " : " + listePoints.toString();
    }

    public String getType() {
        return this.typeForme;
    }
    
    public Color getColor(){
        return this.couleurForme;
    }

    public List<Paire<Integer>> getList() {
        return this.listePoints;
    }
    
    // Ajout de coordonnée à la forme
    public void addList(Paire<Integer> ajoutListe) {
        this.listePoints.add(ajoutListe);
    }
}
