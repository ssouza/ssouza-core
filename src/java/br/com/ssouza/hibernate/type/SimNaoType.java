package br.com.ssouza.hibernate.type;

import org.hibernate.type.CharBooleanType;

import br.com.ssouza.util.Constantes;

/**
 * Classe que define para o Hibernate o tipo 'Sim' ou 'N�o' como boolean.
 * 
 * @author <a href="mailto:sergio.lcs@hotmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since 12/06/2009
 */
public class SimNaoType extends CharBooleanType {

	private static final long serialVersionUID = -4635317427773103818L;

	@Override
	public String getName() {
		return "sim_nao";
	}

	@Override
	protected String getFalseString() {
		return Constantes.NAO;
	}

	@Override
	protected String getTrueString() {
		return Constantes.SIM;
	}

}