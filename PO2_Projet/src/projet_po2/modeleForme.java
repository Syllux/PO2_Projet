package projet_po2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.LinkedList;

public class modeleForme {

    private List<Forme> ListForme = new ArrayList<>();
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();

    public void addForme(Forme forme) {
        ListForme.add(forme);
        notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
    }

    public void swapForme(int forme1, int forme2) {
        Collections.swap(ListForme,forme1,forme2);
        notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
        System.out.println(ListForme.get(0));
        System.out.println(ListForme.get(1));
    }

    public void removeForme(int position) {
        if ((position >= 0) && (position <= ListForme.size() - 1)) {
            ListForme.remove(position);
            notifieur.diffuserAutreEvent(new AutreEvent(this, ListForme));
        }
    }

    public void ajoutCordoonneeForme(int position, Paire<Integer> ajout) {
        if ((position >= 0) && (position <= ListForme.size() - 1)) {
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
