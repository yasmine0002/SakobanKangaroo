package modele;

public class VideObjectifType extends VideObjectif{
    public Type type;

    public VideObjectifType (Jeu _jeu, Type type)
    {
        super(_jeu);
        this.type = type;
    }
}
