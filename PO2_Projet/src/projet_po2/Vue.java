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
    private JTextField x, y;
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
        
        // Zone de dessin
        JPanel PanelZoneDessin = new JPanel();
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(new Dimension(600, 400));
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        PanelZoneDessin.add(zoneDessin);
        this.add(PanelZoneDessin, BorderLayout.WEST);
       
        // Panel Barre d'outils
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
                ChoixForme = new Box(BoxLayout.X_AXIS);
        // Boxe pour les coordonnées
        Box ChoixCoordonnees = new Box(BoxLayout.X_AXIS);
        PanelBarreOutils.add(ChoixCoordonnees, BorderLayout.SOUTH);
        ChoixCoordonnees.add(new JLabel("X : "));
        x = new JTextField(5);
        ChoixCoordonnees.add(x);
        ChoixCoordonnees.add(new JLabel("Y : "));
        y = new JTextField(5);
        ChoixCoordonnees.add(y);
        JButton bouton = new JButton("add");
        ChoixCoordonnees.add(bouton);
        bouton.addActionListener((ActionEvent ae) -> {
            try {
                int valx = Integer.parseInt(x.getText().trim());
                int valy = Integer.parseInt(y.getText().trim());
                System.out.println("vue add");
                notifieur.diffuserAutreEvent(new AutreEvent(this, new Paire<Integer>(valx, valy)));
            } catch (NumberFormatException nfe) {
            } finally {
                x.setText(" ");
                y.setText(" ");
            }
        });
        
        // ChoixCoordonnees.add(Box.createGlue());
        ChoixCoordonnees.add(new JLabel(" "));
        choixPoints = new JList<String>();
        ChoixCoordonnees.add(choixPoints);
        bouton = new JButton("del");
        ChoixCoordonnees.add(bouton);
        
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
