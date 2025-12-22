// Aguarda o carregamento completo do documento
$(document).ready(function() {
    // URL base da API
    const API_URL = 'https://jsonplaceholder.typicode.com/posts/';
    
    // Array para armazenar o histórico de posts
    let historicoPosts = [];
    
    // Elementos da página
    const $postAtual = $('#post-atual');
    const $historicoPosts = $('#historico-posts');
    const $statusApi = $('#status-api');
    const $postIdInput = $('#post-id');
    
    // Função para atualizar o status
    function atualizarStatus(texto, tipo = 'info') {
        $statusApi
            .text(texto)
            .removeClass('carregando erro sucesso')
            .addClass(tipo);
    }
    
    // Função para exibir post na área atual
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
    
    // Função para atualizar o histórico
    function atualizarHistorico() {
        if (historicoPosts.length === 0) {
            $historicoPosts.html('<p class="sem-dados">O histórico de posts aparecerá aqui.</p>');
            return;
        }
        
        let historicoHTML = '';
        
        // Exibe apenas os últimos 5 posts do histórico
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
    
    // Função para buscar um post da API
    function buscarPost(postId) {
        // Atualiza o status para "carregando"
        atualizarStatus('Carregando post...', 'carregando');
        
        // Faz a requisição AJAX usando jQuery
        $.ajax({
            url: API_URL + postId,
            method: 'GET',
            dataType: 'json',
            success: function(post) {
                // Adiciona o post ao histórico
                historicoPosts.push(post);
                
                // Exibe o post atual e atualiza o histórico
                exibirPostAtual(post);
                atualizarHistorico();
                
                // Atualiza o status
                atualizarStatus(`Post ${post.id} carregado com sucesso!`, 'sucesso');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // Trata erros na requisição
                let mensagemErro = 'Erro ao carregar o post. ';
                
                if (jqXHR.status === 404) {
                    mensagemErro += 'Post não encontrado.';
                } else {
                    mensagemErro += `Status: ${textStatus}`;
                }
                
                atualizarStatus(mensagemErro, 'erro');
                
                // Exibe mensagem de erro na área do post atual
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
    
    // Evento para o botão "Buscar Post"
    $('#buscar-post').on('click', function() {
        const postId = $postIdInput.val();
        
        // Validação básica
        if (!postId || postId < 1 || postId > 100) {
            atualizarStatus('Por favor, insira um ID válido entre 1 e 100.', 'erro');
            return;
        }
        
        buscarPost(postId);
    });
    
    // Evento para o botão "Post Aleatório"
    $('#buscar-aleatorio').on('click', function() {
        // Gera um ID aleatório entre 1 e 100
        const postIdAleatorio = Math.floor(Math.random() * 100) + 1;
        
        // Atualiza o campo de entrada
        $postIdInput.val(postIdAleatorio);
        
        // Busca o post aleatório
        buscarPost(postIdAleatorio);
    });
    
    // Evento para o botão "Limpar Resultados"
    $('#limpar').on('click', function() {
        // Limpa o histórico
        historicoPosts = [];
        
        // Limpa as exibições
        $postAtual.html('<p class="sem-dados">Nenhum post carregado ainda. Clique em "Buscar Post" para começar.</p>');
        $historicoPosts.html('<p class="sem-dados">O histórico de posts aparecerá aqui.</p>');
        
        // Reseta o status
        atualizarStatus('Pronto para buscar dados da API.', 'info');
        
        // Reseta o campo de entrada
        $postIdInput.val('1');
    });
    
    // Permite pressionar Enter no campo de ID para buscar
    $postIdInput.on('keypress', function(e) {
        if (e.which === 13) { // Enter key
            $('#buscar-post').click();
        }
    });
    
    // Busca um post inicial ao carregar a página
    buscarPost(1);
});
