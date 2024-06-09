package modele;

public class BlocType extends Bloc{
    public Type type;
    public BlocType(Jeu _jeu, Case c, Type type) {
        super(_jeu, c);
        this.type = type;
    }


}
