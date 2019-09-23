class Expression {
    private String formule; //La formule à calculer.
    public Boolean ValeurSym; //Indique si on est une valleur ou une syblole de calcul
    public Boolean solved; //Indique si on connait la valeur de l'exression

    private double valeur;
    private char symbole;
    private Decodeur  decodage; //Les expressions vont être découpées en sous-éléments calculables

    public Expression(String formule){ //On créé un élément avec une formule à calculer
        this.formule = formule;
        this.ValeurSym = true; //On récupèrera une valeur calculable
        this.solved = false; //Mais on ne la connait pas encore.
        this.valeur = 0;
    }

    public Expression(double valeur) { //On connait une valeur
        this.ValeurSym = true;
        this.solved = true;
        this.valeur = valeur;
    } 

    public Expression(char symbole) { //On connait le symbole
        this.ValeurSym = false;
        this.solved = true;
        this.symbole = symbole;
    }


    public double getValeur(){
        return this.valeur;
    }

    public char getSymbole(){
        return this.symbole;
    }


    public Boolean resolution(){ //Fait les calculs de manière récursive. Si il y a une erreur de syntaxe on renvoie false, si tout va bien on renvoie true
        this.decodage = new Decodeur(this.formule);
        if(this.decodage.succes){
            return true;
        }else{
            System.err.println("On ne sais pas encore résoudre l'expression suivante: ");
            System.err.println(this.formule);
            return false;
        }
    }

}
