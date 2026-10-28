document.getElementById('imcForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    const altura = parseFloat(document.getElementById('altura').value);
    const massa = parseFloat(document.getElementById('massa').value);

    const url = `resultado.html?altura=${altura}&massa=${massa}`;
    window.location.href = url;
});
