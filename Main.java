import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu mainMenu = new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		ClienteCadastro clienteCadastro = new ClienteCadastro();
		Scanner scanner = new Scanner(System.in);

		boolean continuar = true;
		while (continuar) {
			int selecao = mainMenu.getSelection();
			switch (selecao) {
				case 1:
					System.out.println("Conta foi selecionada");
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
			System.out.println(cliente.getNome() + " - " + cliente.getCpf() + " - " + cliente.getEmail());
		}
	}

}
