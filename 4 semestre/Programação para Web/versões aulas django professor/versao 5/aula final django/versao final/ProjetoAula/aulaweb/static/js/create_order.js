document.addEventListener('DOMContentLoaded', function() {

    const addItemBtn = document.getElementById('add-item');
    
    if (addItemBtn) {
        addItemBtn.addEventListener('click', function() {
            adicionarNovoItem();
        });
    }
    
    function adicionarNovoItem() {
        let formsetArea = document.getElementById('formset-area');
        let totalForms = document.getElementById('id_itempedido_set-TOTAL_FORMS');
        
        if (!formsetArea || !totalForms) {
            console.error('Elementos do formset não encontrados');
            return;
        }
        
        let formNum = parseInt(totalForms.value);
        
        if (formsetArea.children.length === 0) {
            console.error('Nenhum formulário inicial para clonar');
            return;
        }
        
        let newForm = formsetArea.children[0].cloneNode(true);
        
        const regex = new RegExp(`-\\d+-`, 'g');
        newForm.innerHTML = newForm.innerHTML.replace(regex, `-${formNum}-`);
        
        const inputs = newForm.querySelectorAll('input, select, textarea');
        inputs.forEach(input => {
            if (input.type !== 'hidden') {
                input.value = '';
            }
            
            input.classList.remove('error');
        });
        
        const errorMessages = newForm.querySelectorAll('.errorlist');
        errorMessages.forEach(error => error.remove());
        
        formsetArea.appendChild(newForm);
        
        totalForms.value = formNum + 1;
        
        const event = new CustomEvent('itemAdicionado', { 
            detail: { numeroItem: formNum } 
        });
        document.dispatchEvent(event);
        
        console.log(`Novo item adicionado. Total: ${totalForms.value}`);
    }
    
});
