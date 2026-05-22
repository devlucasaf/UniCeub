import { efeitoAtivo } from './efeito.js';

export class Sinal {
    constructor(valorInicial) {
        this.valor = valorInicial;
        this.assinantes = new Set();
    }

    obter() {
        if (efeitoAtivo) {
            this.assinantes.add(efeitoAtivo);
            efeitoAtivo.dependencias.add(this);
        }
        return this.valor;
    }

    definir(novoValor) {
        if (this.valor !== novoValor) {
            this.valor = novoValor;
            this.notificar();
        }
    }

    notificar() {
        for (const efeito of this.assinantes) {
            efeito.agendar();
        }
    }
}