import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

	public enum Tipo {
		DEPOSITO,
		SAQUE,
		TRANSFERENCIA_SAIDA,
		TRANSFERENCIA_ENTRADA
	}

	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private final Tipo tipo;
	private final double valor;
	private final LocalDateTime data;

	public Transacao(Tipo tipo, double valor, LocalDateTime data) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	@Override
	public String toString() {
		return tipo + " - " + valor + " - " + data.format(FORMATO_DATA);
	}
}
