/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( ). La manière dont l'héritage est détermiée vient |
|du fait qu'une fonction est un nombre auquel on applique une      |
|opération.                                                        |
\-----------------------------------------------------------------*/

class Fonction extends Nombre {
    
    public Fonction(String fonction,int argc,String[] argv) throws DecodageException,UnsolvableException {
        super(0); //On met une valeur tampon en attendant de faire des calculs.
        FonctionException e = new FonctionException(argc);
        if(fonction.equals("abs")){ //dans un premier temps on regarde quelle est la fonction. On utilise des if/else car on ne veut pas faire de suposition sur la taille du nom de la fonction
            e.compareArg(1);
            Nombre arg0 = new Nombre(argv[0]);
            valeur = arg0.getValeur();
            if(valeur < 0)
                valeur = valeur * (-1);
        }else if(fonction.equals("max")){
            e.compareArg(2);
            Nombre arg0 = new Nombre(argv[0]);
            Nombre arg1 = new Nombre(argv[1]);
            if(arg0.getValeur() < arg1.getValeur())
                valeur = arg1.getValeur();
        }else if(fonction.equals("min")){
            e.compareArg(2);
            Nombre arg0 = new Nombre(argv[0]);
            Nombre arg1 = new Nombre(argv[1]);
            if(arg0.getValeur() > arg1.getValeur())
                valeur = arg1.getValeur();
        }else if(fonction.equals("floor")){
            e.compareArg(1);
            Nombre arg0 = new Nombre(argv[0]);
            valeur = Math.floor(arg0.getValeur());
        }else if(fonction.equals("ceil")){
            e.compareArg(1);
            Nombre arg0 = new Nombre(argv[0]);
            valeur = Math.ceil(arg0.getValeur());
        }else if(fonction.equals("define")){
            e.compareArg(3);
            String res = argv[2].replace(argv[1],argv[0]); //On remplace les constituants du troisième argument qui sont égaux au deuxième par le premier
            this.formule = res;
            valeur = resolution();
        }else{
            throw new DecodageException("Fonction non valide");
        }
    }  
}

class FonctionException extends DecodageException{ //Sert à vérifier le nombre d'arguments des fonctions
    
    private int argc;

    public FonctionException(int argc){
        super("Wrong number of arguments");
        this.argc = argc;
    }

    public void compareArg(int argc) throws FonctionException{
        if(argc != this.argc)
            throw this;
    }
}
