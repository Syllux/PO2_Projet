package exo7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleurLigneBrisee2 implements MouseListener, AutreEventListener {
	private LigneBriseeModele2 modele;
	public ControleurLigneBrisee2(LigneBriseeModele2 modele) {
		this.modele = modele;
	}

	public void mouseClicked(MouseEvent evenement) { 
		Paire<Integer> point = new Paire<>(evenement.getX(), evenement.getY()); 
		modele.addPoint(point); 
	}
	public void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
    public void mouseReleased(MouseEvent arg0) { }

	public void actionADeclancher(AutreEvent event) {
		  if (event.getDonnee() instanceof Paire)  {   System.out.println("actionADeclancher add");
			  Paire<Integer> point = (Paire<Integer>) event.getDonnee();
			  modele.addPoint(point); 
		  } else if (event.getDonnee() instanceof Integer) {   System.out.println("actionADeclancher del");
			  int pos = (Integer) event.getDonnee();
			  modele.removePoint(pos);
		  }
	}

}
