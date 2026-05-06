$(document).ready(function() {
    const API_URL = 'https://jsonplaceholder.typicode.com/posts/';
    
    let historicoPosts = [];
    
    const $postAtual = $('#post-atual');
    const $historicoPosts = $('#historico-posts');
    const $statusApi = $('#status-api');
    const $postIdInput = $('#post-id');
    
    function atualizarStatus(texto, tipo = 'info') {
        $statusApi
            .text(texto)
            .removeClass('carregando erro sucesso')
            .addClass(tipo);
    }
    
    function exibirPostAtual(post) {
        const postHTML = `
            <div class="post">
                <span class="post-id">ID: ${post.id}</span>
                <h4>${post.title}</h4>
                <p>${post.body}</p>
            </div>
        `;
        
        $postAtual.html(postHTML);
    }
    
    function atualizarHistorico() {
        if (historicoPosts.length === 0) {
            $historicoPosts.html('<p class="sem-dados">O histórico de posts aparecerá aqui.</p>');
            return;
        }
        
        let historicoHTML = '';
        
        const postsRecentes = historicoPosts.slice(-5).reverse();
        
        postsRecentes.forEach(post => {
            historicoHTML += `
                <div class="post">
                    <span class="post-id">ID: ${post.id}</span>
                    <h4>${post.title}</h4>
                </div>
            `;
        });
        
        $historicoPosts.html(historicoHTML);
    }
    
    function buscarPost(postId) {
        atualizarStatus('Carregando post...', 'carregando');
        
        $.ajax({
            url: API_URL + postId,
            method: 'GET',
            dataType: 'json',
            success: function(post) {
                historicoPosts.push(post);
                
                exibirPostAtual(post);
                atualizarHistorico();
                
                atualizarStatus(`Post ${post.id} carregado com sucesso!`, 'sucesso');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                let mensagemErro = 'Erro ao carregar o post. ';
                
                if (jqXHR.status === 404) {
                    mensagemErro += 'Post não encontrado.';
                } else {
                    mensagemErro += `Status: ${textStatus}`;
                }
                
                atualizarStatus(mensagemErro, 'erro');
                
                $postAtual.html(`
                    <div class="post">
                        <h4>Erro ao carregar post</h4>
                        <p>${mensagemErro}</p>
                        <p>Verifique se o ID do post está entre 1 e 100.</p>
                    </div>
                `);
            }
        });
    }
    
    $('#buscar-post').on('click', function() {
        const postId = $postIdInput.val();
        
        if (!postId || postId < 1 || postId > 100) {
            atualizarStatus('Por favor, insira um ID válido entre 1 e 100.', 'erro');
            return;
        }
        
        buscarPost(postId);
    });
    
    $('#buscar-aleatorio').on('click', function() {
        const postIdAleatorio = Math.floor(Math.random() * 100) + 1;
        
        $postIdInput.val(postIdAleatorio);
        
        buscarPost(postIdAleatorio);
    });
    
    $('#limpar').on('click', function() {
        historicoPosts = [];
        
        $postAtual.html('<p class="sem-dados">Nenhum post carregado ainda. Clique em "Buscar Post" para começar.</p>');
        $historicoPosts.html('<p class="sem-dados">O histórico de posts aparecerá aqui.</p>');
        
        atualizarStatus('Pronto para buscar dados da API.', 'info');
        
        $postIdInput.val('1');
    });
    
    $postIdInput.on('keypress', function(e) {
        if (e.which === 13) { 
            $('#buscar-post').click();
        }
    });
    
    buscarPost(1);
});
