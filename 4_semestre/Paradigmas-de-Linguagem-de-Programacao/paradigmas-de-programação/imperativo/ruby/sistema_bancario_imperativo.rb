=begin
	Paradigmas de Linguagens de Programação
	Paradigma imperativo: Sistema Bancário em RUBY
	Estudo prático sobre paradigma imperativo 
	*CÓDIGO DESENVOLVIDO A FIM DE ESTUDOS! NÃO FOI PASSADO EM SALA!
=end

$contas_bancarias = []
$historico_transacoes = []
$proximo_numero_conta = 1000
$usuario_logado = nil

def criar_conta(nome, senha, saldo_inicial)
  numero_conta = $proximo_numero_conta
  $proximo_numero_conta += 1  
  
  conta = {
    numero: numero_conta,
    nome: nome,
    senha: senha,
    saldo: saldo_inicial,
    ativa: true
  }
  
  $contas_bancarias << conta  
  
  puts "Conta criada com sucesso!"
  puts "Número da conta: #{numero_conta}"
  puts "Titular: #{nome}"
  puts "Saldo inicial: R$ #{saldo_inicial}"
  puts "-" * 40
  
  return numero_conta
end

def fazer_login(numero_conta, senha)
  conta_encontrada = nil
  i = 0
  
  while i < $contas_bancarias.length
    conta = $contas_bancarias[i]
    if conta[:numero] == numero_conta && conta[:senha] == senha && conta[:ativa]
      conta_encontrada = conta
      break  
    end
    i += 1  
  end
  
  if conta_encontrada
    $usuario_logado = conta_encontrada  
    puts "Login realizado com sucesso!"
    puts "Bem-vindo, #{conta_encontrada[:nome]}!"
    return true
  else
    puts "Login falhou. Verifique número da conta e senha."
    return false
  end
end

def fazer_logout
  $usuario_logado = nil  
  puts "Logout realizado com sucesso!"
end

def consultar_saldo
  if $usuario_logado
    puts "=" * 40
    puts "CONSULTA DE SALDO"
    puts "Conta: #{$usuario_logado[:numero]}"
    puts "Titular: #{$usuario_logado[:nome]}"
    puts "Saldo atual: R$ #{$usuario_logado[:saldo]}"
    puts "=" * 40
  else
    puts "É necessário fazer login primeiro!"
  end
end

def depositar(valor)
  if $usuario_logado
    if valor > 0
      $usuario_logado[:saldo] += valor  
      
      transacao = {
        tipo: :deposito,
        conta: $usuario_logado[:numero],
        valor: valor,
        data: Time.now,
        saldo_apos: $usuario_logado[:saldo]
      }
      $historico_transacoes << transacao  
      
      puts "Depósito de R$ #{valor} realizado com sucesso!"
      puts "Novo saldo: R$ #{$usuario_logado[:saldo]}"
    else
      puts "Valor de depósito inválido!"
    end
  else
    puts "É necessário fazer login primeiro!"
  end
end

def sacar(valor)
  if $usuario_logado
    if valor <= 0
      puts "Valor de saque inválido!"
	  
    elsif valor > $usuario_logado[:saldo]
      puts "Saldo insuficiente!"
      puts "Saldo atual: R$ #{$usuario_logado[:saldo]}"
      puts "Valor solicitado: R$ #{valor}"
	  
    else
      $usuario_logado[:saldo] -= valor  
      
      transacao = {
        tipo: :saque,
        conta: $usuario_logado[:numero],
        valor: valor,
        data: Time.now,
        saldo_apos: $usuario_logado[:saldo]
      }
      $historico_transacoes << transacao 
      
      puts "Saque de R$ #{valor} realizado com sucesso!"
      puts "Novo saldo: R$ #{$usuario_logado[:saldo]}"
    end
  else
    puts "É necessário fazer login primeiro!"
  end
end

def transferir(numero_conta_destino, valor)
  if $usuario_logado
    conta_destino = nil
    
    for conta in $contas_bancarias
      if conta[:numero] == numero_conta_destino && conta[:ativa]
        conta_destino = conta
        break
      end
    end
    
    if conta_destino.nil?
      puts "Conta destino não encontrada ou inativa!"
	  
    elsif valor <= 0
      puts "Valor de transferência inválido!"
	  
    elsif valor > $usuario_logado[:saldo]
      puts "Saldo insuficiente para transferência!"
	  
    else
      $usuario_logado[:saldo] -= valor
      conta_destino[:saldo] += valor
      
      transacao_origem = {
        tipo: :transferencia_envio,
        conta_origem: $usuario_logado[:numero],
        conta_destino: numero_conta_destino,
        valor: valor,
        data: Time.now,
        saldo_apos: $usuario_logado[:saldo]
      }
      
      transacao_destino = {
        tipo: :transferencia_recebimento,
        conta_origem: $usuario_logado[:numero],
        conta_destino: numero_conta_destino,
        valor: valor,
        data: Time.now,
        saldo_apos: conta_destino[:saldo]
      }
      
      $historico_transacoes << transacao_origem
      $historico_transacoes << transacao_destino
      
      puts "Transferência de R$ #{valor} para conta #{numero_conta_destino} realizada!"
      puts "Novo saldo: R$ #{$usuario_logado[:saldo]}"
    end
  else
    puts "É necessário fazer login primeiro!"
  end
end

def exibir_extrato(quantidade_transacoes = 10)
  if $usuario_logado
    puts "=" * 50
    puts "EXTRATO BANCÁRIO"
    puts "Conta: #{$usuario_logado[:numero]}"
    puts "Titular: #{$usuario_logado[:nome]}"
    puts "+=+=" * 50
    
    transacoes_conta = []
    
    $historico_transacoes.each do |transacao|
      if transacao[:conta] == $usuario_logado[:numero] || 
         transacao[:conta_origem] == $usuario_logado[:numero] ||
         transacao[:conta_destino] == $usuario_logado[:numero]
        transacoes_conta << transacao
      end
    end
    
    transacoes_conta.sort! { |a, b| b[:data] <=> a[:data] }
    
    transacoes_conta = transacoes_conta.first(quantidade_transacoes)
    
    if transacoes_conta.empty?
      puts "Nenhuma transação encontrada."
    else
      
      for i in 0...transacoes_conta.length
        transacao = transacoes_conta[i]
        
        case transacao[:tipo]
        when :deposito
          puts "#{i+1}. DEPÓSITO"
          puts "   Valor: +R$ #{transacao[:valor]}"
        when :saque
          puts "#{i+1}. SAQUE"
          puts "   Valor: -R$ #{transacao[:valor]}"
        when :transferencia_envio
          puts "#{i+1}. TRANSFERÊNCIA ENVIADA"
          puts "   Para conta: #{transacao[:conta_destino]}"
          puts "   Valor: -R$ #{transacao[:valor]}"
        when :transferencia_recebimento
          puts "#{i+1}. TRANSFERÊNCIA RECEBIDA"
          puts "   Da conta: #{transacao[:conta_origem]}"
          puts "   Valor: +R$ #{transacao[:valor]}"
        end
        
        puts "   Data: #{transacao[:data].strftime('%d/%m/%Y %H:%M:%S')}"
        puts "   Saldo após: R$ #{transacao[:saldo_apos]}"
        puts "-" * 30
      end
    end
    
    puts "=" * 50
  else
    puts "É necessário fazer login primeiro!"
  end
end

def listar_todas_contas
  puts "=" * 60
  puts "RELATÓRIO DE TODAS AS CONTAS"
  puts "=" * 60
  
  contador = 0
  total_saldos = 0.0
  
  while contador < $contas_bancarias.length
    conta = $contas_bancarias[contador]
    
    status = conta[:ativa] ? "ATIVA" : "INATIVA"
    
    puts "Conta #{conta[:numero]}: #{conta[:nome]}"
    puts "  Saldo: R$ #{conta[:saldo]} | Status: #{status}"
    puts "-" * 40
    
    total_saldos += conta[:saldo]
    contador += 1  
  end
  
  puts "TOTAL DE CONTAS: #{contador}"
  puts "SOMA TOTAL DOS SALDOS: R$ #{total_saldos}"
  puts "=" * 60
end

def menu_principal
  loop do  
    puts "\n" + "=" * 50
    puts "SISTEMA BANCÁRIO - MENU PRINCIPAL"
    puts "=" * 50
    
    if $usuario_logado
      puts "Usuário: #{$usuario_logado[:nome]} (Conta: #{$usuario_logado[:numero]})"
      puts "1. Consultar saldo"
      puts "2. Depositar"
      puts "3. Sacar"
      puts "4. Transferir"
      puts "5. Exibir extrato"
      puts "6. Fazer logout"
    else
      puts "1. Criar nova conta"
      puts "2. Fazer login"
    end
    
    puts "7. Listar todas as contas (admin)"
    puts "8. Sair do sistema"
    puts "-" * 50
    
    print "Escolha uma opção: "
    opcao = gets.chomp.to_i
    
    case opcao
    when 1
      if $usuario_logado
        consultar_saldo
      else
        print "Nome do titular: "
        nome = gets.chomp
        print "Senha: "
        senha = gets.chomp
        print "Saldo inicial: R$ "
        saldo = gets.chomp.to_f
        
        criar_conta(nome, senha, saldo)
      end
      
    when 2
      if $usuario_logado
        print "Valor do depósito: R$ "
        valor = gets.chomp.to_f
        depositar(valor)
      else
        print "Número da conta: "
        numero = gets.chomp.to_i
        print "Senha: "
        senha = gets.chomp
        fazer_login(numero, senha)
      end
      
    when 3
      if $usuario_logado
        print "Valor do saque: R$ "
        valor = gets.chomp.to_f
        sacar(valor)
      else
        puts "É necessário fazer login primeiro!"
      end
      
    when 4
      if $usuario_logado
        print "Número da conta destino: "
        destino = gets.chomp.to_i
        print "Valor da transferência: R$ "
        valor = gets.chomp.to_f
        transferir(destino, valor)
      else
        puts "É necessário fazer login primeiro!"
      end
      
    when 5
      if $usuario_logado
        exibir_extrato
      else
        puts "É necessário fazer login primeiro!"
      end
      
    when 6
      if $usuario_logado
        fazer_logout
      else
        puts "Opção inválida!"
      end
      
    when 7
      listar_todas_contas
      
    when 8
      puts "Obrigado por usar o Sistema Bancário!"
      break  
      
    else
      puts "Opção inválida! Tente novamente."
    end
  end
end

def inicializar_sistema
  puts "Inicializando Sistema Bancário..."
  puts "Criando contas de exemplo..."
  
  criar_conta("João Silva", "1234", 1000.0)
  criar_conta("Maria Santos", "5678", 2500.0)
  criar_conta("Carlos Oliveira", "9012", 500.0)
  
  puts "Sistema inicializado com #{$contas_bancarias.length} contas."
  puts "=" * 50
end

inicializar_sistema
menu_principal

puts "\n" + "+=+=" * 50
puts "RELATÓRIO FINAL DO SISTEMA"
puts "Contas ativas: #{$contas_bancarias.length}"
puts "Total de transações: #{$historico_transacoes.length}"
puts "=" * 50
