// Aguarda o carregamento completo do documento
$(document).ready(function() {
    // Array de cores para alternar
    const cores = [
        '#E3F2FD', // Azul claro
        '#F3E5F5', // Roxo claro
        '#E8F5E9', // Verde claro
        '#FFF3E0', // Laranja claro
        '#FCE4EC'  // Rosa claro
    ];
    
    let indiceCores = {};
    let modoAleatorio = false;
    
    // Inicializa os índices de cores para cada elemento
    $('.elemento').each(function() {
        const id = $(this).attr('id');
        indiceCores[id] = 0;
    });
    
    // Função para alternar a cor de fundo do elemento
    function alternarCor(elemento) {
        const id = $(elemento).attr('id');
        
        if (modoAleatorio) {
            // Modo aleatório
            const corAleatoria = '#' + Math.floor(Math.random()*16777215).toString(16);
            $(elemento).css('background-color', corAleatoria);
        } else {
            // Modo sequencial
            indiceCores[id] = (indiceCores[id] + 1) % cores.length;
            $(elemento).css('background-color', cores[indiceCores[id]]);
        }
        
        // Atualiza o texto de status
        const nomeElemento = $(elemento).find('h3').text();
        const corAtual = $(elemento).css('background-color');
        $('#status-text').text(`${nomeElemento} alterado para cor: ${corAtual}`);
        
        // Adiciona uma borda para destacar
        $(elemento).css('border-color', '#2196F3');
    }
    
    // Função para resetar as cores dos elementos
    function resetarCores() {
        $('.elemento').css({
            'background-color': '#f8f9fa',
            'border-color': 'transparent'
        });
        
        // Reseta os índices
        $('.elemento').each(function() {
            const id = $(this).attr('id');
            indiceCores[id] = 0;
        });
        
        $('#status-text').text('Cores resetadas para o estado original.');
    }
    
    // Adiciona evento de mouseenter para cada elemento
    $('.elemento').on('mouseenter', function() {
        alternarCor(this);
    });
    
    // Adiciona evento de clique para o botão de reset
    $('#botao-reset').on('click', resetarCores);
    
    // Adiciona evento de clique para o botão de alternar modo
    $('#botao-alternar').on('click', function() {
        modoAleatorio = !modoAleatorio;
        const modoTexto = modoAleatorio ? "Aleatório" : "Sequencial";
        $(this).text(`Modo: ${modoTexto}`);
        
        $('#status-text').text(`Modo alterado para: ${modoTexto}`);
    });
    
    // Altera o texto do botão de alternar modo
    $('#botao-alternar').text('Modo: Sequencial');
});
