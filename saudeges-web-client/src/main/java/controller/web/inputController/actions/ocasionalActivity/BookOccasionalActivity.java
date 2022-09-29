package controller.web.inputController.actions.ocasionalActivity;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.dtos.AtividadeDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;
import presentation.web.model.BookOccasionalModel;

@Stateless
public class BookOccasionalActivity extends Action {

	@EJB
	private IServicoVendaRemote vendaService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		BookOccasionalModel model = createHelper(request);
		request.setAttribute("model", model);

		if (validInput(model)) {
			try {
				
				List<AtividadeDTO> atividades = vendaService.agendaAtividadeOcasional(model.getEspecialidade());
				
				
				model.setAtividades(atividades);
				
				
//				System.out.println(model.getEspecialidade());
				//addAtividade(Especialidade nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes)
//				activityService.addAtividade(model.getEspecialidade(), model.getDesignacao(), booleanValue(model.getIsRegular()), intValue(model.getNrSessoes()), intValue(model.getDuracao()), floatValue(model.getPreco()), intValue(model.getMaxParticipantes()));
						
				//model.clearFields();
				//model.addMessage(".");
			} 
			catch (ApplicationException e) {
				model.addMessage("Erro ao adicionar cliente. " + e.getCause().getMessage());
			}
		} 
		else {
			model.addMessage("Erro de validação dos dados do cliente.");
		}

		request.getRequestDispatcher("/bookOcasionalActivity/bookOccasionalSelectActivity.jsp").forward(request, response);
	}


	private boolean validInput(BookOccasionalModel model) {

		// check if designation is filled
		boolean result = isFilled(model, model.getEspecialidade(), "A especialidade tem de estar preenchida.");
		
		return result;
	}


	private BookOccasionalModel createHelper(HttpServletRequest request) {
		// Create the object model
		BookOccasionalModel model = new BookOccasionalModel();
		model.setActivityService(vendaService);
		
		model.setEspecialidade(request.getParameter("especialidade"));

		return model;
	}	
}
