/*-----------------------------------------------------------------------------------\
|Cette classe sert à lancer des exceptions en cas d'erreur de lecture des expressions|
\-----------------------------------------------------------------------------------*/

class DecodageException extends Exception { //Permet de voir si on a une erreur de lecture
    public String problème;

    public DecodageException() {}
    public DecodageException(String s){
        super(s);
        problème = s;
    }

    public String raison(){
        return "Reason : "+problème;
    }
}

