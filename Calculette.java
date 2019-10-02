class Calculette extends ListExp {

    public double valeur;
    private ListExp tabExpOrigine;

    public Calculette(Expression[] tabExp){
        System.out.println(tabExp.length);
        this.tabExpOrigine = new ListExp(tabExp);
        this.init();
        this.valeur = 0;
        System.out.println("New calc");
        System.err.println(this.tabExpOrigine.toString());
    }

    public double calcul(){
        double tempo = this.tabExpOrigine.tabExp[0].getValeur();
        for(int i=0;i<this.tabExpOrigine.nombreOperations();i++){
            if(this.tabExpOrigine.getNSymbole(i) == '+'){
                tempo = tempo + this.tabExpOrigine.getNValeur(i+1);
            }else{
                this.addExpression(tempo);
                this.addExpression(this.tabExpOrigine.getNSymbole(i));
                tempo = this.getNValeur(i+1);
            }
        }
        this.addExpression(tempo);
        return this.tabExp[0].getValeur();
    }



}
