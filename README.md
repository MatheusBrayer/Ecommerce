Perfeito! 😄 Vou montar um **README.md completo** para o seu projeto, já formatado para GitHub, incluindo: descrição do projeto, endpoints, exemplos de requisição/resposta, DTOs e tratamento de erros.

Aqui está um modelo pronto:

````markdown
# E-commerce API

API de e-commerce construída com **Spring Boot**, para gerenciamento de **clientes**, **produtos** e **carrinhos de compras**.

---

## Tecnologias utilizadas
- Java 17
- Spring Boot 3.5.5
- Spring Data JPA
- H2 Database (em memória)
- Lombok
- Spring Validation
- Maven

---

## Estrutura do projeto
- **Entities**: Customer, Product, Cart, CartItem  
- **DTOs**: Request e Response para cada entidade  
- **Mappers**: Conversão entre entidades e DTOs  
- **Repositories**: Acesso ao banco (JPA)  
- **Services**: Lógica de negócio  
- **Controllers**: Endpoints REST  
- **Exceptions**: Tratamento global de erros  

---

## Endpoints da API

### Customers
| Método | URL | Requisição | Resposta | Observações |
|--------|-----|------------|----------|-------------|
| GET | `/customers` | - | Lista de `CustomerResponseDTO` | Retorna todos os clientes |
| GET | `/customers/{id}` | - | `CustomerResponseDTO` | Retorna cliente por ID |
| POST | `/customers` | `CustomerRequestDTO` | `CustomerResponseDTO` | Cria novo cliente |
| PUT | `/customers/{id}` | `CustomerRequestDTO` | `CustomerResponseDTO` | Atualiza cliente existente |
| DELETE | `/customers/{id}` | - | String de sucesso | Deleta cliente |

### Products
| Método | URL | Requisição | Resposta | Observações |
|--------|-----|------------|----------|-------------|
| GET | `/products` | - | Lista de `ProductResponseDTO` | Retorna todos os produtos |
| GET | `/products/{id}` | - | `ProductResponseDTO` | Retorna produto por ID |
| POST | `/products` | `ProductRequestDTO` | `ProductResponseDTO` | Cria novo produto |
| PUT | `/products/{id}` | `ProductRequestDTO` | `ProductResponseDTO` | Atualiza produto existente |
| DELETE | `/products/{id}` | - | String de sucesso | Deleta produto |

### Carts
| Método | URL | Requisição | Resposta | Observações |
|--------|-----|------------|----------|-------------|
| GET | `/carts/{id}` | - | `CartResponseDTO` | Retorna carrinho por ID |
| POST | `/carts` | `CartRequestDTO` | `CartResponseDTO` | Cria novo carrinho |
| PUT | `/carts/{id}` | `CartRequestDTO` | `CartResponseDTO` | Atualiza carrinho existente |
| DELETE | `/carts/{id}` | - | String de sucesso | Deleta carrinho |
| POST | `/carts/{cartId}/add/{productId}?quantity=1` | - | `CartResponseDTO` | Adiciona produto ao carrinho |
| PUT | `/carts/{cartId}/update/{productId}?quantity=2` | - | `CartResponseDTO` | Atualiza quantidade do produto no carrinho |
| PUT | `/carts/{cartId}/remove/{productId}` | - | `CartResponseDTO` | Remove produto do carrinho |

---

## Estrutura de DTOs

### Customer
- **CustomerRequestDTO**  
```json
{
  "name": "Nome do Cliente",
  "email": "email@exemplo.com",
  "password": "senha123",
  "address": "Endereço do cliente"
}
````

* **CustomerResponseDTO**

```json
{
  "id": 1,
  "name": "Nome do Cliente",
  "email": "email@exemplo.com",
  "address": "Endereço do cliente"
}
```

### Product

* **ProductRequestDTO**

```json
{
  "name": "Nome do Produto",
  "description": "Descrição do produto",
  "price": 59.9,
  "stock": 10
}
```

* **ProductResponseDTO**

```json
{
  "id": 1,
  "name": "Nome do Produto",
  "description": "Descrição do produto",
  "price": 59.9,
  "stock": 10
}
```

### Cart

* **CartRequestDTO**

```json
{
  "customerId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

* **CartResponseDTO**

```json
{
  "id": 1,
  "customerId": 1,
  "items": [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Nome do Produto",
        "description": "Descrição do produto",
        "price": 59.9,
        "stock": 10
      },
      "quantity": 2
    }
  ]
}
```

---

## Tratamento de erros

* **400 Bad Request** → RuntimeException

```json
{
  "timestamp": "2025-10-03T12:00:00",
  "status": 400,
  "error": "Mensagem do erro"
}
```

* **500 Internal Server Error** → NullPointerException ou Exception genérica

```json
{
  "timestamp": "2025-10-03T12:00:00",
  "status": 500,
  "error": "Ocorreu um erro inesperado"
}
```

---

## Rodando o projeto

1. Clone o repositório:

```bash
git clone https://github.com/MatheusBrayer/Ecommerce.git
```

2. Entre no diretório do projeto:

```bash
cd Ecommerce
```

3. Execute com Maven:

```bash
mvn spring-boot:run
```

4. A API estará disponível em `http://localhost:8080`

---

## Observações

* Banco de dados H2 em memória, não persiste dados entre reinicializações.
* Todas as validações de campos estão configuradas com **Spring Validation**.
* Para testes de endpoints, pode-se usar **Postman**, **Insomnia** ou **curl**.

