use std::thread;
use std::sync::{Arc, Mutex};
use std::time::Duration;

fn processar_tarefa(id: i32, contador: Arc<Mutex<i32>>) {
    println!("Thread {} iniciada.", id);

    for i in 1..=5 {
        println!("Thread {} executando etapa {}", id, i);
        thread::sleep(Duration::from_millis(500));
    }

    let mut valor = contador.lock().unwrap();
    *valor += 1;

    println!("Thread {} finalizada.", id);
}

fn main() {
    let contador = Arc::new(Mutex::new(0));

    let mut handles = vec![];

    for id in 1..=4 {
        let contador_compartilhado = Arc::clone(&contador);

        let handle = thread::spawn(move || {
            processar_tarefa(id, contador_compartilhado);
        });

        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("\nTodas as threads terminaram.");

    let resultado = contador.lock().unwrap();
    println!("Quantidade de tarefas concluídas: {}", *resultado);

    let numeros = vec![10, 20, 30, 40, 50];

    let mut handles_soma = vec![];

    for numero in numeros {
        let handle = thread::spawn(move || {
            println!("Processando número {}", numero);
            thread::sleep(Duration::from_secs(1));

            let resultado = numero * 2;

            println!(
                "Resultado do processamento do número {} = {}",
                numero,
                resultado
            );
        });

        handles_soma.push(handle);
    }

    for handle in handles_soma {
        handle.join().unwrap();
    }

    println!("\nProcessamento paralelo concluído com sucesso!");
}