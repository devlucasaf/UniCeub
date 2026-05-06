# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
#
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent
from pacman import GameState
from multiAgents import MultiAgentSearchAgent

class MinimaxAgent(MultiAgentSearchAgent):
    def getAction(self, gameState: GameState):
        def minimax(state, depth, agentIndex):
            # Jogo terminou (Pacman venceu ou perdeu)
            if state.isWin() or state.isLose():
                return self.evaluationFunction(state)
            if depth == 0:
                return self.evaluationFunction(state)
            
            # Obtém todas as ações possíveis para o agente atual
            actions = state.getLegalActions(agentIndex)
        
            if not actions:
                return self.evaluationFunction(state)
            
            # Ação do pacman
            if agentIndex == 0:  
                
                maxValue = float('-inf')
                
                for action in actions:
                    # Gera o próximo estado após executar a ação
                    nextState = state.generateSuccessor(agentIndex, action)
                    
                    value = minimax(nextState, depth, 1)

                    if value > maxValue:
                        maxValue = value
                
                return maxValue
            
            # Ações dos fantasmas
            else:  
                minValue = float('inf')
                
                # Cálculo para decidir qual o próximo a tomar decisões
                nextAgent = (agentIndex + 1) % state.getNumAgents()
                
                if nextAgent == 0:
                    newDepth = depth - 1
                else:
                    newDepth = depth
                
                for action in actions:
                    nextState = state.generateSuccessor(agentIndex, action)
                    
                    value = minimax(nextState, newDepth, nextAgent)
                    
                    if value < minValue:
                        minValue = value
                
                return minValue
        
        # Obtém todas as ações possíveis do Pacman
        actions = gameState.getLegalActions(0)
        
        # Lista para armazenar o valor de cada ação
        valores = []
        
        for action in actions:
            nextState = gameState.generateSuccessor(0, action)
            
            valor = minimax(nextState, self.depth, 1)
            
            valores.append(valor)
            print(f"Ação: {action} -> Valor: {valor}")
        
        # Encontra o MAIOR valor (melhor movimento do Pacman)
        melhorValor = max(valores)
        
        # Pega todas as ações que alcançam o melhor valor
        melhoresAcoes = []
        for i in range(len(actions)):
            if valores[i] == melhorValor:
                melhoresAcoes.append(actions[i])
        
        # Escolhe aleatoriamente entre as melhores ações 
        acaoEscolhida = random.choice(melhoresAcoes)
        
        return acaoEscolhida
    
def betterEvaluationFunction(currentGameState: GameState):
    # Posição atual do Pacman
    pos = currentGameState.getPacmanPosition()
    
    # Lista de posições das comidas
    food = currentGameState.getFood().asList()
    
    # Informações dos fantasmas
    ghostStates = currentGameState.getGhostStates()
    scaredTimes = [ghostState.scaredTimer for ghostState in ghostStates]
    
    # A pontuação do jogo 
    score = currentGameState.getScore()

    comidaRestante = len(food)
    isFaseFinal = comidaRestante <= 5   

    if food:
        # Calcula a distância para a comida mais próxima
        distancias = [manhattanDistance(pos, f) for f in food]
        comidaMaisProxima = min(distancias)
        
        if isFaseFinal:
            # Modo final, focando em comer as bolinhas restantes
            print("ativando modo final")
            comidaMaisProx = min(distancias)
            
            # Alta pontuação para comida mais próxima
            score += 1000.0 / (comidaMaisProx + 0.1)
            
            # Bônus extra se conseguir comer agora
            if comidaMaisProx == 1:
                score += 500
            
            # Penalidade por comida restante 
            score -= 20 * comidaRestante
            
            # Bônus para a próxima comida depois da mais próxima
            if len(distancias) >= 2:
                segundaComida = sorted(distancias)[1]
                score += 200.0 / (segundaComida + 0.1)
        
        else:
            comidaMaisProx = min(distancias)
            score += 100.0 / (comidaMaisProx + 0.1)
            score -= 5 * comidaRestante

        # Aumenta a pontuação por cada comida próxima
        score += 100.0 / (comidaMaisProxima + 0.1)
        
        # Quanto mais comida restante a pontuação é diminuída
        score -= 5 * len(food)
        
        # Bônus extra se estiver muito perto da comida
        if comidaMaisProxima == 1:
            score += 50
    
    for i, ghost in enumerate(ghostStates):
        ghostPos = ghost.getPosition()
        distancia = manhattanDistance(pos, ghostPos)
        
        if scaredTimes[i] > 0:
            if distancia == 0:
                # Maior pontuação por comer fantasmas
                score += 1000
            elif distancia <= 2:
                # Aumenta a pontuação caso o Pacman esteja perto de um fantasma assustado
                score += 500 / distancia
            else:
                # Aumenta pouco a pontuação caso os fantasmas fiquem longe, mas sem penalizar
                score += 100 / distancia
        
        else:
            if distancia == 0:
                # Pacman perdeu
                return -float('inf')
            elif distancia == 1:
                # Fantasma perto
                score -= 500
            elif distancia <= 3:
                # Fantasma muito perto
                score -= 200 / distancia
            elif distancia <= 5:
                # Fantasma moderadamente perto
                score -= 50 / distancia
            else:
                # Fantasma longe 
                score -= 5 / distancia

    capsules = currentGameState.getCapsules()
    if capsules:
        # Distância para a cápsula mais próxima
        distCapsula = min(manhattanDistance(pos, cap) for cap in capsules)
        
        # Verifica se tem um fantasma por perto
        FantasmaPerto = False
        for ghost in ghostStates:
            if manhattanDistance(pos, ghost.getPosition()) < 4:
                FantasmaPerto = True
                break
        
        # Se tiver fantasma perto, cápsula é mais valiosa
        if FantasmaPerto:
            score += 200.0 / (distCapsula + 0.1)
        else:
            score += 50.0 / (distCapsula + 0.1)
    
    # Penalidade por parar, mas não impede o Pacman caso seja necessário
    if Directions.STOP in currentGameState.getLegalActions(0):
        score -= 50

    return score