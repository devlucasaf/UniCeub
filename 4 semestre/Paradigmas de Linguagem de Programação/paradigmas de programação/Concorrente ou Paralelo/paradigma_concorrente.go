package main

import (
    "fmt"
    "math/rand"
    "sync"
    "time"
)

// Estrutura que representa uma tarefa
type Task struct {
    ID       int
    Duration time.Duration
}

// Função que simula o processamento de uma tarefa
func worker(id int, tasks <-chan Task, results chan<- string, wg *sync.WaitGroup) {
    defer wg.Done()
    for task := range tasks {
        fmt.Printf("Worker %d iniciou tarefa %d\n", id, task.ID)
        time.Sleep(task.Duration) // simula tempo de execução
        result := fmt.Sprintf("Worker %d concluiu tarefa %d em %v", id, task.ID, task.Duration)
        results <- result
    }
}

func main() {
    rand.Seed(time.Now().UnixNano())

    // Canal para enviar tarefas
    tasks := make(chan Task, 10)
    // Canal para receber resultados
    results := make(chan string, 10)

    var wg sync.WaitGroup

    // Criando múltiplos workers (goroutines)
    numWorkers := 3
    for i := 1; i <= numWorkers; i++ {
        wg.Add(1)
        go worker(i, tasks, results, &wg)
    }

    // Gerando tarefas
    numTasks := 8
    for i := 1; i <= numTasks; i++ {
        duration := time.Duration(rand.Intn(3)+1) * time.Second
        task := Task{ID: i, Duration: duration}
        tasks <- task
        fmt.Printf("Tarefa %d enviada para execução (duração %v)\n", task.ID, task.Duration)
    }
    close(tasks) // fecha o canal de tarefas

    // Goroutine para esperar todos os workers terminarem
    go func() {
        wg.Wait()
        close(results)
    }()

    // Coletando resultados
    for result := range results {
        fmt.Println(result)
    }

    fmt.Println("Todas as tarefas foram processadas!")
}
