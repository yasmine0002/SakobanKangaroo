package modele;

public class VideObjectif extends Vide{
    VideObjectif(Jeu _jeu){super(_jeu);}
    boolean remplie;

    public boolean estRempli(){
        return this.remplie;
    }

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d) {
        return super.entrerSurLaCase(e, d);
    }
}
