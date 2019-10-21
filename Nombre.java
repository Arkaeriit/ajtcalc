/*-------------------------------------------------------------------\
|Cette classe sert à représenter les nombres. Un nombres est         |
|considéré comme étant une formule mathématique résolue. Si on créé  |
|un nouveau nombre à partir d'une formule il va la résoudre tout seul|
\-------------------------------------------------------------------*/

class Nombre extends Formule {
    protected double valeur;

    public Nombre(String formule) throws UnsolvableException { //Si on nous donne une formule on va en en premier temps la calculer
        super(formule);
        valeur = resolution();        
    }

    public Nombre(double valeur) { //On connait une valeur
        this.valeur = valeur;
    } 

    public double getValeur(){
        return valeur;
    }
}  

