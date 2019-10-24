

class Solveur {

    String formule;
    String variable;

    double approximation;

    double result;

    public Solveur(String variable,String formule,double min,double max) throws UnsolvableException,NoSolveJustPrintException{
        this.formule = formule;
        this.variable = variable;
        //approximation = (max-min)/1000;
        approximation = 0.0000001; //Valeur temporaire

        try{
            result = Newton(min);
        }catch(/*NoProgressNewtonException e*/ Exception e){
            result = min - 1; //On assure un résultat impossible
        }
        if( !(min <= result && result <= max)){
            try{
                result = Newton(max);
            }catch(NoProgressNewtonException e){
                if(Math.abs(evaluate(e.valeurBlocante)) < approximation * 10) //assez bon
                    result = e.valeurBlocante;
                else
                    result = Double.NaN; //On est désepéré
            }
        }
    }

    private double evaluate(double valeur) throws UnsolvableException,NoSolveJustPrintException{
        Double valeurWrap = new Double(valeur);
        //System.out.println("calc : "+formule.replace(variable,valeurWrap.toString()));
        Nombre res = new Nombre(formule.replace(variable,valeurWrap.toString()));
        //System.out.println(res.getValeur());
        return res.getValeur();
    }
        

    private double Newton (double start) throws UnsolvableException,NoProgressNewtonException,NoSolveJustPrintException{ //résolution par la méthode de Newton

        double point = start;
        double fPoint = evaluate(point);
        double previous = 0; //pas encore initialisé

        while(Math.abs(fPoint) > approximation){
            double fPrime = (evaluate(point) - evaluate(point - approximation/1000)) / (approximation/1000); //dérivé en notre point
            double cross0 = - fPoint/fPrime + point; //calcul  de l'intersection de la tengente avec les absices
            //System.out.println("point : "+point+" f(point) : "+fPoint+" f'(point) : "+fPrime+" cross0 : "+cross0);
            previous = point; //réset des variavles
            point = cross0;
            fPoint = evaluate(point);
            if(previous == point)
                throw new NoProgressNewtonException(point);
        }
        return point;
    }   

    public double getValeur(){
        return result;
    }
}

class NoProgressNewtonException extends Exception {
    public double valeurBlocante;

    public NoProgressNewtonException (double valeurBlocante){
        super();
        //System.out.println("!!! "+valeurBlocante);
        this.valeurBlocante = valeurBlocante;
    }
}
