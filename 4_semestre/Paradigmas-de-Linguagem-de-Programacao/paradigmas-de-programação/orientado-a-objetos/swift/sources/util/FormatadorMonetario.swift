import Foundation

enum FormatadorMonetario {

    static func formatarValorEmReais(valorMonetario: Double) -> String {
        let valorAbsolutoFormatado = String(format: "%.2f", abs(valorMonetario))
            .replacingOccurrences(of: ".", with: ",")

        return valorMonetario < 0 ? "-R$ \(valorAbsolutoFormatado)" : "R$ \(valorAbsolutoFormatado)"
    }
}
