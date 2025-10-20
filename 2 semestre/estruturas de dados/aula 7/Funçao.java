public class Funçao {
    
    public static void main(String[] args) {
        
        int a = 2;
        int b = 3;
        
        int resultado =  soma(a,b);
        System.out.println(resultado);
    }
    public static int soma(int a, int b) {
        int resultado = a + b;
        return resultado;
    }
}