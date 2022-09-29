package business.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.Horario;
import business.Session;
import business.atividade.Atividade;
import business.atividade.CatalogoAtividades;
import business.especialidade.CatalogoEspecialidades;
import business.especialidade.Especialidade;
import business.utils.DateUtils;
import facade.dtos.DadosPagamentoDTO;
import facade.dtos.HorarioDTO;
import facade.dtos.HorarioPrecoDTO;
import facade.exceptions.ApplicationException;

@Stateless
public class ComprarParticipacaoMensalHandler {
	
	@EJB
	private CatalogoAtividades catalogoAtividades;
	
	@EJB
	private CatalogoEspecialidades catalogoEspecialidades;


	public HorarioPrecoDTO indicaAtividadeParticipacaoMensal(String designacao) throws ApplicationException {
		
		try {
			
			Atividade atividade = catalogoAtividades.getAtividadeByDesignacao(designacao);
			
			if(atividade == null)
				throw new ApplicationException("A atividade nao existe");
			if(!atividade.isRegular())
				throw new ApplicationException("A atividade nao eh regular");
			
			List<Horario> horariosAtividade = atividade.getHorariosAssociados();
			float preco = atividade.getPreco();
			
			List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();
			
			for( int i = 0; i < horariosAtividade.size(); i++ ) {
				
				Horario hor = horariosAtividade.get(i);
				List<Session> sess = hor.getSessoes();
				
				List<String> sessoes = new ArrayList<String>(atividade.getNrSessoes());
				for (int j = 0; j < sess.size() && sessoes.size() < atividade.getNrSessoes(); j++) {
					
					Session s = sess.get(j);
					
					String saux = s.getDiaSemana() + " - " +  s.getHoraInicio();
					
					if(!sessoes.contains(saux)) {
						sessoes.add(saux);
					}
				}
				
				horarios.add(new HorarioDTO(hor.getId(), atividade.getDesignacao(), sessoes, hor.getDataInicio().toString(), String.valueOf(hor.getDuracao())));
			}
			
			
			return new HorarioPrecoDTO(horarios, preco);
		}catch(Exception e) {
			throw new ApplicationException("Erro ao obter a lista de horarios", e);
		}
	}

	public DadosPagamentoDTO escolheHorarioParticipacaoMensal(int horarioId, String dataInicio, int duracao, String email, String designacao) 
			throws ApplicationException {
		
		try {
			Atividade atividade = catalogoAtividades.getAtividadeByDesignacao(designacao);
			
			Horario hor = null;
			
			for (Horario horario : atividade.getHorariosAssociados()) {
				if (horario.getId() == horarioId) {
					hor = horario;
					break;
				}
			}
			
			Date startDate = DateUtils.criarDataAno(dataInicio);
			
			
			if(hor == null) {
				throw new ApplicationException("Erro ao processar horario escolhido");
			}
			
			if(hor.getDataInicio().after(startDate)) {
				throw new ApplicationException("Data de inicio indicada eh antes da data de inicio do horario escolhido");
			}
			
			if(!hor.verificarLugaresDisponiveis(startDate, duracao)) {
				throw new ApplicationException("Nao existem lugares disponiveis");
			}
			
			System.out.println(startDate.toString());
			hor.reservarLugaresSessoes(startDate, duracao);
			
			DadosPagamentoDTO pagamento = new DadosPagamentoDTO (email, atividade.getPreco() * duracao);
			
			return pagamento;
			
			
		}catch(Exception e) {
			throw new ApplicationException("Erro ao comprar participacao mensal em atividade regular", e);
		}
	}
	
	
	public List<Atividade> getAtividades() throws ApplicationException{
		try {
			
			List<Atividade> result = catalogoAtividades.getAtividadesRegulares();
			return result;
			
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de atividades regulares existentes", e);
		}
	}
	
	
	public List<Especialidade> getEspecialidades() throws ApplicationException{
		try {
			
			List<Especialidade> result = catalogoEspecialidades.getEspecialidades();
			return result;
			
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de atividades regulares existentes", e);
		}
	}
	
}
