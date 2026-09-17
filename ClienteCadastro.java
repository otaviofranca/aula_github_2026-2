import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteCadastro {

	private final List<Cliente> clientes = new ArrayList<>();

	public Cliente cadastrar(String nome, String cpf, String email) {
		if (cpf != null) {
			String cpfNormalizado = Cliente.normalizarCpf(cpf);
			for (Cliente cliente : clientes) {
				if (cliente.getCpf().equals(cpfNormalizado)) {
					throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
				}
			}
		}

		Cliente cliente = new Cliente(nome, cpf, email);
		clientes.add(cliente);
		return cliente;
	}

	public List<Cliente> listar() {
		return Collections.unmodifiableList(clientes);
	}
}
