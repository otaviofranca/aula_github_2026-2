import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu mainMenu = new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		ClienteCadastro clienteCadastro = new ClienteCadastro();
		ContaCadastro contaCadastro = new ContaCadastro(clienteCadastro);
		Scanner scanner = new Scanner(System.in);

		boolean continuar = true;
		while (continuar) {
			int selecao = mainMenu.getSelection(scanner);
			switch (selecao) {
				case 1:
					abrirSubmenuConta(contaCadastro, scanner);
					break;
				case 2:
					abrirSubmenuCliente(clienteCadastro, scanner);
					break;
				case 3:
					abrirSubmenuOperacoes(contaCadastro, scanner);
					break;
				case 4:
					continuar = false;
					break;
			}
		}

		System.out.println("Fim");
	}

	private static void abrirSubmenuConta(ContaCadastro contaCadastro, Scanner scanner) {
		Menu contaMenu = new Menu("Abertura de Conta",
				Arrays.asList("Abrir Conta", "Listar Contas", "Consultar Saldo", "Voltar"));

		boolean voltar = false;
		while (!voltar) {
			int selecao = contaMenu.getSelection(scanner);
			switch (selecao) {
				case 1:
					abrirConta(contaCadastro, scanner);
					break;
				case 2:
					listarContas(contaCadastro);
					break;
				case 3:
					consultarSaldo(contaCadastro, scanner);
					break;
				case 4:
					voltar = true;
					break;
			}
		}
	}

	private static void abrirSubmenuOperacoes(ContaCadastro contaCadastro, Scanner scanner) {
		Menu operacoesMenu = new Menu("Operacoes",
				Arrays.asList("Depositar", "Sacar", "Transferir", "Encerrar conta", "Voltar"));

		boolean voltar = false;
		while (!voltar) {
			int selecao = operacoesMenu.getSelection(scanner);
			switch (selecao) {
				case 1:
					depositar(contaCadastro, scanner);
					break;
				case 2:
					sacar(contaCadastro, scanner);
					break;
				case 3:
					transferir(contaCadastro, scanner);
					break;
				case 4:
					encerrarConta(contaCadastro, scanner);
					break;
				case 5:
					voltar = true;
					break;
			}
		}
	}

	// ---------- Issue: Consultar saldo atual de uma conta ----------
	private static void consultarSaldo(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID da conta: ");
		String entradaConta = scanner.nextLine();

		try {
			int contaId = Integer.parseInt(entradaConta.trim());
			ConsultarSaldo consultarSaldo = new ConsultarSaldo(contaCadastro);
			System.out.println("Saldo atual: " + consultarSaldo.consultar(contaId));
		} catch (NumberFormatException e) {
			System.out.println("Erro ao consultar saldo: ID deve ser um numero.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao consultar saldo: " + e.getMessage());
		}
	}

	private static void depositar(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID da conta: ");
		String entradaConta = scanner.nextLine();
		System.out.print("Valor do deposito: ");
		String entradaValor = scanner.nextLine();

		try {
			int contaId = Integer.parseInt(entradaConta.trim());
			double valor = Double.parseDouble(entradaValor.trim());
			Conta conta = contaCadastro.buscarPorId(contaId);
			if (conta == null) {
				System.out.println("Conta nao encontrada: " + contaId);
				return;
			}
			if (!contaEstaAtiva(contaCadastro, conta)) {
				System.out.println("Nao e possivel depositar em uma conta inativa.");
				return;
			}

			conta.depositar(valor);
			System.out.println("Deposito realizado com sucesso. Saldo atual: " + conta.getSaldo());
		} catch (NumberFormatException e) {
			System.out.println("Erro no deposito: ID e valor devem ser numeros.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro no deposito: " + e.getMessage());
		}
	}

	private static void sacar(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID da conta: ");
		String entradaConta = scanner.nextLine();
		System.out.print("Valor do saque: ");
		String entradaValor = scanner.nextLine();

		try {
			int contaId = Integer.parseInt(entradaConta.trim());
			double valor = Double.parseDouble(entradaValor.trim());
			Conta conta = contaCadastro.buscarPorId(contaId);
			if (conta == null) {
				System.out.println("Conta nao encontrada: " + contaId);
				return;
			}
			if (!contaEstaAtiva(contaCadastro, conta)) {
				System.out.println("Nao e possivel sacar de uma conta inativa.");
				return;
			}

			conta.sacar(valor);
			System.out.println("Saque realizado com sucesso. Saldo atual: " + conta.getSaldo());
		} catch (NumberFormatException e) {
			System.out.println("Erro no saque: ID e valor devem ser numeros.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro no saque: " + e.getMessage());
		}
	}

	private static void transferir(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID da conta de origem: ");
		String entradaOrigem = scanner.nextLine();
		System.out.print("ID da conta de destino: ");
		String entradaDestino = scanner.nextLine();
		System.out.print("Valor da transferencia: ");
		String entradaValor = scanner.nextLine();

		try {
			int contaOrigemId = Integer.parseInt(entradaOrigem.trim());
			int contaDestinoId = Integer.parseInt(entradaDestino.trim());
			double valor = Double.parseDouble(entradaValor.trim());
			Conta origem = contaCadastro.buscarPorId(contaOrigemId);
			Conta destino = contaCadastro.buscarPorId(contaDestinoId);
			if (origem == null || destino == null) {
				System.out.println("Conta de origem ou destino nao encontrada.");
				return;
			}
			if (!contaEstaAtiva(contaCadastro, origem) || !contaEstaAtiva(contaCadastro, destino)) {
				System.out.println("Nao e possivel transferir usando uma conta inativa.");
				return;
			}

			String registro = new TransferirConta().Transferir(origem, destino, valor);
			System.out.println(registro);
		} catch (NumberFormatException e) {
			System.out.println("Erro na transferencia: IDs e valor devem ser numeros.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro na transferencia: " + e.getMessage());
		}
	}

	private static void encerrarConta(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID da conta a encerrar: ");
		String entradaConta = scanner.nextLine();

		try {
			int contaId = Integer.parseInt(entradaConta.trim());
			Conta conta = contaCadastro.buscarPorId(contaId);
			if (conta == null) {
				System.out.println("Conta nao encontrada: " + contaId);
				return;
			}

			EncerrarConta encerrarConta = new EncerrarConta(
					contaCadastro.getContasAtivas(), contaCadastro.getContasInativas());
			encerrarConta.Encerrar(conta, contaId);
		} catch (NumberFormatException e) {
			System.out.println("Erro ao encerrar conta: ID deve ser um numero.");
		} catch (ContaJaInativa e) {
			System.out.println("Erro ao encerrar conta: " + e.getMessage());
		}
	}

	private static boolean contaEstaAtiva(ContaCadastro contaCadastro, Conta conta) {
		return contaCadastro.getContasAtivas().contains(conta);
	}

	private static void abrirConta(ContaCadastro contaCadastro, Scanner scanner) {
		System.out.print("ID do cliente: ");
		String entrada = scanner.nextLine();

		try {
			int clienteId = Integer.parseInt(entrada.trim());
			Conta conta = contaCadastro.abrir(clienteId);
			System.out.println("Conta " + conta.getId() + " aberta com sucesso para o cliente " + conta.getClienteId() + " (saldo inicial: " + conta.getSaldo() + ")");
		} catch (NumberFormatException e) {
			System.out.println("Erro ao abrir conta: ID do cliente deve ser um número");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao abrir conta: " + e.getMessage());
		}
	}

	private static void listarContas(ContaCadastro contaCadastro) {
		List<Conta> contas = contaCadastro.listarAtivas();
		if (contas.isEmpty()) {
			System.out.println("Nenhuma conta cadastrada.");
			return;
		}
		for (Conta conta : contas) {
			System.out.println("Conta " + conta.getId() + " - Cliente " + conta.getClienteId() + " - Saldo: " + conta.getSaldo());
		}
	}

	private static void abrirSubmenuCliente(ClienteCadastro clienteCadastro, Scanner scanner) {
		Menu clienteMenu = new Menu("Cadastro de Cliente", Arrays.asList("Cadastrar Cliente", "Listar Clientes", "Voltar"));

		boolean voltar = false;
		while (!voltar) {
			int selecao = clienteMenu.getSelection(scanner);
			switch (selecao) {
				case 1:
					cadastrarCliente(clienteCadastro, scanner);
					break;
				case 2:
					listarClientes(clienteCadastro);
					break;
				case 3:
					voltar = true;
					break;
			}
		}
	}

	private static void cadastrarCliente(ClienteCadastro clienteCadastro, Scanner scanner) {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();
		System.out.print("E-mail: ");
		String email = scanner.nextLine();

		try {
			clienteCadastro.cadastrar(nome, cpf, email);
			System.out.println("Cliente cadastrado com sucesso!");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
		}
	}

	private static void listarClientes(ClienteCadastro clienteCadastro) {
		List<Cliente> clientes = clienteCadastro.listar();
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
			return;
		}
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getId() + " - " + cliente.getNome() + " - " + cliente.getCpf() + " - " + cliente.getEmail());
		}
	}

}
