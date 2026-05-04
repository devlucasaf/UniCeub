package reativo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicLong;

public class BackpressureSubscriber<T> implements Subscriber<T> {
    private final String        name;
    private final AtomicLong    requested   = new AtomicLong(0);
    private final AtomicLong    received    = new AtomicLong(0);
    private final AtomicLong    dropped     = new AtomicLong(0);
    private Subscription        subscription;
    private final int           prefetch;

    public BackpressureSubscriber(String name, int prefetch) {
        this.name = name;
        this.prefetch = prefetch;
    }

    @Override
    public void onSubscribe(Subscription s) {
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