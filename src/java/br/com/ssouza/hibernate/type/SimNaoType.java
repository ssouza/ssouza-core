package br.com.ssouza.hibernate.type;

import org.hibernate.type.CharBooleanType;

import br.com.ssouza.util.Constantes;

/**
 * Classe que define para o Hibernate o tipo 'Sim' ou 'Não' como boolean.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
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