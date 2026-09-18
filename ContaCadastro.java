import java.util.ArrayList;
import java.util.List;

public class ContaCadastro {

	private final ClienteCadastro clienteCadastro;
	private final ArrayList<Conta> contasAtivas = new ArrayList<>();
	private final ArrayList<Conta> contasInativas = new ArrayList<>();
	private int proximoId = 1;

	public ContaCadastro(ClienteCadastro clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public Conta abrir(int clienteId) {
		if (clienteCadastro.buscarPorId(clienteId) == null) {
			throw new IllegalArgumentException("Cliente não encontrado: " + clienteId);
		}

		Conta conta = new Conta(proximoId, clienteId);
		contasAtivas.add(conta);
		proximoId++;
		return conta;
	}

	public List<Conta> listar() {
		List<Conta> todas = new ArrayList<>(contasAtivas);
		todas.addAll(contasInativas);
		return todas;
	}

	public Conta buscarPorId(int id) {
		for (Conta conta : listar()) {
			if (conta.getId() == id) {
				return conta;
			}
		}
		return null;
	}

	// Referências reais, não cópias: encerrar/reativar uma conta move o mesmo objeto entre as duas listas.
	public ArrayList<Conta> getContasAtivas() {
		return contasAtivas;
	}

	public ArrayList<Conta> getContasInativas() {
		return contasInativas;
	}
}
