INSERT INTO merchants (id, merchant_name, merchant_id, password, bank_account_number)
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


INSERT INTO cards (id, card_number, cvv, expiration_month, expiration_year, holder_name, account_number
)
VALUES (
           'd77e5f8c-1d18-42c3-94c5-5df640b84af6', -- UUID for the card row
           '0777710000010000',                  -- Card number
           '451',                                -- CVV
           7,                                    -- Expiration month
           2022,                                 -- Expiration year
           'Lee James',                         -- Cardholder name
           '903859108343'                      -- Account number
       );

INSERT INTO bank_accounts (id, account_number, holder_name, balance
)
VALUES (
           '5bbf3d52-9ad5-4345-9ca6-e653de53daae',
           '903859108343',
           'Lee James',
           0.00
       );