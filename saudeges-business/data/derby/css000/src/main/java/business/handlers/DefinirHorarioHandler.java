package business.handlers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.Horario;
import business.Session;
import business.atividade.Atividade;
import business.atividade.CatalogoAtividades;
import business.instrutor.CatalogoInstrutores;
import business.instrutor.Instrutor;
import business.utils.DateUtils;
import facade.exceptions.ApplicationException;

@Stateless
public class DefinirHorarioHandler {
	
	@EJB
	private CatalogoAtividades catalogoAtividades;
	
	@EJB
	private CatalogoInstrutores catalogoInstrutores;

	public List<Atividade> definirHorario() throws ApplicationException{
		try {
			
			List<Atividade> result = catalogoAtividades.getAtividadesRegulares();
			return result;
			
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de atividades regulares existentes", e);
		}
	}

	public void enviaInformacaoDefinirHorario(String designacao, int nrInstrutor, int duracaoInstrutor, String[] sessoes, String startDate) throws ApplicationException {
		
		
		try {
			
			Atividade atividade = catalogoAtividades.getAtividadeByDesignacao(designacao);
			Instrutor instrutor = catalogoInstrutores.getInstrutorByNumero(nrInstrutor);
			
			if(!atividade.isRegular())
				throw new ApplicationException("A designacao nao eh de uma atividade regular");
			if(sessoes.length != atividade.getNrSessoes() )
				throw new ApplicationException("Quantidade de sessoes incorreta");
			
			
			List<Session> sessoesAux = new ArrayList<Session>();
			for(String sessao: sessoes) {
				String[] sessaoProp = sessao.split("\\s+");
				
				Date i = DateUtils.criarData(sessaoProp[1]);
				LocalDateTime faux = i.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMinutes(atividade.getDuracao());
				Date f = Date.from(faux.atZone(ZoneId.systemDefault()).toInstant());
				
				sessoesAux.add(DateUtils.criarSessao(DayOfWeek.of(Integer.parseInt(sessaoProp[0])), i, f, atividade.getMaxParticipantes()));
			}
			
			
			Horario horario = new Horario(sessoesAux, DateUtils.criarDataAno(startDate), duracaoInstrutor);
			
			
			
			if(!horario.getDataInicio().after(DateUtils.toDate(DateUtils.getMockCurrentDate().toLocalDate())))
				throw new ApplicationException("A data nao eh no futuro");
			
			
			if(horario.getDuracao() < 1)
				throw new ApplicationException("O numero de meses em que o horario vai estar disponivel eh inferior a 1");
			
			
			if(!instrutor.ehCertificado(Arrays.asList(atividade.getEspecialidade().getCertificacaoNecessaria())))
				throw new ApplicationException("O instrutor atribuido nao tem certificado apropriada para a especialidade");
			
			
			if(duracaoInstrutor > horario.getDuracao())
				throw new ApplicationException("O numero de meses atribuido ao instrutor eh maior que a duracao do horario");
			
			
			
			if(!instrutor.isFree(horario)) 
				throw new ApplicationException("O instrutor atribuido nao esta livre nos horarios das sessoes durante o tempo"
						+ " indicado.");
			
			
			
			horario.setAtividadeAssociada(atividade);
			atividade.setHorariosAssociados(horario);
			horario.setInstrutorResponsavel(instrutor);
			horario.setDuracaoInstrutor(duracaoInstrutor);
			instrutor.addHorario(horario);
			
		}catch(Exception e) {
			throw new ApplicationException("Erro ao criar atividade", e);
		}
	}

}
