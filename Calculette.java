/*--------------------------------------------------------\
|Cette classe permet de calculer le résultat d'une formule|
mathématique représentée par une liste d'expressions.     |
\--------------------------------------------------------*/

class Calculette extends ListExp {

    public double valeur;
    private ListExp tabExpOrigine;

    public Calculette(ListExp tabExp){
        super(tabExp.size());
        tabExpOrigine = new ListExp(tabExp);
        init();
        valeur = 0;
    }

    public double calcul(){
        double tempo = tabExpOrigine.getNValeur(0);//Dans un premier temps on calcule les ordres de grandeur
        for(int i=0;i<tabExpOrigine.nombreOperations();i++){
            if(tabExpOrigine.getNSymbole(i) == 'e'){
                tempo = tempo * Math.pow(10, tabExpOrigine.getNValeur(i+1));
            }else{
                addExpression(tempo);
                addExpression(tabExpOrigine.getNSymbole(i));
                tempo = tabExpOrigine.getNValeur(i+1);
            }
        }
        addExpression(tempo);
        tabExpOrigine = new ListExp(this);
        init();

        tempo = tabExpOrigine.getNValeur(0);//Puis les exponentiations
        for(int i=0;i<tabExpOrigine.nombreOperations();i++){
            if(tabExpOrigine.getNSymbole(i) == '^'){
                tempo = Math.pow(tempo, tabExpOrigine.getNValeur(i+1));
            }else{
                addExpression(tempo);
                addExpression(tabExpOrigine.getNSymbole(i));
                tempo = tabExpOrigine.getNValeur(i+1);
            }
        }
        addExpression(tempo);
        tabExpOrigine = new ListExp(this);
        init();

        tempo = tabExpOrigine.getNValeur(0);//Puis les divisions/multiplucations
        for(int i=0;i<tabExpOrigine.nombreOperations();i++){
            if(tabExpOrigine.getNSymbole(i) == 'x'){
                tempo = tempo * tabExpOrigine.getNValeur(i+1);
            }else if(tabExpOrigine.getNSymbole(i) == '/'){
                tempo = tempo / tabExpOrigine.getNValeur(i+1);
            }else{
                addExpression(tempo);
                addExpression(tabExpOrigine.getNSymbole(i));
                tempo = tabExpOrigine.getNValeur(i+1);
            }
        }
        addExpression(tempo);
        tabExpOrigine = new ListExp(this);
        init();
        
        tempo = tabExpOrigine.getNValeur(0);//Puis les additions/soustraction
        for(int i=0;i<tabExpOrigine.nombreOperations();i++){
            if(tabExpOrigine.getNSymbole(i) == '+'){
                tempo = tempo + tabExpOrigine.getNValeur(i+1);
            }else if(tabExpOrigine.getNSymbole(i) == '-'){
                tempo = tempo - tabExpOrigine.getNValeur(i+1);
            }else{
                addExpression(tempo);
                addExpression(tabExpOrigine.getNSymbole(i));
                tempo = tabExpOrigine.getNValeur(i+1);
            }
        }
        addExpression(tempo);
        return getNValeur(0);
    }

}

