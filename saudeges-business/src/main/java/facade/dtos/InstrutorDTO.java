package facade.dtos;

import java.io.Serializable;
import java.util.List;

public class InstrutorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String nome;
	private List<TiposCertificacoes> certificacoes;

	
	public InstrutorDTO(int numero, String name, List<TiposCertificacoes> certificacoes) {
		this.numero = numero;
		this.nome = name;
		this.certificacoes = certificacoes;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public List<TiposCertificacoes> getCertificacoes() {
		return certificacoes;
	}
	
	
}
