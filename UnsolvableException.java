/*------------------------------------------------------------------\
|Cette classe permet d'arrêter les calcus qui ne sont pas faisables.|
\------------------------------------------------------------------*/

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

