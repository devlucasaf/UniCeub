import { reativo, referencia, computado, observarEfeito, lote } from './index.js';
import { Sinal } from './sinal.js';

let contador    = referencia(0);
let dobro       = computado(() => contador.valor * 2);
let quadruplo   = computado(() => dobro.obter() * 2);

observarEfeito(() => {
    console.log(`Contador: ${contador.valor}, Dobro: ${dobro.obter()}, Quadruplo: ${quadruplo.obter()}`);
});

contador.valor = 1;
contador.valor = 2;
contador.valor = 3;

let pessoa = reativo({ 
    nome: 'Alice', 
    idade: 30 
});
let anoNascimento = computado(() => new Date().getFullYear() - pessoa.idade);

observarEfeito(() => {
    console.log(`${pessoa.nome} tem ${pessoa.idade} anos, nasceu em ${anoNascimento.obter()}`);
});

pessoa.idade = 31;
pessoa.nome = 'Bob';

let tarefas = reativo([
    { 
        texto: 'Aprender Reatividade', 
        concluida: false 
    },
    { 
        texto: 'Construir um projeto', 
        concluida: false 
    }
]);

let tarefasAtivas = computado(() => tarefas.filter(t => !t.concluida));
let tarefasConcluidas = computado(() => tarefas.filter(t => t.concluida));

observarEfeito(() => {
    console.log(`Ativas: ${tarefasAtivas.obter().length}, Concluídas: ${tarefasConcluidas.obter().length}`);
});

tarefas[0].concluida = true;
tarefas.push({ 
    texto: 'Escrever documentação', 
    concluida: false 
});

let a       = referencia(10);
let b       = referencia(20);
let soma    = computado(() => a.valor + b.valor);
let produto = computado(() => a.valor * b.valor);

observarEfeito(() => {
    console.log(`Soma: ${soma.obter()}, Produto: ${produto.obter()}`);
});

a.valor = 15;
b.valor = 25;

let estadoProfundo = reativo({
    usuario: {
        perfil: {
            nome: 'João',
            configuracoes: { 
                tema: 'escuro' 
            }
        }
    }
});

observarEfeito(() => {
    console.log(`Tema: ${estadoProfundo.usuario.perfil.configuracoes.tema}`);
});

estadoProfundo.usuario.perfil.configuracoes.tema = 'claro';

function limpezaManual() {
    let parar = observarEfeito(() => {
        console.log(`Efeito que será interrompido após 1 segundo: ${contador.valor}`);
    });
    setTimeout(() => {
        parar.limpar();
        console.log('Efeito interrompido');
    }, 1000);
}
limpezaManual();

let contador2 = referencia(0);
let incrementar = () => contador2.valor++;
setInterval(() => incrementar(), 2000);

let dadosAssincronos = referencia(null);
let carregando = referencia(false);

observarEfeito(async () => {
    if (carregando.valor) {
        return;
    }
    carregando.valor = true;
    console.log('Buscando dados...');
    await new Promise(r => setTimeout(r, 1000));

    dadosAssincronos.valor = { 
        dados: 'Dados de exemplo', 
        timestamp: Date.now() 
    };
    carregando.valor = false;
    console.log('Dados obtidos:', dadosAssincronos.valor);
});

let condicao = referencia(true);
let valorCondicional = computado(() => condicao.valor ? 'Sim' : 'Não');

observarEfeito(() => {
    console.log(`Condição é: ${valorCondicional.obter()}`);
});
condicao.valor = false;

let lista = reativo([1, 2, 3]);
let somaLista = computado(() => lista.reduce((acc, val) => acc + val, 0));

observarEfeito(() => {
    console.log(`Lista: ${lista.join(', ')}, Soma: ${somaLista.obter()}`);
});

lista.push(4);
lista[0] = 10;
lista.splice(1, 1);

let objetoAninhado = reativo({ 
    aninhado: { 
        profundo: { 
            valor: 42 
        } 
    } 
});

observarEfeito(() => {
    console.log(`Valor profundamente aninhado: ${objetoAninhado.aninhado.profundo.valor}`);
});

objetoAninhado.aninhado.profundo.valor = 100;

let mapaExemplo = reativo(new Map([['chave1', 'valor1'], ['chave2', 'valor2']]));
observarEfeito(() => {
    console.log(`Tamanho do mapa: ${mapaExemplo.size}, chave1: ${mapaExemplo.get('chave1')}`);
});

mapaExemplo.set('chave3', 'valor3');
mapaExemplo.set('chave1', 'novo valor');

let conjuntoExemplo = reativo(new Set([1, 2, 3]));
observarEfeito(() => {
    console.log(`Tamanho do conjunto: ${conjuntoExemplo.size}, contém 2: ${conjuntoExemplo.has(2)}`);
});

conjuntoExemplo.add(4);
conjuntoExemplo.delete(1);

let dataSinal = referencia(new Date());
observarEfeito(() => {
    console.log(`Data atual: ${dataSinal.valor.toISOString()}`);
});

setTimeout(() => dataSinal.valor = new Date(), 500);

let computadoComplexo = computado(() => {
    let c = contador.valor;
    let d = dobro.obter();
    let p = pessoa.idade;
    return { 
        c, 
        d, 
        p, 
        total: c + d + p 
    };
});

observarEfeito(() => {
    let resultado = computadoComplexo.obter();
    console.log(`Computado complexo: ${JSON.stringify(resultado)}`);
});

contador.valor = 5;
pessoa.idade = 35;

let lancaraErro = computado(() => {
    if (contador.valor < 0) {
        throw new Error('Contador não pode ser negativo');
    }
    return contador.valor * 10;
});

observarEfeito(() => {
    try {
        console.log(`Computado seguro: ${lancaraErro.obter()}`);
    } catch (e) {
        console.log(`Erro capturado: ${e.message}`);
    }
});
contador.valor = -1;

let loteExemplo1 = referencia(0);
let loteExemplo2 = referencia(0);
let somaLote = computado(() => loteExemplo1.valor + loteExemplo2.valor);
observarEfeito(() => {
    console.log(`Soma em lote: ${somaLote.obter()}`);
});

lote(() => {
    loteExemplo1.valor = 10;
    loteExemplo2.valor = 20;
});

let efeitoLimpo = observarEfeito(() => {
    console.log(`Teste de limpeza: ${contador.valor}`);
    return () => console.log('Efeito limpo');
});
efeitoLimpo.limpar();

let muitosSinais = [];

for (let i = 0; i < 100; i++) {
    muitosSinais.push(referencia(i));
}

let somaMuitos = computado(() => muitosSinais.reduce((acc, s) => acc + s.valor, 0));
observarEfeito(() => {
    console.log(`Soma de 100 sinais: ${somaMuitos.obter()}`);
});

muitosSinais[50].valor = 999;

let cicloA = referencia(1);
let cicloB = computado(() => cicloA.valor + 1);
let cicloC = computado(() => cicloB.obter() + 1);
observarEfeito(() => {
    console.log(`Cadeia cíclica: A=${cicloA.valor}, B=${cicloB.obter()}, C=${cicloC.obter()}`);
});

cicloA.valor = 10;

let testePerformance = referencia(0);
let perfComputado = computado(() => {
    let soma = 0;
    for (let i = 0; i < 1000; i++) {
        soma += testePerformance.valor;
    }
    return soma;
});

observarEfeito(() => {
    console.log(`Resultado computado performance: ${perfComputado.obter()}`);
});

testePerformance.valor = 5;

observarEfeito(() => {
    console.log(`Este efeito executa imediatamente: ${contador.valor}`);
});

let efeitoParar = observarEfeito(() => {
    console.log(`Este efeito será interrompido após 2 segundos: ${Math.random()}`);
});

setTimeout(() => efeitoParar.limpar(), 2000);

let derivadoDeRef = computado(() => {
    let base = contador.valor;
    return { 
        quadrado: base * base, 
        cubo: base * base * base 
    };
});

observarEfeito(() => {
    let { quadrado, cubo } = derivadoDeRef.obter();
    console.log(`Quadrado: ${quadrado}, Cubo: ${cubo}`);
});
contador.valor = 4;

let arrayReativo    = reativo([{ val: 1 }, { val: 2 }]);
let arrayComputado  = computado(() => arrayReativo.map(item => item.val * 2));

observarEfeito(() => {
    console.log(`Array computado: ${arrayComputado.obter().join(', ')}`);
});

arrayReativo[0].val = 10;
arrayReativo.push({ val: 3 });

let computadoPreguicoso = computado(() => {
    console.log('Computado preguiçoso recalculado');
    return contador.valor * 100;
});

console.log(`Valor preguiçoso: ${computadoPreguicoso.obter()}`);
contador.valor = 2;
console.log(`Valor preguiçoso novamente: ${computadoPreguicoso.obter()}`);

let aninhadoReativo = reativo({ 
    nivel1: {
        nivel2: { 
            nivel3: referencia(42) 
        } 
    } 
});

observarEfeito(() => {
    console.log(`Ref aninhado dentro de reativo: ${aninhadoReativo.nivel1.nivel2.nivel3.valor}`);
});
aninhadoReativo.nivel1.nivel2.nivel3.valor = 99;

let sinalComIgualdade = new Sinal(0);

sinalComIgualdade.definir(0);
sinalComIgualdade.definir(0);
sinalComIgualdade.definir(1);

observarEfeito(() => {
    console.log(`Sinal com verificação de igualdade: ${sinalComIgualdade.obter()}`);
});

console.log('Sistema de reatividade totalmente operacional');