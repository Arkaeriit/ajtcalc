class Operation extends Expression{

    private char symbole; //Le symbole d l'oppération

    public Operation(char symbole) { //On connait le symbole
        super(symbole);
        this.symbole = symbole;
    }

    public char getSymbole(){
        return symbole;
    }

    public double getValeur(){
        System.err.println("Tentative de lecture de la valeur d'un symbole");
        return 0;
    }
}
