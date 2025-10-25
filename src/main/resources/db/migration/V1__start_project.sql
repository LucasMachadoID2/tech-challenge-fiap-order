CREATE TABLE clients
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(11),
    email VARCHAR(100)
);

CREATE TABLE payments
(
    id INTEGER PRIMARY KEY,
    qrCode TEXT,
    qrImage TEXT,
    status VARCHAR(50)
);

CREATE TABLE products
(
    id INTEGER PRIMARY KEY,
    category VARCHAR(50),
    name VARCHAR(255),
    description VARCHAR(500),
    image TEXT,
    price INTEGER,
    price_for_client INTEGER,
    quantity INTEGER
);

CREATE TABLE orders
(
    id INTEGER PRIMARY KEY,
    status VARCHAR(50),
    client_id INTEGER,
    payment_id INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT fk_order_payment FOREIGN KEY (payment_id) REFERENCES payments(id)
);


CREATE TABLE products_orders
(
    product_id INTEGER,
    order_id INTEGER,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);