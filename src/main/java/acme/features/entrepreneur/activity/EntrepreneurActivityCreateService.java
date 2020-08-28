
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository			repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean res;
		int id = request.getModel().getInteger("investmentRoundId");
		InvestmentRound ir = this.investmentRoundRepository.findOneById(id);
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

		model.setAttribute("isFinalMode", false);
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;

		Activity res;
		res = new Activity();

		int id = request.getModel().getInteger("investmentRoundId");
		InvestmentRound ir = this.investmentRoundRepository.findOneById(id);

		res.setInvestmentRound(ir);
		return res;
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
	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
