# Estrutura do Projeto: Descrição dos Arquivos

Este documento descreve o papel de cada arquivo e pasta no projeto Pac-Man Minimax.

---

## Arquivos Principais do Sistema

| Arquivo | Descrição |
|---------|-----------|
| `pacman.py` | Núcleo da lógica do jogo. Gerencia o estado do jogo, colisões, movimento dos agentes e é o ponto de entrada para iniciar uma partida (`python pacman.py`). |
| `game.py` | Define as classes base do framework: `Agent` (classe abstrata), `GameState`, `Actions`, `Directions` e o gerenciador do loop principal do jogo. |
| `util.py` | Biblioteca de utilitários gerais com estruturas de dados (`Counter`, `Stack`, `Queue`, `PriorityQueue`), funções de distância (`manhattanDistance`) e funções auxiliares. |

---

## Agentes

| Arquivo | Descrição |
|---------|-----------|
| `seuPacManAgents.py` | **Arquivo do aluno.** Contém a implementação do `MinimaxAgent` com o algoritmo Minimax e a `betterEvaluationFunction`. Este é o arquivo entregue na avaliação. |
| `multiAgents.py` | Define a classe base `MultiAgentSearchAgent` (da qual `MinimaxAgent` herda) e o `ReflexAgent` com sua função de avaliação simples. |
| `ghostAgents.py` | Implementações dos fantasmas: `RandomGhost` (movimentos aleatórios) e `DirectionalGhost` (fantasma que persegue o Pac-Man com preferência direcional). |
| `keyboardAgents.py` | Agente controlado por teclado — permite jogar manualmente usando W/A/S/D ou setas direcionais. |
| `pacmanAgents.py` | Agentes alternativos para o Pac-Man, incluindo implementações de aprendizado por reforço (`QLearningAgent`). |

---

## Tabuleiro e Ambiente

| Arquivo | Descrição |
|---------|-----------|
| `layout.py` | Carrega e interpreta os arquivos `.lay` da pasta `layouts/`. Gerencia a estrutura estática do tabuleiro: paredes, posição de comida, cápsulas e posições iniciais dos agentes. |

---

## Interface Gráfica

| Arquivo | Descrição |
|---------|-----------|
| `graphicsDisplay.py` | Módulo de renderização gráfica (Tkinter). Desenha o tabuleiro, Pac-Man, fantasmas, comida e animações do jogo. |
| `graphicsUtils.py` | Funções utilitárias para gráficos: criação de canvas, desenho de formas, conversão de cores e gerenciamento da janela Tkinter. |
| `textDisplay.py` | Interface de exibição em texto (modo terminal). Mostra o estado do jogo no console sem necessidade de janela gráfica. Útil para testes automatizados. |

---

## Sistema de Avaliação e Testes

| Arquivo | Descrição |
|---------|-----------|
| `autograder.py` | Sistema automático de avaliação. Lê parâmetros de linha de comando, executa casos de teste e calcula a pontuação final do projeto. |
| `grading.py` | Infraestrutura de pontuação para o autograder: gerenciamento de notas, mensagens de sucesso/falha e formatação de resultados. |
| `testClasses.py` | Classes abstratas para modelar questões e casos de teste (`Question`, `PassAllTestsQuestion`). |
| `multiagentTestClasses.py` | Classes especializadas de teste para agentes multiagente. Simula árvores Minimax e valida a correção do algoritmo implementado. |
| `testParser.py` | Parser que lê arquivos de configuração de testes (`.test` e `.solution`), removendo comentários e estruturando os dados. |
| `projectParams.py` | Configurações do projeto: define quais arquivos são do aluno, quais classes de teste usar e o nome do projeto. |

---

## Pastas

### `layouts/`

Contém arquivos `.lay` com diferentes configurações de tabuleiro:

| Layout | Descrição |
|--------|-----------|
| `smallClassic.lay` | Mapa pequeno para testes rápidos |
| `mediumClassic.lay` | Mapa médio, equilíbrio entre complexidade e velocidade |
| `minimaxClassic.lay` | Mapa projetado para testar o algoritmo Minimax |
| `trappedClassic.lay` | Mapa onde o Pac-Man está encurralado (teste de caso extremo) |
| `trickyClassic.lay` | Mapa com situações difíceis para o agente |
| `openClassic.lay` | Mapa aberto com poucas paredes |
| `originalClassic.lay` | Reprodução do mapa original do Pac-Man |
| `capsuleClassic.lay` | Mapa com ênfase em cápsulas de poder |
| `powerClassic.lay` | Mapa com posicionamento estratégico de cápsulas |
| `contestClassic.lay` | Mapa usado para competição entre agentes |
| `testClassic.lay` | Mapa mínimo para depuração |

### `test_cases/`

Contém os casos de teste organizados por questão:

| Pasta | Conteúdo |
|-------|----------|
| `q1/` | Testes para o agente reflexo |
| `q2/` | Testes para o algoritmo Minimax (árvores de decisão, profundidades variadas, múltiplos fantasmas) |
| `q3/` | Testes para poda Alfa-Beta |
| `q4/` | Testes para Expectimax |
| `q5/` | Testes para função de avaliação |
| `extra/` | Testes extras/bônus |

Cada questão contém arquivos `.test` (definição do teste) e `.solution` (resposta esperada).

---

## Diagrama de Dependências

```
seuPacManAgents.py  ←  Seu código (MinimaxAgent)
       ↓
multiAgents.py      ←  Classe base (MultiAgentSearchAgent)
       ↓
game.py             ←  Framework do jogo (Agent, GameState, Directions)
       ↓
pacman.py           ←  Motor do jogo (execução, regras, colisões)
       ↓
layout.py           ←  Carrega mapas (.lay)
       ↓
graphicsDisplay.py  ←  Renderiza o jogo na tela
```

---

## Como Usar

```bash
# Jogar manualmente
python pacman.py

# Usar o MinimaxAgent
python pacman.py --pacman MinimaxAgent --depth 2

# Usar um layout específico
python pacman.py --pacman MinimaxAgent --layout smallClassic

# Modo texto (sem janela gráfica)
python pacman.py --pacman MinimaxAgent --textGraphics

# Executar o autograder
python autograder.py
```
