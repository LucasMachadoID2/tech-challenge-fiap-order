# 🧾 Tech Challenge - Sistema de Autoatendimento para Lanchonete

Este é um sistema de autoatendimento para uma lanchonete em expansão, desenvolvido como parte do **Tech Challenge da FIAP**, que integra conhecimentos de todas as disciplinas da fase. O projeto é essencial para automatizar e organizar o processo de pedidos, desde a escolha dos produtos até a entrega ao cliente.

# 📌 Requisitos de Negócio

### 🧑‍💼 Cliente (Autoatendimento)

- Pode se identificar por CPF
- Pode se cadastrar (nome, e-mail)
- Pode continuar sem se identificar

### Montagem do Pedido

- Selecionar **Lanche** (nome, descrição, preço)
- Selecionar **Acompanhamento** (nome, descrição, preço)
- Selecionar **Bebida** (nome, descrição, preço)

### Resumo e Confirmação do Pedido

- Exibir os itens selecionados e o valor total antes de pagar

### Pagamento

- Integrado via **QRCode do Mercado Pago**

### Acompanhamento do Pedido (pelo cliente)

- Visualizar o status:
  - Recebido
  - Em preparação
  - Pronto
  - Finalizado

### Notificação para Retirada

- O cliente é notificado quando o pedido está pronto

### 👨‍🍳 Cozinha

- Visualizar pedidos recebidos
- Atualizar o status do pedido:
  - Em preparação
  - Pronto
  - Finalizado

### 🛠️ Administrador (Painel de Gestão)

- Cadastrar/editar/excluir produtos com:
  - Nome
  - Descrição
  - Preço
  - Imagem
  - Categoria
    - Lanche
    - Acompanhamento
    - Bebida
    - Sobremesa

### Gerenciar Categorias

- Categorias:
  - Lanche
  - Acompanhamento
  - Bebida
  - Sobremesa

### Acompanhar Pedidos em Tempo Real

- Ver status atual dos pedidos
- Ver tempo de espera por pedido

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **MongoDB**
- **Mercado Pago SDK**
- **Docker**
- **Lombok**
- **Kubernets**

## 📁 Estrutura do Projeto

- `adapter`: camada de interface (controllers, DTOs, handlers).
- `core`: domínio (casos de uso, entidades, interfaces).
- `application`: implementação dos casos de uso.
- `infrastructure`: configurações externas (ex: Mercado Pago).
- `util`: enums, exceptions e conversores.
- `k8s`: arquivos de configuração do Kubernetes.

## 🏗️ Arquitetura da Solução

### 🧱 Arquitetura Hexagonal (Ports and Adapters)

O projeto adota a arquitetura hexagonal para promover separação de responsabilidades, facilitar testes e permitir a substituição de tecnologias externas com baixo acoplamento.

- **Camada de entrada (Inbound Adapter)**: Controladores REST responsáveis por receber requisições HTTP e convertê-las para os casos de uso da aplicação.
- **Camada de aplicação (Use Cases)**: Contém a lógica central de negócios e orquestra as chamadas entre o domínio e os adaptadores.
- **Camada de saída (Outbound Adapter)**: Implementações de acesso a dados (MongoDB), integração com serviços externos (Mercado Pago), entre outros.
- **Banco de Dados**: MongoDB, utilizado para persistência dos dados de clientes, produtos, pedidos e pagamentos.
- **Pagamento**: Integração com a API do Mercado Pago utilizando QRCode.
- **Containers**: O MongoDB é executado em container Docker para facilitar o desenvolvimento e testes locais.

```
               +-------------------------+
               |   Interface do Cliente  |
               |     (HTTP REST API)     |
               +------------+------------+
                            |
            +---------------v---------------+
            |        Camada de Entrada      |
            |     (Controllers REST - API)  |
            +---------------+---------------+
                            |
            +---------------v---------------+
            |       Casos de Uso (Core)     |
            |   Regras de Negócio e Fluxos  |
            +---------------+---------------+
                            |
        +-------------------+-------------------+
        |                                       |
+-------v--------+                     +--------v--------+
| Banco de Dados |                     | Serviços Externos|
|   MongoDB      |                     |  Mercado Pago    |
+----------------+                     +------------------+
```

## 🏗️ Arquitetura da infraestrutura

<img src="./docs/k8s.gif" alt="Descrição do GIF" width="800">

## 🚀 Como Executar

### Pré-requisitos

- Minikube

### Passo a Passo

1. **Clone o repositório**

   ```bash
   git clone https://github.com/LucasMachadoID2/tech-challenge-fiap
   cd tech-challenge-fiap
   ```

2. **Inicie o Minikube**
   ```bash
   minikube start --driver=docker
   ```
3. **Aplique os manifestos Kubernets**

   ```bash
   kubectl apply -f k8s/
   ```

4. **Confirme se tudo esta rodandos**

   ```bash
   kubectl get all
   ```

5. **Acesse o link swagger**

   ```bash
   http://localhost:8080/swagger-ui/index.html
   ```

   ou execute o comando

   ```bash
   minikube service tech-chall-service
   ```

<br>

## 📫 Endpoints Principais

**Clientes:**
| Método | Endpoint | Descrição |
| ------ | ------------- | ------------------------ |
| GET | `/v1/clients` | Listar todos os clientes |
| POST | `/v1/clients` | Criar um cliente |
<br>

**Produtos:**
| Método | Endpoint | Descrição |
| ------ | ------------------------------------------ | ------------------------ |
| GET | `/v1/productEntities` | Listar todos os produtos |
| POST | `/v1/productEntities` | Criar um produto |
| GET | `/v1/productEntities/category?category=SOBREMESA` | Filtrar por categoria |
<br>

**Pedidos:**
| Método | Endpoint | Descrição |
| ------ | --------------------------------------- | -------------------------- |
| POST | `/v1/orders` | Criar um pedido |
| PATCH | `/v1/orders/{id}?status=IN_PREPARATION` | Atualizar status do pedido |
<br>

**Pagamentos:**
| Método | Endpoint | Descrição |
| ------ | -------------- | ----------------------------- |
| PATCH | `/v1/payments` | Atualizar status do pagamento |
<br>

## 🙋‍♀️ Equipe

| Nome                               | RA     | Nome Discord                 |
| ---------------------------------- | ------ | ---------------------------- |
| Danilo Augusto Pereira             | 364411 | Danilo Augusto - RM364411    |
| Gabriela Trindade Ferreira         | 364756 | Gabriela Ferreira - RM364756 |
| Guilherme Garcia Dos Santos Moraes | 364613 | Guilherme Garcia - RM364613  |
| Lucas Matheus Monteiro Machado     | 361059 | Lucas Machado - RM361059     |
| Marjory Bispo Matos                | 361150 | Marjory Matos - RM361150     |
