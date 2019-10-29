/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( ). La manière dont l'héritage est détermiée vient |
|du fait qu'une fonction est un nombre auquel on applique une      |
|opération.                                                        |
\-----------------------------------------------------------------*/

import java.io.FileNotFoundException;

class Fonction extends Nombre {
    
    public Fonction(String fonction,int argc,String[] argv) throws DecodageException,UnsolvableException,NoSolveJustPrintException {
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
        }else if(fonction.equals("solve")){
            e.compareArg(4);
            Solveur solveur = new Solveur(argv[0],argv[1],Double.parseDouble(argv[2]),Double.parseDouble(argv[3]));
            valeur = solveur.getValeur();
        }else if(fonction.equals("echo")){
            e.compareArg(1);
            throw new NoSolveJustPrintException(argv[0]);
        }else if(fonction.equals("ans")){ //On resort un élément de la stack de réponses
            e.compareArg(0,1);
            int n; //Le combientième de la pile
            try{
                n = Integer.parseInt(argv[0]);
            }catch(NumberFormatException xyz){
                n = 0;//Si on a pas de bon argument on lit le sommet de la pile
            }
            valeur = Stack.getNelem(n);
        }else if(fonction.equals("exit")){
            e.compareArg(0);
            throw new NoSolveJustPrintException("","exit");
        }else if(fonction.equals("stackSave")){
            e.compareArg(0);
            Stack.stackSave();
            throw new NoSolveJustPrintException("","noStack");
        }else if(fonction.equals("stackBack")){
            e.compareArg(0);
            Stack.stackBack();
            throw new NoSolveJustPrintException("","noStack");
        }else if(fonction.equals("q") || fonction.equals("quiet")){
            e.compareArg(1);
            try{ //On met le résultat sur la stack
                Nombre arg0 = new Nombre(argv[0]);
                Stack.addElem(arg0.getValeur());
            }catch(UnsolvableException exp){
                 Stack.addElem(Double.NaN);
            }
            catch(NoSolveJustPrintException exp){
                if(!exp.getSpecialMessage().equals("noStack"))
                    Stack.addElem(Double.NaN);
            }
            throw new NoSolveJustPrintException("","noStack"); //On ne fait rien d'autre
        }else if(fonction.equals("run")){
            e.compareArg(1);
            try{
                Interpreteur interpreteur = new Interpreteur(argv[0]);
                throw new NoSolveJustPrintException(interpreteur.toString(),"noStack");
            }catch(FileNotFoundException exp){
                throw new DecodageException("No such file as "+argv[0]);
            }
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

    public void compareArg(int argc1,int argc2) throws FonctionException{
        if(argc != argc1 && argc != argc2)
            throw this;
    }
}

class NoSolveJustPrintException extends Exception { //Sert si on ne doit pas faire de calculs mais seulement afficher des trucs
    private String message;
    private String specialMessage; //Sert, entre autres, à la fonction exit

    public NoSolveJustPrintException(String message){
        super();
        this.specialMessage = "";
        this.message = message+"\n"; //Les messages ne doivet pas être affichés avec println
    }

    public NoSolveJustPrintException(String message,String specialMessage){
        super();
        this.message = message;
        this.specialMessage = specialMessage;
    }

    public String getMessage(){
        return message;
    }

    public String getSpecialMessage(){
        return specialMessage;
    }
}

