/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

class Ser {
    void mover() {
        System.out.println("Movendo-se de alguma forma...");
    }
}

class Voador {
    void voar() {
        System.out.println("Voando de forma genérica...");
    }
}

class Armazenamento {
    void armazenarDados() {
        System.out.println("Armazenando dados de alguma forma...");
    }
}

class Tratamento {
    void tratar() {
        System.out.println("Tratando de forma genérica...");
    }
}

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

public class PolimorfismoExercicio {
    public static void main(String[] args) {

        Voador[] voadores = { new Borboleta(), new Passaro(), new Aviao() };
        for (Voador v : voadores) {
            v.voar();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Ser[] seres = { new Pessoa(), new Carro(), new Cavalo() };
        for (Ser s : seres) {
            s.mover();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Armazenamento[] dispositivos = { new Disquete(), new HD() };
        for (Armazenamento a : dispositivos) {
            a.armazenarDados();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Tratamento[] tratamentos = { new Seringa(), new Remedio() };
        for (Tratamento t : tratamentos) {
            t.tratar();
        }
    }
}
