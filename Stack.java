/*-------------------------------------------------------------------\
|Ces classes permettent de rapeler des éléments précédements calculés|
|quand on est dans une shell ou en interprétant un fichier           |
\-------------------------------------------------------------------*/

class Stack {

    private static StackElem sommet; //Début de la pile
    private static Boolean isInit = false; //Indique si on est dans un mode où on a une stack
    private static int nombreElem = -1; //Indique le nombre d'élément. Pour initialisé on met en NaN en bas donc on commence à -1

    private static StackSave save; //Sert à sauvegarder des états de la stack

    public static void enableStack(){ //Permet d'initialiser la stack
        isInit = true;
        if(nombreElem == -1){ //On n'as jamais rien mis dedans
            addElem(Double.NaN);
            save = new StackSave(sommet);
        }
    }

    public static void addElem(double elem){
        //System.out.println("toStack : "+elem); //usefull for debugging
        if(nombreElem == -1){
            sommet = new StackElem(elem);
        }else{
            StackElem tmp = new StackElem(elem);
            tmp.nextElem = sommet;
            sommet = tmp;
        }
        nombreElem++;
    }

    public static double getNelem(int n) throws DecodageException{//Récupère le n-ième élément de la pile
        if(!isInit)
            throw new DecodageException("Stack disabled in this mode");
        if(nombreElem == 0)
            throw new DecodageException("No elements on the stack");
        if(n > nombreElem)
            throw new DecodageException("Not enought elements on the stack");
        StackElem tmp = sommet;
        for(int i=0;i<n;i++)
            tmp = tmp.nextElem;
        //System.out.println("n : "+n+" valeur : "+tmp.valeur); //usefull for debugging
        return tmp.valeur;
    }

    public static String showStack() throws DecodageException{ //ce n'est pas toString car ça doit être statique
        String ret = "top : "+Stack.getNelem(0);
        for(int i=1; i<nombreElem;i++)
            ret=ret+"\n-"+i+" : "+Stack.getNelem(i);
        return ret+"\n";
    }

    //Fonctions pour la sauvegerde
    
    public static void stackSave(){
        StackSave tmp = save;
        save = new StackSave(sommet);
        save.nextSave = tmp;
        save.nombreElem = nombreElem;
    }

    public static void stackBack(){
        sommet = save.savedElem;
        nombreElem = save.nombreElem;
        save =  save.nextSave;
    }


}

class StackElem {
    public double valeur;
    public StackElem nextElem;

    public StackElem(double valeur){
        this.valeur = valeur;
        this.nextElem = this; //Permet d'éviter de lire un null
    }
}

class StackSave { //Sert à se remémorer des niveaux de la pile
    public StackElem savedElem;
    public StackSave nextSave;
    public int nombreElem;

    public StackSave(StackElem savedElem){
        this.savedElem = savedElem;
        this.nextSave = this;
    }
}
