class Nombre extends Formule { //Un nombre est une formule déja résolue
    private double valeur;

    public Nombre(String formule){ //Si on nous donne une formule on va en en premier temps la calculer
        super(formule);
        valeur = resolution();        
    }

    public Nombre(double valeur) { //On connait une valeur
        super(valeur);
        this.valeur = valeur;
    } 

    public double getValeur(){
        return valeur;
    }
}  

