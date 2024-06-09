package modele;

public class Rails extends Case{

    public int nb_rails =0;

    public Rails(Jeu _jeu, int nb_rails){
        super(_jeu);
        this.nb_rails = nb_rails;
    }


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
