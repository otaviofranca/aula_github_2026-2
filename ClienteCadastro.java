import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteCadastro {

	private final List<Cliente> clientes = new ArrayList<>();
	private int proximoId = 1;

	public Cliente cadastrar(String nome, String cpf, String email) {
		if (cpf != null) {
			String cpfNormalizado = Cliente.normalizarCpf(cpf);
			for (Cliente cliente : clientes) {
				if (cliente.getCpf().equals(cpfNormalizado)) {
					throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
				}
			}
		}

		Cliente cliente = new Cliente(proximoId, nome, cpf, email);
		clientes.add(cliente);
		proximoId++;
		return cliente;
	}

	public Cliente buscarPorId(int id) {
		for (Cliente cliente : clientes) {
			if (cliente.getId() == id) {
				return cliente;
			}
		}
		return null;
	}

	public List<Cliente> listar() {
		return Collections.unmodifiableList(clientes);
	}
}
