# Guia do Projeto: Implementação do Algoritmo Minimax para Pac-Man

**Disciplina:** Inteligência Artificial  
**Curso:** Ciências da Computação  
**Professor:** Nikson Bernardes Fernandes Ferreira

---

## 1. Introdução

Neste projeto, implementamos o algoritmo Minimax para controlar o agente Pac-Man em um cenário de jogo dinâmico. O Pac-Man e os fantasmas são os agentes que interagem no ambiente, e a tarefa é desenvolver a lógica para que o Pac-Man tome as melhores decisões possíveis, antecipando e reagindo aos movimentos dos fantasmas.

---

## 2. Objetivo

Proporcionar uma compreensão aprofundada do algoritmo Minimax — seus conceitos de minimização e maximização — e como aplicá-los de forma prática para criar um agente inteligente em jogos de soma zero (o ganho de um é a perda de outro).

---

## 3. O Algoritmo Minimax

O Minimax é um algoritmo de busca recursivo fundamental em jogos de dois jogadores e soma zero, onde os jogadores se revezam em seus turnos. Ele opera sob a premissa de que ambos os jogadores agem de maneira ideal e estratégica.

- **Nó Max (Pac-Man):** O jogador maximizador sempre busca a ação que resulta no estado com a **maior utilidade** (maior pontuação).
- **Nó Min (Fantasmas):** O jogador minimizador escolhe a ação que leva ao estado com a **menor utilidade** (menor pontuação para o Pac-Man).

### Árvore de Decisão

```
        Pac-Man (MAX) — escolhe o MAIOR valor
       /       |       \
  Fantasma1 (MIN)  ...    — escolhe o MENOR valor
     /    \
  Pac-Man (MAX)           — próxima rodada, profundidade +1
    ...
```

Cada nível da árvore alterna entre agentes. Um "turno completo" (uma profundidade) só se completa quando **todos** os agentes jogaram (Pac-Man + todos os fantasmas).

---

## 4. Estrutura do Código

O trabalho é desenvolvido no arquivo `seuPacManAgents.py`. A lógica do algoritmo Minimax está na função `getAction` da classe `MinimaxAgent`.

### Estrutura básica:

```python
class MinimaxAgent(MultiAgentSearchAgent):

    def getAction(self, gameState: GameState):
        def minimax(state, depth, agentIndex):
            # Condição de parada
            # Cálculo do próximo agente e profundidade
            # Maximização (Pac-Man) ou Minimização (Fantasmas)
            pass
        return minimax(gameState, 0, 0)
```

---

## 5. Detalhes da Implementação

### 5.1. Parâmetros da Função `minimax`

| Parâmetro | Significado |
|-----------|-------------|
| `agentIndex` | Qual agente está agindo (0 = Pac-Man, 1+ = fantasmas) |
| `depth` | Profundidade atual na árvore de busca |
| `state` | O estado atual do jogo (`GameState`) sendo avaliado |

### 5.2. Condição de Parada (Base Case)

A recursão para quando:
- **Jogo Finalizado:** `state.isWin()` ou `state.isLose()`
- **Profundidade Máxima Atingida:** `depth == self.depth`

Nesses casos, retorna-se `self.evaluationFunction(state)`.

```python
if state.isWin() or state.isLose() or depth == self.depth:
    return self.evaluationFunction(state)
```

### 5.3. Gerenciamento de Agentes e Profundidade

```python
nextAgent = (agentIndex + 1) % numAgents
nextDepth = depth + 1 if nextAgent == 0 else depth
```

- **Próximo Agente:** Se o agente atual é o último fantasma, o próximo é o Pac-Man (0). Caso contrário, é `agentIndex + 1`.
- **Próxima Profundidade:** A profundidade só incrementa quando todos os fantasmas jogaram e o turno volta ao Pac-Man.

### 5.4. Lógica de Maximização (Turno do Pac-Man — `agentIndex == 0`)

```python
maxValue = -float("inf")
bestActions = []

for action in actions:
    successor = state.generateSuccessor(agentIndex, action)
    value = minimax(successor, nextDepth, nextAgent)

    if value > maxValue:
        maxValue = value
        bestActions = [action]
    elif value == maxValue:
        bestActions.append(action)

if depth == 0:
    return random.choice(bestActions)  # Na raiz, retorna a AÇÃO
return maxValue  # Nas demais chamadas, retorna o VALOR
```

### 5.5. Lógica de Minimização (Turno dos Fantasmas — `agentIndex > 0`)

```python
minValue = float("inf")
for action in actions:
    successor = state.generateSuccessor(agentIndex, action)
    value = minimax(successor, nextDepth, nextAgent)
    minValue = min(minValue, value)
return minValue
```

O fantasma retorna apenas o **menor valor** — não precisa guardar a ação.

---

## 6. Função de Avaliação (`betterEvaluationFunction`)

É a "inteligência" que dá nota a um estado do jogo. Considera:

| Fator | Efeito na pontuação |
|-------|---------------------|
| Comida próxima | Aumenta score (incentiva comer) |
| Muita comida restante | Diminui score (penaliza) |
| Fantasma perto (ativo) | Diminui score (perigo!) |
| Fantasma perto (assustado) | Aumenta score (chance de comer) |
| Cápsula próxima + fantasma perto | Aumenta score (estratégia defensiva) |
| Fase final (≤5 comidas) | Modo agressivo para terminar o jogo |

---

## 7. Conceitos-Chave

1. **Minimax assume jogo ótimo** — os fantasmas sempre fazem o pior para o Pac-Man
2. **Profundidade limita a busca** — sem limite, seria impossível computar (explosão combinatória)
3. **Função de avaliação é essencial** — como o algoritmo não explora até o fim do jogo, precisa "estimar" quão bom é um estado intermediário
4. **Complexidade:** O(b^m) onde b = fator de ramificação e m = profundidade. Profundidades maiores são mais lentas
5. **Soma zero** — o que é bom para o Pac-Man é ruim para os fantasmas e vice-versa

---

## 8. Como Executar

```bash
# Executar com profundidade padrão
python pacman.py --pacman MinimaxAgent

# Executar com profundidade específica
python pacman.py --pacman MinimaxAgent --depth 2
```

> **Dica:** Comece com profundidades pequenas (`--depth 1` ou `--depth 2`) para facilitar a depuração.

---

## 9. Critérios de Avaliação

| Critério | O que o professor espera |
|----------|--------------------------|
| **Correção do Algoritmo** | O Minimax segue corretamente max/min alternados e gerencia profundidade |
| **Desempenho do Pac-Man** | O Pac-Man joga de forma inteligente contra os fantasmas |
| **Compreensão Conceitual** | Demonstração de entendimento de maximização e minimização |
| **Qualidade do Código** | Organização, clareza e comentários |

---

## 10. Possíveis Perguntas do Professor

**P: Por que o Pac-Man às vezes perde mesmo com Minimax?**  
R: Porque a profundidade é limitada. O Pac-Man não consegue "ver" perigos que estão além da profundidade configurada.

**P: O que acontece se aumentar a profundidade?**  
R: O Pac-Man joga melhor, mas o tempo de execução cresce exponencialmente.

**P: Por que precisamos de uma função de avaliação?**  
R: Porque não é viável explorar a árvore até o final do jogo. A função de avaliação estima a qualidade de estados intermediários.

**P: Como o algoritmo lida com múltiplos fantasmas?**  
R: Cada fantasma é um agente MIN separado. Eles jogam em sequência dentro de uma mesma rodada, e cada um minimiza o score individualmente.

**P: O que é soma zero neste contexto?**  
R: Significa que o ganho do Pac-Man é exatamente a perda dos fantasmas. Se o Pac-Man marca +10, os fantasmas "perderam" 10 pontos.

---

## 11. Entrega

O trabalho deve ser entregue como o arquivo `seuPacManAgents.py` contendo a implementação completa e funcional do algoritmo Minimax dentro da classe `MinimaxAgent`.
