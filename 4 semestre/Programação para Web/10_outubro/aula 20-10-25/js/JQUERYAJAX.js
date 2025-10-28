$.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts/1',     // URL do servidor/API
    method: 'GET', // Método HTTP (GET para buscar dados)
    dataType: 'json',

    // Função a ser executada ANTES da chamada ser enviada
    beforeSend: function() {
        $('#loading').show();       // Mostra a mensagem "Carregando..."
        $('#resultado').html('');   // Limpa resultados anteriores
    },

    // Função a ser executada se a chamada for bem-sucedida
    sucess: function(data) {
        // 'data' é o objeto JSON retornado pelo servidor
        console.log(data);      // Ótimo para depuração! Veja o console do navegador.

        // Montando uma saudação personalizada com base no horário atual
        // (Sexta-feira, 26/09/2025, noite em Brasília)
        let saudacao = "<h3>Boa noite! Sexta-feira em Brasília!</h3>"

        // Montando o HTML para exibir os dados recebidos
        let conteudo = `<p><strong>Saudação do Servidor:</strong> ${data.tittle}</p>
                        <p><strong>ID do Usuário:</strong> ${data.userId}</p>`;
        
        // Inserindo o conteúdo no nosso <div> de resultado
        $('#resultado').html(saudacao + conteudo);                
    },
    // Função a ser executada se ocorrer um erro na chamada
    error: function(xhr, status, error) {
        console.error("Erro na chamada AJAX:", status, error);
        $('#resultado').html('<p style="colo:red;">Ocorreu um erro ao buscar os dados.</p>')
    },
    // Função a ser executada AO FINAL da chamada (seja sucesso ou erro)
    complete: function() {
        $('#loading').hide();   // Esconde a mensagem de "Carregamento..."
    }
});