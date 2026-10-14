import { Efeito, efeitoAtivo } from './efeito.js';

export class Computado {
    constructor(funcaoComputar) {
        this.funcaoComputar = funcaoComputar;
        this.valor = undefined;
        this.dependencias = new Set();
        this.isSujo = true;
        this.assinantes = undefined;
        this.efeito = null;
        this.inicializar();
    }

    inicializar() {
        this.efeito = new Efeito(() => {
            this.isSujo = true;
            const novoValor = this.funcaoComputar();
            if (this.valor !== novoValor) {
                this.valor = novoValor;
                this.notificar();
            }
            this.isSujo = false;
        });
    }

    obter() {
        if (efeitoAtivo) {
            efeitoAtivo.dependencias.add(this);
            this.assinantes = this.assinantes || new Set();
            this.assinantes.add(efeitoAtivo);
        }

        if (this.isSujo) {
            this.valor = this.funcaoComputar();
            this.isSujo = false;
        }
        return this.valor;
    }

    notificar() {
        if (this.assinantes) {
            for (const efeito of this.assinantes) {
                efeito.agendar();
            }
        }
    }
}