CREATE EXTENSION IF NOT EXISTS pgcrypto; -- para gen_random_uuid()

CREATE TABLE clients
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE,
    email VARCHAR(100) UNIQUE,
    client_id VARCHAR(100) UNIQUE
);

CREATE TABLE payments
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    qrCode TEXT,
    qrImage TEXT,
    status VARCHAR(50),
    payment_id VARCHAR(100) UNIQUE
);

CREATE TABLE products
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category VARCHAR(50),
    name VARCHAR(255),
    description VARCHAR(500),
    image TEXT,
    price INTEGER,
    price_for_client INTEGER,
    quantity INTEGER,
    product_id VARCHAR(100) UNIQUE
);

CREATE TABLE orders
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(50),
    client_id UUID,
    payment_id UUID,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT fk_order_payment FOREIGN KEY (payment_id) REFERENCES payments(id)
);

CREATE TABLE products_orders
(
    product_id UUID,
    order_id UUID,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);
