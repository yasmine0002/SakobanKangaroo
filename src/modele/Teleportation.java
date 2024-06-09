package modele;

public class Teleportation extends Case {
    public Teleportation destination; //deuxieme portail destination

    public Teleportation(Jeu _jeu, Teleportation _destination) {
        super(_jeu);
        this.destination = _destination;
    }

    public boolean peutEtreParcouru() {
        return true;
    }

    public boolean entrerSurLaCase(Entite e, Direction d) {
        if (destination != null) {
            System.out.println("commence teleportation");
            e.getCase().quitterLaCase();
            System.out.println("on quitte la case");
            destination.setEntite(e);
            jeu.deplacerEntite(e,d);
            System.out.println("e se trouve dans destination");
           /* e.setCase(destination);
            System.out.println("la case de e est destintion");
            e.getCase().quitterLaCase();*/
            return true;
        }
        System.out.println("erreur");
        return false;
    }
}

