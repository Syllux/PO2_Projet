/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex8
 */
public class Forme {

    private String typeForme;
    private List<Paire<Integer>> listePoints = new ArrayList<>();

    public Forme(List<Paire<Integer>> liste, String type) {
        this.listePoints = liste;
        this.typeForme = type;
    }

    public String toString() {
        return "(" + typeForme.toString() + "," + listePoints.toString() + ")";
    }

    public String getType() {
        return this.typeForme;
    }

    public List<Paire<Integer>> getList() {
        return this.listePoints;
    }
}
