package facade.dtos;

import java.io.Serializable;

public class EspecialidadeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private int duracaoMinima;
	private TiposCertificacoes certificacaoNecessaria;
	
	public EspecialidadeDTO(int id, String nome, int duracaoMinima, TiposCertificacoes certificacao) {
		this.id = id;
		this.nome = nome;
		this.duracaoMinima = duracaoMinima;
		this.certificacaoNecessaria = certificacao;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getDuracaoMinima() {
		return duracaoMinima;
	}

	public TiposCertificacoes getCertificacaoNecessaria() {
		return certificacaoNecessaria;
	}

}
