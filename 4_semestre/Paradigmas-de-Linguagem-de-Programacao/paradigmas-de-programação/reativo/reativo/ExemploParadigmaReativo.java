package reativo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.concurrent.CountDownLatch;

public class ExemploParadigmaReativo {
    public static void main(String[] args) throws InterruptedException {
        FluxoComplexoPipeline pipeline = new FluxoComplexoPipeline();

        System.out.println("=== Execução do Pipeline Reativo ===");
        CountDownLatch travaPipeline = new CountDownLatch(1);
        pipeline.construirPipeline(20, 200)
                .subscribe(
                        resultado -> System.out.println("[SAÍDA] " + resultado),
                        erro -> System.err.println("Erro no pipeline: " + erro),
                        () -> {
                            System.out.println("Pipeline concluído.");
                            travaPipeline.countDown();
                        }
                );

        travaPipeline.await();

        System.out.println("\n=== Publishers Quente vs Frio ===");
        new QuenteEFrioDemo().demonstrar();

        System.out.println("\n=== Agendadores e Paralelismo ===");
        AgendadorDemo agendadorDemo = new AgendadorDemo();
        agendadorDemo.executarExemploParalelo();
        agendadorDemo.mostrarFluxoParalelo();

        System.out.println("\n=== Tratamento de Erros ===");
        new TratamentoErroDemo().executarExemplos();

        System.out.println("\n=== Contrapressão ===");
        ContrapressaoDemo cpDemo = new ContrapressaoDemo();
        cpDemo.demonstrarContrapressao();
        cpDemo.demonstrarBufferContrapressao();

        System.out.println("\n=== Combinação de Streams (Zip & Merge) ===");
        ZipEMesclagemDemo combinarDemo = new ZipEMesclagemDemo();
        combinarDemo.executarZipDemo();
        combinarDemo.executarMesclagemDemo();

        System.out.println("\n=== Finalizando Demo do Paradigma Reativo ===");
        Flux.range(0, 3)
                .flatMap(i -> Mono.just("Mensagem " + i).delayElement(java.time.Duration.ofMillis(100)))
                .collectList()
                .subscribe(lista -> System.out.println("Coletado: " + lista));

        Thread.sleep(2000);
        System.out.println("Main encerrando.");
    }
}