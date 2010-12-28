package br.com.ssouza.util;

/**
 * Classe que define m�todos de formata��o de String.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class FormatHelper {

	private static final int TAMANHO_MAXIMO_CPF = 11;
	private static final int TAMANHO_MAXIMO_CNPJ = 14;

	/**
	 * Efetuar a formata��o de um cpf para a sua m�scara.
	 * 
	 * @param cpf
	 * @return <code>ddd.ddd.ddd-dd</code>, onde "d" � um d�gito decimal.
	 * @throws NullPointerException
	 */
	public static String cpfComMascara(String cpf) {

		if (cpf == null) {
			throw new NullPointerException("CPF n�o pode ser nulo");
		}

		cpf = StringHelper.removerNaoDigitos(cpf);
		cpf = completeComZerosAEsquerda(cpf, TAMANHO_MAXIMO_CPF);

		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}

	/**
	 * Efetuar a formata��o de um cnpj para a sua m�scara.
	 * 
	 * @param cnpj
	 * @return <code>dd.ddd.ddd-dddd/dd</code>, onde "d" � um d�gito decimal.
	 * @throws NullPointerException
	 */
	public static String cnpjComMascara(String cnpj) {

		if (cnpj == null) {
			throw new NullPointerException("CNPJ n�o pode ser nulo");
		}

		cnpj = StringHelper.removerNaoDigitos(cnpj);
		cnpj = completeComZerosAEsquerda(cnpj, TAMANHO_MAXIMO_CNPJ);

		return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
	}

	/**
	 * Completa uma string com zeros a esquerda at� completar o tamanho desejado.
	 * 
	 * @param valor
	 * @param tamanho
	 * @return
	 * @throws NullPointerException
	 */
	public static String completeComZerosAEsquerda(String valor, int tamanho) {

		if (valor == null) {
			throw new NullPointerException();
		}

		for (int i = (tamanho - valor.length()); i > 0; i--) {
			valor = "0" + valor;
		}

		return valor;
	}

}