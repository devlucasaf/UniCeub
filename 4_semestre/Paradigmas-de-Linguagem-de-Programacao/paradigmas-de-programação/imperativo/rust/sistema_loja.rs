use std::collections::HashMap;

struct Produto {
    id: u32,
    nome: String,
    preco: f64,
    quantidade: u32,
}

struct Pedido {
    id_pedido: u32,
    itens: Vec<(u32, u32)>,
    valor_total: f64,
    esta_pago: bool,
}

struct Loja {
    estoque: HashMap<u32, Produto>,
    pedidos: Vec<Pedido>,
    receita: f64,
    proximo_id_pedido: u32,
}

impl Loja {
    fn nova() -> Self {
        Loja {
            estoque: HashMap::new(),
            pedidos: Vec::new(),
            receita: 0.0,
            proximo_id_pedido: 1,
        }
    }

    fn adicionar_produto(
        &mut self,
        id: u32,
        nome: &str,
        preco: f64,
        quantidade: u32,
    ) {
        let produto = Produto {
            id,
            nome: nome.to_string(),
            preco,
            quantidade,
        };

        self.estoque.insert(id, produto);
    }

    fn atualizar_estoque(
        &mut self,
        id_produto: u32,
        quantidade: i32,
    ) -> bool {
        if let Some(produto) = self.estoque.get_mut(&id_produto) {
            let nova_quantidade = produto.quantidade as i32 + quantidade;

            if nova_quantidade >= 0 {
                produto.quantidade = nova_quantidade as u32;
                return true;
            }
        }

        false
    }

    fn criar_pedido(
        &mut self,
        itens: Vec<(u32, u32)>,
    ) -> Option<u32> {
        let mut total_atual = 0.0;
        let mut pedido_valido = true;

        for (id, quantidade) in &itens {
            if let Some(produto) = self.estoque.get(id) {
                if produto.quantidade < *quantidade {
                    pedido_valido = false;
                    break;
                }

                total_atual += produto.preco * (*quantidade as f64);
            } else {
                pedido_valido = false;
                break;
            }
        }

        if !pedido_valido {
            return None;
        }

        for (id, quantidade) in &itens {
            self.atualizar_estoque(*id, -(*quantidade as i32));
        }

        let pedido = Pedido {
            id_pedido: self.proximo_id_pedido,
            itens,
            valor_total: total_atual,
            esta_pago: false,
        };

        self.pedidos.push(pedido);

        let id_atribuido = self.proximo_id_pedido;
        self.proximo_id_pedido += 1;

        Some(id_atribuido)
    }

    fn processar_pagamento(&mut self, id_pedido: u32) -> bool {
        let mut sucesso = false;

        for pedido in &mut self.pedidos {
            if pedido.id_pedido == id_pedido && !pedido.esta_pago {
                pedido.esta_pago = true;
                self.receita += pedido.valor_total;
                sucesso = true;
                break;
            }
        }

        sucesso
    }

    fn gerar_relatorio(&self) {
        println!("--- Relatório da Loja ---");
        println!("Receita: R$ {:.2}", self.receita);
        println!("Situação do estoque:");

        for produto in self.estoque.values() {
            println!(
                "- {}: {} unidades (R$ {:.2} cada)",
                produto.nome,
                produto.quantidade,
                produto.preco
            );
        }

        println!("Pedidos processados: {}", self.pedidos.len());
        println!("-------------------------");
    }

    fn aplicar_desconto_em_todos(&mut self, porcentagem: f64) {
        let fator = 1.0 - (porcentagem / 100.0);

        for produto in self.estoque.values_mut() {
            produto.preco *= fator;
        }
    }

    fn remover_produtos_sem_estoque(&mut self) {
        let mut produtos_para_remover = Vec::new();

        for (&id, produto) in &self.estoque {
            if produto.quantidade == 0 {
                produtos_para_remover.push(id);
            }
        }

        for id in produtos_para_remover {
            self.estoque.remove(&id);
        }
    }
}

fn main() {
    let mut minha_loja = Loja::nova();

    minha_loja.adicionar_produto(1, "Notebook", 1200.0, 10);
    minha_loja.adicionar_produto(2, "Mouse", 25.0, 50);
    minha_loja.adicionar_produto(3, "Teclado", 75.0, 20);
    minha_loja.adicionar_produto(4, "Monitor", 300.0, 0);

    minha_loja.gerar_relatorio();

    let itens_pedido = vec![(1, 2), (2, 5)];

    if let Some(id) = minha_loja.criar_pedido(itens_pedido) {
        println!("Pedido número {} criado com sucesso.", id);

        if minha_loja.processar_pagamento(id) {
            println!("Pedido número {} pago.", id);
        }
    }

    minha_loja.aplicar_desconto_em_todos(10.0);
    println!("Desconto de 10% aplicado em todos os produtos.");

    minha_loja.remover_produtos_sem_estoque();
    println!("Produtos sem estoque foram removidos.");

    let mut pedido_em_lote = Vec::new();
    let mut i = 1;

    while i <= 3 {
        pedido_em_lote.push((i, 1));
        i += 1;
    }

    match minha_loja.criar_pedido(pedido_em_lote) {
        Some(id) => {
            println!("Pedido em lote número {} processado.", id);
        }
        None => {
            println!("Não foi possível processar o pedido em lote.");
        }
    }

    let estoque_para_adicionar = vec![
        (1, 5),
        (2, 10),
        (3, 15),
    ];

    for item in estoque_para_adicionar {
        minha_loja.atualizar_estoque(
            item.0,
            item.1 as i32,
        );
    }

    minha_loja.gerar_relatorio();

    let quantidade_final_pedidos = minha_loja.pedidos.len();

    if quantidade_final_pedidos > 0 {
        let mut total_itens_vendidos = 0;

        for pedido in &minha_loja.pedidos {
            for item in &pedido.itens {
                total_itens_vendidos += item.1;
            }
        }

        println!(
            "Total de itens vendidos hoje: {}",
            total_itens_vendidos
        );
    }
}
