document.addEventListener("DOMContentLoaded", () => {
    const inputTarefa = document.getElementById("novaTarefa");
    const btnAdicionar = document.getElementById("btnAdicionar");
    const listaTarefas = document.getElementById("listaTarefas");

    let tarefas = JSON.parse(localStorage.getItem("tarefas")) || [];

    function salvarTarefas() {
        localStorage.setItem("tarefas", JSON.stringify(tarefas));
    }

    function renderizarTarefas() {
        listaTarefas.innerHTML = "";

        tarefas.forEach((tarefa, index) => {
            const li = document.createElement("li");
            
            const span = document.createElement("span");
            span.textContent = tarefa.texto;
            
            if (tarefa.concluida) {
                span.classList.add("concluida");
            }
            
            span.addEventListener("click", () => {
                tarefas[index].concluida = !tarefas[index].concluida;
                salvarTarefas();
                renderizarTarefas(); 
            });

            const btnRemover = document.createElement("button");
            btnRemover.textContent = "Remover";
            btnRemover.addEventListener("click", () => {
                tarefas.splice(index, 1); 
                salvarTarefas();
                renderizarTarefas(); 
            });

            li.appendChild(span);
            li.appendChild(btnRemover);
            listaTarefas.appendChild(li);
        });
    }

    btnAdicionar.addEventListener("click", () => {
        const texto = inputTarefa.value.trim();
        if (texto !== "") {
            tarefas.push({ texto: texto, concluida: false });
            inputTarefa.value = ""; 
            salvarTarefas();
            renderizarTarefas();
        }
    });

    inputTarefa.addEventListener("keypress", (e) => {
        if (e.key === "Enter") {
            btnAdicionar.click();
        }
    });

    renderizarTarefas();
});