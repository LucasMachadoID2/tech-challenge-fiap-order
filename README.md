# 🧾 Client Microservice - Tech Challenge

## 📖 Sobre o projeto

O Client Microservice é o serviço de pedidos do **Fast Food FIAP** para a fase 4 do Tech Challenge, implementado em Java
21 com Spring Boot 3. Ele expõe operações REST para CRUD de pedidos,
valida dados de entrada, persiste em Postgres e entrega contratos claros de erro.

## 📌 Estrutura de Endpoints do Microsserviço de Pedidos

| Método | Endpoint                                     | Descrição                                   |
|--------|----------------------------------------------|---------------------------------------------|
| GET    | /v1/orders                                   | Lista todos os pedidos                      |
| POST   | /v1/orders                                   | Cria um novo pedido                         |
| PUT    | /v1/orders/{orderId}                         | Atualiza o status de um pedido              |
| PUT    | /v1/orders/update-payment-status/{paymentId} | Atualiza o status de um pagamento de pedido |

### Passo a Passo

1. **Clone o repositório**

   ```bash
   git clone git@github.com:LucasMachadoID2/tech-challenge-fiap-order.git
   cd tech-challenge-fiap-order

   ```
2. **Startando a aplicação com Docker Compose**

   ```bash
   docker-compose up -d
   ```

<br>

## 🙋‍♀️ Equipe

| Nome                               | RA     | Nome Discord                 |
|------------------------------------|--------|------------------------------|
| Danilo Augusto Pereira             | 364411 | Danilo Augusto - RM364411    |
| Gabriela Trindade Ferreira         | 364756 | Gabriela Ferreira - RM364756 |
| Guilherme Garcia Dos Santos Moraes | 364613 | Guilherme Garcia - RM364613  |
| Lucas Matheus Monteiro Machado     | 361059 | Lucas Machado - RM361059     |
| Marjory Bispo Matos                | 361150 | Marjory Matos - RM361150     |

```

```
