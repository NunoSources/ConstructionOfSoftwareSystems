package controller.web.inputController.actions.ocasionalActivity;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.services.IServicoVendaRemote;
import presentation.web.model.BookOccasionalModel;


/**
 * Handles the novo atividade event
 *
 */
@Stateless
public class NewBookOcasionalActivity extends Action {
	
	@EJB 
	private IServicoVendaRemote salesService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		BookOccasionalModel model = new BookOccasionalModel();
		model.setActivityService(salesService);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/bookOcasionalActivity/bookOccasional.jsp").forward(request, response);
	}

}
