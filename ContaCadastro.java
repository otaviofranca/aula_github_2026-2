import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaCadastro {

	private final ClienteCadastro clienteCadastro;
	private final List<Conta> contas = new ArrayList<>();
	private int proximoId = 1;

	public ContaCadastro(ClienteCadastro clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public Conta abrir(int clienteId) {
		if (clienteCadastro.buscarPorId(clienteId) == null) {
			throw new IllegalArgumentException("Cliente não encontrado: " + clienteId);
		}

		Conta conta = new Conta(proximoId, clienteId);
		contas.add(conta);
		proximoId++;
		return conta;
	}

	public List<Conta> listar() {
		return Collections.unmodifiableList(contas);
	}
}
