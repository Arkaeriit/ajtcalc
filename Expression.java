class Expression {
    private String formule; //La formule à calculer.
    public Boolean solved; //Indique si on connait la valeur de l'exression

    private double valeur;
    private char symbole;
    //private Decodeur  decodage; //Les expressions vont être découpées en sous-éléments calculables

    public Expression(String formule){ //On créé un élément avec une formule à calculer
        this.formule = formule;
        solved = false; //Mais on ne la connait pas encore.
        valeur = 0;
    }

    public Expression(double valeur) { //On connait une valeur
        solved = true;
        this.valeur = valeur;
    } 

    public Expression(char symbole) { //On connait le symbole
        solved = true;
        this.symbole = symbole;
    }


    public double getValeur(){
        return valeur;
    }

    public char getSymbole(){
        return symbole;
    }


    public Boolean resolution(){ //Fait les calculs de manière récursive. Si il y a une erreur de syntaxe on renvoie false, si tout va bien on renvoie true
        Decodeur decodage = new Decodeur(formule);
        if(decodage.succes){
            for(int i = 0;i < decodage.tabExp.length;i++){
                if(!decodage.tabExp[i].solved)
                    decodage.tabExp[i].resolution();
            }
            Calculette calculette = new Calculette(decodage.tabExp);
            valeur = calculette.calcul();    
            solved = true;
            return true;
        }else{
            unresolvable();
            return false;
        }
    }

    private void unresolvable(){
        System.err.println("The following expression can't be solved: ");
        System.err.println(formule);
        System.err.println("");
    }

}  

