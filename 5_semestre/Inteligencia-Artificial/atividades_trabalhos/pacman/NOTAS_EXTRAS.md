# Notas Extras do Projeto — O que mais você precisa saber

---

## 1. Classe Base `MultiAgentSearchAgent`

Seu `MinimaxAgent` herda desta classe. Ela define automaticamente:

```python
self.index = 0          # Pac-Man é sempre agente 0
self.depth = int(depth) # Profundidade padrão = 3
self.evaluationFunction = scoreEvaluationFunction  # padrão
```

Você pode trocar a função de avaliação via linha de comando:
```bash
python pacman.py --pacman MinimaxAgent --evalFn betterEvaluationFunction
```

---

## 2. Diferença entre `scoreEvaluationFunction` e `betterEvaluationFunction`

| Função | Comportamento |
|--------|---------------|
| `scoreEvaluationFunction` (padrão) | Retorna apenas `currentGameState.getScore()` — pontuação bruta do jogo |
| `betterEvaluationFunction` (sua) | Usa heurísticas (distância à comida, proximidade de fantasmas, etc.) para uma avaliação mais inteligente |

Se quiser usar a sua função melhorada, passe `--evalFn better` ou altere o código para chamá-la diretamente.

---

## 3. Decisões de design no código que podem gerar perguntas

| Detalhe | Implicação |
|---------|------------|
| Remoção do `Directions.STOP` | Impede o Pac-Man de ficar parado, mas o enunciado **não pede isso** — pode ser questionado |
| Desempate aleatório (`random.choice(bestActions)`) | Boa prática, mas o autograder espera resultado **determinístico** — pode falhar em testes automatizados |
| `betterEvaluationFunction` não é usada por padrão | Só é chamada se você passar `--evalFn better`; o Minimax usa `scoreEvaluationFunction` por padrão |

---

## 4. API do `GameState` que você deve conhecer

| Método | O que retorna |
|--------|---------------|
| `state.getLegalActions(agentIndex)` | Lista de ações válidas para o agente |
| `state.generateSuccessor(agentIndex, action)` | Novo `GameState` após a ação |
| `state.getNumAgents()` | Número total de agentes (Pac-Man + fantasmas) |
| `state.isWin()` / `state.isLose()` | Se o jogo terminou |
| `state.getPacmanPosition()` | Tupla (x, y) do Pac-Man |
| `state.getFood()` | Grid booleano de comida |
| `state.getGhostStates()` | Lista de objetos fantasma (posição + `scaredTimer`) |
| `state.getScore()` | Pontuação atual |

---

## 5. Pontuação do jogo (como funciona internamente)

| Evento | Pontos |
|--------|--------|
| Comer comida | +10 |
| Comer fantasma assustado | +200 |
| Morrer (fantasma ativo encosta) | -500 |
| Cada turno que passa | -1 (penaliza demora) |
| Vitória (toda comida comida) | +500 |

Isso explica por que profundidades maiores ajudam: o Pac-Man "enxerga" que demorar custa pontos.

---

## 6. Potencial problema com o autograder

O autograder em `test_cases/q2/` valida se seu Minimax retorna **exatamente** os mesmos valores que a implementação de referência. O `random.choice` no desempate e a remoção de `STOP` podem causar falhas. Se precisar passar nos testes automáticos, considere:

- Não remover `STOP`
- Retornar a **primeira** melhor ação ao invés de escolha aleatória

---

## 7. Poda Alfa-Beta e Expectimax (extensões futuras)

O projeto tem testes para `q3` (Alfa-Beta) e `q4` (Expectimax). Se o professor pedir extensões:

| Algoritmo | Diferença em relação ao Minimax |
|-----------|----------------------------------|
| **Alfa-Beta** | Mesmo Minimax, mas "poda" ramos que não afetam a decisão final — muito mais rápido |
| **Expectimax** | Substitui MIN por **média ponderada** — fantasmas jogam aleatoriamente, não otimamente |

Essas extensões usariam a mesma estrutura do seu código atual.

---

## 8. Dicas para a apresentação/defesa

- Saiba explicar **por que** o Pac-Man perde em certas situações (profundidade limitada)
- Entenda que `depth = 2` significa **2 rodadas completas** (Pac-Man + todos os fantasmas, duas vezes)
- Saiba a diferença entre a função de avaliação padrão e a sua melhorada
- Esteja preparado para explicar o que acontece se um agente não tem ações legais
- Conheça a complexidade: O(b^m) — se há 5 ações e profundidade 3 com 2 fantasmas, são 5^(3×3) = 5^9 ≈ 2 milhões de nós no pior caso
