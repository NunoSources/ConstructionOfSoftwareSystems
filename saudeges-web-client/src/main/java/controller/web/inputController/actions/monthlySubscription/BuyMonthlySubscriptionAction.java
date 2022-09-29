package controller.web.inputController.actions.monthlySubscription;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.web.inputController.actions.Action;
import facade.services.IServicoVendaRemote;
import presentation.web.model.MonthlySubModel;


/**
 * Handles the novo atividade event
 *
 */
@Stateless
public class BuyMonthlySubscriptionAction extends Action {
	
	@EJB 
	private IServicoVendaRemote salesService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		MonthlySubModel model = new MonthlySubModel();
		model.setActivityService(salesService);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/buyMonthlySubscription/buyMonthlySelectActivity.jsp").forward(request, response);
	}

}
