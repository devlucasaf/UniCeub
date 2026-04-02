let display = document.getElementById("display");
let botoes = document.querySelectorAll("button");

let numero1 = 0;
let numero2 = 0;
let operador = "";
let novoNumero = true;

botoes.forEach(botao => {
    botao.addEventListener("click", () => {
        let valor = botao.textContent;

        if (!isNaN(valor)) {
            if (novoNumero) {
                display.textContent = valor;
                novoNumero = false;
            } 
            
            else {
                display.textContent += valor;
            }
        } 
        
        else if (valor === ".") {
            if (novoNumero) {
                display.textContent = "0.";
                novoNumero = false;
            } else if (!display.textContent.includes(".")) {
                display.textContent += ".";
            }
        } 
        
        else if (valor === "+" || valor === "-" || valor === "*" || valor === "/") {
            operador = valor;
            numero1 = parseFloat(display.textContent);
            novoNumero = true;
        } 
        
        else if (valor === "=") {
            numero2 = parseFloat(display.textContent);
            let resultado = calcular();
            display.textContent = formatar(resultado);
            novoNumero = true;
        } 
        
        else if (valor === "C") {
            display.textContent = "0";
            numero1 = 0;
            numero2 = 0;
            operador = "";
            novoNumero = true;
        }
    });
});

function calcular() {
    if (operador === "+") {
        return numero1 + numero2;
    }

    if (operador === "-") {
        return numero1 - numero2;
    }

    if (operador === "*") {
        return numero1 * numero2;
    }

    if (operador === "/") {
        if (numero2 === 0) {
            return 0;
        }
        return numero1 / numero2;
    }
    return numero2;
}

function formatar(valor) {
    if (Number.isInteger(valor)) {
        return valor.toString();
    }
    return valor.toString();
}