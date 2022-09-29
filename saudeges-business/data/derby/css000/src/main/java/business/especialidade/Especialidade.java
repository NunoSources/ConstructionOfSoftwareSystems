package business.especialidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import facade.dtos.TiposCertificacoes;

/*@NamedQueries({
	@NamedQuery(name = Especialidade.FIND_ALL, query = "SELECT e FROM Especialidade e"),
	@NamedQuery(name = Especialidade.FIND_BY_NAME, query = "SELECT e FROM Especialidade e WHERE e.nome = :" + 
					   Especialidade.NOME_ESPECIALIDADE)
})*/
@Entity
@NamedQuery(name = Especialidade.FIND_ALL, query = "SELECT e FROM Especialidade e")
@NamedQuery(name = Especialidade.FIND_BY_NOME, query = "SELECT a FROM Especialidade a WHERE a.nome = :" + Especialidade.NOME_ESPECIALIDADE)
public class Especialidade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "Especialidade.findAll";
	public static final String FIND_BY_NAME = "Especialidade.findByName";
	public static final String FIND_BY_NOME = "Especialidade.getEspecialidadeByNome";
	public static final String NOME_ESPECIALIDADE = "nome";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private int duracao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TiposCertificacoes certificacaoNecessaria;
	
	Especialidade(){}

	public Especialidade(String nome, int duracao, TiposCertificacoes certificacaoNecessaria) {
		this.nome = nome;
		this.duracao = duracao;
		this.certificacaoNecessaria = certificacaoNecessaria;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getDuracao() {
		return this.duracao;
	}
	
	public TiposCertificacoes getCertificacaoNecessaria() {
		return this.certificacaoNecessaria;
	}
	
}
