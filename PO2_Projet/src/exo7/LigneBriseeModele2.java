package exo7;

import java.util.List;

import java.util.ArrayList;

public class LigneBriseeModele2  {
    private List<Paire<Integer>> listePoints = new ArrayList<>();
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
	public void addPoint(Paire<Integer> point) {
		listePoints.add(point);                   
                System.out.println("modele notifie");
		notifieur.diffuserAutreEvent(new AutreEvent(this, listePoints));
	}
	public void removePoint(int position) {
		if ((position >= 0) && (position <= listePoints.size()-1)) {
			listePoints.remove(position);              
                        System.out.println("modele notifie");
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
