class Calculette extends ListExp {

    public double valeur;

    public Calculette(Expression[] tabExp){
        this.tabExp = tabExp;
        this.valeur = 0;
    }

    public void calcul(){
        for(int i=0;i<(this.tabExp.length - 1) / 2;i++){ //On cherche en 1er les exponantiation
            if(this.tabExp[ 2 * i + 1 ].getSymbole() == '^'){
            
            }
        }
    }

}
