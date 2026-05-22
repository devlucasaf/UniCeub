// var tem escopo extensivo ao local de declaração

/**
function testarLet() {
    for (var j = 0; j < 5; j++) {
        console.log("Dentro do loop:", j);  // Funciona
    }
    // 'j' só existe dentro do bloco do loop
    console.log("Fora do loop", j); // ERRO! ReferenceError: j is not defined
}
*/

// let tem escopo de bloco, sem visível apenas no bloco

function testarLet() {
    for (let h = 0; h < 5; h++) {
        console.log("Dentro do loop:", h);  // Funciona
    }
    // 'h' só existe dentro do bloco do loop
    console.log("Fora do loop", h); // ERRO! ReferenceError: j is not defined
}