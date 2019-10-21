/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( ). La manière dont l'héritage est détermiée vient |
|du fait qu'une fonction est un nombre auquel on applique une      |
|opération.                                                        |
\-----------------------------------------------------------------*/

class Fonction extends Nombre {
    
    public Fonction(String fonction,int argc,String[] argv) throws DecodageExeption{
        super(argv[0]);
        FonctionExeption e = new FonctionExeption(argc);
        if(fonction.equals("abs")){ //dans un premier temps on regarde quelle est la fonction. On utilise des if/else car on ne veut pas faire de suposition sur la taille du nom de la fonction
            e.compareArg(1);
            if(valeur < 0)
                valeur = valeur * (-1);
        }else if(fonction.equals("max")){
            e.compareArg(2);
            Nombre arg2 = new Nombre(argv[1]);
            if(valeur < arg2.getValeur())
                valeur = arg2.getValeur();
        }else if(fonction.equals("min")){
            e.compareArg(2);
            Nombre arg2 = new Nombre(argv[1]);
            if(valeur > arg2.getValeur())
                valeur = arg2.getValeur();
        }else if(fonction.equals("floor")){
            e.compareArg(1);
            valeur = Math.floor(valeur);
        }else if(fonction.equals("ceil")){
            e.compareArg(1);
            valeur = Math.ceil(valeur);
        }else{
            throw new DecodageExeption("Fonction non valide");
        }
    }  
}

class FonctionExeption extends DecodageExeption{ //Sert à vérifier le nombre d'arguments des fonctions
    
    private int argc;

    public FonctionExeption(int argc){
        super("Mauvais nombre d'arguments");
        this.argc = argc;
    }

    public void compareArg(int argc) throws FonctionExeption{
        if(argc != this.argc)
            throw this;
    }
}
