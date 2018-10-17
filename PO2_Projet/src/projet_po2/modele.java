/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;
import java.util.List;


import java.util.LinkedList;
/**
 *
 * @author alex8
 */
public class modele {

    private List<Paire<Integer>> ListForme = new LinkedList<>();
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
	public void addPoint(Paire<Integer> point) {
		listePoints.add(point);                   System.out.println("modele notifie");
		notifieur.diffuserAutreEvent(new AutreEvent(this, listePoints));
	}
	public void removePoint(int position) {
		if ((position >= 0) && (position <= listePoints.size()-1)) {
			listePoints.remove(position);              System.out.println("modele notifie");
			notifieur.diffuserAutreEvent(new AutreEvent(this, listePoints));
		}		
	}
	public void addAutreEventListener(AutreEventListener listener) {
	    notifieur.addAutreEventListener(listener);
	}
	  
	public void removeAutreEventListener(AutreEventListener listener) {
		notifieur.removeAutreEventListener(listener);
	}
}
