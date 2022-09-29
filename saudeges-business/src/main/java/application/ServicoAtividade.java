package application;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import business.atividade.Atividade;
import business.especialidade.Especialidade;
import business.handlers.CriarAtividadeHandler;
import business.handlers.DefinirHorarioHandler;
import business.instrutor.Instrutor;
import facade.dtos.AtividadeDTO;
import facade.dtos.EspecialidadeDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ServicoAtividade implements IServicoAtividadeRemote {

	@EJB
	private CriarAtividadeHandler atividadeHandler;
	@EJB
	private DefinirHorarioHandler horarioHandler;

	@Override
	public List<EspecialidadeDTO> criaAtividade() throws ApplicationException {
		List<EspecialidadeDTO> result = new LinkedList<>(); 
		for (Especialidade especialidade : atividadeHandler.criaAtividade())
			result.add(new EspecialidadeDTO(especialidade.getId(), especialidade.getNome(), especialidade.getDuracao(), especialidade.getCertificacaoNecessaria()));
		return result;
	}
	
	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public AtividadeDTO addAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) throws ApplicationException {
		return atividadeHandler.addAtividade(nome, designacao, isRegular, nrSessoes, duracao, preco, maxParticipantes);
	}
	
	@Override
	public List<AtividadeDTO> definirHorario() throws ApplicationException {
		List<AtividadeDTO> result = new LinkedList<>(); 
		for (Atividade atividade : horarioHandler.definirHorario())
			result.add(new AtividadeDTO(atividade.getEspecialidade().getNome(), atividade.getDesignacao(), atividade.isRegular(), atividade.getNrSessoes(), atividade.getDuracao(), atividade.getPreco(), atividade.getMaxParticipantes()));
		return result;
	}

	@Override
	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes,
			int duracao, float preco) throws ApplicationException {
		atividadeHandler.enviaInformacaoCriarAtividade(nome, designacao, isRegular, nrSessoes, duracao, preco);
	}

	@Override
	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes,
			int duracao, float preco, int maxParticipantes) throws ApplicationException {
		atividadeHandler.enviaInformacaoCriarAtividade(nome, designacao, isRegular, nrSessoes, duracao, preco, maxParticipantes);
		
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void enviaInformacaoDefinirHorario(String designacao, int nrInstrutor, int duracaoInstrutor, String[] sessoes, String startDate)
			throws ApplicationException {
		horarioHandler.enviaInformacaoDefinirHorario(designacao, nrInstrutor, duracaoInstrutor, sessoes, startDate);
	}
	
	@Override
	public Collection<EspecialidadeDTO> getEspecialidades() throws ApplicationException {
		List<EspecialidadeDTO> result = new LinkedList<>(); 
		for (Especialidade especialidade : atividadeHandler.criaAtividade())
			result.add(new EspecialidadeDTO(especialidade.getId(), especialidade.getNome(), especialidade.getDuracao(), especialidade.getCertificacaoNecessaria()));
		return result;
	}

	@Override
	public Collection<InstrutorDTO> getInstrutores() throws ApplicationException {
		List<InstrutorDTO> result = new LinkedList<>(); 
		for (Instrutor instrutor : atividadeHandler.getInstrutores())
			result.add(new InstrutorDTO(instrutor.getNumero(), instrutor.getNome(), instrutor.getCertificacoes()));
		return result;
	}

	
	
	
}