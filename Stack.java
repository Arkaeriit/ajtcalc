/*-------------------------------------------------------------------\
|Ces classes permettent de rapeler des éléments précédements calculés|
|quand on est dans une shell ou en interprétant un fichier           |
\-------------------------------------------------------------------*/

class Stack {

    private static StackElem sommet; //Début de la pile
    private static Boolean isInit = false; //Indique si on est dans un mode où on a une stack
    private static int nombreElem = 0; //Indique le nombre d'élément

    private static StackSave save; //Sert à sauvegarder des états de la stack

    public static void enableStack(){ //Permet d'initialiser la stack
        isInit = true;
        if(nombreElem == 0){ //On n'as jamais rien mis dedans
            addElem(Double.NaN);
            save = new StackSave(sommet);
        }
    }

    public static void addElem(double elem){
        if(nombreElem == 0){
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
        return tmp.valeur;
    }

    public static void stackSave(){
        StackSave tmp = save;
        save = new StackSave(sommet);
        save.nextSave = tmp;
    }

    public static void stackBack(){
        sommet = save.savedElem;
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

    public StackSave(StackElem savedElem){
        this.savedElem = savedElem;
        this.nextSave = this;
    }
}
