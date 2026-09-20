public class ContaDepositoTest {

	public static void main(String[] args) {
		deveAtualizarSaldoERegistrarDeposito();
		naoDeveAceitarValorZeroOuNegativo();
		System.out.println("Todos os testes de deposito passaram.");
	}

	private static void deveAtualizarSaldoERegistrarDeposito() {
		Conta conta = new Conta(1, 1);

		conta.depositar(50.0);

		if (Double.compare(conta.getSaldo(), 50.0) != 0) {
			throw new AssertionError("O saldo deve ser atualizado apos o deposito.");
		}
		if (conta.getExtrato().size() != 1 || !conta.getExtrato().get(0).equals("Deposito: 50.0")) {
			throw new AssertionError("O deposito deve ser registrado no extrato.");
		}
	}

	private static void naoDeveAceitarValorZeroOuNegativo() {
		Conta conta = new Conta(1, 1);
		deveLancarExcecao(() -> conta.depositar(0));
		deveLancarExcecao(() -> conta.depositar(-1));
	}

	private static void deveLancarExcecao(Runnable operacao) {
		try {
			operacao.run();
			throw new AssertionError("Era esperada uma IllegalArgumentException.");
		} catch (IllegalArgumentException expected) {
			// Comportamento esperado.
		}
	}
}
