INSERT INTO merchants (id, merchant_name, merchant_external_id, password, bank_account_number)
VALUES (
           'e4eaaaf2-d142-11e1-b3e4-080027620cdd',
           'Test Merchant',
           'merchant_id_001',
           'securepassword',
           '1234567890123456'
) ON CONFLICT DO NOTHING;


INSERT INTO cards (id, card_number, cvv, expiration_month, expiration_year, holder_name, account_number
)
VALUES (
           '5cc514dd-6540-47ed-8b49-19108f41b99e', -- UUID for the card row
           '10009000301010100',                  -- Card number
           '222',                                -- CVV
           2,                                    -- Expiration month
           2026,                                 -- Expiration year
           'Eeby Jeeby',                         -- Cardholder name
           '10109091010331'                      -- Account number
);

INSERT INTO bank_accounts (id, account_number, holder_name, balance
)
VALUES (
           'a4eaaaf2-d142-11e1-b3e4-080027620cdd',
           '10109091010331',
           'Eeby Jeeby',
           1000.00
 );
