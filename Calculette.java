class Calculette extends ListExp {

    public double valeur;
    private Expression[] tabExpOrigine;

    public Calculette(Expression[] tabExp){
        this.tabExpOrigine = tabExp;
        this.init();
        this.valeur = 0;
    }

    public double calcul(){
        if(this.tabExpOrigine.length == 1){
            return this.tabExpOrigine[0].getValeur();
        }
        Boolean calc = true; //On ne fait qu'un seul calcul par appel de fonction
        for(int i=0;i<(this.tabExpOrigine.length - 1) / 2;i++){ //On cherche en 1er les exponantiation
            if(this.tabExpOrigine[ 2 * i + 1 ].getSymbole() == '^' && calc){
                this.addExpression(Math.pow(this.tabExpOrigine[2 * i].getValeur(),this.tabExpOrigine[2 * i + 2].getValeur()));
                calc = false;
            }else{
                if(i == 0){
                    this.addExpression(this.tabExpOrigine[0].getValeur());
                    this.addExpression(this.tabExpOrigine[1].getSymbole());
                    this.addExpression(this.tabExpOrigine[2].getValeur());
                }else{
                    this.addExpression(this.tabExpOrigine[2 * i + 1].getSymbole());
                    this.addExpression(this.tabExpOrigine[2 * i + 2].getValeur());
                }
            }
        }
        if(!calc){ //Il y a ey une exponentiation
            Calculette calcRecursion = new Calculette(this.tabExp);
            return calcRecursion.calcul();
        }else{
            this.tabExpOrigine = tabExp;
            this.init();
            for(int i=0;i<(this.tabExpOrigine.length - 1) / 2;i++){ //On cherche en 2eme les multiplications
                if(this.tabExpOrigine[ 2 * i + 1 ].getSymbole() == 'x' && calc){
                    this.addExpression(this.tabExpOrigine[2 * i].getValeur() * this.tabExpOrigine[2 * i + 2].getValeur());
                    calc = false;
                }else if(this.tabExpOrigine[ 2 * i + 1 ].getSymbole() == '/' && calc){
                    this.addExpression(this.tabExpOrigine[2 * i].getValeur() / this.tabExpOrigine[2 * i + 2].getValeur());
                    calc = false;
                }else{
                    if(i == 0){
                        this.addExpression(this.tabExpOrigine[0].getValeur());
                        this.addExpression(this.tabExpOrigine[1].getSymbole());
                        this.addExpression(this.tabExpOrigine[2].getValeur());
                    }else{
                        this.addExpression(this.tabExpOrigine[2 * i + 1].getSymbole());
                        this.addExpression(this.tabExpOrigine[2 * i + 2].getValeur());
                    }
                }
            }
            if(!calc){
                Calculette calcRecursion = new Calculette(this.tabExp);
                return calcRecursion.calcul();
            }else{
                this.tabExpOrigine = tabExp;
                this.init();
                for(int i=0;i<(this.tabExpOrigine.length - 1) / 2;i++){ //On cherche en 3eme les additions
                    if(this.tabExpOrigine[ 2 * i + 1 ].getSymbole() == '+' && calc){
                        this.addExpression(this.tabExpOrigine[2 * i].getValeur() + this.tabExpOrigine[2 * i + 2].getValeur());
                    }else if(this.tabExpOrigine[ 2 * i + 1 ].getSymbole() == '-' && calc){
                        this.addExpression(this.tabExpOrigine[2 * i].getValeur() - this.tabExpOrigine[2 * i + 2].getValeur());
                    }else{
                        if(i==0){
                            this.addExpression(this.tabExpOrigine[0].getValeur());
                            this.addExpression(this.tabExpOrigine[1].getSymbole());
                            this.addExpression(this.tabExpOrigine[2].getValeur());
                        }else{
                            this.addExpression(this.tabExpOrigine[2 * i + 1].getSymbole());
                            this.addExpression(this.tabExpOrigine[2 * i + 2].getValeur());
                        }
                    }
                }
                if(!calc){
                    Calculette calcRecursion = new Calculette(this.tabExp);
                    return calcRecursion.calcul();
                }else{
                    if(this.tabExp.length == 1){
                        return this.tabExp[0].getValeur();
                    }else{
                        System.err.println("Erreur : pas de résultat. "+this.tabExp.length+" éléments restants.");
                        return 0;
                    }
                }
            }
        }
    }

}
