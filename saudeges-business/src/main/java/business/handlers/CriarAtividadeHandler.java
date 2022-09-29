package business.handlers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.atividade.Atividade;
import business.atividade.AtividadesOcasionais;
import business.atividade.AtividadesRegulares;
import business.atividade.CatalogoAtividades;
import business.especialidade.CatalogoEspecialidades;
import business.especialidade.Especialidade;
import business.instrutor.CatalogoInstrutores;
import business.instrutor.Instrutor;
import facade.dtos.AtividadeDTO;
import facade.exceptions.ApplicationException;

@Stateless
public class CriarAtividadeHandler {
	
	@EJB
	private CatalogoEspecialidades catalogoEspecialidades;
	
	@EJB
	private CatalogoAtividades catalogoAtividades;
	
	@EJB
	private CatalogoInstrutores catalogoInstrutores;
	

	public List<Especialidade> criaAtividade() throws ApplicationException {
		
		try {
			List<Especialidade> especialidades = catalogoEspecialidades.getEspecialidades();
			return especialidades;
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de especialidades", e);
		}
	}
	
	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco) throws ApplicationException {
		
		try {
			
			Especialidade especialidade = catalogoEspecialidades.getEspecialidadeByNome(nome);
			
			if(nome == null)
				throw new ApplicationException("Eh necessario especificar uma especialidade.");
			if(!(designacao.matches("^[A-Za-z0-9? ,_-]+$") && designacao.charAt(0) >= 'A' && designacao.charAt(0) <= 'Z' && catalogoAtividades.ehUnica(designacao)))
				throw new ApplicationException("A designacao nao eh valida ou nao eh unica.");
			if(isRegular && !AtividadesRegulares.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade regular.");
			if(!isRegular && !AtividadesOcasionais.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade ocasional.");
			if(duracao < 0 || duracao < especialidade.getDuracao())
				throw new ApplicationException("A dura��o da atividade n�o pode ser inferior � dura��o m�nima definida para a especialidade.");
			if(preco < 0)
				throw new ApplicationException("O preco nao eh positivo.");
			if(isRegular)
				throw new ApplicationException("O n�mero m�ximo de participantes numa atividade regular � obrigat�rio.");
			
			
			catalogoAtividades.criarAtividade(especialidade, designacao, isRegular, nrSessoes, duracao, preco);
			
			
			
		}catch(Exception e) {
			throw new ApplicationException("Erro ao criar atividade", e);
		}
	}

	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) throws ApplicationException {
		
		try {
			
			Especialidade especialidade = catalogoEspecialidades.getEspecialidadeByNome(nome);
			
			if(nome == null)
				throw new ApplicationException("Eh necessario especificar uma especialidade");
			if(!(designacao.matches("^[A-Za-z0-9? ,_-]+$") && designacao.charAt(0) >= 'A' && designacao.charAt(0) <= 'Z' && catalogoAtividades.ehUnica(designacao)))
				throw new ApplicationException("A designacao nao eh valida ou nao eh unica");
			if(isRegular && !AtividadesRegulares.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade regular");
			if(!isRegular && !AtividadesOcasionais.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade ocasional");
			if(duracao < 0 || duracao < especialidade.getDuracao())
				throw new ApplicationException("A duracao nao eh positiva ou eh inferior ao minimo definido para a especialidade");
			if(preco < 0)
				throw new ApplicationException("O preco nao eh positivo");
			if(isRegular && maxParticipantes <= 0)
				throw new ApplicationException("O numero maximo de participantes da atividade regular nao eh positivo");
			

			catalogoAtividades.criarAtividade(especialidade, designacao, isRegular, nrSessoes, duracao, preco, maxParticipantes);
			
		}catch(Exception e) {
			throw new ApplicationException("Erro ao criar atividade", e);
		}
		
	}
	
	
	public AtividadeDTO addAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) throws ApplicationException {
		
		try {
			
			Especialidade especialidade = catalogoEspecialidades.getEspecialidadeByNome(nome);
			
			if(nome == null)
				throw new ApplicationException("Eh necessario especificar uma especialidade");
			if(!(designacao.matches("^[A-Za-z0-9? ,_-]+$") && designacao.charAt(0) >= 'A' && designacao.charAt(0) <= 'Z' && catalogoAtividades.ehUnica(designacao)))
				throw new ApplicationException("A designacao nao eh valida ou nao eh unica");
			if(isRegular && !AtividadesRegulares.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade regular");
			if(!isRegular && !AtividadesOcasionais.nrSessoesValido(nrSessoes))
				throw new ApplicationException("O numero de sessoes nao eh valido para a atividade ocasional");
			if(duracao < 0 || duracao < especialidade.getDuracao())
				throw new ApplicationException("A duracao nao eh positiva ou eh inferior ao minimo definido para a especialidade");
			if(preco < 0)
				throw new ApplicationException("O preco nao eh positivo");
			if(isRegular && maxParticipantes <= 0)
				throw new ApplicationException("O numero maximo de participantes da atividade regular nao eh positivo");
			
			
			Atividade newActivity = catalogoAtividades.criarAtividade(especialidade, designacao, isRegular, nrSessoes, duracao, preco, maxParticipantes);
			
			return new AtividadeDTO(newActivity.getEspecialidade().getNome(), newActivity.getDesignacao(), newActivity.isRegular(), newActivity.getNrSessoes(), newActivity.getDuracao(), newActivity.getPreco(), newActivity.getMaxParticipantes());
			
		}catch(Exception e) {
			throw new ApplicationException("Erro ao criar atividade", e);
		}
		
	}
	
	public List<Instrutor> getInstrutores() throws ApplicationException {
		
		try {
			List<Instrutor> instrutores = catalogoInstrutores.getAllInstructors();
			return instrutores;
		}catch(Exception e){
			throw new ApplicationException("Erro ao obter a lista de especialidades", e);
		}
	}


	

}
