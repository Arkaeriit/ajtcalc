/*-----------------------------------------------------------------\
|Cette classe créé la base pour résoudre les formules mathématiques|
|et donner un nombre.                                              |
\-----------------------------------------------------------------*/

abstract class Formule extends Expression{
    //private String formule; //La formule à résoudre
    protected String formule; //La formule à résoudre

    public Formule(){} //Si on veux créer un nombre connu

    public Formule(String formule){ //On créé un élément avec une formule à calculer
        this.formule = formule;
    }

    abstract public double getValeur(); //Défini dans le cas des Nombres

    public char getSymbole(){ //N'as pas de sens. Se manifeste chez les oppérations
        System.err.println("Tentative de lecture du symbole d'une oppération");
        return ' ';
    }


    public double resolution() throws UnsolvableException,NoSolveJustPrintException{ //Fait les calculs de manière récursive. Si il y a une erreur de syntaxe on renvoie false, si tout va bien on renvoie true
        try{
            Decodeur decodage = new Decodeur(formule);
            Calculette calculette = new Calculette(decodage.tabExp);
            return calculette.calcul();    
        }catch(DecodageException e){
            throw new UnsolvableException(e,formule);
        }
    }

}  


