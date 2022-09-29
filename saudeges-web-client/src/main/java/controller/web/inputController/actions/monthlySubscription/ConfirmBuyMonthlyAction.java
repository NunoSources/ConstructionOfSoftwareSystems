package controller.web.inputController.actions.monthlySubscription;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.dtos.DadosPagamentoDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;
import presentation.web.model.MonthlySubModel;

@Stateless
public class ConfirmBuyMonthlyAction extends Action {

	@EJB
	private IServicoVendaRemote salesService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		MonthlySubModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				DadosPagamentoDTO pagamento = salesService.escolheHorarioParticipacaoMensal(model.getHorarioId(), model.getStartDate(), intValue(model.getDuracao()), model.getEmail(), model.getDesignacao());
				
				model.clearFields();
				model.addMessage("Participação mensal comprada com sucesso.");
				model.addMessage(pagamento.toHTML());
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
		boolean result = isFilled(model, model.getDesignacao(), "A designacao tem de estar preenchida.");
		
		result &= isFilled(model, model.getStartDate(), "A data de inicio tem de estar preenchida.") && isDate(model, model.getStartDate(), "O data de inicio nao esta bem preenchida");
		
		result &= isFilled(model, model.getEmail(), "O email precisa de estar preenchido.") && isEmail(model, model.getEmail(), "O email nao esta bem preenchido.");
		
		result &= isFilled(model, String.valueOf(model.getHorarioId()), "O horario precisa de estar selecionado");
		
		result &= isFilled(model, model.getDuracao(), "A duracao precisa de estar preenchida");
		
		return result;
	}


	private MonthlySubModel createHelper(HttpServletRequest request) {
		// Create the object model
		MonthlySubModel model = new MonthlySubModel();
		model.setActivityService(salesService);
	
		model.setDesignacao(request.getParameter("designacao"));
		model.setPreco(request.getParameter("preco"));
		model.setStartDate(request.getParameter("startDate"));
		model.setEmail(request.getParameter("email"));
		model.setHorarioId(intValue(request.getParameter("horario")));
		model.setDuracao(request.getParameter("duracao"));

		return model;
	}	
}
