package controller.web.inputController.actions.activity;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.services.IServicoAtividadeRemote;
import presentation.web.model.NewActivityModel;


/**
 * Handles the novo atividade event
 *
 */
@Stateless
public class NewActivityAction extends Action {
	
	@EJB 
	private IServicoAtividadeRemote activityService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewActivityModel model = new NewActivityModel();
		model.setActivityService(activityService);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/addActivity/newActivity.jsp").forward(request, response);
	}

}
