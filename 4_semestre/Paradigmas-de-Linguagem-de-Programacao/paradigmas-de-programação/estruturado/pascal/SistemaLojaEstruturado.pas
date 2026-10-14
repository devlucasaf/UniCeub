program SistemaLojaEstruturado;

uses crt;

var
    opcao: integer;
    nomeProduto: string;
    precoProduto: real;
    quantidade: integer;
    totalCompra: real;

procedure Linha;
begin
    writeln('------------------------------------------');
end;

procedure CadastroProduto;
begin
    clrscr;
    Linha;
    writeln('       CADASTRO DE PRODUTO');
    Linha;

    write('Digite o nome do produto: ');
    readln(nomeProduto);

    write('Digite o preco do produto: ');
    readln(precoProduto);

    write('Digite a quantidade: ');
    readln(quantidade);

    writeln;
    writeln('Produto cadastrado com sucesso!');
    writeln('Nome: ', nomeProduto);
    writeln('Preco: R$ ', precoProduto:0:2);
    writeln('Quantidade: ', quantidade);

    readln;
end;

procedure CalcularCompra;
begin
    clrscr;
    Linha;
    writeln('        CALCULO DE COMPRA');
    Linha;

    write('Digite o nome do produto: ');
    readln(nomeProduto);

    write('Digite o preco do produto: ');
    readln(precoProduto);

    write('Digite a quantidade comprada: ');
    readln(quantidade);

    totalCompra := precoProduto * quantidade;

    writeln;
    Linha;
    writeln('RESUMO DA COMPRA');
    Linha;
    writeln('Produto: ', nomeProduto);
    writeln('Preco unitario: R$ ', precoProduto:0:2);
    writeln('Quantidade: ', quantidade);
    writeln('Valor total: R$ ', totalCompra:0:2);

    readln;
end;

procedure Menu;
begin
    writeln('=========== MENU ===========');
    writeln('1 - Cadastrar produto');
    writeln('2 - Calcular compra');
    writeln('3 - Sair');
    Linha;
    write('Escolha uma opcao: ');
    readln(opcao);
end;

begin
    totalCompra := 0;

    repeat
        clrscr;
        Menu;

        case opcao of
            1: CadastroProduto;
            2: CalcularCompra;
            3:
            begin
                writeln;
                writeln('Encerrando o sistema...');
                readln;
            end;
        else
            begin
                writeln;
                writeln('Opcao invalida!');
                readln;
            end;
        end;

    until opcao = 3;

end.