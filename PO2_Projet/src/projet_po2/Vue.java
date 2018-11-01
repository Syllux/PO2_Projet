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
    private JTextField x1, y1, x2, y2, Epaisseur, Couleur;
    private Dessin zoneDessin;
    private JRadioButton ligneBrisee, rectangle;
    private ButtonGroup selectionTypeForme;
    private JTextPane AfficheTexte;
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
        zoneDessin.setPreferredSize(new Dimension(800, 400));
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
        PanelXY.add(new JLabel("X2 : "));
        x2 = new JTextField(5);
        PanelXY.add(x2);
        PanelXY.add(new JLabel ("Y2 : "));
        y2 = new JTextField(5);
        PanelXY.add(y2);
        ChoixCoordonnees.add(PanelXY, BorderLayout.WEST);
        // Panel Dimension et couleur
        JPanel PanelDimCouleur = new JPanel();
        PanelDimCouleur.add(new JLabel("Epaisseur : "));
        Epaisseur = new JTextField(5);
        PanelDimCouleur.add(Epaisseur);
        PanelDimCouleur.add(new JLabel("Couleur : "));
        Couleur = new JTextField(5);
        PanelDimCouleur.add(Couleur);
        ChoixCoordonnees.add(PanelDimCouleur, BorderLayout.CENTER);
        // Panel bouton
        JPanel PanelAddDelete = new JPanel();
        JButton bouton = new JButton("add");
        PanelAddDelete.add(bouton);
        ChoixCoordonnees.add(PanelAddDelete, BorderLayout.EAST);
        bouton.addActionListener((ActionEvent ae) -> {
            try {
                // Si l'utilisateur appuie sur "add"
                if (rectangle.getSelectedObjects() != null || ligneBrisee.getSelectedObjects() != null) {
                    int valx1 = Integer.parseInt(x1.getText().trim());
                    int valy1 = Integer.parseInt(y1.getText().trim());
                    int valx2 = Integer.parseInt(x2.getText().trim());
                    int valy2 = Integer.parseInt(y2.getText().trim()); 
                    int valEpaisseur = Integer.parseInt(Epaisseur.getText().trim()); 
                    String valCouleur = String.valueOf(Couleur.getText());
                    System.out.println("vue add");
                    Paire x1y1 = new Paire(valx1, valy1);
                    Paire x2y2 = new Paire (valx2, valy2);
                    List listePoints = new ArrayList();
                    //Récupère le nom de la forme selectionnee
                    if(rectangle.getSelectedObjects() != null)
                        listePoints.add(rectangle.getActionCommand());
                    else
                        listePoints.add(ligneBrisee.getActionCommand());
                    listePoints.add(x1y1);
                    listePoints.add(x2y2);
                    listePoints.add(valEpaisseur);
                    listePoints.add(valCouleur);
                    notifieur.diffuserAutreEvent(new AutreEvent(this,  listePoints));
                }
                else 
                    throw new Exception("Aucune forme n'a été selectionnée");
            } 
            catch (NumberFormatException nfe) {
                System.err.println("Il faut donner l'ensemble des coordonnées");
            } 
            catch (Exception e) {
                System.err.println(e.getMessage());
            } 
            finally {
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
        AfficheTexte = new JTextPane();
        ZoneTextuelle.add(AfficheTexte);
        AfficheTexte.setText("                                                ");
        
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
        // Affichage de la nouvelle forme dans la zone textuelle
        if (evt.getSource() instanceof modeleForme && evt.getDonnee() instanceof ArrayList) {
            System.out.println("Dans action a declencher");
            List ListeForme = new ArrayList<>((ArrayList)evt.getDonnee());
            AfficheTexte.setText(ListeForme.get(ListeForme.size()-1) + "\n" + AfficheTexte.getText());
        }
        else 
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

}
