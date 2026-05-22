const readline = require("node:readline/promises");
const { stdin: input, stdout: output } = require("node:process");

function calcularMedia(nota1, nota2, nota3) {
    return (nota1 + nota2 + nota3) / 3;
}

function classificarSituacao(media) {
    if (media >= 7) {
        return "APROVADO";
    }

    if (media >= 5) {
        return "RECUPERACAO";
    }
    return "REPROVADO";
}

function validarNota(nota) {
    return !Number.isNaN(nota) && nota >= 0 && nota <= 10;
}

async function executarSistemaNotas() {
    const rl = readline.createInterface({ 
        input, 
        output 
    });

    const nomeAluno = (await rl.question("Digite o nome do aluno: ")).trim();

    if (!nomeAluno) {
        console.log("Erro: nome invalido.");
        rl.close();
        return;
    }

    const nota1 = Number(await rl.question("Digite a primeira nota (0 a 10): "));
    const nota2 = Number(await rl.question("Digite a segunda nota (0 a 10): "));
    const nota3 = Number(await rl.question("Digite a terceira nota (0 a 10): "));

    if (!validarNota(nota1) || !validarNota(nota2) || !validarNota(nota3)) {
        console.log("Erro: todas as notas devem ser numeros entre 0 e 10.");
        rl.close();
        return;
    }

    const media = calcularMedia(nota1, nota2, nota3);
    const situacao = classificarSituacao(media);

    console.log("\nRESULTADO DO ALUNO");
    console.log(`Nome: ${nomeAluno}`);
    console.log(`Notas: ${nota1}, ${nota2}, ${nota3}`);
    console.log(`Media: ${media.toFixed(2)}`);
    console.log(`Situacao: ${situacao}`);

    rl.close();
}

executarSistemaNotas();