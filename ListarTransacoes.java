import java.util.List;

public class ListarTransacoes {

	private final ContaCadastro contaCadastro;

	public ListarTransacoes(ContaCadastro contaCadastro) {
		this.contaCadastro = contaCadastro;
	}

	// Lista as transacoes (saques, depositos, transferencias) da conta,
	// em ordem cronologica, com tipo, valor e data de cada uma.
	// Retorna lista vazia (nao erro) se nao houver transacoes.
	// Lanca erro apenas se a conta nao existir.
	public List<Transacao> listar(int contaId) {
		Conta conta = contaCadastro.buscarPorId(contaId);
		if (conta == null) {
			throw new IllegalArgumentException("Conta não encontrada: " + contaId);
		}
		return conta.getTransacoes();
	}
}
