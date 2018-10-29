/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Vue extends Box implements AutreEventListener {

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
        super(BoxLayout.X_AXIS);
        this.modele = modele;
        modele.addAutreEventListener(this);
        this.controleur = controleur;
        this.addAutreEventListener(controleur);

        Box boiteGauche = new Box(BoxLayout.Y_AXIS);
        JPanel bg = new JPanel();
        bg.add(boiteGauche);
        this.add(bg);
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(new Dimension(400, 400));
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        this.add(zoneDessin);
       
        boiteGauche.add(Box.createGlue());
        Box boite = new Box(BoxLayout.X_AXIS);
        boiteGauche.add(boite);
        selectionTypeForme = new ButtonGroup();
        rectangle = new JRadioButton("Rectangle");
        selectionTypeForme.add(rectangle);
        ligneBrisee= new JRadioButton("Ligne Brisée");
        selectionTypeForme.add(ligneBrisee);
        boite.add(rectangle);
        boite.add(ligneBrisee);
        
//        ligneBrisee = new JToggleButton("Ligne Brisée");
//        boite.add(ligneBrisee);
//        
//        boite = new Box(BoxLayout.X_AXIS);
//        boiteGauche.add(boite);
//        rectangle = new JToggleButton("Rectangle");
//        boite.add(rectangle);
        
        
        boite = new Box(BoxLayout.X_AXIS);
        boiteGauche.add(boite);
        boite.add(new JLabel("X : "));
        x = new JTextField(" ");
        boite.add(x);
        boite = new Box(BoxLayout.X_AXIS);
        boiteGauche.add(boite);
        boite.add(new JLabel("Y : "));
        y = new JTextField(" ");
        boite.add(y);
        JButton bouton = new JButton("add");
        boiteGauche.add(bouton);
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
        
        boiteGauche.add(Box.createGlue());
        boiteGauche.add(new JLabel(" "));
        choixPoints = new JList<String>();
        boiteGauche.add(choixPoints);
        bouton = new JButton("del");
        boiteGauche.add(bouton);
        
        
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
