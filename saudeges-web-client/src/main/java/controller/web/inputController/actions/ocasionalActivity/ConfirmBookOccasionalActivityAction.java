package controller.web.inputController.actions.ocasionalActivity;

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
import presentation.web.model.BookOccasionalModel;

@Stateless
public class ConfirmBookOccasionalActivityAction extends Action {

	@EJB
	private IServicoVendaRemote salesService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		BookOccasionalModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				DadosPagamentoDTO pagamento = salesService.indicaDadosAtividadeOcasional(model.getDesignacao(), model.getSessoes(), model.getInstrutorId(), model.getEmail());
				
				model.clearFields();
				model.addMessage("Atividade Ocasional agendada com sucesso.");
				model.addMessage(pagamento.toHTML());
			} 
			catch (ApplicationException e) {
				model.addMessage("Erro ao adicionar cliente. " + e.getCause().getMessage());
			}
		} 
		else {
			model.addMessage("Erro de validação dos dados do cliente.");
		}

		request.getRequestDispatcher("/bookOcasionalActivity/bookOccasionalSelectInstructor.jsp").forward(request, response);
	}


	private boolean validInput(BookOccasionalModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getDesignacao(), "A designacao tem de estar preenchida.");
//		
		result &= isFilledArrays(model, model.getSessoes(), "As sessões tem de estar preenchida.");
//		
		result &= isFilled(model, model.getEspecialidade(), "A especialidade tem de estar preenchida.");
		
		result &= isFilled(model, String.valueOf(model.getInstrutorId()), "Instrutor tem de estar selecionado");
		
		result &= isFilled(model, model.getEmail(), "Email tem de ser preenchido.") && isEmail(model, model.getEmail(), "Email incorreto.");
//		
		return result;
	}


	private BookOccasionalModel createHelper(HttpServletRequest request) {
		// Create the object model
		BookOccasionalModel model = new BookOccasionalModel();
		model.setActivityService(salesService);
	
		model.setDesignacao(request.getParameter("designacao"));
		model.setSessoes(request.getParameterValues("sessoesInseridas[]"));
		model.setEspecialidade(request.getParameter("especialidade"));
		model.setInstrutorId(intValue(request.getParameter("instrutor")));
		model.setEmail(request.getParameter("email"));

		return model;
	}	
}
