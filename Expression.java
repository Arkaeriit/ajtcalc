/*--------------------------------------------------------------------\
|Les Expressions servent à représenter nimporte quel sous-éléments des|
|formules mathématiques. Comme ils seront placés dans un vecteur il   |
|faut les méthodes abstraites pour exploiter touts les éléments des   |
|vecteurs d'expressions.                                              |
\--------------------------------------------------------------------*/

abstract class Expression {

    abstract public double getValeur(); //Défini dans le cas des nombres

    abstract public char getSymbole(); //Défini dans le cas des Opérations
}

