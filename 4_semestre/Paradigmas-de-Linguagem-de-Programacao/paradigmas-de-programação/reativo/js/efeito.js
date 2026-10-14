export let  efeitoAtivo         = null;
const       efeitosPendentes    = new Set();
let         isLotePendente      = false;

function executarEfeitosPendentes() {
    if (!isLotePendente) {
        return;
    }

    const efeitosParaExecutar = Array.from(efeitosPendentes);
    efeitosPendentes.clear();
    isLotePendente = false;

    for (const efeito of efeitosParaExecutar) {
        if (efeito.deveExecutar) {
            efeito.executar();
        }
    }
}

function agendarEfeito(efeito) {
    efeitosPendentes.add(efeito);
    if (!isLotePendente) {
        isLotePendente = true;
        Promise.resolve().then(executarEfeitosPendentes);
    }
}

export class Efeito {
    constructor(fn) {
        this.fn = fn;
        this.dependencias = new Set();
        this.deveExecutar = true;
        this.executar();
    }

    executar() {
        this.limpar();
        efeitoAtivo = this;
        this.deveExecutar = false;
        this.fn();
        efeitoAtivo = null;
    }

    limpar() {
        for (const dep of this.dependencias) {
            dep.assinantes.delete(this);
        }
        this.dependencias.clear();
    }

    agendar() {
        this.deveExecutar = true;
        agendarEfeito(this);
    }
}

export function lote(fn) {
    isLotePendente = true;
    fn();
    executarEfeitosPendentes();
}