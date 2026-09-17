public class Conta {

	private final int id;
	private final int clienteId;
	private double saldo;

	public Conta(int id, int clienteId) {
		this.id = id;
		this.clienteId = clienteId;
		this.saldo = 0;
	}

	public int getId() {
		return id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public double getSaldo() {
		return saldo;
	}
}
