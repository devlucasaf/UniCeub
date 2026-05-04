package reativo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.concurrent.CountDownLatch;

public class ParadigmaReativoExemplo {
    public static void main(String[] args) throws InterruptedException {
        ComplexStreamPipeline pipeline = new ComplexStreamPipeline();

        System.out.println("=== Reactive Pipeline Execution ===");
        CountDownLatch pipelineLatch = new CountDownLatch(1);
        pipeline.buildPipeline(20, 200)
                .subscribe(
                        result -> System.out.println("[OUTPUT] " + result),
                        error -> System.err.println("Pipeline error: " + error),
                        () -> {
                            System.out.println("Pipeline completed.");
                            pipelineLatch.countDown();
                        }
                );

        pipelineLatch.await();

        System.out.println("\n=== Hot vs Cold Publishers ===");
        new HotAndColdDemo().demonstrate();

        System.out.println("\n=== Schedulers and Parallelism ===");
        SchedulerDemo schedulerDemo = new SchedulerDemo();
        schedulerDemo.runParallelExample();
        schedulerDemo.showParallelFlux();

        System.out.println("\n=== Error Handling ===");
        new ErrorHandlingDemo().runExamples();

        System.out.println("\n=== Backpressure ===");
        BackpressureDemo bpDemo = new BackpressureDemo();
        bpDemo.demonstrateBackpressure();
        bpDemo.onBackpressureBufferDemo();

        System.out.println("\n=== Combining Streams (Zip & Merge) ===");
        ZipAndMergeDemo combineDemo = new ZipAndMergeDemo();
        combineDemo.runZipDemo();
        combineDemo.runMergeDemo();

        System.out.println("\n=== Finalizing Reactive Paradigm Demo ===");
        Flux.range(0, 3)
                .flatMap(i -> Mono.just("Message " + i).delayElement(java.time.Duration.ofMillis(100)))
                .collectList()
                .subscribe(list -> System.out.println("Collected: " + list));

        Thread.sleep(2000);
        System.out.println("Main exiting.");
    }
}