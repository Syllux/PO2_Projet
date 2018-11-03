/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex8
 */
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
}
