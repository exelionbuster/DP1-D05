
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean res;
		int id = request.getModel().getInteger("id");
		Activity ac = this.repository.findOneById(id);
		InvestmentRound ir = ac.getInvestmentRound();
		Entrepreneur e = ir.getEntrepreneur();

		Principal principal = request.getPrincipal();
		res = e.getUserAccount().getId() == principal.getAccountId() && !ir.isFinalMode();

		return res;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startDate", "endDate", "budget");

		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());

		InvestmentRound ir = entity.getInvestmentRound();

		model.setAttribute("isFinalMode", ir.isFinalMode());

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("startDate") && !errors.hasErrors("endDate")) {
			boolean isBefore;
			isBefore = entity.getStartDate().before(entity.getEndDate());
			errors.state(request, isBefore, "endDate", "entrepreneur.activity.form.error.dates");
		}

		if (!errors.hasErrors("budget")) {
			int id = request.getModel().getInteger("id");
			Activity ac = this.repository.findOneById(id);
			InvestmentRound investmentRound = ac.getInvestmentRound();

			errors.state(request, !investmentRound.isFinalMode(), "budget", "entrepreneur.activity.form.error.isFinalMode");
		}
	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;

		this.repository.save(entity);
	}

}
