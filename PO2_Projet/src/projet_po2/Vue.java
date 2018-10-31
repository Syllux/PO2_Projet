package projet_po2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

// Vue projet
public class Vue extends JPanel implements AutreEventListener {

    private modeleForme modele;
    private controleurForme controleur;
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
    private JList<String> choixPoints;
    private JTextField x1, y1, x2, y2;
    private Dessin zoneDessin;
    private JRadioButton ligneBrisee, rectangle;
    private ButtonGroup selectionTypeForme;
//    private JToggleButton ligneBrisee, rectangle;

    public Vue(modeleForme modele, controleurForme controleur) {

        this.modele = modele;
        modele.addAutreEventListener(this);
        this.controleur = controleur;
        this.addAutreEventListener(controleur);
        this.setLayout(new BorderLayout());
        
        // Zone de dessin (panel à gauche de la frame)
        JPanel PanelZoneDessin = new JPanel();
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(new Dimension(400, 200));
        zoneDessin.setBorder(BorderFactory.createLoweredBevelBorder());
        PanelZoneDessin.add(zoneDessin);
        this.add(PanelZoneDessin, BorderLayout.WEST);
       
        // Panel Barre d'outils (panel du haut de la frame)
        JPanel PanelBarreOutils = new JPanel();
        PanelBarreOutils.setLayout(new BorderLayout());
        PanelBarreOutils.setBorder(BorderFactory.createTitledBorder("Barre d'outils"));
        this.add(PanelBarreOutils, BorderLayout.NORTH);
        // Box contenant le choix des formes
        Box ChoixForme = new Box(BoxLayout.X_AXIS);
        PanelBarreOutils.add(ChoixForme, BorderLayout.NORTH);
        selectionTypeForme = new ButtonGroup();
        rectangle = new JRadioButton("Rectangle");
        selectionTypeForme.add(rectangle);
        ligneBrisee= new JRadioButton("Ligne Brisée");
        selectionTypeForme.add(ligneBrisee);
        ChoixForme.add(rectangle);
        ChoixForme.add(ligneBrisee);
        // Panel pour les coordonnées
        JPanel ChoixCoordonnees = new JPanel();
        ChoixCoordonnees.setLayout(new BorderLayout());
        PanelBarreOutils.add(ChoixCoordonnees, BorderLayout.SOUTH);
        //  Panel pour les x et y
        JPanel PanelXY = new JPanel();
        PanelXY.add(new JLabel("X1 : "));
        x1 = new JTextField(5);
        PanelXY.add(x1);
        PanelXY.add(new JLabel("Y1 : "));
        y1 = new JTextField(5);
        PanelXY.add(y1);
        ChoixCoordonnees.add(PanelXY, BorderLayout.CENTER);
        PanelXY.add(new JLabel("X2 : "));
        x2 = new JTextField(5);
        PanelXY.add(x2);
        PanelXY.add(new JLabel ("Y2 : "));
        y2 = new JTextField(5);
        PanelXY.add(y2);
        // Panel bouton
        JPanel PanelAddDelete = new JPanel();
        JButton bouton = new JButton("add");
        PanelAddDelete.add(bouton);
        ChoixCoordonnees.add(PanelAddDelete, BorderLayout.EAST);
        bouton.addActionListener((ActionEvent ae) -> {
            try {
                int valx1 = Integer.parseInt(x1.getText().trim());
                int valy1 = Integer.parseInt(y1.getText().trim());
                int valx2 = Integer.parseInt(x2.getText().trim());
                int valy2 = Integer.parseInt(y2.getText().trim());              
                System.out.println("vue add");
                Paire x1y1 = new Paire(valx1, valy1);
                Paire x2y2 = new Paire (valx2, valy2);
                List<Paire<Integer>> listePoints = new ArrayList<>();
                listePoints.add(x1y1);
                listePoints.add(x2y2);
                //System.out.println(listePoints.get(0));
                //System.out.println(listePoints.get(1));
                //notifieur.diffuserAutreEvent(new AutreEvent(this, new List<Paire<Integer>>(valx, valy)));
            } catch (NumberFormatException nfe) {
                System.out.println("Il faut donner l'ensemble des coordonnées");
            } finally {
                x1.setText(" ");
                y1.setText(" ");
                x2.setText(" ");
                y2.setText(" ");
            }
        });
        choixPoints = new JList<String>();
        //ChoixCoordonnees.add(choixPoints);
        bouton = new JButton("del");
        PanelAddDelete.add(bouton);
        
        // Panel de la description visuelle (panel à droite de la frame)
        JPanel ZoneTextuelle = new JPanel();
        ZoneTextuelle.setBorder(BorderFactory.createTitledBorder("Zone textuelle"));
        this.add(ZoneTextuelle, BorderLayout.EAST);
        JTextPane AfficheTexte = new JTextPane();
        ZoneTextuelle.add(AfficheTexte);
        String TexteDebut = "Historique des actions";
        AfficheTexte.setText(TexteDebut);
        /* Ajouter du text --> String test = "Premiere action";
        AfficheTexte.setText(AfficheTexte.getText() + test); */
        
//        ligneBrisee = new JToggleButton("Ligne Brisée");
//        boite.add(ligneBrisee);
//        boite = new Box(BoxLayout.X_AXIS);
//        boiteGauche.add(boite);
//        rectangle = new JToggleButton("Rectangle");
//        boite.add(rectangle);
        
        
    }
    
    
    public class Dessin extends JPanel {

       
    }


    public void addAutreEventListener(AutreEventListener listener) {
        notifieur.addAutreEventListener(listener);
    }

    public void removeAutreEventListener(AutreEventListener listener) {
        notifieur.removeAutreEventListener(listener);
    }

    @Override
    public void actionADeclancher(AutreEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
