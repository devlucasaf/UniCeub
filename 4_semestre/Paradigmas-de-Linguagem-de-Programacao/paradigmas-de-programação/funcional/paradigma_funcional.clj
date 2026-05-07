(ns loja.funcional)

;; Lista de produtos

(def produtos
    [
        {:id 1 :nome "Notebook" :categoria "Eletrônicos"    :preco 3500}
        {:id 2 :nome "Mouse"    :categoria "Periféricos"    :preco 120}
        {:id 3 :nome "Teclado"  :categoria "Periféricos"    :preco 250}
        {:id 4 :nome "Monitor"  :categoria "Eletrônicos"    :preco 900}
        {:id 5 :nome "Headset"  :categoria "Áudio"          :preco 300}
    ]
)

;; Função para exibir produtos

(defn exibir-produtos [lista]
    (doseq [produto lista]
        (println
            (str "ID: " (:id produto)
                " | Nome: " (:nome produto)
                " | Categoria: " (:categoria produto)
                " | Preço: R$" (:preco produto)))))

;; Filtrar produtos caros

(defn produtos-caros [lista valor-minimo]
    (filter #(> (:preco %) valor-minimo) lista))

;; Aplicar desconto

(defn aplicar-desconto [produto percentual]
    (update produto :preco #(* % (- 1 percentual))))

;; Aplicar desconto em todos

(defn desconto-geral [lista percentual]
    (map #(aplicar-desconto % percentual) lista))

;; Somar valores dos produtos

(defn valor-total [lista]
    (reduce + (map :preco lista)))

;; Buscar produto por categoria

(defn buscar-por-categoria [lista categoria]
    (filter #(= (:categoria %) categoria) lista))

;; Programa principal

(defn -main []
    
    (println "\n===== TODOS OS PRODUTOS =====")
    (exibir-produtos produtos)

    (println "\n===== PRODUTOS ACIMA DE R$500 =====")
    (exibir-produtos (produtos-caros produtos 500))

    (println "\n===== PRODUTOS COM 10% DE DESCONTO =====")
    (exibir-produtos (desconto-geral produtos 0.10))

    (println "\n===== PRODUTOS DA CATEGORIA ELETRÔNICOS =====")
    (exibir-produtos
        (buscar-por-categoria produtos "Eletrônicos"))

    (println "\n===== VALOR TOTAL DO ESTOQUE =====")
    (println
        (str "R$" (valor-total produtos))))

;; Executar programa

(-main)