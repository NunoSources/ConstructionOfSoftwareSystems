package controller.web.inputController.actions.ocasionalActivity;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;
import presentation.web.model.BookOccasionalModel;

@Stateless
public class BookOccasionalActivitySelectInstructorAction extends Action {

	@EJB
	private IServicoVendaRemote salesService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		BookOccasionalModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				List<InstrutorDTO> instrutores = salesService.enviaInformacaoAtividadeOcasional(model.getDesignacao(), model.getSessoes());
				
				model.setInstrutores(instrutores);
				//model.clearFields();
				//model.addMessage("Participação mensada comprada com sucesso.");
				//model.addMessage(pagamento.toHTML());
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

		return model;
	}	
}
