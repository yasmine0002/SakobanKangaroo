package VueControleur;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


import modele.*;

/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction Pacman, etc.))
 *
 */import static modele.Direction.*;

public class VueControleur extends JFrame implements Observer {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille
    private ImageIcon icoHeroG;
    private ImageIcon icoHeroD;

    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoBloc;
    private ImageIcon icoGlace;
    private ImageIcon icoSolFragile;
    private ImageIcon icoTrou;

    private ImageIcon icoBoutonPressé;

    private ImageIcon icoBouton;

    private ImageIcon icoPorte;

    private ImageIcon icoPorteOuverte;

    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)

    private ImageIcon icoAimant;

    private ImageIcon icoVideObj;

    private ImageIcon icoTelep;

    private ImageIcon icoLev;

    private ImageIcon icoLevOn;

    private ImageIcon icoLevOff;

    private ImageIcon icoBlocCoeur;
    private ImageIcon icoBlocTrefle;
    private ImageIcon icoBlocPique;
    private ImageIcon icoBlocCarreau;

    private ImageIcon icoCoeur;
    private ImageIcon icoTrefle;
    private ImageIcon icoPique;
    private ImageIcon icoCarreau;






    public VueControleur(Jeu _jeu) {
        sizeX = jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();

        jeu.addObserver(this);

        mettreAJourAffichage();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized (ComponentEvent c){
                Dimension dim = c.getComponent().getSize();
                System.out.println(dim);
            }
        });
    }



    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée

                    case KeyEvent.VK_LEFT : jeu.deplacerHeros(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : jeu.deplacerHeros(Direction.droite); break;
                    case KeyEvent.VK_DOWN : jeu.deplacerHeros(Direction.bas); break;
                    case KeyEvent.VK_UP : jeu.deplacerHeros(Direction.haut); break;


                }
            }
        });
    }


    private void chargerLesIcones() {
        icoHeroG = chargerIcone("Images/Kanga.png");
        icoHeroD = chargerIcone("Images/KangaD.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoBloc = chargerIcone("Images/Colonne.png");
        icoGlace = chargerIcone("Images/Glace.png");
        icoTrou = chargerIcone("Images/Trou.png");
        icoSolFragile = chargerIcone("Images/SolFragile.png");
        icoBoutonPressé = chargerIcone("Images/BoutonPressé.png");
        icoBouton = chargerIcone("Images/Bouton.png");
        icoPorte = chargerIcone("Images/Porte.png");
        icoPorteOuverte = chargerIcone("Images/PorteOuverte.png");
        icoAimant = chargerIcone("Images/Aimant.jpg");
        icoVideObj = chargerIcone("Images/Point.png");
        icoTelep = chargerIcone("Images/Telep.png");
        icoLevOn = chargerIcone("Images/LevierOn.png");
        icoLevOff = chargerIcone("Images/LevierOff.png");
        icoBlocCarreau = chargerIcone("Images/BlocCarreau.png");
        icoBlocCoeur = chargerIcone("Images/BlocCoeur.png");
        icoBlocTrefle = chargerIcone("Images/BlocTrefle.png");
        icoBlocPique = chargerIcone("Images/BlocPique.png");
        icoCarreau = chargerIcone("Images/Carreau.png");
        icoCoeur = chargerIcone("Images/Coeur.png");
        icoTrefle = chargerIcone("Images/Trefle.png");
        icoPique = chargerIcone("Images/Pique.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {
        Image image = null;
        try {
            image = ImageIO.read(new File(urlIcone));
            BufferedImage buff = ImageIO.read(new File(urlIcone));
            image = buff.getScaledInstance(35,35, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {
        setTitle("Sokoban");
        setSize(914, 397);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

    if(! jeu.finPartie()) {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {

                Case c = jeu.getGrille()[x][y];

                if (c != null) {

                    Entite e = c.getEntite();

                    if (e != null) {
                        if (c.getEntite() instanceof Heros) {
                            tabJLabel[x][y].setIcon(icoHeroG);

                        } else if (c.getEntite() instanceof BlocType) {
                            if(((BlocType) c.getEntite()).type == modele.Type.coeur)
                                tabJLabel[x][y].setIcon(icoBlocCoeur);
                            if(((BlocType) c.getEntite()).type == modele.Type.trefle)
                                tabJLabel[x][y].setIcon(icoBlocTrefle);
                            if(((BlocType) c.getEntite()).type == modele.Type.pique)
                                tabJLabel[x][y].setIcon(icoBlocPique);
                            if(((BlocType) c.getEntite()).type == modele.Type.carreau)
                                tabJLabel[x][y].setIcon(icoBlocCarreau);
                        } else if (c.getEntite() instanceof Bloc) {
                            tabJLabel[x][y].setIcon(icoBloc);
                        }
                    } else {
                        if (jeu.getGrille()[x][y] instanceof Mur) {
                            tabJLabel[x][y].setIcon(icoMur);
                        } else if (jeu.getGrille()[x][y] instanceof VideObjectifType) {
                            if((((VideObjectifType) jeu.getGrille()[x][y]).type == modele.Type.coeur))
                                 tabJLabel[x][y].setIcon(icoCoeur);
                            if((((VideObjectifType) jeu.getGrille()[x][y]).type == modele.Type.trefle))
                                tabJLabel[x][y].setIcon(icoTrefle);
                            if((((VideObjectifType) jeu.getGrille()[x][y]).type == modele.Type.pique))
                                tabJLabel[x][y].setIcon(icoPique);
                            if((((VideObjectifType) jeu.getGrille()[x][y]).type == modele.Type.carreau))
                                tabJLabel[x][y].setIcon(icoCarreau);
                        } else if (jeu.getGrille()[x][y] instanceof VideObjectif) {
                            tabJLabel[x][y].setIcon(icoVideObj);
                        } else if (jeu.getGrille()[x][y] instanceof Vide) {

                            tabJLabel[x][y].setIcon(icoVide);
                        } else if (jeu.getGrille()[x][y] instanceof Glace) {
                            tabJLabel[x][y].setIcon(icoGlace);
                        } else if (jeu.getGrille()[x][y] instanceof SolFragile && !((SolFragile) jeu.getGrille()[x][y]).getCassé()) {
                            tabJLabel[x][y].setIcon(icoSolFragile);
                        } else if (jeu.getGrille()[x][y] instanceof SolFragile && !((SolFragile) jeu.getGrille()[x][y]).getRemplie()) {
                            tabJLabel[x][y].setIcon(icoTrou);
                        } else if (jeu.getGrille()[x][y] instanceof SolFragile) {
                            tabJLabel[x][y].setIcon(icoVide);
                        } else if (jeu.getGrille()[x][y] instanceof Bouton && !((Bouton) jeu.getGrille()[x][y]).getPressé()) {
                            tabJLabel[x][y].setIcon(icoBoutonPressé);
                        } else if (jeu.getGrille()[x][y] instanceof Bouton) {
                            tabJLabel[x][y].setIcon(icoBouton);
                        } else if (jeu.getGrille()[x][y] instanceof Porte && !((Porte) jeu.getGrille()[x][y]).getFermée()) {
                            tabJLabel[x][y].setIcon(icoPorteOuverte);
                        } else if (jeu.getGrille()[x][y] instanceof Porte) {
                            tabJLabel[x][y].setIcon(icoPorte);
                        } else if (jeu.getGrille()[x][y] instanceof Aimant) {
                            tabJLabel[x][y].setIcon(icoAimant);
                        } else if (jeu.getGrille()[x][y] instanceof Teleportation) {
                            tabJLabel[x][y].setIcon(icoTelep);
                        } else if (jeu.getGrille()[x][y] instanceof Levier) {
                            if(((Levier) jeu.getGrille()[x][y]).allume)
                                tabJLabel[x][y].setIcon(icoLevOn);
                            else tabJLabel[x][y].setIcon(icoLevOff);
                        } else {
                            System.out.println("problement d'affichage");
                        }
                    }
                }

            }
        }
    }
    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*

        // récupérer le processus graphique pour rafraichir
        // (normalement, à l'inverse, a l'appel du modèle depuis le contrôleur, utiliser un autre processus, voir classe Executor)


        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }


}
