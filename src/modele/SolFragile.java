package modele;

public class SolFragile extends Case{
    private boolean cassé;

    public boolean getCassé()
    {
        return cassé;
    }
    private boolean remplie;

    public boolean getRemplie()
    {
        return remplie;
    }

    public SolFragile(Jeu _jeu) {
        super(_jeu);
        cassé = false;
        remplie = false;
    }
    @Override
    public boolean peutEtreParcouru() {
        if (!cassé)
        {
            return e == null; //s'il y a  pas deja quelqu'un dedans
            //return true;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean entrerSurLaCase(Entite e, Direction d) {

        //Case c = e.getCase();
        //if (c !=null) {
        //    c.quitterLaCase();
        //}
        if (!cassé) {
            setEntite(e);
        }
        else
        {
            if (remplie)
            {
                setEntite(e);
            }
            else
            {
                if (e instanceof Bloc)
                {
                    remplie = true;
                    setEntite(null);
                }
                if (e instanceof Heros)
                {
                    System.out.println("fin de partie, echec"); // faire une fin de partie
                    System.exit(0);
                }
            }
        }
        return true;
    }

    public void quitterLaCase() {
        e = null;
        //System.out.println("e null");
        if (!cassé)
        {
            cassé = true;
        }
    }
}
