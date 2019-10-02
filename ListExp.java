class ListExp {
    public Expression[] tabExp;
    private Boolean initTabl; //Indique si tabExp est vide ou pas

    public ListExp(){
        this.initTabl = false;
    }

    public ListExp(Expression[] tabExp){
        if(tabExp.length > 0){
            this.tabExp = tabExp;
            this.initTabl = true;
        }else{
            this.initTabl = false;
        }
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<this.tabExp.length;i++){
            if(i%2 == 0)
                //ret = ret.concat(this.tabExp[i].getValeur());
                System.out.print(this.tabExp[i].getValeur());
            else
                //ret = ret.concat(this.tabExp[i].getSymbole());
                System.out.print(this.tabExp[i].getSymbole());
        }
        return ret;
    }

    protected void addExpression(char symbole){
        if(initTabl){
            Expression[] tmp = new Expression[ this.tabExp.length + 1 ];
            for(int i=0;i < this.tabExp.length;i++){
                tmp[i] = this.tabExp[i];
            }
            tmp[this.tabExp.length] = new Expression(symbole);
            this.tabExp = tmp;
        }else{
            System.err.println("Erreur impossible");
        }
    }

    protected void addExpression(double nombre){
        if(initTabl){
            Expression[] tmp = new Expression[ this.tabExp.length + 1 ];
            for(int i=0;i < this.tabExp.length;i++){
                tmp[i] = this.tabExp[i];
            }
            tmp[this.tabExp.length] = new Expression(nombre);
            this.tabExp = tmp;
        }else{
            this.tabExp = new Expression[1];
            tabExp[0] = new Expression(nombre);
            this.initTabl = true;
        }
    }

    protected void init(){
        this.initTabl = false;
    }

    protected int nombreOperations(){
        if(this.initTabl){
            return (this.tabExp.length - 1)/2;
        }else{
            return 0;
        }
    }

    protected char getNSymbole(int i){
        return this.tabExp[2 * i + 1].getSymbole();
    }

    protected double getNValeur(int i){
        return this.tabExp[2 * i].getValeur();
    }
}
