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

class UnsolvableException extends Exception { //Permet de sortir des non résolutions
    DecodageException e; //Sert aux affichages de stack
    String formule;

    public UnsolvableException(DecodageException e,String formule){
        super();
        this.e = e;
        this.formule = formule;
    }

    public String raison(){
        String ret = "The following expression can't be solved:"+"\n";
        ret = ret+formule+"\n";
        ret = ret+e.raison()+"\n";
        return ret;
    }
}
