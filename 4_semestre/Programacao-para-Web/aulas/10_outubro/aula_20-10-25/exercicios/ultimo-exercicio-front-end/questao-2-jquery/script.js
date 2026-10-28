$(document).ready(function() {
    const cores = [
        '#E3F2FD', 
        '#F3E5F5', 
        '#E8F5E9', 
        '#FFF3E0', 
        '#FCE4EC'  
    ];
    
    let indiceCores = {};
    let modoAleatorio = false;
    
    $('.elemento').each(function() {
        const id = $(this).attr('id');
        indiceCores[id] = 0;
    });
    
    function alternarCor(elemento) {
        const id = $(elemento).attr('id');
        
        if (modoAleatorio) {
            const corAleatoria = '#' + Math.floor(Math.random()*16777215).toString(16);
            $(elemento).css('background-color', corAleatoria);
        } else {
            indiceCores[id] = (indiceCores[id] + 1) % cores.length;
            $(elemento).css('background-color', cores[indiceCores[id]]);
        }
        
        const nomeElemento = $(elemento).find('h3').text();
        const corAtual = $(elemento).css('background-color');
        $('#status-text').text(`${nomeElemento} alterado para cor: ${corAtual}`);
        
        $(elemento).css('border-color', '#2196F3');
    }
    
    function resetarCores() {
        $('.elemento').css({
            'background-color': '#f8f9fa',
            'border-color': 'transparent'
        });
        
        $('.elemento').each(function() {
            const id = $(this).attr('id');
            indiceCores[id] = 0;
        });
        
        $('#status-text').text('Cores resetadas para o estado original.');
    }
    
    $('.elemento').on('mouseenter', function() {
        alternarCor(this);
    });
    
    $('#botao-reset').on('click', resetarCores);
    
    $('#botao-alternar').on('click', function() {
        modoAleatorio = !modoAleatorio;
        const modoTexto = modoAleatorio ? "Aleatório" : "Sequencial";
        $(this).text(`Modo: ${modoTexto}`);
        
        $('#status-text').text(`Modo alterado para: ${modoTexto}`);
    });
    
    $('#botao-alternar').text('Modo: Sequencial');
});
