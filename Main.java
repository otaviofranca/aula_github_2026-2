import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes"));
		System.out.println(mainMenu.getSelection() + "foi selecionada");
		System.out.println("Fim");
	}


	private static void submenuOperacoesConta(ContaCadastro contaCadastro, Scanner scanner){
		
		Menu opMenu = new Menu("Operacoes entre contas", Array.asList("Transferir","Encerrar conta","Voltar"));
		boolean voltar = false;
		while (!voltar){
			int selecao = opMenu.getSelection();
			switch(selecao){
				case 1: // transferir com algumas verificacoes que poderiam ter sido feitas na classe do metodo mas ja fiz assim

					System.out.println("Conta a ser debitada: ");
					String entrada_1 = scanner.nextLine();

					System.out.println("Conta a ser creditada: ");
					String entrada_2 = scanner.nextLine();
					
					try{
						int id_conta1 = Integer.parseInt(entrada_1.trim());
						int id_conta2 = Integer.parseInt(entrada_2.trim());
					}catch (NumberFormatException e) {
						System.out.println("Erro ao digitar conta: ID e formado por numeros");
					} catch (IllegalArgumentException e) {
						System.out.println("Erro ao digitar conta: " + e.getMessage());
					}

					Conta_1 = contaCadastro.buscarPorId(id_conta1);
					Conta_2 = contaCadastro.buscarPorId(id_conta2);
					if (Conta_1 == null || Conta_2 == null){
					System.out.println("Alguma conta nao esta cadastrada.");
						}else{ // nao verificamos se a conta ta inativa ou nao, somente nao cadastrada!! ficar de olho

					TransferirConta transferencia = new TransferirConta();
					transferecia.Transferir(Conta_1, Conta_2);
					System.out.println("Tranferencia concluida!");
						}
					break;

				case 2:  // encerrar com algumas verificacoes que poderiam ter sido feitas na classe do metodo mas ja fiz assim
					System.out.println("Conta a ser encerrada: ");
					String entrada_1 = scanner.nextLine();
					try{
						int id_conta = Integer.parseInt(entrada_1.trim())
					}catch (NumberFormatException e) {
						System.out.println("Erro ao digitar conta: ID e formado por numeros");
					} catch (IllegalArgumentException e) {
						System.out.println("Erro ao digitar conta: " + e.getMessage());
					}
					EncerrarConta encerrarConta = new EncerrarConta(contaCadastro.getContasAtivas(), contaCadastro.getContasInativas());
					Conta_E = contaCadastro.buscarPorID(id_conta);
					if (Conta_E == null){
						return "Conta nao encontrada.";
					}else{
						encerrarConta.Encerrar(Conta_E);
					}
					break;

				case 3:
					voltar = true;
					break;

			}	
			
		}


	}





}