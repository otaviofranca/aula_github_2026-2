import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
			int selecao = mainMenu.getSelection();
			switch (selecao) {
				case 1:
					abrirSubmenuConta(contaCadastro, scanner);
					break;
				case 2:
					abrirSubmenuCliente(clienteCadastro, scanner);
					break;
				case 3:
					System.out.println("Operacoes foi selecionada");
					break;
				case 4:
					continuar = false;
					break;
			}
		}

		System.out.println("Fim");
	}

	private static void abrirSubmenuConta(ContaCadastro contaCadastro, Scanner scanner) {
		Menu contaMenu = new Menu("Abertura de Conta", Arrays.asList("Abrir Conta", "Listar Contas", "Voltar"));

		boolean voltar = false;
		while (!voltar) {
			int selecao = contaMenu.getSelection();
			switch (selecao) {
				case 1:
					abrirConta(contaCadastro, scanner);
					break;
				case 2:
					listarContas(contaCadastro);
					break;
				case 3:
					voltar = true;
					break;
			}
		}
	}



		private static void submenuOperacoesConta(ContaCadastro contaCadastro, Scanner scanner){
			
			Menu opMenu = new Menu("Operacoes entre contas", Arrays.asList("Transferir","Encerrar conta","Voltar"));
			boolean voltar = false;
			while (!voltar){
				int selecao = opMenu.getSelection();
				switch(selecao){
					String entrada_1;
					case 1: // transferir com algumas verificacoes que poderiam ter sido feitas na classe do metodo mas ja fiz assim
		
						System.out.println("Conta a ser debitada: ");
						entrada_1 = scanner.nextLine();
		
						System.out.println("Conta a ser creditada: ");
						String entrada_2 = scanner.nextLine();
						
						System.out.println("Total a ser transferido: ")
						String entrada_3 = scanner.nextLine();
						try{
						int id_conta1 = Integer.parseInt(entrada_1.trim());
						int id_conta2 = Integer.parseInt(entrada_2.trim());
						double saldo_transferir = Double.parseDouble(entrada_3.trim());
						
						Conta Conta_1 = contaCadastro.buscarPorId(id_conta1);
						Conta Conta_2 = contaCadastro.buscarPorId(id_conta2);
						if (Conta_1 == null || Conta_2 == null){
						System.out.println("Alguma conta nao esta cadastrada.");
							}else{ // nao verificamos se a conta ta inativa ou nao, somente nao cadastrada!! ficar de olho
		
						TransferirConta transferencia = new TransferirConta();
						transferencia.Transferir(Conta_1, Conta_2, saldo_transferir);
						System.out.println("Tranferencia concluida!");
							}
						}catch  (NumberFormatException e) {
							System.out.println("Erro ao abrir conta: ID do cliente deve ser um número");
						} catch (IllegalArgumentException e) {
							System.out.println("Erro ao abrir conta: " + e.getMessage());
						}	
						break;
		
					case 2:  // encerrar com algumas verificacoes que poderiam ter sido feitas na classe do metodo mas ja fiz assim
						System.out.println("Conta a ser encerrada: ");
						 entrada_1 = scanner.nextLine();
						try{
							int id_conta = Integer.parseInt(entrada_1.trim());
						
						EncerrarConta encerrarConta = new EncerrarConta(contaCadastro.getContasAtivas(), contaCadastro.getContasInativas());
						Conta Conta_E = contaCadastro.buscarPorId(id_conta);
						if (Conta_E == null){
							System.out.println("Conta nao encontrada");
							break;
						}else{
							encerrarConta.Encerrar(Conta_E, id_conta);
						}
						}catch (NumberFormatException e) {
							System.out.println("Erro ao digitar conta: ID e formado por numeros");
						} catch (IllegalArgumentException e) {
							System.out.println("Erro ao digitar conta: " + e.getMessage());
						}
						break;
		
					case 3:
						voltar = true;
						break;
		
				}	
				
			}
		
		
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
		List<Conta> contas = contaCadastro.listar();
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
			int selecao = clienteMenu.getSelection();
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