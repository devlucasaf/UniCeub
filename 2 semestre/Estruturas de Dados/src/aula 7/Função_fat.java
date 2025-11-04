public class Função_fat {
    
    public static void main(String[] args) {
        
        int resultado = calcularFatorial(10);

        System.out.println("");
        System.out.println("===========");
        System.out.println(resultado);
        System.out.println("==========");
    }

    public static int calcularFatorial(int n) {

        if(n==0 || n==1){
            return 1;
        }
        
        else {
            return n * calcularFatorial(n - 1);
        }

    }

}