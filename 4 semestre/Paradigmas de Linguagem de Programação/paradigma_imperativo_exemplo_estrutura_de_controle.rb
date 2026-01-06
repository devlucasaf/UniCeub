=begin
    Paradigmas de Linguagens de Programaçãp
    Paradigma Imperativo: estruturas de controle e conceitos básicos
    Estudo prático sobre paradigma imperativo utilizando Ruby
=end

saldo = 1000.0
transacoes = []
usuario_ativo = false

def fazer_login(senha_correta, senha_digitada, usuario_ativo)
    if senha_digitada == senha_correta
        usuario_ativo = true
        puts "Login realizado com sucesso!"
    else
        puts "Senha incorreta!"
    end
    return usuario_ativo
end

def depositar(valor, saldo, transacoes)
    if valor > 0
        saldo += valor
        transacoes << { tipo: :deposito, valor: valor }
        puts "Depósito de R$ #{valor} realizado!"
    else
        puts "Valor inválido!"
    end
    return saldo, transacoes
end

def sacar(valor, saldo, transacoes)
    if valor > 0 && valor <= saldo
        saldo -= valor
        transacoes << { tipo: :saque, valor: valor }
        puts "Saque de R$ #{valor} realizado!"
    else
        puts "Saque não autorizado!"
    end
    return saldo, transacoes
end

def exibir_menu
    puts "\n" + "+=+=" * 69
    puts "SISTEMA BANCÁRIO SIMPLES"
    puts "=" * 30
    puts "1. Consultar saldo"
    puts "2. Depositar"
    puts "3. Sacar"
    puts "4. Ver extrato"
    puts "5. Sair"
    print "Opção: "
end

def processar_extrato(transacoes)
    puts "\n--- EXTRATO ---"
    if transacoes.empty?
        puts "Nenhuma transação realizada"
    else
        i = 0
        while i < transacoes.length
        transacao = transacoes[i]
        sinal = transacao[:tipo] == :deposito ? "+" : "-"
        puts "#{i+1}. #{transacao[:tipo].to_s.upcase}: #{sinal}R$ #{transacao[:valor]}"
        i += 1
        end
    end
end

SENHA_CORRETA = "1234"
puts "Digite a senha para acessar: "
senha_digitada = gets.chomp
usuario_ativo = fazer_login(SENHA_CORRETA, senha_digitada, usuario_ativo)

if usuario_ativo
    loop do
        exibir_menu
        opcao = gets.chomp.to_i
        
        case opcao
        when 1
        puts "Saldo atual: R$ #{saldo}"
        
        when 2
        print "Valor para depósito: R$ "
        valor = gets.chomp.to_f
        saldo, transacoes = depositar(valor, saldo, transacoes)
        
        when 3
        print "Valor para saque: R$ "
        valor = gets.chomp.to_f
        saldo, transacoes = sacar(valor, saldo, transacoes)
        
        when 4
        processar_extrato(transacoes)
        
        when 5
        puts "Obrigado por usar nosso sistema!"
        break
        
        else
        puts "Opção inválida!"
        end
    end
end

puts "\n" + "+=+=" * 69
puts "RELATÓRIO FINAL:"
puts "Saldo final: R$ #{saldo}"
puts "Total de transações: #{transacoes.length}"
puts "=" * 30
