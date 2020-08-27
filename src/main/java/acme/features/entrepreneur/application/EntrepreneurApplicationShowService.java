
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurApplicationShowService implements AbstractShowService<Entrepreneur, Application> {
	// Internal interface --------------------

	@Autowired
	EntrepreneurApplicationRepository repository;


	// AbstractListService<Entrepreneur, Application> interface ------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean res;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findApplicationById(applicationId);
		entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		res = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return res;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "statement", "status", "justification", "offer", "investor.userAccount.username");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		Integer id;

		id = request.getModel().getInteger("id");
		result = this.repository.findApplicationById(id);

		return result;
	}
}
