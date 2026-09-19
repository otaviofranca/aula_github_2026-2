public class ConsultarSaldo {

	private final ContaCadastro contaCadastro;

	public ConsultarSaldo(ContaCadastro contaCadastro) {
		this.contaCadastro = contaCadastro;
	}

	// Retorna o saldo formatado corretamente.
	// Lanca erro se a conta nao existir.
	public String consultar(int contaId) {
		Conta conta = contaCadastro.buscarPorId(contaId);
		if (conta == null) {
			throw new IllegalArgumentException("Conta não encontrada: " + contaId);
		}
		return formatarSaldo(conta.getSaldo());
	}

	private String formatarSaldo(double saldo) {
		return String.format("R$ %.2f", saldo);
	}
}
