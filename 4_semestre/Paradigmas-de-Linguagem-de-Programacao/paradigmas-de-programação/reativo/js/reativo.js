import { Sinal } from './sinal.js';
import { efeitoAtivo } from './efeito.js';

class ObjetoReativo {
    constructor(obj) {
        this.proxies        = new WeakMap();
        this.brutoParaProxy = new WeakMap();
        this.proxyParaBruto = new WeakMap();
        return this.criarReativo(obj);
    }

    criarReativo(obj) {
        if (this.brutoParaProxy.has(obj)) {
            return this.brutoParaProxy.get(obj);
        }

        if (this.proxyParaBruto.has(obj)) {
            return obj;
        }

        const proxy = new Proxy(obj, {
            get: (alvo, chave, receptor) => {
                const resultado = Reflect.get(alvo, chave, receptor);
                if (typeof resultado === 'object' && resultado !== null) {
                    return this.criarReativo(resultado);
                }
                const sinal = this.obterSinal(alvo, chave);
                
                if (efeitoAtivo) {
                    sinal.obter();
                }
                return sinal.obter();
            },
            set: (alvo, chave, valor, receptor) => {
                const valorAntigo = Reflect.get(alvo, chave, receptor);
                if (valorAntigo !== valor) {
                    const sinal = this.obterSinal(alvo, chave);
                    sinal.definir(valor);
                    Reflect.set(alvo, chave, valor, receptor);
                }
                return true;
            }
        });

        this.brutoParaProxy.set(obj, proxy);
        this.proxyParaBruto.set(proxy, obj);
        return proxy;
    }

    obterSinal(alvo, chave) {
        if (!alvo.__sinais) {
            Object.defineProperty(alvo, '__sinais', {
                value:          new Map(),
                enumerable:     false,
                configurable:   false,
                writable:       false
            });
        }
        
        if (!alvo.__sinais.has(chave)) {
            alvo.__sinais.set(chave, new Sinal(Reflect.get(alvo, chave)));
        }
        return alvo.__sinais.get(chave);
    }
}

export function reativo(obj) {
    return new ObjetoReativo(obj);
}