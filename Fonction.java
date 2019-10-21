/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( ). La manière dont l'héritage est détermiée vient |
|du fait qu'une fonction est un nombre auquel on applique une      |
|opération.                                                        |
\-----------------------------------------------------------------*/

class Fonction extends Nombre {
    
    public Fonction(String fonction,int argc,String[] argv) throws DecodageExeption{
        super(argv[0]);
        if(fonction.compareTo("abs") == 0){ //dans un premier temps on regarde quelle est la fonction. On utilise des if/else car on ne veut pas faire de suposition sur la taille du nom de la fonction
            if(valeur < 0)
                valeur = valeur * (-1);
        }else{
            throw new DecodageExeption("Fonction non valide");
        }
    }  
}
