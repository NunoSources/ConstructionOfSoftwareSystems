package controller.web.inputController.actions.monthlySubscription;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.dtos.HorarioPrecoDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;
import presentation.web.model.MonthlySubModel;

@Stateless
public class BuyMonthlyChooseActivityAction extends Action {

	@EJB
	private IServicoVendaRemote vendaService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		MonthlySubModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				HorarioPrecoDTO horarioPreco = vendaService.indicaAtividadeParticipacaoMensal(model.getDesignacao());
				
				
				model.setPreco(String.valueOf(horarioPreco.getPreco()));
				model.setHorario(horarioPreco.getHorario());
				
				
//				System.out.println(model.getEspecialidade());
				//addAtividade(Especialidade nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes)
//				activityService.addAtividade(model.getEspecialidade(), model.getDesignacao(), booleanValue(model.getIsRegular()), intValue(model.getNrSessoes()), intValue(model.getDuracao()), floatValue(model.getPreco()), intValue(model.getMaxParticipantes()));
						
				//model.clearFields();
				model.addMessage("Atividade adicionada com sucesso.");
			} 
			catch (ApplicationException e) {
				model.addMessage("Erro ao adicionar cliente. " + e.getCause().getMessage());
			}
		} 
		else {
			model.addMessage("Erro de validação dos dados do cliente.");
		}

		request.getRequestDispatcher("/buyMonthlySubscription/buyMonthly.jsp").forward(request, response);
	}


	private boolean validInput(MonthlySubModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getDesignacao(), "A designação tem de estar preenchida.");

		
		return result;
	}


	private MonthlySubModel createHelper(HttpServletRequest request) {
		// Create the object model
		MonthlySubModel model = new MonthlySubModel();
		model.setActivityService(vendaService);
		
		model.setDesignacao(request.getParameter("designacao"));

		return model;
	}	
}
