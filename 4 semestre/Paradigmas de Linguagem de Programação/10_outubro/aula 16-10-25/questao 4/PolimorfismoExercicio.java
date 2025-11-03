/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// Exemplo de Polimorfismo com os objetos da imagem

// ======= CLASSES BASE =======

// Classe base para seres ou objetos que se movem
class Ser {
    void mover() {
        System.out.println("Movendo-se de alguma forma...");
    }
}

// Classe base para objetos que voam
class Voador {
    void voar() {
        System.out.println("Voando de forma genérica...");
    }
}

// Classe base para dispositivos de armazenamento
class Armazenamento {
    void armazenarDados() {
        System.out.println("Armazenando dados de alguma forma...");
    }
}

// Classe base para ferramentas de tratamento
class Tratamento {
    void tratar() {
        System.out.println("Tratando de forma genérica...");
    }
}

// ======= SUBCLASSES =======

// Grupo 1: VOAR
class Borboleta extends Voador {
    @Override
    void voar() {
        System.out.println("A borboleta voa batendo as asas lentamente.");
    }
}

class Passaro extends Voador {
    @Override
    void voar() {
        System.out.println("O pássaro voa batendo as asas rapidamente.");
    }
}

class Aviao extends Voador {
    @Override
    void voar() {
        System.out.println("O avião voa usando motores a jato.");
    }
}

// Grupo 2: MOVER
class Pessoa extends Ser {
    @Override
    void mover() {
        System.out.println("A pessoa anda com as pernas.");
    }
}

class Carro extends Ser {
    @Override
    void mover() {
        System.out.println("O carro se move com rodas e motor.");
    }
}

class Cavalo extends Ser {
    @Override
    void mover() {
        System.out.println("O cavalo galopa com suas patas.");
    }
}

// Grupo 3: ARMAZENAR
class Disquete extends Armazenamento {
    @Override
    void armazenarDados() {
        System.out.println("O disquete armazena dados magneticamente.");
    }
}

class HD extends Armazenamento {
    @Override
    void armazenarDados() {
        System.out.println("O HD armazena grande volume de dados digitalmente.");
    }
}

// Grupo 4: TRATAR
class Seringa extends Tratamento {
    @Override
    void tratar() {
        System.out.println("A seringa injeta o remédio no paciente.");
    }
}

class Remedio extends Tratamento {
    @Override
    void tratar() {
        System.out.println("O remédio trata o paciente quando ingerido.");
    }
}

// ======= CLASSE PRINCIPAL =======
public class PolimorfismoExercicio {
    public static void main(String[] args) {

        // Grupo 1: Polimorfismo de voo
        Voador[] voadores = { new Borboleta(), new Passaro(), new Aviao() };
        for (Voador v : voadores) {
            v.voar();
        }

        System.out.println("\n----------------------");

        // Grupo 2: Polimorfismo de movimento
        Ser[] seres = { new Pessoa(), new Carro(), new Cavalo() };
        for (Ser s : seres) {
            s.mover();
        }

        System.out.println("\n----------------------");

        // Grupo 3: Polimorfismo de armazenamento
        Armazenamento[] dispositivos = { new Disquete(), new HD() };
        for (Armazenamento a : dispositivos) {
            a.armazenarDados();
        }

        System.out.println("\n----------------------");

        // Grupo 4: Polimorfismo de tratamento
        Tratamento[] tratamentos = { new Seringa(), new Remedio() };
        for (Tratamento t : tratamentos) {
            t.tratar();
        }
    }
}
