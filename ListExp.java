class ListExp {
    public Expression[] tabExp;
    protected Boolean initTabl; //Indique si tabExp est vide ou pas

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
}
