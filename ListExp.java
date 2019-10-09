class ListExp {
    public Expression[] tabExp;
    private Boolean initTabl; //Indique si tabExp est vide ou pas

    public ListExp(){
        initTabl = false;
    }

    public ListExp(Expression[] tabExp){
        if(tabExp.length > 0){
            this.tabExp = tabExp;
            initTabl = true;
        }else{
            initTabl = false;
        }
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<tabExp.length;i++){
            if(i%2 == 0)
                ret = ret.concat(String.format("%d",tabExp[i].getValeur()));
            else
                ret = ret.concat(String.format("%c",tabExp[i].getSymbole()));
        }
        return ret;
    }

    protected void addExpression(char symbole){
        if(initTabl){
            Expression[] tmp = new Expression[ tabExp.length + 1 ];
            for(int i=0;i < tabExp.length;i++){
                tmp[i] = tabExp[i];
            }
            tmp[tabExp.length] = new Operation(symbole);
            tabExp = tmp;
        }else{
            System.err.println("Erreur impossible");
        }
    }

    protected void addExpression(double nombre){
        if(initTabl){
            Expression[] tmp = new Expression[ tabExp.length + 1 ];
            for(int i=0;i < tabExp.length;i++){
                tmp[i] = tabExp[i];
            }
            tmp[tabExp.length] = new Nombre(nombre);
            tabExp = tmp;
        }else{
            tabExp = new Expression[1];
            tabExp[0] = new Nombre(nombre);
            initTabl = true;
        }
    }

    protected void addExpression(String formule){
        if(initTabl){
            Expression[] tmp = new Expression[ tabExp.length + 1 ];
            for(int i=0;i < tabExp.length;i++){
                tmp[i] = tabExp[i];
            }
            tmp[tabExp.length] = new Nombre(formule);
            tabExp = tmp;
        }else{
            tabExp = new Expression[1];
            tabExp[0] = new Nombre(formule);
            initTabl = true;
        }
    }
    protected void init(){
        initTabl = false;
    }

    protected int nombreOperations(){
        if(initTabl){
            return (tabExp.length - 1)/2;
        }else{
            return 0;
        }
    }

    protected char getNSymbole(int i){
        return tabExp[2 * i + 1].getSymbole();
    }

    protected double getNValeur(int i){
        return tabExp[2 * i].getValeur();
    }
}
