package atividadesTrabalhos.prova;

public class ProvaMolina {
    public static void main(String[] args) {
        int a =2;
        int b =3;
        int resultado = somar(a,b);

        System.out.println("O resultado é:");
        System.out.print(resultado);

        int number = 5;
        int fatorial = 1;

        for(int i = 1;i <= number; i++) {
            fatorial = fatorial * i;
            System.out.println(i);
        }

        int result = calculo(4);
        System.out.println(result);

    }

    //RECURSIVIDADE
    public static int somar(int a, int b) {
        int resultado = a + b;
        return resultado;
    }

    public static int calculo(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        else {
            return n * calculo(n - 1);
        }
    }
}
