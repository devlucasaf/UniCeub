package atividadesTrabalhos.prova;

import java.util.HashMap;

public class MyMapProvaMolina {
    public static void main(String[] args) {
        int i = 0;

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("Nome", "João");
        hashMap.put("Idade", "24");
        hashMap.put("Altura", "0,80");
        hashMap.put("Endereço", "Brasilia");

        System.out.println(hashMap);

        if (hashMap.size() != i) {
            System.out.println("Deu bom oh");
        }

        if (hashMap.size() == i) {
            System.out.println("Tem nada aq nn doidão");
        }
        
        for (String p: hashMap.values()) {
            System.out.println(p);
        }
    }
}
