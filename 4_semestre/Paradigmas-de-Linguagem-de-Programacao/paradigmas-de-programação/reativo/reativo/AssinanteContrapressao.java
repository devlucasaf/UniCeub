package reativo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicLong;

public class AssinanteContrapressao<T> implements Subscriber<T> {
    private final String        nome;
    private final AtomicLong    solicitados   = new AtomicLong(0);
    private final AtomicLong    recebidos     = new AtomicLong(0);
    private final AtomicLong    descartados   = new AtomicLong(0);
    private Subscription        assinatura;
    private final int           prefetch;

    public AssinanteContrapressao(String nome, int prefetch) {
        this.nome = nome;
        this.prefetch = prefetch;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.assinatura = s;
        System.out.println(nome + " assinou, solicitando " + prefetch);
        solicitados.addAndGet(prefetch);
        s.request(prefetch);
    }

    @Override
    public void onNext(T item) {
        long recebidosCount = recebidos.incrementAndGet();
        System.out.println(nome + " recebeu [" + recebidosCount + "]: " + item);

        long restante = solicitados.decrementAndGet();
        if (restante <= prefetch / 2) {
            long paraSolicitar = prefetch;
            solicitados.addAndGet(paraSolicitar);
            assinatura.request(paraSolicitar);
            System.out.println(nome + " solicitou mais: " + paraSolicitar);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(nome + " erro: " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(nome + " concluído. Total recebido: " + recebidos.get());
    }

    public long getDescartados() {
        return descartados.get();
    }
}