use std::collections::HashMap;

struct Product {
    id: u32,
    name: String,
    price: f64,
    quantity: u32,
}

struct Order {
    order_id: u32,
    items: Vec<(u32, u32)>,
    total_amount: f64,
    is_paid: bool,
}

struct Store {
    inventory: HashMap<u32, Product>,
    orders: Vec<Order>,
    revenue: f64,
    next_order_id: u32,
}

impl Store {
    fn new() -> Self {
        Store {
            inventory: HashMap::new(),
            orders: Vec::new(),
            revenue: 0.0,
            next_order_id: 1,
        }
    }

    fn add_product(&mut self, id: u32, name: &str, price: f64, quantity: u32) {
        let product = Product {
            id,
            name: name.to_string(),
            price,
            quantity,
        };
        self.inventory.insert(id, product);
    }

    fn update_stock(&mut self, product_id: u32, amount: i32) -> bool {
        if let Some(product) = self.inventory.get_mut(&product_id) {
            let new_quantity = product.quantity as i32 + amount;
            if new_quantity >= 0 {
                product.quantity = new_quantity as u32;
                return true;
            }
        }
        false
    }

    fn create_order(&mut self, items: Vec<(u32, u32)>) -> Option<u32> {
        let mut current_total = 0.0;
        let mut valid_order = true;

        for (id, qty) in &items {
            if let Some(product) = self.inventory.get(id) {
                if product.quantity < *qty {
                    valid_order = false;
                    break;
                }
                current_total += product.price * (*qty as f64);
            } else {
                valid_order = false;
                break;
            }
        }

        if !valid_order {
            return None;
        }

        for (id, qty) in &items {
            self.update_stock(*id, -(*qty as i32));
        }

        let order = Order {
            order_id: self.next_order_id,
            items,
            total_amount: current_total,
            is_paid: false,
        };

        self.orders.push(order);
        let assigned_id = self.next_order_id;
        self.next_order_id += 1;
        
        Some(assigned_id)
    }

    fn process_payment(&mut self, order_id: u32) -> bool {
        let mut success = false;
        for order in &mut self.orders {
            if order.order_id == order_id && !order.is_paid {
                order.is_paid = true;
                self.revenue += order.total_amount;
                success = true;
                break;
            }
        }
        success
    }

    fn generate_report(&self) {
        println!("--- Store Report ---");
        println!("Revenue: ${:.2}", self.revenue);
        println!("Inventory Status:");
        for product in self.inventory.values() {
            println!("- {}: {} units (${:.2} each)", product.name, product.quantity, product.price);
        }
        println!("Orders processed: {}", self.orders.len());
        println!("--------------------");
    }

    fn apply_discount_to_all(&mut self, percentage: f64) {
        let factor = 1.0 - (percentage / 100.0);
        for product in self.inventory.values_mut() {
            product.price *= factor;
        }
    }

    fn clear_out_of_stock(&mut self) {
        let mut to_remove = Vec::new();
        for (&id, product) in &self.inventory {
            if product.quantity == 0 {
                to_remove.push(id);
            }
        }
        for id in to_remove {
            self.inventory.remove(&id);
        }
    }
}

fn main() {
    let mut my_store = Store::new();

    my_store.add_product(1, "Laptop", 1200.0, 10);
    my_store.add_product(2, "Mouse", 25.0, 50);
    my_store.add_product(3, "Keyboard", 75.0, 20);
    my_store.add_product(4, "Monitor", 300.0, 0);

    my_store.generate_report();

    let order_items = vec![(1, 2), (2, 5)];
    if let Some(id) = my_store.create_order(order_items) {
        println!("Order #{} created successfully.", id);
        if my_store.process_payment(id) {
            println!("Order #{} paid.", id);
        }
    }

    my_store.apply_discount_to_all(10.0);
    println!("Applied 10% discount to all products.");

    my_store.clear_out_of_stock();
    println!("Removed out of stock items.");

    let mut bulk_order = Vec::new();
    let mut i = 1;
    while i <= 3 {
        bulk_order.push((i, 1));
        i += 1;
    }

    match my_store.create_order(bulk_order) {
        Some(id) => println!("Bulk order #{} processed.", id),
        None => println!("Failed to process bulk order."),
    }

    let mut stock_to_add = vec![(1, 5), (2, 10), (3, 15)];
    for item in stock_to_add {
        my_store.update_stock(item.0, item.1 as i32);
    }

    my_store.generate_report();

    let final_orders_count = my_store.orders.len();
    if final_orders_count > 0 {
        let mut total_items_sold = 0;
        for order in &my_store.orders {
            for item in &order.items {
                total_items_sold += item.1;
            }
        }
        println!("Total items sold today: {}", total_items_sold);
    }
}