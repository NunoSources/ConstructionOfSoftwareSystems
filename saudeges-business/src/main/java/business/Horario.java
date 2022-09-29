package business;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import business.atividade.Atividade;
import business.instrutor.Instrutor;
import business.utils.DateUtils;

@Entity
public class Horario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL) @JoinTable(name="atividadeAssociada")
	private Atividade atividadeAssociada;
	
	@ElementCollection(targetClass = Session.class)
	private List<Session> sessoes = new ArrayList<Session>();
	
	@Temporal(value = TemporalType.DATE)
    private Date dataInicio;
	
	@Column(nullable = true)
	private int duracao;


	@ManyToOne(cascade = CascadeType.ALL) @JoinTable(name="instrutorResponsavel")
	private Instrutor instrutorResponsavel;
	
	@Column(nullable = true)
	private int duracaoInstrutor;
	
	/**
	 * The version for concurrency control
	 */
	@Version
	private long version;


	public int getId() {
		return id;
	}
	
	/**
	 * @return the sessoes
	 */
	public List<Session> getSessoes() {
		return sessoes;
	}

	/**
	 * @param sessoes the sessoes to set
	 */
	public void setSessoes(List<Session> sessoes) {
		this.sessoes = sessoes;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the duracao
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * @param duracao the duracao to set
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	/**
	 * Constructor needed by JPA.
	 */
	public Horario() {
	}

	public Horario(List<Session> sessoes, Date dataInicio, int duracao) {
		//this.sessoes = sessoes;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		
		LocalDate da = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fim = da.plusMonths(duracao);
		
		for(int i = 0; i < sessoes.size(); i++) {
			Session sess = sessoes.get(i);
			
			LocalDate next = da.with(TemporalAdjusters.nextOrSame(sess.getDiaSemana()));
			
			while(next.isBefore(fim) || next.isEqual(fim)) {
				Date sessInicio = sess.getHoraInicio();
				Date sessFim = sess.getHoraFim();
				
				Calendar calInicio = Calendar.getInstance();
				calInicio.setTime(sessInicio);
				calInicio.set(Calendar.YEAR, next.getYear());
				calInicio.set(Calendar.MONTH, next.getMonthValue() - 1);
				calInicio.set(Calendar.DAY_OF_MONTH, next.getDayOfMonth());
				
				Calendar calFim = Calendar.getInstance();
				calFim.setTime(sessFim);
				calFim.set(Calendar.YEAR, next.getYear());
				calFim.set(Calendar.MONTH, next.getMonthValue() - 1);
				calFim.set(Calendar.DAY_OF_MONTH, next.getDayOfMonth());
				//cal.set(Calendar.HOUR_OF_DAY, sessInicio.getHours());
				//cal.set(Calendar.MINUTE, minutes);
				//cal.set(Calendar.SECOND, 00);
				
				Date nextInicio = calInicio.getTime();
				Date nextFim = calFim.getTime();
				
				this.sessoes.add(new Session(sess.getDiaSemana(),nextInicio,nextFim,sess.getLugaresDisponiveis()));
				next = next.with(TemporalAdjusters.next(sess.getDiaSemana()));
			}
			
			
			
			
		}
		
	}
	
	/**
	 * @return the atividadeAssociada
	 */
	public Atividade getAtividadeAssociada() {
		return atividadeAssociada;
	}

	/**
	 * @param atividadeAssociada the atividadeAssociada to set
	 */
	public void setAtividadeAssociada(Atividade atividadeAssociada) {
		this.atividadeAssociada = atividadeAssociada;
	}

	/**
	 * @return the instrutorResponsavel
	 */
	public Instrutor getInstrutorResponsavel() {
		return instrutorResponsavel;
	}

	/**
	 * @param instrutorResponsavel the instrutorResponsavel to set
	 */
	public void setInstrutorResponsavel(Instrutor instrutorResponsavel) {
		this.instrutorResponsavel = instrutorResponsavel;
	}

	/**
	 * @return the duracaoInstrutor
	 */
	public int getDuracaoInstrutor() {
		return duracaoInstrutor;
	}

	/**
	 * @param duracaoInstrutor the duracaoInstrutor to set
	 */
	public void setDuracaoInstrutor(int duracaoInstrutor) {
		this.duracaoInstrutor = duracaoInstrutor;
	}
	
	
	public boolean overlaps(Horario horario) {
		
		
		List<Session> sess = horario.getSessoes();
			
		for (int i = 0; i < sess.size(); i++) {
			Session se1 = sess.get(i);
			
			for (int j = 0; j < this.sessoes.size(); j++) {
				Session se2 = this.sessoes.get(j);
				
				if(se1.overlap(se2)) {
					return true;
				}

			}
		}
		return false;
	}

	public boolean verificarLugaresDisponiveis(Date dataInicio, int duracao) {
		
		LocalDate initial = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fim = initial.plusMonths(duracao);
		
		for (int i = 0; i < sessoes.size(); i++) {
			Session se = sessoes.get(i);
			
			if (se.getHoraInicio().getTime() >= dataInicio.getTime() && se.getHoraFim().getTime() <= DateUtils.toDate(fim).getTime() && se.getLugaresDisponiveis() == 0) {
					return false;
			}
			
		}
		
		return true;
	}

	public void reservarLugaresSessoes(Date dataInicio, int duracao) {
		LocalDate initial = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fim = initial.plusMonths(duracao);
		
		for (int i = 0; i < sessoes.size(); i++) {
			Session se = sessoes.get(i);
			
			if (se.getDataInicio().getTime() >= dataInicio.getTime() && se.getDataFim().getTime() <= DateUtils.toDate(fim).getTime()) {
					se.reservarSlot();
			}
			
		}
		
	}
}