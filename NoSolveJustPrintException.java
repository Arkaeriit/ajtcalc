/*---------------------------------------------------------------------------------\
|Cette classe sert à arrêter les calculs quand on doit juste afficher quelque-chose|
\---------------------------------------------------------------------------------*/

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
