// Aguarda o carregamento completo do DOM
document.addEventListener('DOMContentLoaded', function() {
    // Verifica se estamos na página de resultado
    if (window.location.pathname.includes('resultado.html') || 
        window.location.pathname.endsWith('resultado.html')) {
        calcularResultadoIMC();
    } else {
        // Estamos na página inicial (formulário)
        configurarFormulario();
    }
});

// Configura o formulário na página inicial
function configurarFormulario() {
    const form = document.getElementById('imc-form');
    
    if (form) {
        // Adiciona validação personalizada antes do envio
        form.addEventListener('submit', function(event) {
            const altura = document.getElementById('altura').value;
            const massa = document.getElementById('massa').value;
            
            // Validação básica
            if (!altura || altura <= 0 || altura > 2.5) {
                alert('Por favor, insira uma altura válida entre 0.5m e 2.5m');
                event.preventDefault();
                return;
            }
            
            if (!massa || massa <= 0 || massa > 300) {
                alert('Por favor, insira uma massa válida entre 20kg e 300kg');
                event.preventDefault();
                return;
            }
            
            // Se passar na validação, o formulário é enviado normalmente
            // Os parâmetros serão passados via URL (método GET)
        });
        
        // Adiciona máscaras aos campos de entrada (opcional)
        const alturaInput = document.getElementById('altura');
        const massaInput = document.getElementById('massa');
        
        // Permite apenas números e ponto decimal
        [alturaInput, massaInput].forEach(input => {
            input.addEventListener('input', function() {
                this.value = this.value.replace(/[^0-9.]/g, '');
                
                // Remove pontos extras, permitindo apenas um
                const parts = this.value.split('.');
                if (parts.length > 2) {
                    this.value = parts[0] + '.' + parts.slice(1).join('');
                }
            });
        });
    }
}

// Calcula e exibe o resultado do IMC na página de resultado
function calcularResultadoIMC() {
    // Obtém os parâmetros da URL
    const urlParams = new URLSearchParams(window.location.search);
    const altura = parseFloat(urlParams.get('altura'));
    const massa = parseFloat(urlParams.get('massa'));
    
    // Verifica se os parâmetros são válidos
    if (!altura || !massa || isNaN(altura) || isNaN(massa) || altura <= 0 || massa <= 0) {
        exibirErro();
        return;
    }
    
    // Calcula o IMC
    const imc = massa / (altura * altura);
    
    // Determina a classificação
    const classificacao = classificarIMC(imc);
    
    // Exibe o resultado após um pequeno delay para simular processamento
    setTimeout(() => {
        exibirResultado(altura, massa, imc, classificacao);
    }, 800);
}

// Classifica o IMC de acordo com os valores padrão
function classificarIMC(imc) {
    if (imc < 18.5) {
        return {
            categoria: "Baixo peso",
            descricao: "Você está abaixo do peso ideal. Considere buscar orientação nutricional para uma dieta balanceada.",
            cor: "#ffeb3b",
            dica: "Procure um nutricionista para avaliar sua dieta. Foque em alimentos nutritivos e calóricos como abacate, nozes e cereais integrais."
        };
    } else if (imc < 25) {
        return {
            categoria: "Peso normal",
            descricao: "Parabéns! Seu peso está dentro da faixa considerada saudável para sua altura.",
            cor: "#4caf50",
            dica: "Mantenha seus hábitos saudáveis! Continue com uma dieta balanceada e pratique atividades físicas regularmente."
        };
    } else if (imc < 30) {
        return {
            categoria: "Sobrepeso",
            descricao: "Você está acima do peso ideal. Considere ajustes na dieta e aumento da atividade física.",
            cor: "#ff9800",
            dica: "Considere aumentar a atividade física e revisar sua alimentação. Pequenas mudanças como reduzir bebidas açucaradas podem fazer diferença."
        };
    } else if (imc < 35) {
        return {
            categoria: "Obesidade Grau I",
            descricao: "Você está na faixa de obesidade grau I. Recomenda-se acompanhamento médico e nutricional.",
            cor: "#f44336",
            dica: "É recomendado buscar orientação médica e nutricional. Foque em uma alimentação equilibrada e aumente gradualmente a atividade física."
        };
    } else if (imc < 40) {
        return {
            categoria: "Obesidade Grau II",
            descricao: "Você está na faixa de obesidade grau II. É importante buscar acompanhamento de saúde.",
            cor: "#d32f2f",
            dica: "Consulte um médico para avaliação. Um plano de saúde supervisionado por profissionais é recomendado para sua segurança."
        };
    } else {
        return {
            categoria: "Obesidade Grau III",
            descricao: "Você está na faixa de obesidade grau III. É fundamental buscar acompanhamento médico especializado.",
            cor: "#b71c1c",
            dica: "É importante consultar um médico para avaliação completa. O acompanhamento profissional é essencial para sua saúde."
        };
    }
}

// Exibe o resultado na página
function exibirResultado(altura, massa, imc, classificacao) {
    const resultContent = document.getElementById('result-content');
    
    // Formata o IMC com 1 casa decimal
    const imcFormatado = imc.toFixed(1);
    
    // Atualiza o conteúdo do resultado
    resultContent.innerHTML = `
        <div class="imc-value" style="color: ${classificacao.cor}">${imcFormatado}</div>
        <div class="imc-classification" style="color: ${classificacao.cor}">${classificacao.categoria}</div>
        <p class="imc-description">${classificacao.descricao}</p>
    `;
    
    // Atualiza a interpretação
    document.getElementById('interpretation-text').textContent = classificacao.descricao;
    
    // Atualiza as dicas de saúde
    document.getElementById('health-tip').textContent = classificacao.dica;
    
    // Atualiza os detalhes do cálculo
    document.getElementById('detail-altura').textContent = `${altura} m`;
    document.getElementById('detail-massa').textContent = `${massa} kg`;
    document.getElementById('detail-calculo').textContent = `${massa} / (${altura} × ${altura}) = ${imcFormatado}`;
    
    // Altera a cor da borda do card de resultado
    const resultCard = document.querySelector('.result-card > div');
    if (resultCard) {
        resultCard.style.borderTopColor = classificacao.cor;
    }
}

// Exibe mensagem de erro se os parâmetros não forem válidos
function exibirErro() {
    const resultContent = document.getElementById('result-content');
    
    resultContent.innerHTML = `
        <div class="error-message">
            <i class="fas fa-exclamation-triangle" style="font-size: 4rem; color: #e74c3c; margin-bottom: 20px;"></i>
            <h3 style="color: #e74c3c;">Dados inválidos ou não informados</h3>
            <p>Não foi possível calcular o IMC com os parâmetros fornecidos.</p>
            <p>Por favor, retorne à <a href="index.html" style="color: #3498db; font-weight: bold;">página inicial</a> e insira valores válidos para altura e massa.</p>
        </div>
    `;
    
    document.getElementById('interpretation-text').textContent = "Os dados fornecidos não são válidos para cálculo do IMC.";
    document.getElementById('health-tip').textContent = "Por favor, retorne à página inicial e insira valores válidos para altura (entre 0.5m e 2.5m) e massa (entre 20kg e 300kg).";
}
