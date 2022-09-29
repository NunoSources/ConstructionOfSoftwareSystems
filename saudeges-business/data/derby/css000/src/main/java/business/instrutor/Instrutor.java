package business.instrutor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import business.Horario;
import facade.dtos.TiposCertificacoes;

/**
 * Entity implementation class for Entity: Instrutor
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Instrutor.FIND_BY_NUMERO, query = "SELECT v FROM Instrutor v WHERE v.numero = :"
				+ Instrutor.INSTRUTOR_NUMERO),
		@NamedQuery(name = Instrutor.GET_ALL_NAMES, query = "SELECT v.nome FROM Instrutor v"),
		@NamedQuery(name = Instrutor.GET_ALL, query = "SELECT v FROM Instrutor v"), })
public class Instrutor implements Serializable {

	public static final String FIND_BY_NUMERO = "Instrutor.findByNumero";
	public static final String GET_ALL_NAMES = "Instrutor.getAllNames";
	public static final String INSTRUTOR_NUMERO = "numero";
	public static final String GET_ALL = "Instrutor.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private int numero;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = TiposCertificacoes.class)
	private List<TiposCertificacoes> certificacoes = new ArrayList<>();

	@OneToMany(mappedBy = "instrutorResponsavel", targetEntity = Horario.class, cascade = CascadeType.ALL)
	private List<Horario> horarios = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	
	/**
	 * The version for concurrency control
	 */
	@Version
	private long version;


	/**
	 * Constructor needed by JPA.
	 */
	public Instrutor() {
	}

	/**
	 * Constructor for a new Instrutor.
	 * 
	 * @param name The name of the Instrutor
	 */
	public Instrutor(int numero, String name, List<TiposCertificacoes> certificacoes) {
		this.numero = numero;
		this.nome = name;
		this.certificacoes = certificacoes;
	}

	/**
	 * Returns the number of the Instrutor
	 * 
	 * @return the number
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Returns the name of the Instrutor
	 * 
	 * @return the name of the Instrutor
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Returns the certifications of the Instrutor
	 * 
	 * @return the certificacoes
	 */
	public List<TiposCertificacoes> getCertificacoes() {
		return certificacoes;
	}

	/**
	 * Returns if the Instrutor has the certifications
	 * 
	 * @return yes or no
	 */
	public boolean ehCertificado(List<TiposCertificacoes> certificacoesNecessarias) {
		return certificacoes.containsAll(certificacoesNecessarias);
	}

	@Override
	public String toString() {
		return this.getNome();
	}

	/**
	 * Returns the list of event days in which the Instrutor is used
	 * 
	 * @return the list of event days in which the Instrutor is used
	 */
	public List<Horario> getHorarios() {
		return horarios;
	}

	/**
	 * Assigns event days to the Instrutor
	 * 
	 * @param days The list of event days we're assigning to the Instrutor
	 */
	public void addHorario(List<Horario> horario) {
		horarios.addAll(horario);
	}

	/**
	 * Checks if there is any date/hour period intersection between the given list
	 * and that of the events already scheduled to the Instrutor.
	 * 
	 * @param dayPeriods a list of day periods
	 * @return whether the Instrutor is free in all periods in the list
	 */
	// TODO: make this search in the database with a query!!!!
	public boolean isFree(Horario horario) {
		if (horarios.isEmpty())
			return true;
		
		for (Horario h : horarios) {
			
			//Horario horarioInstrutor = new Horario(h.getSessoes(), h.getDataInicio(), h.getDuracaoInstrutor());
			
			System.out.println("TESTE");
			
			if (h.overlaps(horario))
				return false;
		}
		return true;
	}
	
	public boolean isFreeAll(List<Horario> horarios) {
		if (horarios.isEmpty())
			return true;
		
		for (Horario h : horarios) {
			
			if(!isFree(h))
				return false;
		}
		return true;
	}

	public void addHorario(Horario horario) {
		horarios.add(horario);
	}

}
