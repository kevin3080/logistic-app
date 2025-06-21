CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS app_users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS clients (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS warehouses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS land_shipments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id UUID REFERENCES clients(id),
    product_id UUID REFERENCES products(id),
    quantity INT,
    registration_date DATE,
    delivery_date DATE,
    warehouse_id UUID REFERENCES warehouses(id),
    shipping_cost DOUBLE PRECISION,
    discounted_cost DOUBLE PRECISION,
    vehicle_plate VARCHAR(255),
    guide_number VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS maritime_shipments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id UUID REFERENCES clients(id),
    product_id UUID REFERENCES products(id),
    quantity INT,
    registration_date DATE,
    delivery_date DATE,
    port_id UUID REFERENCES ports(id),
    shipping_cost DOUBLE PRECISION,
    discounted_cost DOUBLE PRECISION,
    fleet_number VARCHAR(255),
    guide_number VARCHAR(255) UNIQUE
);

-- Datos iniciales
-- UUIDs have been corrected to contain only hexadecimal characters (0-9, a-f).

INSERT INTO app_users (username, password, roles)
VALUES (
    'admin',
    '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', -- password is 'password'
    'ROLE_USER,ROLE_ADMIN'
) ON CONFLICT (username) DO NOTHING;


-- Clients
INSERT INTO clients (id, name, email, phone) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Global Corp Ltd', 'contact@globalcorp.com', '+1-555-0100'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Local Retail Solutions', 'info@localretail.net', '+44-20-7123-4567'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'E-commerce Innovators', 'support@ecommerce-innov.org', '+61-2-9876-5432'),
('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'AgriFoods Inc.', 'sales@agrifoods.com', '+57-310-123-4567');


-- Products
INSERT INTO products (id, name, description) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 'Electronics Component Kit', 'Kit of various electronic components for assembly.'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Fresh Produce Assortment', 'Assorted fresh fruits and vegetables, refrigerated.'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 'Heavy Machinery Parts', 'Spare parts for industrial heavy machinery.'),
('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24', 'Textile Raw Materials', 'Large bales of cotton and synthetic fibers.');


-- Warehouses
INSERT INTO warehouses (id, name, address, city, country) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a31', 'Central Distribution Hub', '789 Logistics Park', 'Bogota', 'Colombia'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32', 'Regional North Depot', '101 Industrial Way', 'Medellin', 'Colombia'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'South America Gateway', '456 Export Blvd', 'Sao Paulo', 'Brazil');


-- Ports
INSERT INTO ports (id, name, address, city, country) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a41', 'Port of Buenaventura', 'Container Terminal 1', 'Buenaventura', 'Colombia'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a42', 'Port of Cartagena', 'Main Cargo Dock', 'Cartagena', 'Colombia'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a43', 'Port of Callao', 'Maritime Zone 3', 'Callao', 'Peru');


-- Land Shipments (discount: quantity > 10 get 5% off)
INSERT INTO land_shipments (client_id, product_id, quantity, registration_date, delivery_date, warehouse_id, shipping_cost, discounted_cost, vehicle_plate, guide_number) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 15, '2024-06-01', '2024-06-05', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a31', 200.00, 190.00, 'ABC123', 'LANDSHP001'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 8, '2024-06-02', '2024-06-06', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32', 120.00, 120.00, 'DEF456', 'LANDSHP002'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 25, '2024-06-03', '2024-06-07', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a31', 350.00, 332.50, 'GHI789', 'LANDSHP003'),
('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 12, '2024-06-04', '2024-06-08', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32', 180.00, 171.00, 'JKL012', 'LANDSHP004');


-- Maritime Shipments (discount: quantity > 10 get 3% off)
INSERT INTO maritime_shipments (client_id, product_id, quantity, registration_date, delivery_date, port_id, shipping_cost, discounted_cost, fleet_number, guide_number) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 500, '2024-05-10', '2024-06-10', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a41', 5000.00, 4850.00, 'FLT001A', 'MARITIME001'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24', 8, '2024-05-12', '2024-06-12', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a42', 1500.00, 1500.00, 'FLT002B', 'MARITIME002'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 150, '2024-05-15', '2024-06-15', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a41', 2500.00, 2425.00, 'FLT003C', 'MARITIME003'),
('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24', 10, '2024-05-18', '2024-06-18', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a43', 1800.00, 1800.00, 'FLT004D', 'MARITIME004');
