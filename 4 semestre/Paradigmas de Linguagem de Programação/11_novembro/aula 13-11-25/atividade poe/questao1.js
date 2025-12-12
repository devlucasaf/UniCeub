// Simulação dos "controles" do formulário
let edtNumero1 = "";
let edtNumero2 = "";

// Função equivalente ao evento btnSomarClick
function btnSomarClick() {
    const n1 = parseInt(edtNumero1) || 0;
    const n2 = parseInt(edtNumero2) || 0;
    const soma = n1 + n2;

    console.log("Resultado da soma: " + soma);
}

// Função equivalente ao evento btnLimparClick
function btnLimparClick() {
    edtNumero1 = "";
    edtNumero2 = "";
    console.log("Campos limpos. Foco em edtNumero1.");
}

// ----------------------
// Exemplo de uso:
edtNumero1 = "10";
edtNumero2 = "20";
btnSomarClick();   // Resultado da soma: 30

btnLimparClick();  // Campos limpos. Foco em edtNumero1.
