public class ContaSaqueTest {

	public static void main(String[] args) {
		deveAtualizarSaldoERegistrarSaque();
		naoDevePermitirSaqueMaiorQueSaldo();
		System.out.println("Todos os testes de saque passaram.");
	}

	private static void deveAtualizarSaldoERegistrarSaque() {
		Conta conta = new Conta(1, 1);
		conta.depositar(100.0);

		conta.sacar(40.0);

		if (Double.compare(conta.getSaldo(), 60.0) != 0) {
			throw new AssertionError("O saldo deve ser atualizado apos o saque.");
		}
		if (!conta.getExtrato().get(1).equals("Saque: 40.0")) {
			throw new AssertionError("O saque deve ser registrado no extrato.");
		}
	}

	private static void naoDevePermitirSaqueMaiorQueSaldo() {
		Conta conta = new Conta(1, 1);
		conta.depositar(10.0);

		try {
			conta.sacar(11.0);
			throw new AssertionError("Era esperada uma IllegalArgumentException.");
		} catch (IllegalArgumentException expected) {
			if (Double.compare(conta.getSaldo(), 10.0) != 0) {
				throw new AssertionError("O saldo nao deve mudar quando o saque falhar.");
			}
		}
	}
}
