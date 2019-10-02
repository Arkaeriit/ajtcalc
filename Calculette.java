class Calculette extends ListExp {

    public double valeur;
    private ListExp tabExpOrigine;

    public Calculette(Expression[] tabExp){
        this.tabExpOrigine = new ListExp(tabExp);
        this.init();
        this.valeur = 0;
    }

    public double calcul(){
        double tempo = this.tabExpOrigine.tabExp[0].getValeur();//Dans un premier temps on calcule les exponentiations
        for(int i=0;i<this.tabExpOrigine.nombreOperations();i++){
            if(this.tabExpOrigine.getNSymbole(i) == '^'){
                tempo = Math.pow(tempo, this.tabExpOrigine.getNValeur(i+1));
            }else{
                this.addExpression(tempo);
                this.addExpression(this.tabExpOrigine.getNSymbole(i));
                tempo = this.tabExpOrigine.getNValeur(i+1);
            }
        }
        this.addExpression(tempo);
        this.tabExpOrigine.tabExp = this.tabExp;
        this.init();

        tempo = this.tabExpOrigine.tabExp[0].getValeur();//Puis les divisions/multiplucations
        for(int i=0;i<this.tabExpOrigine.nombreOperations();i++){
            if(this.tabExpOrigine.getNSymbole(i) == 'x'){
                tempo = tempo * this.tabExpOrigine.getNValeur(i+1);
            }else if(this.tabExpOrigine.getNSymbole(i) == '/'){
                tempo = tempo / this.tabExpOrigine.getNValeur(i+1);
            }else{
                this.addExpression(tempo);
                this.addExpression(this.tabExpOrigine.getNSymbole(i));
                tempo = this.tabExpOrigine.getNValeur(i+1);
            }
        }
        this.addExpression(tempo);
        this.tabExpOrigine.tabExp = this.tabExp;
        this.init();
        
        tempo = this.tabExpOrigine.tabExp[0].getValeur();//Puis les additions/soustraction
        for(int i=0;i<this.tabExpOrigine.nombreOperations();i++){
            if(this.tabExpOrigine.getNSymbole(i) == '+'){
                tempo = tempo + this.tabExpOrigine.getNValeur(i+1);
            }else if(this.tabExpOrigine.getNSymbole(i) == '-'){
                tempo = tempo - this.tabExpOrigine.getNValeur(i+1);
            }else{
                this.addExpression(tempo);
                this.addExpression(this.tabExpOrigine.getNSymbole(i));
                tempo = this.tabExpOrigine.getNValeur(i+1);
            }
        }
        this.addExpression(tempo);
        return this.tabExp[0].getValeur();
    }



}
