/*-------------------------------------------------------------------\
|Cette classe permet de créer et de modifier des listes d'Expressions|
\-------------------------------------------------------------------*/

import java.util.ArrayList;

class ListExp extends ArrayList<Expression> {

    public ListExp(){
        super(10);
    }

    public ListExp(int n){
        super(n);
    }

    public ListExp(ArrayList<Expression> tabExp){
        super(tabExp);
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<size();i++){
            if(i%2 == 0)
                ret = ret + get(i).getValeur();
            else
                ret = ret.concat(String.format("%c",get(i).getSymbole()));
        }
        return ret;
    }

    protected void addExpression(char symbole){
        add(new Operation(symbole));
    }

    protected void addExpression(double nombre){
        add(new Nombre(nombre));
    }

    protected void addExpression(String formule) throws UnsolvableException,NoSolveJustPrintException {
        add(new Nombre(formule));
    }

    protected void addExpression(String fonction,int argc,String[] argv) throws DecodageException,UnsolvableException,NoSolveJustPrintException {
        add(new Fonction(fonction,argc,argv));
    }

    protected void init(){
        clear();
    }

    protected int nombreOperations(){
        return (size() - 1)/2;
    }

    protected char getNSymbole(int i){
        return get(2 * i + 1).getSymbole();
    }

    protected double getNValeur(int i){
        return get(2 * i).getValeur();
    }
}

