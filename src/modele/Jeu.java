/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
//import java

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;


public class Jeu extends Observable {

    public static final int SIZE_X = 25;
    public static final int SIZE_Y = 10;

    public int nb_obj_tot = 4;

    public VideObjectifType [] tableau_VObj = new VideObjectifType[nb_obj_tot];
    public BlocType [] tableau_BObj = new BlocType[nb_obj_tot];

    public boolean tout_est_rempli = false;


    private Heros heros;

    private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une case à partir de sa référence
    private Case[][] grilleEntites = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une case à partir de ses coordonnées



    public Jeu() {
        initialisationNiveau();
    }



    public Case[][] getGrille() {
        return grilleEntites;
    }

    public Heros getHeros() {
        return heros;
    }

    public void deplacerHeros(Direction d) {
        heros.avancerDirectionChoisie(d);
        setChanged();
        notifyObservers();
    }


    private void initialisationNiveau() {

        // murs extérieurs horizontaux
        for (int x = 0; x < SIZE_X; x++) {
            addCase(new Mur(this), x, 0);
            addCase(new Mur(this), x, SIZE_Y-1);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < SIZE_Y; y++) {
            addCase(new Mur(this), 0, y);
            addCase(new Mur(this), SIZE_X-1, y);
        }

        for (int x = 1; x < SIZE_X-1; x++) {
            for (int y = 1; y < SIZE_Y-1; y++) {
                if (x == 3 && y == 2)
                {
                    addCase(new Glace(this), 3, 2);
                }
                else
                {
                    addCase(new Vide(this), x, y);
                }
            }

        }

        for(int i =0; i< nb_obj_tot; i++)
        {
            int posx = (int)(Math.random() * 100) % (SIZE_X-2) +1;
            int posy = (int)(Math.random() * 100) % (SIZE_Y-2) +1;

            //Point cellule =  map.get(posx, posy);

          /* if(grilleEntites[posx][posy].getEntite() == null)// ||grilleEntites[posx][posy].getEntite().getCase() instanceof Vide)
           {
               System.out.println("ok");
           }*/

            System.out.println("test : "+ (grilleEntites[posx][posy]==null));
            System.out.println("test : "+ (grilleEntites[posx][posy].getEntite()== null));

            System.out.println("test : "+ (grilleEntites[posx][posy] == null));

           // ((grilleEntites[posx][posy].getEntite()!= null) || (!(grilleEntites[posx][posy] instanceof Vide))|| (grilleEntites[posx][posy] instanceof Teleportation) || (grilleEntites[posx][posy] instanceof Glace)|| (grilleEntites[posx][posy] instanceof Levier) || (grilleEntites[posx][posy] instanceof Porte) || (grilleEntites[posx][posy] instanceof SolFragile) ||(grilleEntites[posx][posy] instanceof Mur) || (grilleEntites[posx][posy] instanceof Aimant) || (grilleEntites[posx][posy] instanceof Bouton) ||  (grilleEntites[posx][posy] instanceof VideObjectif)){


            while((grilleEntites[posx][posy].getEntite()!= null) || (!(grilleEntites[posx][posy] instanceof Vide)) || (grilleEntites[posx][posy] instanceof Teleportation)|| (grilleEntites[posx][posy] instanceof Glace)||(grilleEntites[posx][posy] instanceof Levier)||(grilleEntites[posx][posy] instanceof Porte)||(grilleEntites[posx][posy] instanceof SolFragile)||(grilleEntites[posx][posy] instanceof Mur) ||(grilleEntites[posx][posy] instanceof Aimant) || (grilleEntites[posx][posy] instanceof Bouton)||(grilleEntites[posx][posy] instanceof VideObjectif) )
            {
                posx = (int)(Math.random() * 100) % (SIZE_X-2) +1;
                posy = (int)(Math.random() * 100) % (SIZE_Y-2) +1;
                System.out.println("test : "+ (grilleEntites[posx][posy]==null));
            }

            tableau_VObj[i] = new VideObjectifType(this, Type.coeur);
            addCase(tableau_VObj[i], posx, posy);
        }



        // VideObjectif VObj1 = new VideObjectif(this);
        //addCase(VObj1, 8,7);

        Porte p1 = new Porte(this, 2);
        Porte p2 = new Porte(this, 2);
        Porte p3 = new Porte(this, 2);
        Porte p4 = new Porte(this, 2);
        Porte p5 = new Porte(this, 2);
        Bouton b1 = new Bouton(this, 5);
        Bouton b2 = new Bouton(this, 5);


        b1.addPorte(p1);
        b2.addPorte(p1);
        b1.addPorte(p2);
        b2.addPorte(p2);
        b1.addPorte(p3);
        b2.addPorte(p3);
        b1.addPorte(p4);
        b2.addPorte(p4);
        b1.addPorte(p5);
        b2.addPorte(p5);

        addCase(p1, 16, 3);
        addCase(p2, 17, 3);
        addCase(p3, 18, 3);
        addCase(p4, 16, 2);
        addCase(p5, 16, 1);
        addCase(b1, 15,6);
        addCase(b2, 13,6);

        addCase(new SolFragile(this), 12, 3);
        addCase(new Glace(this), 3, 3);

        Levier lev = new Levier(this);
        addCase(lev, 4, 5);
        addCase(new Aimant(this, lev),7,5);

        heros = new Heros(this, grilleEntites[4][4]);

        for(int i =0; i< nb_obj_tot; i++)
        {
            int posx = (int)(Math.random() * 100) % (SIZE_X -4) +2; //on ne veux pas qu un bloc soit a cote des murs
            int posy = (int)(Math.random() * 100) % (SIZE_Y -4) +2;

            System.out.println("test : "+ (grilleEntites[posx][posy]==null));
            System.out.println("test : "+ (grilleEntites[posx][posy].getEntite()== null));
            System.out.println("test : "+ (grilleEntites[posx][posy] == null));

            while((grilleEntites[posx][posy].getEntite()!= null) || (!(grilleEntites[posx][posy] instanceof Vide))|| (grilleEntites[posx][posy] instanceof Teleportation) || (grilleEntites[posx][posy] instanceof Glace)|| (grilleEntites[posx][posy] instanceof Levier) || (grilleEntites[posx][posy] instanceof Porte) || (grilleEntites[posx][posy] instanceof SolFragile) ||(grilleEntites[posx][posy] instanceof Mur) || (grilleEntites[posx][posy] instanceof Aimant) || (grilleEntites[posx][posy] instanceof Bouton) ||  (grilleEntites[posx][posy] instanceof VideObjectif)){
                posx = (int)(Math.random() * 100) % (SIZE_X-4) +2;
                posy = (int)(Math.random() * 100)% (SIZE_Y-4) +2;
                System.out.println("test : "+ (grilleEntites[posx][posy]==null));
            }

            tableau_BObj[i] = new BlocType(this, grilleEntites[posx][posy], Type.coeur);
        }

        tableau_BObj[0].type = Type.trefle;
        tableau_BObj[1].type = Type.carreau;
        tableau_BObj[2].type = Type.pique;
        tableau_BObj[3].type = Type.coeur;

        tableau_VObj[0].type = Type.trefle;
        tableau_VObj[1].type = Type.carreau;
        tableau_VObj[2].type = Type.pique;
        tableau_VObj[3].type = Type.coeur;

        Teleportation t1 = new Teleportation(this, null);
        Teleportation t2 = new Teleportation(this, t1);

        t1.destination = t2;

        addCase(t1,  10, 3);
        addCase(t2,12,8);

        Bloc bloc1 = new Bloc(this, grilleEntites[6][6]);

        //addCase(b1, 6,6);

    }
    private void addCase(Case e, int x, int y) {
        grilleEntites[x][y] = e;
        map.put(e, new Point(x, y));
    }


    /** Si le déplacement de l'entité est autorisé (pas de mur ou autre entité), il est réalisé
     * Sinon, rien n'est fait.
     */

    public boolean finPartie(){
        for(int i=0; i< nb_obj_tot; i++) {
            if (!(tableau_BObj[i].getCase() instanceof VideObjectifType)) {
                return false;
            }
            else {
                if (!(tableau_BObj[i].type ==  ((VideObjectifType)tableau_BObj[i].getCase()).type)) {
                    return false;}
                else {
                    ((VideObjectifType) tableau_BObj[i].getCase()).remplie = true;
                    System.out.println("tableau_VObj[" + i + "].remplie : " + tableau_VObj[i].remplie);

                }
            }
        }
        System.out.println("fin partie");
        System.exit(0);
        return true;

    }

    public boolean deplacerEntite(Entite e, Direction d) {
        boolean retour = true;

        Point pCourant = map.get(e.getCase());

        Point pCible = calculerPointCible(pCourant, d);

        if (contenuDansGrille(pCible)) {
            Entite eCible = caseALaPosition(pCible).getEntite();
            if (eCible != null && !(e instanceof Bloc)){
                Point p = new Point(0,-1);
                Point aim = new Point(pCible.x + p.x, pCible.y + p.y);
                if(!(caseALaPosition(aim) instanceof Aimant))
                {
                    eCible.pousser(d);
                }

                if((caseALaPosition(aim) instanceof Aimant))
                {
                    if(!((Aimant) caseALaPosition(aim)).levier.allume)
                    eCible.pousser(d);
                }
            }


            // si la case est libérée
            if (caseALaPosition(pCible).peutEtreParcouru()) {
                e.getCase().quitterLaCase();
                caseALaPosition(pCible).entrerSurLaCase(e,d);
            }
            if((eCible != null) && eCible.getCase() instanceof VideObjectif && (e instanceof Bloc) )
            {
                eCible.pousser(d);
                System.out.println("ok");
            }
            if (eCible != null)
            {
                if (eCible.getCase() instanceof VideObjectif) //&&(e instanceof Bloc)
                {
                    //  eCible.pousser(d);
                    //nb_obj_couvert++;
                    System.out.println("remplie");
                }
                else
                {
                    // nb_obj_couvert--;
                    System.out.println("pas remplie");
                }

                //if(eCible.getCase() instanceof )

            }
            else {
                retour = false;
            }

        } else {
            retour = false;
        }



        finPartie();

        return retour;
    }


    private Point calculerPointCible(Point pCourant, Direction d) {
        Point pCible = null;

        switch(d) {
            case haut: pCible = new Point(pCourant.x, pCourant.y - 1); break;
            case bas : pCible = new Point(pCourant.x, pCourant.y + 1); break;
            case gauche : pCible = new Point(pCourant.x - 1, pCourant.y); break;
            case droite : pCible = new Point(pCourant.x + 1, pCourant.y); break;

        }

        return pCible;
    }



    /** Indique si p est contenu dans la grille
     */
    private boolean contenuDansGrille(Point p) {
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }

    private Case caseALaPosition(Point p) {
        Case retour = null;

        if (contenuDansGrille(p)) {
            retour = grilleEntites[p.x][p.y];
        }
        return retour;
    }

}
