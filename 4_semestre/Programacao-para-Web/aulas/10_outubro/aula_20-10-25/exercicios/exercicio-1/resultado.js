function getQueryParams() {
    const params = {};
    location.search.substring(1).split('&').forEach(pair => {
        const [key, value] = pair.split('=');
        params[key] = decodeURIComponent(value);
    });
    return params;
}

const params = getQueryParams();
const altura = parseFloat(params.altura);
const massa = parseFloat(params.massa);

if (!isNaN(altura) && !isNaN(massa) && altura > 0) {
    const imc = massa / (altura * altura);
    document.getElementById('resultado').innerText = `Seu IMC é: ${imc.toFixed(2)}`;
} else {
    document.getElementById('resultado').innerText = 'Dados inválidos. Por favor, volte e tente novamente.';
}
