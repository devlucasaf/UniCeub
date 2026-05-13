Nenhum selecionado

Pular para o conteúdo
Como usar o Gmail com leitores de tela
Ativar as notificações na área de trabalho para o Gmail.
   OK  Agora não(a)

Você pode perder o acesso ao seu histórico de 3 anos do Gmail
Adicionar um telefone e e-mail de recuperação pode ajudar você a fazer login e manter sua conta segura
Conversas
4% de 15 GB usados
Termos · Privacidade · Regulamentos do programa
Última atividade da conta: há 0 minuto
Aberta em um outro local · Detalhes
from util import manhattanDistance
from game import Directions
import random, util

from game import Agent
from pacman import GameState
from multiAgents import MultiAgentSearchAgent


class MinimaxAgent(MultiAgentSearchAgent):

    def getAction(self, gameState: GameState):
        def minimax(state, depth, agentIndex):

            # Condição de parada
            if state.isWin() or state.isLose() or depth == self.depth:
                return self.evaluationFunction(state)

            numAgents = state.getNumAgents()

            # Próximo agente
            nextAgent = (agentIndex + 1) % numAgents
            nextDepth = depth + 1 if nextAgent == 0 else depth
            actions = state.getLegalActions(agentIndex)

            # Remoção do Stop para evitar paradas
            if Directions.STOP in actions:
                actions.remove(Directions.STOP)

            if len(actions) == 0:
                return self.evaluationFunction(state)

            if agentIndex == 0:
                maxValue = -float("inf")
                bestActions = []

                for action in actions:
                    successor = state.generateSuccessor(agentIndex, action)
                    value = minimax(
                        successor,
                        nextDepth,
                        nextAgent
                    )

                    if value > maxValue:
                        maxValue = value
                        bestActions = [action]

                    elif value == maxValue:
                        bestActions.append(action)

                if depth == 0:
                    return random.choice(bestActions)

                return maxValue
            else:

                minValue = float("inf")
                for action in actions:
                    successor = state.generateSuccessor(agentIndex, action)
                    value = minimax(
                        successor,
                        nextDepth,
                        nextAgent
                    )

                    minValue = min(minValue, value)
                return minValue

        return minimax(gameState, 0, 0)

def betterEvaluationFunction(currentGameState: GameState):

    pos = currentGameState.getPacmanPosition()
    foodList = currentGameState.getFood().asList()
    ghostStates = currentGameState.getGhostStates()
    score = currentGameState.getScore()

    if foodList:
        foodDistances = [
            manhattanDistance(pos, food)
            for food in foodList
        ]

        # Incentivos para buscar comida
        score += 30 / min(foodDistances) 
        score -= 120 * len(foodList)

        # Pressão pra finalizar o 
        score -= 0.3 * sum(foodDistances)

        if len(foodList) <= 5:
            score -= 15 * min(foodDistances)

    for ghost in ghostStates:
        ghostPos = ghost.getPosition()
        ghostDist = manhattanDistance(pos, ghostPos)

        # Incentivo para comer fantasmas assutados
        if ghost.scaredTimer > 0:

            score += 25.0 / (ghostDist + 1)

        else:
            # Fantasmas sem medo pertos demais to Pacman são perigosos
            if ghostDist < 2:
                score -= 1000

    return score

# Abbreviation
better = betterEvaluationFunction
seuPacManAgents (2).py
Exibindo seuPacManAgents (2).py.