import 'dart:html';

void main() {
    DivElement container = DivElement();
    container.style.width = '400px';
    container.style.margin = '50px auto';
    container.style.padding = '20px';
    container.style.border = '1px solid #ccc';
    container.style.borderRadius = '10px';
    container.style.fontFamily = 'Arial';

    HeadingElement titulo = HeadingElement.h2();
    titulo.text = 'Calculadora de Média';
    titulo.style.textAlign = 'center';

    LabelElement labelN1 = LabelElement();
    labelN1.text = 'Nota 1:';

    InputElement inputN1 = InputElement();
    inputN1.type = 'number';
    inputN1.placeholder = 'Digite a primeira nota';
    inputN1.style.width = '100%';
    inputN1.style.marginBottom = '10px';

    LabelElement labelN2 = LabelElement();
    labelN2.text = 'Nota 2:';

    InputElement inputN2 = InputElement();
    inputN2.type = 'number';
    inputN2.placeholder = 'Digite a segunda nota';
    inputN2.style.width = '100%';
    inputN2.style.marginBottom = '10px';

    ButtonElement botaoCalcular = ButtonElement();
    botaoCalcular.text = 'Calcular Média';
    botaoCalcular.style.width = '100%';
    botaoCalcular.style.padding = '10px';
    botaoCalcular.style.marginTop = '10px';
    botaoCalcular.style.cursor = 'pointer';

    ButtonElement botaoLimpar = ButtonElement();
    botaoLimpar.text = 'Limpar';
    botaoLimpar.style.width = '100%';
    botaoLimpar.style.padding = '10px';
    botaoLimpar.style.marginTop = '10px';
    botaoLimpar.style.cursor = 'pointer';

    ParagraphElement resultado = ParagraphElement();
    resultado.text = 'Resultado aparecerá aqui';
    resultado.style.marginTop = '20px';
    resultado.style.fontWeight = 'bold';
    resultado.style.textAlign = 'center';

    ParagraphElement status = ParagraphElement();
    status.text = '';
    status.style.marginTop = '10px';
    status.style.textAlign = 'center';

    void calcularMedia(Event e) {
        double? n1 = double.tryParse(inputN1.value ?? '');
        double? n2 = double.tryParse(inputN2.value ?? '');

        if (n1 == null || n2 == null) {
        resultado.text = 'Por favor, insira valores válidos!';
        resultado.style.color = 'red';
        status.text = '';
        return;
        }

        double media = (n1 + n2) / 2;

        resultado.text = 'Média: ${media.toStringAsFixed(2)}';
        resultado.style.color = 'green';

        if (media >= 7) {
        status.text = 'Aprovado';
        status.style.color = 'green';
        } else if (media >= 5) {
        status.text = 'Recuperação';
        status.style.color = 'orange';
        } else {
        status.text = 'Reprovado';
        status.style.color = 'red';
        }
    }

    void limparCampos(Event e) {
        inputN1.value = '';
        inputN2.value = '';
        resultado.text = 'Resultado aparecerá aqui';
        resultado.style.color = 'black';
        status.text = '';
    }

    inputN1.onInput.listen((event) {
        inputN1.style.border = '1px solid #ccc';
    });

    inputN2.onInput.listen((event) {
        inputN2.style.border = '1px solid #ccc';
    });

    botaoCalcular.onClick.listen(calcularMedia);
    botaoLimpar.onClick.listen(limparCampos);

    container.children.add(titulo);
    container.children.add(labelN1);
    container.children.add(inputN1);
    container.children.add(labelN2);
    container.children.add(inputN2);
    container.children.add(botaoCalcular);
    container.children.add(botaoLimpar);
    container.children.add(resultado);
    container.children.add(status);

    document.body?.children.add(container);

    DivElement rodape = DivElement();
    rodape.text = 'Aplicação Dart Web usando dart:html';
    rodape.style.textAlign = 'center';
    rodape.style.marginTop = '30px';
    rodape.style.fontSize = '12px';
    rodape.style.color = '#777';

    document.body?.children.add(rodape);

    DivElement info = DivElement();
    info.style.marginTop = '20px';
    info.style.fontSize = '14px';

    ParagraphElement p1 = ParagraphElement();
    p1.text = 'Digite duas notas para calcular a média.';

    ParagraphElement p2 = ParagraphElement();
    p2.text = 'A média será exibida junto com o status do aluno.';

    ParagraphElement p3 = ParagraphElement();
    p3.text = 'Este exemplo utiliza eventos e manipulação do DOM com Dart.';

    info.children.add(p1);
    info.children.add(p2);
    info.children.add(p3);

    container.children.add(info);

    DivElement decoracao = DivElement();
    decoracao.style.height = '5px';
    decoracao.style.marginTop = '20px';
    decoracao.style.backgroundColor = '#4CAF50';

    container.children.add(decoracao);
}
