package projet_po2;

import java.awt.BorderLayout;
import static java.awt.Color.black;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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
    private JTextField x1, y1, x2, y2, Epaisseur, CouleurR, CouleurG, CouleurB, Delete;
    private Dessin zoneDessin;
    private JRadioButton ligneBrisee, rectangle;
    private ButtonGroup selectionTypeForme;
    private JTextPane AfficheTexte;
    private int nbEltListe = 0;
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
        ligneBrisee = new JRadioButton("Ligne Brisée");
        selectionTypeForme.add(ligneBrisee);
        ChoixForme.add(rectangle);
        ChoixForme.add(ligneBrisee);
        // Panel pour les coordonnées
        JPanel ChoixCoordonnees = new JPanel();
        ChoixCoordonnees.setLayout(new BorderLayout());
        PanelBarreOutils.add(ChoixCoordonnees, BorderLayout.SOUTH);
        //  Panel pour les x, y, epaisseur, couleur et bouton add
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
        PanelXY.add(new JLabel("Y2 : "));
        y2 = new JTextField(5);
        PanelXY.add(y2);
        PanelXY.add(new JLabel("Epaisseur : "));
        Epaisseur = new JTextField(5);
        PanelXY.add(Epaisseur);
        PanelXY.add(new JLabel("Couleur : "));
        PanelXY.add(new JLabel("R : "));
        CouleurR = new JTextField(5);
        PanelXY.add(CouleurR);
        PanelXY.add(new JLabel("G : "));
        CouleurG = new JTextField(5);
        PanelXY.add(CouleurG);
        PanelXY.add(new JLabel("B : "));
        CouleurB = new JTextField(5);
        PanelXY.add(CouleurB);
        ChoixCoordonnees.add(PanelXY, BorderLayout.WEST);
        JButton boutonAdd = new JButton("add");
        PanelXY.add(boutonAdd);
        boutonAdd.addActionListener((ActionEvent ae) -> {
            try {
                // Si l'utilisateur appuie sur "add"
                if (rectangle.getSelectedObjects() != null || ligneBrisee.getSelectedObjects() != null) {
                    int valx1 = Integer.parseInt(x1.getText().trim());
                    int valy1 = Integer.parseInt(y1.getText().trim());
                    int valx2 = Integer.parseInt(x2.getText().trim());
                    int valy2 = Integer.parseInt(y2.getText().trim());
                    int valEpaisseur = 1;
                    int valCouleurR = 0;
                    int valCouleurG = 0;
                    int valCouleurB = 0;                  
                    if (Epaisseur.getText() != "") {
                        valEpaisseur = Integer.parseInt(Epaisseur.getText().trim());
                    }
                    if (CouleurR.getText() != "") {
                        valCouleurR = Integer.parseInt(CouleurR.getText().trim());
                    }
                    if (CouleurG.getText() != "") {
                        valCouleurG = Integer.parseInt(CouleurG.getText().trim());
                    }

                    if (CouleurB.getText() != "") {
                        valCouleurB = Integer.parseInt(CouleurB.getText().trim());
                    }
                    
//                    if (CouleurR.getText() == "") {
//                        valCouleur = String.valueOf(CouleurR.getText());
//                    }
                    System.out.println("vue add");
                    Paire x1y1 = new Paire(valx1, valy1);
                    Paire x2y2 = new Paire(valx2, valy2);
                    List listePoints = new ArrayList();
                    //Récupère le nom de la forme selectionnee
                    if (rectangle.getSelectedObjects() != null) {
                        listePoints.add(rectangle.getActionCommand());
                    } else {
                        listePoints.add(ligneBrisee.getActionCommand());
                    }
                    listePoints.add(x1y1);
                    listePoints.add(x2y2);
                    listePoints.add(valEpaisseur);
                    listePoints.add(valCouleurR);
                    listePoints.add(valCouleurG);
                    listePoints.add(valCouleurB);
                    
                    nbEltListe++;
                    notifieur.diffuserAutreEvent(new AutreEvent(this, listePoints));
                } else {
                    throw new Exception("Aucune forme n'a été selectionnée");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Veuillez spécifier toutes les coordonnées");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                x1.setText(" ");
                y1.setText(" ");
                x2.setText(" ");
                y2.setText(" ");
                Epaisseur.setText(" ");
                CouleurR.setText(" ");
                CouleurG.setText(" ");
                CouleurB.setText(" ");
            }
        }
        );
        // Panel pour delete
        JPanel PanelDelete = new JPanel();
        choixPoints = new JList<String>();
        Delete = new JTextField("N° forme à supprimer");

        PanelDelete.add(Delete);
        JButton boutonDelete = new JButton("del");

        PanelDelete.add(boutonDelete);

        ChoixCoordonnees.add(PanelDelete, BorderLayout.SOUTH);

        boutonDelete.addActionListener(
                (ActionEvent ae2) -> {
                    try {
                        // Si l'utilisateur appuie sur "dell"
                        if (Delete.getText() != null) {
                            int valDelete = Integer.parseInt(Delete.getText().trim());
                            System.out.println("vue delete");
                            notifieur.diffuserAutreEvent(new AutreEvent(this, valDelete));
                        } else {
                            throw new Exception("Aucune forme définie pour la suppression");
                        }
                    } catch (NumberFormatException nfe) {
                        System.err.println(nfe.getMessage());
                        System.out.println("Dans delete");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        System.out.println("Dans delete");
                    } finally {
                        Delete.setText("          ");
                    }
                }
        );

        // Panel de la description visuelle (panel à droite de la frame)
        JPanel ZoneTextuelle = new JPanel();

        ZoneTextuelle.setBorder(BorderFactory.createTitledBorder("Zone textuelle"));

        this.add(ZoneTextuelle, BorderLayout.EAST);
        AfficheTexte = new JTextPane();

        ZoneTextuelle.add(AfficheTexte);

        ZoneTextuelle.setPreferredSize(
                new Dimension(200, 100));
        AfficheTexte.setSize(ZoneTextuelle.getPreferredSize());

//        ligneBrisee = new JToggleButton("Ligne Brisée");
//        boite.add(ligneBrisee);
//        boite = new Box(BoxLayout.X_AXIS);
//        boiteGauche.add(boite);
//        rectangle = new JToggleButton("Rectangle");
//        boite.add(rectangle);
    }

    public class Dessin extends JPanel {

        private List<Forme> ListeForme = new ArrayList<>();
        private List<Paire<Integer>> listePoints = new ArrayList<>();

        public Dessin() {
            this.setPreferredSize(new Dimension(400, 400));
        }

        public void setListeForme(List Forme) {
            this.ListeForme = Forme;
        }

        public void paintComponent(Graphics g) {
            System.out.println("paint");
            super.paintComponent(g);
            int nombre = ListeForme.size();
            if (nombre > 0) {
                Forme FormeActuel = new Forme();
                Paire<Integer> pointActuel;
                Paire<Integer> pointSuivant;
                for (int i = 0; i < nombre; i++) {
                    //On récupère les formes une par une
                    FormeActuel = ListeForme.get(i);
                    System.out.println(FormeActuel.getType());
                    System.out.println(FormeActuel.getList());
                    listePoints = FormeActuel.getList();                    
                    if (FormeActuel.getType() == "Rectangle") {

                        Paire Paire1 = listePoints.get(0);
                        Point point1 = new Point((int) Paire1.getPremier(), (int) Paire1.getSecond());
                        Paire1 = listePoints.get(1);
                        Point point2 = new Point((int) Paire1.getPremier(), (int) Paire1.getSecond());
                        Rectangle rect = new Rectangle(point1);
                        rect.add(point2);
                        g.setColor(FormeActuel.getColor());
                        g.drawRect(rect.x, rect.y, rect.width, rect.height);
                    } else {
                        int nombrePoint = listePoints.size();
                        if (nombrePoint > 0) {
                            pointActuel = listePoints.get(0);
                            for (int j = 1; j < nombre; j++) {
                                pointSuivant = listePoints.get(j);
                                g.drawLine(pointActuel.getPremier(), pointActuel.getSecond(),
                                        pointSuivant.getPremier(), pointSuivant.getSecond());
                                pointActuel = pointSuivant;
                            }
                        }
                    }

//                   
                }

            }

        }
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
            List ListeForme = new ArrayList<>((ArrayList) evt.getDonnee());
            System.out.println(nbEltListe);
            System.out.println(ListeForme.size());

            // Si on ajoute une forme, on l'affiche dans la zone textuelle
            if (ListeForme.size() == nbEltListe) {
                AfficheTexte.setText(ListeForme.size() - 1 + " -> " + ListeForme.get(ListeForme.size() - 1) + "\n" + AfficheTexte.getText());
            } // Sinon on la supprime de la zone textuelle
            else if (ListeForme.size() != nbEltListe) {
                if (ListeForme.size() == 0) {
                    AfficheTexte.setText("            ");
                } else {
                    AfficheTexte.setText("0 -> " + ListeForme.get(0) + "\n");
                }
                for (int i = 1; i < ListeForme.size(); i++) {
                    AfficheTexte.setText(i + " -> " + ListeForme.get(i) + "\n" + AfficheTexte.getText());
                }

                nbEltListe--;

            }

            zoneDessin.setListeForme(ListeForme);
            zoneDessin.repaint();
        } else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
