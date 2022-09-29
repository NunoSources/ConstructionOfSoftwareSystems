package business.atividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import business.Horario;
import business.especialidade.Especialidade;


@Entity
@NamedQuery(name = Atividade.FIND_BY_DESIGNACAO, query = "SELECT a FROM Atividade a WHERE a.designacao = :" + Atividade.ATIVIDADE_DESIGNACAO)
@NamedQuery(name = Atividade.GET_ALL_OCASIONAIS, query = "SELECT a FROM Atividade a WHERE a.isRegular = false")
@NamedQuery(name = Atividade.GET_ALL_REGULARES, query = "SELECT a FROM Atividade a WHERE a.isRegular = true")
public class Atividade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_DESIGNACAO = "Atividade.findByDesignacao";
	public static final String ATIVIDADE_DESIGNACAO = "designacao";
	public static final String GET_ALL_OCASIONAIS = "Atividade.getAllOcasionais";
	public static final String GET_ALL_REGULARES = "Atividade.getAllRegulares";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne @JoinColumn
	private Especialidade especialidade;
	
	@Column(nullable = false, unique = true)
	private String designacao;
	
	@Column(nullable = false)
	private boolean isRegular;
	
	@Column(nullable = false)
	private int nrSessoes;
	
	@Column(nullable = false)
	private float preco;
	
	@Basic(optional = true)
	private int maxParticipantes;
	
	@Column(nullable = false)
	private int duracao;
	
	@OneToMany(mappedBy = "atividadeAssociada", targetEntity = Horario.class, cascade = CascadeType.ALL)
	private List<Horario> horariosAssociados = new ArrayList<Horario>();
	
	/**
	 * The version for concurrency control
	 */
	@Version
	private long version;

	
	//Construtor do JPA
	Atividade(){}
	
	public Atividade(Especialidade especialidade, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco) {
		this.especialidade = especialidade;
		this.designacao = designacao;
		this.isRegular = isRegular;
		this.nrSessoes = nrSessoes;
		this.duracao = duracao;
		this.preco = preco;
	}
	
	public Atividade(Especialidade especialidade, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) {
		this.especialidade = especialidade;
		this.designacao = designacao;
		this.isRegular = isRegular;
		this.nrSessoes = nrSessoes;
		this.duracao = duracao;
		this.maxParticipantes = maxParticipantes;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * @return the especialidade
	 */
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	/**
	 * @param especialidade the especialidade to set
	 */
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	/**
	 * @return the designacao
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * @param designacao the designacao to set
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * @return the isRegular
	 */
	public boolean isRegular() {
		return isRegular;
	}

	/**
	 * @param isRegular the isRegular to set
	 */
	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	/**
	 * @return the nrSessoes
	 */
	public int getNrSessoes() {
		return nrSessoes;
	}

	/**
	 * @param nrSessoes the nrSessoes to set
	 */
	public void setNrSessoes(int nrSessoes) {
		this.nrSessoes = nrSessoes;
	}

	/**
	 * @return the preco
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}

	/**
	 * @return the maxParticipantes
	 */
	public int getMaxParticipantes() {
		return maxParticipantes;
	}

	/**
	 * @param maxParticipantes the maxParticipantes to set
	 */
	public void setMaxParticipantes(int maxParticipantes) {
		this.maxParticipantes = maxParticipantes;
	}

	/**
	 * @return the duracao
	 */
	public int getDuracao() {
		return duracao;
	}
	

	public void setHorariosAssociados(Horario horario) {
		this.horariosAssociados.add(horario);
	}
	
	public List<Horario> getHorariosAssociados() {
		return horariosAssociados;
	}
	
	
	public boolean isHorarioAssociado(Horario horario)  {
		return horariosAssociados.contains(horario);
	}


}
