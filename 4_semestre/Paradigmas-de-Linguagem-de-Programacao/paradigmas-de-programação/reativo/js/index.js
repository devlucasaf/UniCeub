import { Sinal }        from './sinal.js';
import { Efeito, lote } from './efeito.js';
import { Computado }    from './computado.js';
import { reativo }      from './reativo.js';

export function referencia(valorInicial) {
    const sinal = new Sinal(valorInicial);
    return {
        get valor() {
            return sinal.obter();
        },
        set valor(novoValor) {
            sinal.definir(novoValor);
        }
    };
}

export function computado(fn) {
    return new Computado(fn);
}

export function observarEfeito(fn) {
    return new Efeito(fn);
}

export { reativo, lote };