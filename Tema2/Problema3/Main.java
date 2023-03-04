
import org.graalvm.polyglot.*;

class Polyglot {
    private static double probabilitate(int x, int n) {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value prob = polyglot.eval("R", "func <- function(a, b, c) {  return(dbinom(a, b, c))}").execute(x, n, 0.5);
        double newRez = prob.asDouble();
        polyglot.close();
        return newRez;
    }
     private static int citire() {
         Context polyglot = Context.newBuilder().allowAllAccess(true).build();
         Value rez = polyglot.eval("python", "\n" +
                 "x = input()\n" + "x" );
         int newRez = Integer.parseInt(rez.asString());
         polyglot.close();
         return  newRez;

     }

    //functia MAIN
    public static void main(String[] args) {
         System.out.println("Nr de incercari:");
         int n = citire();
        System.out.println("Nr de aruncari: ");
         int x = citire();
         System.out.println("Probabilitatea sa se obtina x ori pajura din numarul total de aruncari: " + probabilitate(x,n));

        }

    }


