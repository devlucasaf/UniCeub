import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParadigmaReativoExemplo {

    static class SensorData {
        private final String sensorId;
        private final double value;
        private final long timestamp;

        SensorData(String sensorId, double value, long timestamp) {
            this.sensorId = sensorId;
            this.value = value;
            this.timestamp = timestamp;
        }

        String getSensorId() { 
            return sensorId; 
        }

        double getValue() { 
            return value; 
        }

        long getTimestamp() { 
            return timestamp; 
        }

        @Override
        public String toString() {
            return "SensorData{sensorId='" + sensorId + "', value=" + value + ", timestamp=" + timestamp + "}";
        }
    }

    static class Alert {
        private final String message;
        private final Severity severity;

        enum Severity { 
            INFO, 
            WARNING, 
            CRITICAL 
        }

        Alert(String message, Severity severity) {
            this.message = message;
            this.severity = severity;
        }

        @Override
        public String toString() {
            return "Alert{message='" + message + "', severity=" + severity + "}";
        }
    }

    static class ProcessedData {
        private final String sensorId;
        private final double movingAverage;
        private final int windowSize;

        ProcessedData(String sensorId, double movingAverage, int windowSize) {
            this.sensorId = sensorId;
            this.movingAverage = movingAverage;
            this.windowSize = windowSize;
        }

        @Override
        public String toString() {
            return "ProcessedData{sensorId='" + sensorId + "', movingAverage=" + movingAverage + ", windowSize=" + windowSize + "}";
        }
    }

    static class DataSimulator {
        private final Random random = new Random();
        private final List<String> sensorIds = List.of("TEMP-01", "TEMP-02", "PRESS-01", "HUM-01");

        Flux<SensorData> streamSensorData(long intervalMillis, int maxEvents) {
            return Flux.interval(Duration.ofMillis(intervalMillis))
                    .take(maxEvents)
                    .map(tick -> {
                        String sensorId = sensorIds.get(random.nextInt(sensorIds.size()));
                        double value;

                        if (sensorId.startsWith("TEMP")) {
                            value = 20.0 + random.nextDouble() * 15.0;
                        } 
                        
                        else if (sensorId.startsWith("PRESS")) {
                            value = 1013.0 + random.nextDouble() * 30.0;
                        } 
                        
                        else {
                            value = 40.0 + random.nextDouble() * 30.0;
                        }
                        return new SensorData(sensorId, value, System.currentTimeMillis());
                    });
        }

        Flux<SensorData> burstyStream(int totalEvents, int burstSize, long burstDelayMillis) {
            return Flux.generate(() -> 0, (counter, sink) -> {
                if (counter >= totalEvents) {
                    sink.complete();
                    return counter;
                }
                int start = counter;
                int end = Math.min(counter + burstSize, totalEvents);

                for (int i = start; i < end; i++) {
                    String sensorId = sensorIds.get(new Random().nextInt(sensorIds.size()));
                    double value = 50.0 + new Random().nextDouble() * 50.0;
                    sink.next(new SensorData(sensorId, value, System.currentTimeMillis()));
                }
                
                try {
                    Thread.sleep(burstDelayMillis);
                } 
                
                catch (InterruptedException e) {
                    sink.error(e);
                }
                return end;
            });
        }
    }

    static class DataProcessor {
        public Flux<ProcessedData> computeMovingAverage(Flux<SensorData> source, int windowSize) {
            return source
                    .groupBy(SensorData::getSensorId)
                    .flatMap(groupedFlux -> groupedFlux
                            .buffer(windowSize, 1)
                            .filter(buffer -> buffer.size() == windowSize)
                            .map(buffer -> {
                                double avg = buffer.stream().mapToDouble(SensorData::getValue).average().orElse(0.0);
                                return new ProcessedData(groupedFlux.key(), avg, windowSize);
                            })
                    );
        }

        public Flux<Alert> detectAnomalies(Flux<ProcessedData> processedFlux) {
            return processedFlux
                    .filter(pd -> {
                        if (pd.sensorId.startsWith("TEMP")) {
                            return pd.movingAverage > 30.0 || pd.movingAverage < 10.0;
                        } 
                        
                        else if (pd.sensorId.startsWith("PRESS")) {
                            return pd.movingAverage > 1040.0 || pd.movingAverage < 980.0;
                        } 
                        
                        else {
                            return pd.movingAverage > 70.0 || pd.movingAverage < 20.0;
                        }
                    })
                    .map(pd -> {
                        Alert.Severity severity = Alert.Severity.WARNING;

                        if ((pd.sensorId.startsWith("TEMP") && pd.movingAverage > 35.0) ||
                            (pd.sensorId.startsWith("PRESS") && pd.movingAverage > 1060.0) ||
                            (pd.sensorId.startsWith("HUM") && pd.movingAverage > 80.0)) {
                            severity = Alert.Severity.CRITICAL;
                        }
                        return new Alert("Anomaly detected for " + pd.sensorId + ": avg=" + pd.movingAverage, severity);
                    });
        }
    }

    static class BackpressureSubscriber<T> implements org.reactivestreams.Subscriber<T> {
        private final String name;
        private final AtomicLong requested = new AtomicLong(0);
        private final AtomicLong received = new AtomicLong(0);
        private final AtomicLong dropped = new AtomicLong(0);
        private org.reactivestreams.Subscription subscription;
        private final int prefetch;

        BackpressureSubscriber(String name, int prefetch) {
            this.name = name;
            this.prefetch = prefetch;
        }

        @Override
        public void onSubscribe(org.reactivestreams.Subscription s) {
            this.subscription = s;
            System.out.println(name + " subscribed, requesting " + prefetch);
            requested.addAndGet(prefetch);
            s.request(prefetch);
        }

        @Override
        public void onNext(T item) {
            long receivedCount = received.incrementAndGet();

            System.out.println(name + " received [" + receivedCount + "]: " + item);

            long remaining = requested.decrementAndGet();

            if (remaining <= prefetch / 2) {
                long toRequest = prefetch;
                requested.addAndGet(toRequest);
                subscription.request(toRequest);
                System.out.println(name + " requested more: " + toRequest);
            }
        }

        @Override
        public void onError(Throwable t) {
            System.err.println(name + " error: " + t.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println(name + " completed. Total received: " + received.get());
        }

        public long getDropped() { 
            return dropped.get(); 
        }
    }

    static class ComplexStreamPipeline {
        private final DataSimulator simulator = new DataSimulator();
        private final DataProcessor processor = new DataProcessor();

        public Flux<String> buildPipeline(int events, long intervalMs) {
            return simulator.streamSensorData(intervalMs, events)
                    .doOnNext(data -> System.out.println("[RAW] " + data))
                    .filter(data -> data.getValue() > 0)
                    .map(data -> {
                        if (data.getSensorId().contains("TEMP")) {
                            return new SensorData(data.getSensorId(), data.getValue() * 1.8 + 32, data.getTimestamp());
                        }
                        return data;
                    })
                    .flatMap(data -> {
                        if (data.getSensorId().equals("PRESS-01")) {
                            return Mono.just(data).delayElement(Duration.ofMillis(10));
                        }
                        return Mono.just(data);
                    })
                    .transform(processor::computeMovingAverage, 5)
                    .transform(processor::detectAnomalies)
                    .flatMap(alert -> {
                        if (alert.severity == Alert.Severity.CRITICAL) {
                            return Mono.just("CRITICAL: " + alert.message).delayElement(Duration.ofMillis(5));
                        } 
                        
                        else {
                            return Mono.just("WARNING: " + alert.message);
                        }
                    })
                    .onErrorResume(e -> Mono.just("Recovered from error: " + e.getMessage()));
        }
    }

    static class HotAndColdDemo {
        public void demonstrate() throws InterruptedException {
            System.out.println("=== Cold Publisher Demo ===");
            Flux<Integer> coldFlux = Flux.range(1, 5).delayElements(Duration.ofMillis(100));

            coldFlux.subscribe(i -> System.out.println("Subscriber A: " + i));
            Thread.sleep(200);
            coldFlux.subscribe(i -> System.out.println("Subscriber B: " + i));
            Thread.sleep(1000);

            System.out.println("\n=== Hot Publisher Demo (share) ===");
            Flux<Integer> hotFlux = Flux.range(1, 5).delayElements(Duration.ofMillis(100)).share();

            hotFlux.subscribe(i -> System.out.println("Subscriber X: " + i));
            Thread.sleep(200);
            hotFlux.subscribe(i -> System.out.println("Subscriber Y: " + i));
            Thread.sleep(1000);
        }
    }

    static class SchedulerDemo {
        public void runParallelExample() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            System.out.println("Main thread: " + Thread.currentThread().getName());

            Flux.range(1, 10)
                    .map(i -> {
                        System.out.println("Map 1 on " + Thread.currentThread().getName() + " value " + i);
                        return i * 2;
                    })
                    .subscribeOn(Schedulers.boundedElastic())
                    .publishOn(Schedulers.parallel())
                    .map(i -> {
                        System.out.println("Map 2 on " + Thread.currentThread().getName() + " value " + i);
                        return i + 1;
                    })
                    .subscribeOn(Schedulers.single())
                    .subscribe(i -> System.out.println("Final value " + i + " on " + Thread.currentThread().getName()),
                            error -> System.err.println(error),
                            latch::countDown);

            latch.await();
        }

        public void showParallelFlux() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            Flux.range(1, 100)
                    .parallel(4)
                    .runOn(Schedulers.parallel())
                    .map(i -> {
                        int result = i * i;
                        System.out.println("Parallel compute " + i + "^2=" + result + " on " + Thread.currentThread().getName());
                        return result;
                    })
                    .sequential()
                    .collectList()
                    .subscribe(list -> {
                        System.out.println("Collected " + list.size() + " results");
                        latch.countDown();
                    });
            latch.await();
        }
    }

    static class ErrorHandlingDemo {
        public void runExamples() {
            System.out.println("=== onErrorReturn ===");
            Flux<Integer> fluxWithError = Flux.just(1, 2, 3)
                    .map(i -> {
                        if (i == 2) {
                            throw new RuntimeException("Simulated error");
                        }
                        return i;
                    })
                    .onErrorReturn(-1);
            fluxWithError.subscribe(System.out::println);

            System.out.println("\n=== onErrorResume ===");
            Flux.just(1, 2, 3)
                    .map(i -> {
                        if (i == 3) {
                            throw new RuntimeException("Failure");
                        }
                        return i;
                    })
                    .onErrorResume(e -> Flux.just(10, 20, 30))
                    .subscribe(System.out::println);

            System.out.println("\n=== retry ===");
            AtomicInteger counter = new AtomicInteger(0);
            Flux.just(1, 2, 3)
                    .map(i -> {
                        if (counter.incrementAndGet() < 3 && i == 2) {
                            throw new RuntimeException("Retry me");
                        }
                        return i;
                    })
                    .retry(2)
                    .subscribe(System.out::println, e -> System.out.println("Final error: " + e));

            System.out.println("\n=== timeout ===");
            Flux.just(1, 2, 3)
                    .delayElements(Duration.ofMillis(200))
                    .timeout(Duration.ofMillis(100))
                    .onErrorReturn(999)
                    .subscribe(System.out::println);
            try { 
                Thread.sleep(1000); 
            } 
            
            catch (InterruptedException e) {}
        }
    }

    static class BackpressureDemo {
        public void demonstrateBackpressure() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            System.out.println("=== Backpressure with custom subscriber ===");
            Flux<Integer> fastProducer = Flux.range(1, 1000)
                    .doOnRequest(r -> System.out.println("Producer requested " + r))
                    .doOnNext(i -> System.out.println("Producing " + i))
                    .delayElements(Duration.ofMillis(1));

            BackpressureSubscriber<Integer> subscriber = new BackpressureSubscriber<>("SlowConsumer", 10);
            fastProducer.subscribe(subscriber);

            Thread.sleep(5000);
            System.out.println("Backpressure demo finished.");
        }

        public void onBackpressureBufferDemo() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            System.out.println("=== onBackpressureBuffer ===");
            Flux.interval(Duration.ofMillis(10))
                    .take(200)
                    .onBackpressureBuffer(50, dropped -> System.out.println("Dropped: " + dropped))
                    .doOnNext(i -> {
                        if (i % 20 == 0) {
                            try { 
                                Thread.sleep(100); 
                            } 
                            
                            catch (InterruptedException e) {}
                        }
                    })
                    .subscribe(i -> System.out.println("Consumed " + i),
                            error -> System.err.println(error),
                            latch::countDown);
            Thread.sleep(10000);
            latch.await();
        }
    }

    static class ZipAndMergeDemo {
        public void runZipDemo() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            Flux<String> names = Flux.just("Ana", "Bob", "Charlie").delayElements(Duration.ofMillis(100));
            Flux<Integer> ages = Flux.just(25, 30, 35).delayElements(Duration.ofMillis(150));

            Flux.zip(names, ages)
                    .map(tuple -> tuple.getT1() + " has " + tuple.getT2() + " years")
                    .subscribe(System.out::println, null, latch::countDown);
            latch.await();
        }

        public void runMergeDemo() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            Flux<Integer> fluxA = Flux.range(1, 5).delayElements(Duration.ofMillis(50));
            Flux<Integer> fluxB = Flux.range(10, 5).delayElements(Duration.ofMillis(70));

            Flux.merge(fluxA, fluxB)
                    .subscribe(i -> System.out.println("Merged: " + i), null, latch::countDown);
            latch.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ParadigmaReativoExemplo example = new ParadigmaReativoExemplo();
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
                .flatMap(i -> Mono.just("Message " + i).delayElement(Duration.ofMillis(100)))
                .collectList()
                .subscribe(list -> System.out.println("Collected: " + list));

        Thread.sleep(2000);
        System.out.println("Main exiting.");
    }
}