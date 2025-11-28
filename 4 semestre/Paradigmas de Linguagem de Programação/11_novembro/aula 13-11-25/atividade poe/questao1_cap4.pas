{*
Paradigmas de Linguagem de Programação
Aula: 13-11-25
Programação Orientada a Eventos
Capítulo 4 - Livro Base sobre Paradigma Orientado a Eventos

1. Marque V para Verdadeiro e F para Falso, considerando que as afirmações
indicam eventos de acordo com o paradigma estudado neste capítulo:
    ( ) Clicar com o botão esquerdo do mouse
    ( ) Acessar uma tabela no banco de dados
    ( ) Pressionar uma tecla sobre um controle
    ( ) Mostrar um componente (controle) na tela
    ( ) Entrar ou sair de um formulário (janela)

2. Complete as lacunas:
    Os controles, também podem ser chamados de componentes ou objetos.
    Os controles possuem ________________ que são as suas características e
    _____________ que permitem a inserção de código-fonte associado à
    ocorrência de ____________________________.
*}

unit Unit1;

{$mode objfpc}{$H+}

interface

uses
    Classes, SysUtils, Forms, Controls, Graphics, Dialogs, StdCtrls;

type
    { TForm1 }
    TForm1 = class(TForm)
        lblNumero1: TLabel;
        lblNumero2: TLabel;
        edtNumero1: TEdit;
        edtNumero2: TEdit;
        btnSomar: TButton;
        btnLimpar: TButton;

    procedure btnSomarClick(Sender: TObject);
    procedure btnLimparClick(Sender: TObject);
    private

    public

end;

var
    Form1: TForm1;

implementation

{$R *.lfm}

procedure TForm1.btnSomarClick(Sender: TObject);
var
    n1, n2, soma: Integer;
begin
    n1 := StrToInt(edtNumero1.Text);
    n2 := StrToInt(edtNumero2.Text);
    soma := n1 + n2;

    ShowMessage('Resultado da soma: ' + IntToStr(soma));
end;

procedure TForm1.btnLimparClick(Sender: TObject);
begin
    edtNumero1.Clear;
    edtNumero2.Clear;
    edtNumero1.SetFocus;
end;

end.
