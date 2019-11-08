/*-------------------------------------------------------------------\
|Cette classe permet de créer et de modifier des listes d'Expressions|
\-------------------------------------------------------------------*/

import java.util.ArrayList;

class ListExp {
    public ArrayList<Expression> tabExp;

    public ListExp(){
        tabExp = new ArrayList<Expression>(10);
    }

    public ListExp(ArrayList<Expression> tabExp){
        this.tabExp = tabExp;
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<tabExp.size();i++){
            if(i%2 == 0)
                ret = ret + tabExp.get(i).getValeur();
            else
                ret = ret.concat(String.format("%c",tabExp.get(i).getSymbole()));
        }
        return ret;
    }

    protected void addExpression(char symbole){
        tabExp.add(new Operation(symbole));
    }

    protected void addExpression(double nombre){
        tabExp.add(new Nombre(nombre));
    }

    protected void addExpression(String formule) throws UnsolvableException,NoSolveJustPrintException {
        tabExp.add(new Nombre(formule));
    }

    protected void addExpression(String fonction,int argc,String[] argv) throws DecodageException,UnsolvableException,NoSolveJustPrintException {
        tabExp.add(new Fonction(fonction,argc,argv));
    }

    protected void init(){
        tabExp.clear();
    }

    protected int nombreOperations(){
        return (tabExp.size() - 1)/2;
    }

    protected char getNSymbole(int i){
        return tabExp.get(2 * i + 1).getSymbole();
    }

    protected double getNValeur(int i){
        return tabExp.get(2 * i).getValeur();
    }
}
