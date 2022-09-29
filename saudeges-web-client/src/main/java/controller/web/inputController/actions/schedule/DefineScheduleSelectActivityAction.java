package controller.web.inputController.actions.schedule;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;
import presentation.web.model.DefineScheduleModel;

@Stateless
public class DefineScheduleSelectActivityAction extends Action {

	@EJB
	private IServicoAtividadeRemote activityService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		DefineScheduleModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			
				
				try {
					
					activityService.enviaInformacaoDefinirHorario(model.getDesignacao(), intValue(model.getNumeroInstrutor()), intValue(model.getDuracao()), model.getSessoes(), model.getStartDate());
				
					model.clearFields();
					model.addMessage("Horario definido com sucesso.");
				
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					model.addMessage("Erro ao definir horario. " + e.getCause().getMessage());
				}
		} 
		else {
			model.addMessage("Erro de validação dos dados do cliente.");
		}

		request.getRequestDispatcher("/defineSchedule/defineScheduleSelectActivity.jsp").forward(request, response);
	}


	private boolean validInput(DefineScheduleModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getDesignacao(), "A designação tem de estar preenchida.");
		
		//verificarCampos
		result &= isFilled(model, model.getStartDate(), "A data de inicio tem de estar preenchida.") && 
				isDate(model, model.getStartDate(), "A data de inicio não é válida.");
		
		result &= isFilled(model, model.getDuracao(), "A duração tem de estar preenchida.") && 
				isInt(model, model.getDuracao(), "A duração não é válida.");
		
		result &= isFilled(model, model.getNumeroInstrutor(), "O instrutor tem de estar selecionado.") && 
				isInt(model, model.getNumeroInstrutor(), "O instrutor não é válido.");
		
		result &= isFilledArrays(model, model.getSessoes(), "As sessões tem que estar preenchidas.");
		
		return result;
	}


	private DefineScheduleModel createHelper(HttpServletRequest request) {
		// Create the object model
		DefineScheduleModel model = new DefineScheduleModel();
		model.setActivityService(activityService);
		
		model.setDesignacao(request.getParameter("designacao"));
		model.setDuracao(request.getParameter("duracao"));
		model.setNumeroInstrutor(request.getParameter("instrutor"));
		model.setSessoes(request.getParameterValues("sessoesInseridas[]"));
		model.setStartDate(request.getParameter("startDate"));
		
		
		
		

		return model;
	}	
}
