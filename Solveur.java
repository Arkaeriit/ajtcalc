

class Solveur {

    String formule;
    String variable;

    double approximation;

    double result;

    public Solveur(String variable,String formule,double min,double max) throws UnsolvableException{
        this.formule = formule;
        this.variable = variable;
        approximation = (max-min)/1000;

        try{
            result = Newton(min);
        }catch(NoProgressNewtonException e){
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

    private double evaluate(double valeur) throws UnsolvableException{
        Double valeurWrap = new Double(valeur);
        Nombre res = new Nombre(formule.replace(variable,valeurWrap.toString()));
        return res.getValeur();
    }
        

    private double Newton (double start) throws UnsolvableException,NoProgressNewtonException{ //résolution par la méthode de Newton

        double point = start;
        double fPoint = evaluate(point);
        double previous = 0; //pas encore initialisé

            System.out.println(fPoint+" "+approximation);
        while(Math.abs(fPoint) > approximation){
            System.out.println(point+" "+fPoint);
            double fPrime = (evaluate(point) - evaluate(point + approximation/100000)) / (point - approximation/1000000); //dérivé en notre point
            double cross0 = - fPoint/fPrime + point; //calcul  de l'intersection de la tengente avec les absices
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
        System.out.println("!!! "+valeurBlocante);
        this.valeurBlocante = valeurBlocante;
    }
}
