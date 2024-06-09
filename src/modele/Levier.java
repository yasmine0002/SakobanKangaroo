package modele;

import static modele.Direction.bas;
import static modele.Direction.haut;

public class Levier extends Case{
    public boolean allume = false;
    public Levier(Jeu _jeu) { super(_jeu); }

    public boolean peutEtreParcouru() {
        return true;
    }

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d) {
        setEntite(e);
        System.out.println(jeu.deplacerEntite(e,d));
        if(allume && (d==bas)) allume =false;
        else if(!allume && (d==haut)) allume =true;
        return true;
    }
}
