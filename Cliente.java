public class Cliente {

	private final int id;
	private final String nome;
	private final String cpf;
	private final String email;

	public Cliente(int id, String nome, String cpf, String email) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome é obrigatório");
		}
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("CPF é obrigatório");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("E-mail é obrigatório");
		}

		String cpfNormalizado = normalizarCpf(cpf);
		if (!cpfValido(cpfNormalizado)) {
			throw new IllegalArgumentException("CPF inválido: " + cpf);
		}
		if (!emailValido(email)) {
			throw new IllegalArgumentException("E-mail inválido: " + email);
		}

		this.id = id;
		this.nome = nome.trim();
		this.cpf = cpfNormalizado;
		this.email = email.trim();
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public static String normalizarCpf(String cpf) {
		return cpf.replaceAll("[^0-9]", "");
	}

	private static boolean emailValido(String email) {
		return email.trim().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
	}

	private static boolean cpfValido(String cpf) {
		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		int[] digitos = new int[11];
		for (int i = 0; i < 11; i++) {
			digitos[i] = cpf.charAt(i) - '0';
		}

		int primeiroDigito = calcularDigitoVerificador(digitos, 9);
		if (primeiroDigito != digitos[9]) {
			return false;
		}

		int segundoDigito = calcularDigitoVerificador(digitos, 10);
		return segundoDigito == digitos[10];
	}

	private static int calcularDigitoVerificador(int[] digitos, int quantidade) {
		int soma = 0;
		int peso = quantidade + 1;
		for (int i = 0; i < quantidade; i++) {
			soma += digitos[i] * peso;
			peso--;
		}
		int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;
	}
}
