package controller.web.inputController.actions.activity;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;
import presentation.web.model.NewActivityModel;

@Stateless
public class CreateActivityAction extends Action {

	@EJB
	private IServicoAtividadeRemote activityService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewActivityModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				System.out.println(model.getEspecialidade());
				//addAtividade(Especialidade nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes)
				activityService.addAtividade(model.getEspecialidade(), model.getDesignacao(), booleanValue(model.getIsRegular()), intValue(model.getNrSessoes()), intValue(model.getDuracao()), floatValue(model.getPreco()), intValue(model.getMaxParticipantes()));
						
				model.clearFields();
				model.addMessage("Atividade adicionada com sucesso.");
			} 
			catch (ApplicationException e) {
				model.addMessage("Erro ao adicionar cliente. " + e.getCause().getMessage());
			}
		} 
		else {
			model.addMessage("Erro de validação dos dados do cliente.");
		}

		request.getRequestDispatcher("/addActivity/newActivity.jsp").forward(request, response);
	}


	private boolean validInput(NewActivityModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getDesignacao(), "A designação tem de estar preenchida.");
		
		result &= isFilled(model, model.getEspecialidade(), "A especialidade tem de estar preenchida.");
		
		result &= isFilled(model, model.getNrSessoes(), "O numero de sessoes tem de estar preenchido.") && isInt(model, model.getNrSessoes(), "O numero de sessoes contem caracteres inválidos.");
		
		result &= isFilled(model, model.getIsRegular(), "O campo eh regular tem de estar preenchido.") && isBoolean(model, model.getIsRegular(), "O campo eh regular nao esta corretamente preenchido.");

		if (!model.getMaxParticipantes().equals(""))
			result &= isInt(model, model.getMaxParticipantes(), "O numero maximo de participantes nao esta corretamente preenchido.");

		result &= isFilled(model, model.getPreco(), "O preco tem de estar preenchido.") && isFloat(model, model.getPreco(), "O preco contem caracteres inválidos.");

		
		return result;
	}


	private NewActivityModel createHelper(HttpServletRequest request) {
		// Create the object model
		NewActivityModel model = new NewActivityModel();
		model.setActivityService(activityService);
		
		model.setDesignacao(request.getParameter("designacao"));
		model.setDuracao(request.getParameter("duracao"));
		model.setEspecialidade(request.getParameter("especialidade"));
		model.setIsRegular(request.getParameter("regular"));
		model.setMaxParticipantes(request.getParameter("maxparticipantes"));
		model.setNrSessoes(request.getParameter("nrsessoes"));
		model.setPreco(request.getParameter("preco"));

		return model;
	}	
}
