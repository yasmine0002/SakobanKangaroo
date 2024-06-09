package modele;


public class Bouton extends Case {

    private boolean pressé;

    public boolean getPressé()
    {
        return pressé;
    }

    private Porte[] tabPorte;

    private int indexPorte;

    public Bouton(Jeu _jeu, int nbPorte) {
        super(_jeu);
        pressé = false;
        tabPorte = new Porte[nbPorte];
        indexPorte = 0;
    }

    public void addPorte(Porte p)
    {
        tabPorte[indexPorte] = p;
        indexPorte++;
    }

    @Override
    public boolean peutEtreParcouru() {
        return e == null;
    }

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d) {
        setEntite(e);
        pressé = true;
        for(int i = 0; i < tabPorte.length; i++)
        {
            System.out.println("appel a update");
            tabPorte[i].update(true);
        }
        return true;
    }

    @Override
    public void quitterLaCase()
    {
        e = null;
        pressé = false;
        for(int i = 0; i < tabPorte.length; i++)
        {
            tabPorte[i].update(false);
        }
    }

}
