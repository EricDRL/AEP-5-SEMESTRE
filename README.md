# Sistema de Gerenciamento de Solicitações (AEP - 5º Semestre)

Este projeto é uma aplicação de console (CLI) desenvolvida em Java para o gerenciamento de solicitações de suporte técnico. Ele permite que usuários criem solicitações e que administradores gerenciem o ciclo de vida dessas chamadas.

## 🚀 Funcionalidades

### Área do Cliente
- **Cadastro de Usuário:** Registro de novos usuários.
- **Nova Solicitação:** Registro de pedidos com categoria (Hardware, Software, Rede) e nível de prioridade.
- **Acompanhamento:** Consulta do status de solicitações através de um número de protocolo único.

### Área Administrativa
- **Autenticação:** Acesso restrito para administradores.
- **Gestão de Status:** Visualização de todas as solicitações e atualização do status (Pendente, Em Andamento, Concluído).
- **Filtros:** Possibilidade de filtrar e visualizar a fila de trabalho.

## 🛠️ Tecnologias e Conceitos Utilizados

- **Linguagem:** Java 17+
- **Persistência:** Repositórios em memória e em arquivo CSV (`usuarios.csv`).
- **Arquitetura:** Separação em camadas (Entity, Repository, Service, View).
- **Clean Code & SOLID:**
  - **Inversão de Dependência:** Uso de interfaces para repositórios.
  - **Responsabilidade Única:** Classes com funções bem definidas.
  - **Interface Fluída:** Menu interativo e colorido no console com animações de "loading".

## 📋 Como Executar

1. Certifique-se de ter o **JDK 17** ou superior instalado.
2. Clone o repositório.
3. Navegue até a pasta `project/src`.
4. Compile o projeto:
   ```bash
   javac Main.java
   ```
5. Execute a aplicação:
   ```bash
   java Main
   ```

## 📂 Estrutura de Pastas

```text
src/
├── entity/       # Modelos de dados (Solicitacao, Usuario)
├── enums/        # Definições de Status, Prioridade e Categoria
├── infra/        # Implementações de repositórios (Memória/Arquivo)
├── repositories/ # Interfaces de acesso a dados
├── services/     # Lógica de negócio
├── utils/        # Utilitários de interface e teclado
├── views/        # Telas do console (CLI)
└── Main.java     # Ponto de entrada
```

---
Projeto desenvolvido como parte da avaliação da AEP - 5º Semestre.
