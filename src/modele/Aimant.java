package modele;

public class Aimant extends Case{
    public int pos =0;
    public Levier levier;
    public Aimant (Jeu _jeu, Levier levier) {
        super(_jeu);
        this.levier = levier;
    }

    @Override
    public boolean peutEtreParcouru(){return false;}

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d)
    {
        return true;
    }

    public boolean estDansZoneAimant(Entite e){
        return true;
    }



}
