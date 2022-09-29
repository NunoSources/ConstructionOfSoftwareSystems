package controller.web.inputController.actions.schedule;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.services.IServicoAtividadeRemote;
import presentation.web.model.DefineScheduleModel;


/**
 * Handles the novo atividade event
 *
 */
@Stateless
public class NewDefineScheduleAction extends Action {
	
	@EJB 
	private IServicoAtividadeRemote activityService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		DefineScheduleModel model = new DefineScheduleModel();
		model.setActivityService(activityService);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/defineSchedule/defineScheduleSelectActivity.jsp").forward(request, response);
	}

}
