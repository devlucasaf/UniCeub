package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Tarefa struct {
	ID      int
	Duracao time.Duration
}

func trabalhador(id int, tarefas <-chan Tarefa, resultados chan<- string, wg *sync.WaitGroup) {
	defer wg.Done()

	for tarefa := range tarefas {
		fmt.Printf("Trabalhador %d iniciou a tarefa %d\n", id, tarefa.ID)

		time.Sleep(tarefa.Duracao)

		resultado := fmt.Sprintf(
			"Trabalhador %d concluiu a tarefa %d em %v",
			id,
			tarefa.ID,
			tarefa.Duracao,
		)

		resultados <- resultado
	}
}

func main() {
	rand.Seed(time.Now().UnixNano())

	tarefas := make(chan Tarefa, 10)

	resultados := make(chan string, 10)

	var wg sync.WaitGroup

	numeroTrabalhadores := 3

	for i := 1; i <= numeroTrabalhadores; i++ {
		wg.Add(1)
		go trabalhador(i, tarefas, resultados, &wg)
	}

	numeroTarefas := 8

	for i := 1; i <= numeroTarefas; i++ {
		duracao := time.Duration(rand.Intn(3)+1) * time.Second

		tarefa := Tarefa {
			ID:      i,
			Duracao: duracao,
		}

		tarefas <- tarefa

		fmt.Printf(
			"Tarefa %d enviada para execução (duração %v)\n",
			tarefa.ID,
			tarefa.Duracao,
		)
	}

	close(tarefas)

	go func() {
		wg.Wait()
		close(resultados)
	}()

	for resultado := range resultados {
		fmt.Println(resultado)
	}

	fmt.Println("Todas as tarefas foram processadas!")
}