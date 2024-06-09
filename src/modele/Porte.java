package modele;


public class Porte extends Case {
    private int nb_bouton;

    private int nb_bouton_pressé;

    private boolean fermée;

    public boolean getFermée()
    {
        return fermée;
    }


    public Porte(Jeu _jeu, int _nb_bouton)
    {
        super(_jeu);
        nb_bouton = _nb_bouton;
        nb_bouton_pressé = 0;
        fermée = true;
    }

    public void update(boolean b) {
        System.out.println("fonction update");

        if (b)
        {
            System.out.println("bouton presse");
            nb_bouton_pressé++;
            if (nb_bouton_pressé == nb_bouton)
            {
                fermée = false;
                System.out.println("ouvert !!!");
            }
        }
        else
        {
            System.out.println("bouton relaché");
            nb_bouton_pressé--;
            if (nb_bouton_pressé != nb_bouton)
            {
                fermée = true;
            }
        }

    }

    @Override
    public boolean peutEtreParcouru() {
        return !fermée;
    }
}
