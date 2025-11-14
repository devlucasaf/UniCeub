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
