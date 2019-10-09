abstract class Expression {

    public Expression(String formule){} //sert à définir une formule

    public Expression(double valeur) {} //sert à définir un nombre

    public Expression(char symbole) {} //sert à définir les operations

    abstract public double getValeur(); //Défini dans le cas des nombres

    abstract public char getSymbole(); //Défini dans le cas des Opérations
}

