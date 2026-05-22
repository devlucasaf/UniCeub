import poojava.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class OOPFaculdadeDemo {
    public static void main(String[] args) {
        Universidade uni = new Universidade("Universidade Exemplo");

        Departamento comp = new Departamento("Computação");
        Departamento math = new Departamento("Matemática");
        uni.adicionarDepartamento(comp);
        uni.adicionarDepartamento(math);

        Professor profAna = new Professor("Ana", "ana@uni.edu", "P001", 120.0);
        Professor profBruno = new Professor("Bruno", "bruno@uni.edu", "P002", 150.0);
        Funcionario funcCarla = new Funcionario("Carla", "carla@uni.edu", "S001", "Secretaria");

        comp.adicionarMembro(profAna);
        comp.adicionarMembro(funcCarla);
        math.adicionarMembro(profBruno);

        Curso poo = new Curso("POO-101", "Programação Orientada a Objetos", 60);
        Curso calc = new Curso("MAT-110", "Cálculo I", 60);
        Curso alg = new Curso("MAT-120", "Álgebra Linear", 60);

        comp.adicionarCurso(poo);
        math.adicionarCurso(calc);
        math.adicionarCurso(alg);

        poo.atribuirProfessor(profAna);
        calc.atribuirProfessor(profBruno);
        alg.atribuirProfessor(profBruno);

        Aluno lucas = new Aluno("Lucas", "lucas@uni.edu", "A1001");
        Aluno maria = new Aluno("Maria", "maria@uni.edu", "A1002");
        Aluno joao = new Aluno("João", "joao@uni.edu", "A1003");

        uni.registrarAluno(lucas);
        uni.registrarAluno(maria);
        uni.registrarAluno(joao);

        Matricula m1 = uni.matricular(lucas, poo);
        Matricula m2 = uni.matricular(lucas, calc);
        Matricula m3 = uni.matricular(maria, poo);
        Matricula m4 = uni.matricular(maria, alg);
        Matricula m5 = uni.matricular(joao, calc);

        m1.adicionarAvaliacao(new Avaliacao("Trabalho 1", 10.0));
        m1.adicionarAvaliacao(new Avaliacao("Prova 1", 10.0));
        m2.adicionarAvaliacao(new Avaliacao("Lista 1", 10.0));
        m2.adicionarAvaliacao(new Avaliacao("Prova 1", 10.0));
        m3.adicionarAvaliacao(new Avaliacao("Trabalho 1", 10.0));
        m3.adicionarAvaliacao(new Avaliacao("Prova 1", 10.0));
        m4.adicionarAvaliacao(new Avaliacao("Prova 1", 10.0));
        m5.adicionarAvaliacao(new Avaliacao("Lista 1", 10.0));
        m5.adicionarAvaliacao(new Avaliacao("Prova 1", 10.0));

        profAna.lancarNota(m1, "Trabalho 1", 8.5);
        profAna.lancarNota(m1, "Prova 1", 7.0);
        profBruno.lancarNota(m2, "Lista 1", 9.0);
        profBruno.lancarNota(m2, "Prova 1", 6.5);

        profAna.lancarNota(m3, "Trabalho 1", 10.0);
        profAna.lancarNota(m3, "Prova 1", 9.5);

        profBruno.lancarNota(m4, "Prova 1", 7.5);

        profBruno.lancarNota(m5, "Lista 1", 5.0);
        profBruno.lancarNota(m5, "Prova 1", 4.0);

        uni.criarFatura(lucas, 2, 350.0);
        uni.criarFatura(maria, 2, 350.0);
        uni.criarFatura(joao, 1, 175.0);

        lucas.pagarFatura(uni.getFaturaPara(lucas).getId(), 700.0);
        maria.pagarFatura(uni.getFaturaPara(maria).getId(), 350.0);

        System.out.println("==== Pessoas (Polimorfismo via Pessoa) ====");
        
        List<Pessoa> pessoas = new ArrayList<>();
        
        pessoas.add(profAna);
        pessoas.add(profBruno);
        pessoas.add(funcCarla);
        pessoas.add(lucas);
        pessoas.add(maria);
        pessoas.add(joao);

        for (Pessoa p : pessoas) {
            System.out.println(p.resumo());
        }

        System.out.println();
        System.out.println("==== Histórico e Médias ====");
        printHistorico(lucas);
        printHistorico(maria);
        printHistorico(joao);

        System.out.println();
        System.out.println("==== Financeiro ====");
        System.out.println(uni.getFaturaPara(lucas));
        System.out.println(uni.getFaturaPara(maria));
        System.out.println(uni.getFaturaPara(joao));

        System.out.println();
        System.out.println("==== Relatório de Cursos ====");
        
        for (Departamento d : uni.getDepartamentos()) {
            System.out.println("Departamento: " + d.getNome());
            
            for (Curso c : d.getCursos()) {
                System.out.println(" - " + c.detalhes());
            }
        }

        System.out.println();
        System.out.println("==== Folha de Pagamento (Interface Pagavel) ====");
        
        List<Pagavel> folhaPagamento = new ArrayList<>();
        
        folhaPagamento.add(profAna);
        folhaPagamento.add(profBruno);
        folhaPagamento.add(funcCarla);
        
        for (Pagavel p : folhaPagamento) {
            System.out.println(p.resumoPagamento());
        }
    }

    private static void printHistorico(Aluno a) {
        System.out.println("Aluno: " + a.getNome() + " (" + a.getIdAluno() + ")");
        
        for (Matricula m : a.getMatriculas()) {
            System.out.println("  Curso: " + m.getCurso().getCodigo() + " - " + m.getCurso().getNome());
            System.out.println("  Professor: " + (m.getCurso().getProfessor() == null ? "N/A" : m.getCurso().getProfessor().getNome()));
            System.out.println("  Nota final: " + String.format(Locale.US, "%.2f", m.notaFinal()));
            System.out.println("  Situação: " + m.situacao());
        }
    }
}