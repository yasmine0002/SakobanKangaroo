package modele;

public class Glace extends Case {

    public Glace(Jeu _jeu) { super(_jeu); }
    @Override
    public boolean peutEtreParcouru() {
        return e == null;
    }

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d) {
        setEntite(e);
        System.out.println(jeu.deplacerEntite(e,d));
        return true;
    }
}
