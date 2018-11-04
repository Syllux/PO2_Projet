/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;
import java.util.ArrayList;
import java.util.List;


import java.util.LinkedList;
/**
 *
 * @author alex8
 */
public class modeleForme {

    private List<Forme> ListForme = new ArrayList<>();
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
    public void addForme(Forme forme) {
		ListForme.add(forme);                  
		notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
    }
//	public void addRectangle(Forme forme) {
//		ListForme.add(forme);                  
//                System.out.println("modele notifie");
//		notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
//	}
//        
//        public void addLigneBrisee(Forme forme) {
//		ListForme.add(forme);                  
//                System.out.println("modele notifie");
//		notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
//	}
        
        
	public void removeForme(int position) {
		if ((position >= 0) && (position <= ListForme.size()-1)) {
			ListForme.remove(position);            
			notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
		}		
	}
        
        public void ajoutCordoonneeForme(int position, Paire<Integer> ajout) {
            if ((position >= 0) && (position <= ListForme.size()-1)) {
                Forme formeModif = ListForme.get(position);
                formeModif.addList(ajout);
                ListForme.set(position, formeModif);
                notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
            }
        }
        
       // public void setColor();
    
        
        
	public void addAutreEventListener(AutreEventListener listener) {
	    notifieur.addAutreEventListener(listener);
	}
	  
	public void removeAutreEventListener(AutreEventListener listener) {
		notifieur.removeAutreEventListener(listener);
	}
}
