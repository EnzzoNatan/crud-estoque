CRUD de Estoque — Java + Spring Boot

API REST desenvolvida em Java com Spring Boot para controle de estoque, permitindo o gerenciamento de Produtos e Categorias, com validações de regras de negócio e relacionamento entre entidades utilizando JPA/Hibernate.

🚀 Funcionalidades

- Cadastro de categorias
- Cadastro de produtos vinculados a categorias
- Atualização de produtos (PUT)
- Listagem de produtos e categorias
- Remoção de registros
- Validações de domínio:
  - Preço não pode ser negativo
  - Quantidade não pode ser negativa
  - Categoria obrigatória e existente

---

🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- Banco de dados relacional (MySQL)
- Postman (para testes da API)

---

📐 Modelagem

### Relacionamento entre entidades
- **Categoria** → OneToMany → Produto
- **Produto** → ManyToOne → Categoria

O relacionamento é tratado corretamente para evitar problemas de serialização infinita e entidades não gerenciadas pelo JPA.
