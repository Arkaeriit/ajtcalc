/*-----------------------------------------------------------------\
|Cette classe permet de calculer le résultat de certaines fonctions|
|telles que abs( )                                                 |
\-----------------------------------------------------------------*/

class Fonction extends Nombre {
    
    public Fonction(String fonction,String formule) throws DecodageExeption{
        super(formule);
        if(fonction.compareTo("abs") == 0){ //dans un premier temps on regarde quelle est la fonction. On utilise des if/else car on ne veut pas faire de suposition sur la taille du nom de la fonction
            if(valeur < 0)
                valeur = valeur * (-1);
        }else{
            throw new DecodageExeption("Fonction non valide");
        }
    }  
}
