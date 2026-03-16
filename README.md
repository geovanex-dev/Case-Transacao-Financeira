# 💸 Transação Financeira

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blue?style=for-the-badge)

## 📝 Descrição

Este projeto implementa um sistema robusto de processamento de transações financeiras entre contas. O objetivo principal foi refatorar e melhorar um código originalmente fornecido em um desafio técnico, aplicando boas práticas de engenharia de software, corrigindo problemas críticos de concorrência e organizando a arquitetura sob o padrão Hexagonal.

A aplicação simula transferências entre contas executadas de forma concorrente, garantindo a integridade dos saldos em um ambiente multi-thread.

---

## 🔍 Problemas Identificados no Código Original

Durante a análise do código legado, foram identificados os seguintes gargalos técnicos:

1.  **Problemas de Concorrência:** O código utilizava execução paralela sem controle de acesso aos saldos (*Race Conditions*), o que gerava inconsistências de dados quando múltiplas transações acessavam as mesmas contas simultaneamente.
2.  **Estrutura Acoplada:** A lógica de negócio, o acesso a dados e a execução estavam fortemente acoplados, dificultando a manutenção e a criação de testes automatizados.
3.  **Tipos Inadequados para Moeda:** Valores financeiros estavam sendo representados por tipos primitivos (como `double` ou `float`), conhecidos por causar erros de precisão decimal.
4.  **Falta de Organização Arquitetural:** O código não seguia uma divisão clara de camadas ou responsabilidades.

---

## 🛠️ Melhorias Aplicadas

### 1. Arquitetura Hexagonal (Ports and Adapters)
O projeto foi reorganizado para separar as preocupações em camadas bem definidas:
* **Domain:** Modelos e regras puras de negócio.
* **Application:** Orquestração dos casos de uso.
* **Ports:** Definição de interfaces de entrada e saída.
* **Adapters:** Implementações técnicas (infraestrutura, logs, persistência).

### 2. Controle de Concorrência
Implementação de mecanismos de controle (como `Locks` ou blocos sincronizados) para garantir a atomicidade das operações, impedindo que o saldo de uma conta seja lido ou alterado de forma inconsistente por threads simultâneas.

### 3. Precisão com BigDecimal
Todos os cálculos financeiros foram migrados para o tipo **`BigDecimal`**, garantindo precisão absoluta em operações de débito e crédito.

### 4. Separação de Responsabilidades
Abaixo, a divisão clara de cada camada:

| Camada | Responsabilidade |
| :--- | :--- |
| **Domain** | Modelos de negócio e regras centrais. |
| **Application** | Orquestração das regras de negócio. |
| **Ports** | Interfaces de comunicação (contratos). |
| **Adapters** | Implementações de entrada e saída (infra). |
| **Config** | Configuração de Beans e inicialização do sistema. |

---

## 📁 Estrutura do Projeto

```text
src
├── main
│   └── java
│       └── com.geovane.transacaofinanceira
│           ├── adapters      # Entrada (Input) e Saída (Output)
│           ├── application   # Serviços de aplicação
│           ├── config        # Configurações de sistema
│           ├── domain        # Modelos de domínio
│           ├── ports         # Interfaces (Input/Output ports)
│           └── Aplicacao.java # Classe principal
└── test                      # Testes unitários e integrados
```
---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Gerenciamento de contexto e injeção de dependência)
* **Lombok** (Produtividade e código limpo)
* **JUnit 5** (Testes unitários e de integração)
* **ExecutorService** (Gerenciamento de concorrência e processamento paralelo)
* **Arquitetura Hexagonal**

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Java 17 instalado.
* Maven ou Gradle.

### Passos
1. **Clonar o repositório:**
   ```bash
   git clone <link-do-repositorio>
   cd transacao-financeira

## 🚀 Como Executar

### Executar via Maven
```bash
mvn spring-boot:run