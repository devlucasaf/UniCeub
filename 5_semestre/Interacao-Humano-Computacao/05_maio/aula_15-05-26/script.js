(function() {
    const form = document.getElementById('academicForm');
    
    function formatarDataBR(dataISO) {
        if (!dataISO) {
            return '';
        }
        
        const partes = dataISO.split('-');
        
        if (partes.length !== 3) {
            return dataISO;
        }
        return `${partes[2]}/${partes[1]}/${partes[0]}`;
    }
    
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const nome       = document.getElementById('nomeAluno').value.trim();
        const matricula  = document.getElementById('matricula').value.trim();
        const disciplina = document.getElementById('disciplina').value.trim();
        const dataRaw    = document.getElementById('dataAula').value;
        
        if (!nome) { 
            alert("Preencha o campo Nome do aluno."); 
            return; 
        }

        if (!matricula) { 
            alert("Preencha o número de Matrícula."); 
            return; 
        }

        if (!disciplina) { 
            alert("Informe a Disciplina."); 
            return; 
        }

        if (!dataRaw) { 
            alert("Selecione a Data."); 
            return; 
        }
        
        const dataFormatada = formatarDataBR(dataRaw);
        alert(`Acesso registrado!\n\nAluno(a): ${nome}\n🎓 Matrícula: ${matricula}\nDisciplina: ${disciplina}\nData: ${dataFormatada}\n\nBem-vindo(a) ao sistema!`);
        
        const btn = document.querySelector('.btn-entrar');
        btn.style.transform = 'scale(0.98)';
        setTimeout(() => btn.style.transform = '', 150);
    });
})();
