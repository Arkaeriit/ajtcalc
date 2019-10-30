/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( ). La manière dont l'héritage est détermiée vient |
|du fait qu'une fonction est un nombre auquel on applique une      |
|opération.                                                        |
\-----------------------------------------------------------------*/

import java.io.FileNotFoundException;
import java.util.Scanner;

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
            double[] tmp = new double[argc]; //On y stocke les valeurs que l'on va mettre sur la stack
            for(int i=0;i<argc;i++){ //On met un truc sur la stack
                Nombre ret = new Nombre(argv[i]);
                tmp[i] = ret.getValeur();
            }
            Stack.stackBack(); //On restaure la stack
            for(int i=0;i<argc;i++){ //On y met les éléments que l'on veut
                Stack.addElem(tmp[i]);
            }
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
        }else if(fonction.equals("if")){
            e.compareArg(3);
            Nombre arg0 = new Nombre(argv[0]);
            Nombre ret;
            if(arg0.getValeur() > 0)
                ret = new Nombre(argv[2]);
            else
                ret = new Nombre(argv[1]);
            valeur = ret.getValeur();
        }else if(fonction.equals("input")){
            e.compareArg(0,1);
            Scanner scanner = new Scanner(System.in);
            String promp = "";
            if(argc == 1)
                promp = argv[0];
            System.out.print(promp);
            String input = "";
            try{
                input = scanner.nextLine();
            }catch(java.util.NoSuchElementException exp){
                throw new NoSolveJustPrintException("","exit");
            }
            Nombre ret = new Nombre(input);
            valeur = ret.getValeur();
        }else if(fonction.equals("disp")){
            e.compareArg(0);
            throw new NoSolveJustPrintException("","disp");
        }else if(fonction.equals("showStack")){
            e.compareArg(0);
            throw new NoSolveJustPrintException(Stack.showStack(),"noStack");
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

