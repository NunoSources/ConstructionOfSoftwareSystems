package business.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import facade.dtos.DadosPagamentoDTO;
import facade.dtos.TiposCertificacoes;
import facade.exceptions.ApplicationException;

@Stateless
public class AgendarAtividadeOcasionalHandler {
	
	@EJB
	private CatalogoAtividades catalogoAtividades;
	
	@EJB
	private CatalogoInstrutores catalogoInstrutores;

	public List<Atividade> agendaAtividadeOcasional(String especialidade) throws ApplicationException{
		
		try {
		
			
			List<Atividade> result = catalogoAtividades.getAtividadesOcasionaisByEspecialidade(especialidade);
			
			Collections.sort(result, new Comparator<Atividade>() {
			  @Override
			  public int compare(Atividade a1, Atividade a2) {
			    return Float.compare(a1.getPreco(), a2.getPreco());
			  }
			});
			return result;
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de atividades ocasionais existentes", e);
		}
	}

	public List<Instrutor> enviaInformacaoAtividadeOcasional(String atividadeEscolhida, String[] sessoes) throws ApplicationException {
		
		
		try {
			
			Atividade at = catalogoAtividades.getAtividadeByDesignacao(atividadeEscolhida);
			
			if(at == null)
				throw new ApplicationException("Problema ao tentar encontrar atividade com a designacao escolhida");
			
			if(at.isRegular())
				throw new ApplicationException("A designacao eh de uma atividade regular");
			
			if(sessoes.length != at.getNrSessoes() )
				throw new ApplicationException("Quantidade de sessoes incorreta");
			
			
			List<Horario> horarios = parseSessoes(at, sessoes);
			
			List<Instrutor> listaInstrutores = catalogoInstrutores.getAllInstructors();
			List<Instrutor> instrutoresDisponiveis = new ArrayList<>();
			
			TiposCertificacoes certificacaoNecessaria = at.getEspecialidade().getCertificacaoNecessaria();
			
			for( Instrutor v : listaInstrutores ) {
				if (v.isFreeAll(horarios) && v.ehCertificado(Arrays.asList(certificacaoNecessaria)))
					instrutoresDisponiveis.add(v);
			}
			
			
			return instrutoresDisponiveis;
		}catch(Exception e) {
			
			throw new ApplicationException("Erro ao criar atividade", e);
		}
	
	}

	public DadosPagamentoDTO indicaDadosAtividadeOcasional(String atividadeEscolhida, String[] sessoes, int instrutor, String emailUtente) throws ApplicationException {
		
		try {
			
			Atividade at = catalogoAtividades.getAtividadeByDesignacao(atividadeEscolhida);
			
			if(at == null)
				throw new ApplicationException("Problema ao tentar encontrar atividade com a designacao escolhida");
			
			if(at.isRegular())
				throw new ApplicationException("A designacao eh de uma atividade regular");
			
			if(sessoes.length != at.getNrSessoes() )
				throw new ApplicationException("Quantidade de sessoes incorreta");
			
			
			List<Horario> horarios = parseSessoes(at, sessoes);
			
			Instrutor instrutorC = catalogoInstrutores.getInstrutorByNumero(instrutor);
			
			if(!instrutorC.isFreeAll(horarios)) 
				throw new ApplicationException("O instrutor nao estah livre durante os horarios de todas as sessoes "
						+ "no periodo atribuido");
			
			for( Horario h : horarios) {
				h.setDuracaoInstrutor(0);
				h.setInstrutorResponsavel(instrutorC);
				h.setAtividadeAssociada(at);
			}
			
			
			instrutorC.addHorario(horarios);

			DadosPagamentoDTO pagamento = new DadosPagamentoDTO(emailUtente, at.getPreco());

			
			return pagamento;
		} catch (Exception e) {
			
			throw new ApplicationException("Error while trying to book an ocasional activity for \"" + atividadeEscolhida + "\"", e);
		}
	}
	
	private List<Horario> parseSessoes(Atividade at, String[] sessoes) throws ParseException {
		List<Horario> horarios = new ArrayList<Horario>();
		for(String sessao: sessoes) {	
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Date date = sdf.parse(sessao);
			
			DayOfWeek dayOfWeek = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getDayOfWeek();
			LocalDateTime faux = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMinutes(at.getDuracao());
			Date f = Date.from(faux.atZone(ZoneId.systemDefault()).toInstant());
			
			Session sessionAux = DateUtils.criarSessao(dayOfWeek, date, f, at.getMaxParticipantes());
			
			horarios.add(new Horario(Arrays.asList(sessionAux), date, 0));
		}
		return horarios;
	}

}
