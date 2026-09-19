import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta {

	private final int id;
	private final int clienteId;
	private double saldo;
	private final List<String> extrato = new ArrayList<>();

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

	public void depositar(double valor) {
		if (!Double.isFinite(valor) || valor <= 0) {
			throw new IllegalArgumentException("Valor do deposito deve ser maior que zero");
		}

		saldo += valor;
		registrarExtrato("Deposito: " + valor);
	}

	public void sacar(double valor) {
		if (!Double.isFinite(valor)) {
			throw new IllegalArgumentException("Valor do saque deve ser valido");
		}

		debitarSaldo(valor);
		registrarExtrato("Saque: " + valor);
	}

	public void debitarSaldo(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor a debitar deve ser maior que zero");
		}
		if (valor > saldo) {
			throw new IllegalArgumentException("Saldo insuficiente na conta " + id);
		}
		saldo -= valor;
	}

	public void creditarSaldo(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor a creditar deve ser maior que zero");
		}
		saldo += valor;
	}

	public void registrarExtrato(String registro) {
		extrato.add(registro);
	}

	public List<String> getExtrato() {
		return Collections.unmodifiableList(extrato);
	}
}
