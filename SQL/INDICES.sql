-- INDEX para buscar por tipo de moneda en cuentas
CREATE INDEX idx_account_currency ON account(currency);

-- INDEX para ordenar o filtrar transacciones por fecha
CREATE INDEX idx_transaction_date ON transaction(transaction_date);

-- INDEX para consultar productos financieros por fecha de vencimiento
CREATE INDEX idx_financial_product_due_date ON financial_product(due_date);
CREATE INDEX idx_financial_product_start_date ON financial_product(start_date);

-- INDEX para buscar tarjetas por nombre del titular
CREATE INDEX idx_credit_card_user_name ON credit_card(user_name);

-- INDEX para filtrar préstamos por interés
CREATE INDEX idx_loan_interest ON loan(interest);

-- INDEX para filtrar plazos fijos por interés
CREATE INDEX idx_time_deposit_interest ON time_deposit(interest);

-- INDEX para login o búsqueda rápida de usuario por email
-- Usá UNIQUE si querés forzar que no haya dos usuarios con el mismo email
CREATE UNIQUE INDEX idx_user_email ON user(email);

