from fpdf import FPDF

class RelatorioPDF(FPDF):
    def header(self):
        self.set_fill_color(10, 45, 80)

        self.rect(0, 0, 210, 25, "F")
        self.set_text_color(255, 255, 255)

        self.set_font("Arial", "B", 22)
        self.cell(0, 15, "RELATÓRIO DE PERFORMANCE", ln=True, align="C")

        self.ln(5)

    def footer(self):
        self.set_y(-15)

        self.set_font("Arial", "", 8)
        self.set_text_color(100,100,100)

        self.cell(0, 10, f"Pagina {self.page_no()}", align="C")


def gerar_pdf(dados, grafico_path):
    pdf = RelatorioPDF()
    pdf.add_page()

    pdf.set_text_color(0,0,0)

    # Título da análise
    pdf.set_font("Arial", "B", 16)
    pdf.cell(0, 10, "Resumo Executivo", ln=True)

    pdf.ln(3)

    # Resumo gerado pelo Gemini
    pdf.set_font("Arial", "", 11)

    pdf.multi_cell(0,6,dados.resumo)

    pdf.ln(5)

    # Inserção do gráfico
    pdf.set_font("Arial", "B", 14)
    pdf.cell(0, 10, "Indicadores", ln=True)

    pdf.image(grafico_path, x=30, w=150)

    pdf.ln(100)

    altura_cabecalho = 10
    altura_linha = 10

    altura_tabela = altura_cabecalho + (len(dados.historico) * altura_linha)

    espaco_restante = pdf.h - pdf.get_y() - pdf.b_margin

    if altura_tabela > espaco_restante:
        pdf.add_page()

    # Tabela de dados
    pdf.set_font("Arial", "B", 12)

    if dados.estilo == "barras":
        pdf.cell(70, 10, "Rotulo", 1)
        pdf.cell(50, 10, "Valor", 1)
        pdf.cell(70, 10, "Tipo", 1)
        pdf.ln()

        pdf.set_font("Arial", "", 11)

        for item in dados.historico:
            pdf.cell(70, 10, item.rotulo, 1)
            pdf.cell(50, 10, f"{str(item.valor)}%", 1)
            pdf.cell(70, 10, item.tipo, 1)

            pdf.ln()

    else:
        pdf.output("Relatorio_Final.pdf")

    print("PDF criado com sucesso!")
