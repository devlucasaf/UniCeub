package questao2;

// Importa a classe LocalTime para trabalhar com horários
import java.time.LocalTime;
// Importa a classe DateTimeFormatter para formatar a exibição de horários
import java.time.format.DateTimeFormatter;

// Thread 2: Envia a hora a cada 10 segundos (Total: 5 vezes)
public class ThreadHora implements Runnable {
    // Implementa o método run() da interface Runnable
    @Override
    public void run() {
        // Cria um formatador para exibir a hora no formato HH:mm:ss (horas:minutos:segundos)
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Bloco try-catch para tratar possíveis interrupções da thread
        try {
            // Loop para enviar a hora 5 vezes
            for (int i = 0; i < 5; i++) {
                // Obtém a hora atual do sistema e formata conforme o padrão definido
                String horaAtual = LocalTime.now().format(formatador);
                // Imprime a hora atual formatada com destaque visual
                System.out.println("\n********** [HORA " + (i + 1) + "/5]: " + horaAtual + " **********\n");

                // Pausa a execução da thread por 10 segundos (10000 milissegundos)
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            // Captura e trata a exceção caso a thread seja interrompida durante o sleep
            System.out.println("Thread de hora interrompida.");
        }
        // Mensagem indicando o fim do relógio
        System.out.println(">> Fim do Relógio.");
    }
}