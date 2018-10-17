package exo7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueLigneBrisee2 extends Box implements AutreEventListener {

    private LigneBriseeModele2 modele;
    private ControleurLigneBrisee2 controleur;
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
    private JComboBox<String> choixPoints;
    private JTextField x, y;
    private Dessin zoneDessin;

    public VueLigneBrisee2(LigneBriseeModele2 modele, ControleurLigneBrisee2 controleur) {
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
        zoneDessin.addMouseListener(controleur);

        boiteGauche.add(Box.createGlue());
        Box boite = new Box(BoxLayout.X_AXIS);
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
        choixPoints = new JComboBox<String>();
        boiteGauche.add(choixPoints);
        bouton = new JButton("del");
        boiteGauche.add(bouton);
        bouton.addActionListener((ActionEvent ae) -> {
            System.out.println("vue del");
            int pos = choixPoints.getSelectedIndex();
            if (pos != -1) {
                notifieur.diffuserAutreEvent(new AutreEvent(this, pos));
            }
        });
        boiteGauche.add(Box.createGlue());
    }

    public void actionADeclancher(AutreEvent event) {
        if (event.getSource() == modele) {
            System.out.println("vue actionADeclancher");
            choixPoints.removeAllItems();
            List<Paire<Integer>> listePoints = (List<Paire<Integer>>) event.getDonnee();
            int n = listePoints.size();
            for (int i = 0; i < n; ++i) {
                choixPoints.addItem(listePoints.get(i).toString());
            }
            zoneDessin.setListePoints(listePoints);
            zoneDessin.repaint();
            //zoneDessin.revalidate();
        }
    }

    public class Dessin extends JPanel {

        private List<Paire<Integer>> listePoints = new ArrayList<>();

        public Dessin() {
            this.setPreferredSize(new Dimension(400, 400));
        }

        public void setListePoints(List<Paire<Integer>> listePoints) {
            this.listePoints = listePoints;
        }

        public void paintComponent(Graphics g) {
            System.out.println("paint");
            super.paintComponent(g);
            int nombre = listePoints.size();
            if (nombre > 1) {
                Paire<Integer> pointActuel = listePoints.get(0);
                Paire<Integer> pointSuivant;
                for (int i = 1; i < nombre; i++) {
                    pointSuivant = listePoints.get(i);
                    g.drawLine(pointActuel.getPremier(), pointActuel.getSecond(),
                            pointSuivant.getPremier(), pointSuivant.getSecond());
                    pointActuel = pointSuivant;
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

}
