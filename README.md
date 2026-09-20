# Sistema Bancário em terminal

Aplicação Java de linha de comando para cadastrar clientes, abrir contas e fazer operações bancárias básicas. Projeto da disciplina de Engenharia de Software II (UFPI, 2026-2), usado para praticar Git e GitHub com issues, branches e pull requests.

Os dados ficam só em memória: ao sair do programa, tudo é perdido.

## Funcionalidades

**Menu Principal**: Conta, Cliente, Operacoes, Sair.

| Menu | Opção | O que faz |
|---|---|---|
| Cliente | Cadastrar Cliente | Cadastra nome, CPF e e-mail. Valida campos obrigatórios, e-mail, CPF (dígitos verificadores) e impede CPF duplicado. |
| Cliente | Listar Clientes | Mostra ID, nome, CPF e e-mail de cada cliente. |
| Conta | Abrir Conta | Abre uma conta vinculada ao ID de um cliente já cadastrado, com saldo inicial zero. |
| Conta | Listar Contas | Lista as contas ativas com o nome do cliente e o saldo. |
| Conta | Consultar Saldo | Mostra o saldo de uma conta, formatado como `R$ 0,00`. |
| Operacoes | Depositar | Soma um valor ao saldo da conta. |
| Operacoes | Sacar | Retira um valor do saldo, se houver saldo suficiente. |
| Operacoes | Transferir | Move um valor de uma conta para outra. |
| Operacoes | Encerrar conta | Torna a conta inativa. |
| Operacoes | Listar Transações | Mostra o histórico da conta com tipo, valor e data, em ordem cronológica. |

### Regras de negócio

- **CPF**: aceito com ou sem máscara (`529.982.247-25` ou `52998224725`). É guardado só com dígitos. Não precisa ser um CPF real, só passar no cálculo dos dígitos verificadores. Sequências repetidas como `111.111.111-11` são rejeitadas.
- **Valores**: depósito, saque e transferência exigem valor maior que zero.
- **Saldo insuficiente**: o saque é recusado com erro. Na transferência, nada é alterado e o programa informa o problema de saldo.
- **Conta inativa**: só é possível encerrar uma conta com saldo zero. Conta inativa não aceita depósito, saque nem transferência, e some do "Listar Contas". Saldo e transações dela continuam consultáveis.
- **Tipos de transação**: `DEPOSITO`, `SAQUE`, `TRANSFERENCIA_SAIDA` (conta que enviou) e `TRANSFERENCIA_ENTRADA` (conta que recebeu).

## Requisitos

- JDK instalado (o projeto foi testado com Java 21 e não usa recursos além do Java 8).
- Não há Maven nem Gradle: só o `javac`.

## Como compilar e executar

```
javac *.java
java Main
```

## Exemplo de uso

```
Menu Principal

1 - Conta
2 - Cliente
3 - Operacoes
4 - Sair
Informe a opcao desejada.
```

Um caminho típico: escolha **Cliente**, depois **Cadastrar Cliente** e informe nome, CPF e e-mail. Volte ao menu principal, entre em **Conta**, abra uma conta informando o ID do cliente (o primeiro cliente tem ID 1) e use **Operacoes** para depositar, sacar ou transferir.

## Testes

Os testes são programas Java simples, sem framework. Rode depois de compilar:

```
java ContaDepositoTest
java ContaSaqueTest
```

Cada um imprime uma mensagem de sucesso, ou lança `AssertionError` se algo falhar.

## Estrutura do projeto

Todos os arquivos ficam na raiz, sem pacotes.

| Arquivo | Papel |
|---|---|
| `Main.java` | Ponto de entrada. Monta os menus e chama as funcionalidades. |
| `Menu.java` | Menu numerado de terminal. Repete a pergunta até receber uma opção válida. |
| `Cliente.java` | Cliente (ID, nome, CPF, e-mail) e suas validações. |
| `ClienteCadastro.java` | Guarda os clientes, gera IDs e impede CPF duplicado. |
| `Conta.java` | Conta (ID, ID do cliente, saldo), com depósito, saque, extrato em texto e transações. |
| `ContaCadastro.java` | Guarda as contas ativas e inativas e valida que o cliente existe ao abrir. |
| `Transacao.java` | Registro de uma movimentação (tipo, valor e data). |
| `ConsultarSaldo.java` | Consulta o saldo de uma conta pelo ID. |
| `ListarTransacoes.java` | Retorna as transações de uma conta pelo ID. |
| `TransferirConta.java` | Transferência entre duas contas. |
| `EncerrarConta.java` | Move uma conta de ativa para inativa. |
| `ContaJaInativa.java` | Exceção lançada ao encerrar uma conta que já está inativa. |
| `ContaDepositoTest.java`, `ContaSaqueTest.java` | Testes do depósito e do saque. |

## Limitações conhecidas

- **Sem persistência**: clientes, contas e transações vivem só em memória.
- **Valores em `double`**: adequado para estudo, mas dinheiro real pede `BigDecimal` para evitar erros de arredondamento.
- **Extrato em texto sem uso**: a `Conta` guarda um extrato em texto (`getExtrato()`), mas nenhuma opção de menu o exibe. Só o histórico de transações aparece.
- **Cobertura de testes**: só depósito e saque têm testes. Cadastro de cliente, abertura de conta, transferência e encerramento não têm.
- **Nomes de métodos**: `TransferirConta.Transferir` e `EncerrarConta.Encerrar` começam com maiúscula, fora da convenção Java (`transferir` e `encerrar`).
- **`Menu.getSelection()` sem argumento** cria um `Scanner` novo a cada chamada, o que pode perder entrada quando ela vem por pipe. O `Main` usa `getSelection(Scanner)`, que evita o problema.

## Como contribuir (fluxo Git)

1. Crie uma branch a partir da `dev`, com um nome que identifique a issue (por exemplo `Fulano#5`).
2. Implemente, rode `javac *.java` e os testes.
3. Faça o commit com uma mensagem que cite a issue (por exemplo `..., close issue #5`).
4. Dê push da branch e abra um Pull Request **para a `dev`**, escolhendo o seu fork como repositório base.
5. Antes de pedir revisão, atualize a sua branch com a `dev` (`git merge origin/dev`) e resolva conflitos, principalmente no `Main.java`, que todos editam.

## Contribuidores

| GitHub | Funcionalidades |
|---|---|
| `otaviofranca` | Cadastro de cliente, abertura de conta |
| `ArthurMagnoRS` | Transferência, encerramento de conta |
| `pcarvalhomgs` | Depósito, saque, ajustes na listagem de transações e de contas |
| `AlmeidaLm` | Consulta de saldo, relatório de transações |
