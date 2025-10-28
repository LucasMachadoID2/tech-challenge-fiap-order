# language: pt
Funcionalidade: Order

  Cenario: Busca de Produtos ordenados por status e data de criação, ignorando status finalizado e criado
    Dado que exista produtos cadastrados no sistema
      | id                                   | category       | name         | description        | image       | price | priceForClient | quantity |
      | 2848657e-a25d-41ac-bea6-917623abc678 | LANCHE         | Hamburguer   | Hamburguer caseiro | image1_link | 1500  |                | 1        |
      | 8740c537-cc34-4599-bded-7d7ba2375b9d | ACOMPANHAMENTO | Salada       | Salada caesar      | image2_link | 800   |                | 1        |
      | c5fda290-f712-4302-8358-6bea10f8868f | BEBIDA         | Refrigerante | Coca-cola          | image3_link | 500   | 400            | 1        |
    E que exista pagamentos cadastrados no sistema
      | id                                   | qrImage | qrCode  | status  |
      | d9745315-ad04-4a8d-93df-c4e00d887d10 | codeQr1 | qrCode1 | CREATED |
      | 41a8f832-2748-4bd1-89ce-455480d90813 | codeQr1 | qrCode2 | PAID    |
      | 4f031c55-d04b-48ca-9142-de04962c395b | codeQr1 | qrCode3 | PAID    |
      | 18daac4e-e0b8-4efd-93a1-02d24ee9f4f8 | codeQr1 | qrCode3 | PAID    |
      | a1faa0db-cf47-4859-816e-5720f41a83ae | codeQr1 | qrCode3 | PAID    |
    E que exista pedidos cadastrados no sistema
      | id                                   | status         | clientId | productIds                                                                | paymentId                            | createdAt           |
      | 96aa213f-25ed-4b8d-90e0-3e0593b33554 | CREATED        |          | 2848657e-a25d-41ac-bea6-917623abc678,8740c537-cc34-4599-bded-7d7ba2375b9d | d9745315-ad04-4a8d-93df-c4e00d887d10 | 27/10/2025 17:20:00 |
      | 21069019-67b4-4c00-a4e6-dcd859803b9f | RECEIVED       |          | 8740c537-cc34-4599-bded-7d7ba2375b9d                                      | 41a8f832-2748-4bd1-89ce-455480d90813 | 27/10/2025 17:10:00 |
      | 030bfe10-6dcb-47ae-9996-d09b3f9afe95 | IN_PREPARATION |          | 2848657e-a25d-41ac-bea6-917623abc678,c5fda290-f712-4302-8358-6bea10f8868f | 4f031c55-d04b-48ca-9142-de04962c395b | 27/10/2025 17:05:00 |
      | 0ca67906-00eb-4513-98c2-0d9f3036000e | READY          |          | 2848657e-a25d-41ac-bea6-917623abc678,c5fda290-f712-4302-8358-6bea10f8868f | 18daac4e-e0b8-4efd-93a1-02d24ee9f4f8 | 27/10/2025 17:02:00 |
      | b181e410-73ae-4fc9-90a7-f88dd0d98376 | FINALIZED      |          | 2848657e-a25d-41ac-bea6-917623abc678                                      | a1faa0db-cf47-4859-816e-5720f41a83ae | 27/10/2025 17:00:00 |
    Quando for feito a request para buscar pedidos
    Entao o retorno deverá ser
      | id                                   | status         | clientId | productIds                                                                 | paymentId                            | createdAt           |
      | 030bfe10-6dcb-47ae-9996-d09b3f9afe95 | IN_PREPARATION |          | 2848657e-a25d-41ac-bea6-917623abc678,c5fda290-f712-4302-8358-6bea10f8868f  | 4f031c55-d04b-48ca-9142-de04962c395b | 27/10/2025 17:05:00 |
      | 0ca67906-00eb-4513-98c2-0d9f3036000e | READY          |          | 2848657e-a25d-41ac-bea6-917623abc678,3c5fda290-f712-4302-8358-6bea10f8868f | 18daac4e-e0b8-4efd-93a1-02d24ee9f4f8 | 27/10/2025 17:02:00 |
      | 21069019-67b4-4c00-a4e6-dcd859803b9f | RECEIVED       |          | 8740c537-cc34-4599-bded-7d7ba2375b9d                                       | 41a8f832-2748-4bd1-89ce-455480d90813 | 27/10/2025 17:10:00 |