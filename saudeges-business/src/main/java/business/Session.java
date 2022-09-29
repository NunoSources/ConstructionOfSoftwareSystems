package business;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Session implements Serializable {
	

			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(nullable = true)
		@Enumerated(EnumType.STRING)
	    private DayOfWeek diaSemana;
		
		@Column(nullable = true)
	    @Temporal(value = TemporalType.DATE)
	    private Date dataInicio;
	    
		@Column(nullable = true)
	    @Temporal(value = TemporalType.DATE)
	    private Date dataFim;

		@Column(nullable = true)
	    @Temporal(value = TemporalType.TIME)
	    private Date horaInicio;
	    
		@Column(nullable = true)
	    @Temporal(value = TemporalType.TIME)
	    private Date horaFim;
		
		@Column(nullable = true)
		private int lugaresDisponiveis;


	    public Session() {}

	    public Session(DayOfWeek diaSemana, Date horaInicio, Date horaFim, int lugaresDisponiveis) {
	        this.diaSemana = diaSemana;
	        this.horaInicio = horaInicio;
	        this.dataInicio = horaInicio;
	        this.horaFim = horaFim;
	        this.dataFim = horaFim;
	        this.lugaresDisponiveis = lugaresDisponiveis;
	    }

	    public DayOfWeek getDiaSemana() {
	        return diaSemana;
	    }

	    public Date getHoraInicio() {
	        return horaInicio;
	    }
	    
	    public Date getHoraFim() {
	        return horaFim;
	    }
	    
	    public int getLugaresDisponiveis() {
	    	return lugaresDisponiveis;
	    }
	    
	    public void reservarSlot() {
	    	this.lugaresDisponiveis -= 1;
	    }
	    
	    public boolean sameDay(Session session) {
	    	return this.diaSemana == session.getDiaSemana();
	    }
	    
	    
	    /**
		 * @return the dataInicio
		 */
		public Date getDataInicio() {
			return dataInicio;
		}

		/**
		 * @return the dataFim
		 */
		public Date getDataFim() {
			return dataFim;
		}

		public boolean overlap(Session session) {
	    	
	    	Date sDI = session.getDataInicio();
	    	Date sHI = session.getHoraInicio();
	    	
	    	
	    	DateFormat dfH = new SimpleDateFormat("hh");
	    	DateFormat dfM = new SimpleDateFormat("mm");
	    	
	    	String hour = dfH.format(sHI);
	    	String minutes = dfM.format(sHI);
	    	
	    	Calendar calInicio = Calendar.getInstance();
			calInicio.setTime(sDI);
			calInicio.set(Calendar.HOUR, Integer.parseInt(hour));
			calInicio.set(Calendar.MINUTE, Integer.parseInt(minutes));
			
			

	    	Date sDF = session.getDataFim();
			Date sHF = session.getHoraFim();
			
	    	hour = dfH.format(sHF);
	    	minutes = dfM.format(sHF);
			
			Calendar calFim = Calendar.getInstance();
			calFim.setTime(sDF);
			calFim.set(Calendar.HOUR, Integer.parseInt(hour));
			calFim.set(Calendar.MINUTE, Integer.parseInt(minutes));
			
		
			Date sInicio = calInicio.getTime();
			Date sFim = calFim.getTime();
	    	
	    	
	    	return this.horaInicio.before(sFim) && sInicio.before(this.horaFim);
	    }
	
}
