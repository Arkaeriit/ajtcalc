/*----------------------------------------------------------------\
|Cette classe sert à décoder les arguments et à lacer les calculs.|
\----------------------------------------------------------------*/

class Interface{
    public static void main(String argv[]){
        if(argv.length < 1){
            manuel();
        }else{
            String formule = ""; //Formule indique le contenu total des arguments que l'on donne
            for(int i=0;i < argv.length;i++){
                formule = formule.concat(argv[i]);
            }
            Nombre valeurIn = new Nombre(formule);
            if(!valeurIn.error){
                if(Math.round(valeurIn.getValeur()) == valeurIn.getValeur()) //Le résultat est entier
                    System.out.println((long) valeurIn.getValeur());
                else
                    System.out.println(valeurIn.getValeur());     
           }else{
               manuel();
           }
        }
    }
    public static void manuel(){
        System.err.println("Usage: ajtcalc [expression to solve]\n");
        System.err.println("The expression to solve is a sequence of number or sub-expression put between parenthesis and operations such as: 3 x ( 4 + 1 )\n");
        System.err.println("You can use {,[ or ( to open a parenthesis and },] or ) to close one.\nNumber are in the decimal system and can be decimal.");
        System.err.println("List of operations:\n* addition, symbolised by +\n* substation, symbolised by -\n* multiplication, symbolised by * or x\n* division, symbolised by /\n* exponentiation, symbolised by ^ or **");
    }
} 

