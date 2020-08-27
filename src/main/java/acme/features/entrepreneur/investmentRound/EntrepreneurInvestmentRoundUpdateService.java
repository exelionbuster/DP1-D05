
package acme.features.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	//	Internal states ------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurActivityRepository					activityRepository;


	// AbstractUpdateService<Entrepreneur, InvestmentRound> interface -----

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		boolean finalMode;
		finalMode = investmentRound.isFinalMode();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && !finalMode;

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());

		if (!activities.isEmpty()) {
			model.setAttribute("activities", activities);
		} else {
			model.setAttribute("activities", null);
		}

		request.unbind(entity, model, "ticker", "creationDate", "kind", "title", "description", "amount", "link", "finalMode");

		InvestmentRound investmentRound = this.repository.findOneById(entity.getId());

		model.setAttribute("isFinalMode", investmentRound.isFinalMode());

		Set<String> kinds = new HashSet<String>(Arrays.asList(this.repository.findInvRoundKinds().split(";")));
		kinds = kinds.stream().map(String::trim).collect(Collectors.toSet());
		kinds.remove(entity.getKind());

		model.setAttribute("kinds", kinds);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");
		result = this.repository.findOneById(investmentRoundId);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean validAmount = false;
		boolean haveActivities = false;
		boolean res = request.getModel().getBoolean("finalMode");

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());

		if (!activities.isEmpty()) {
			request.getModel().setAttribute("activities", activities);
		} else {
			request.getModel().setAttribute("activities", null);
		}

		if (request.getModel().getAttribute("activities") != null && entity.isFinalMode()) {
			haveActivities = true;

			if (this.repository.checkAmount(entity.getId())) {
				validAmount = true;
			} else {
				errors.state(request, validAmount, "finalMode", "entrepreneur.investment-round.error.amount");
			}

		} else if (res) {
			errors.state(request, haveActivities, "finalMode", "entrepreneur.investment-round.error.activities");
		}

		if (errors.hasErrors()) {
			InvestmentRound investmentRound = this.repository.findOneById(entity.getId());
			request.getModel().setAttribute("isFinalMode", investmentRound.isFinalMode());

			Set<String> kinds = new HashSet<String>(Arrays.asList(this.repository.findInvRoundKinds().split(";")));
			kinds = kinds.stream().map(String::trim).collect(Collectors.toSet());
			if (entity.getKind() != null) {
				kinds.remove(entity.getKind());
			}

			request.getModel().setAttribute("kinds", kinds);
		}

		// Detectar que las cadenas no son spam

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);

		entity.setCreationDate(creationDate);

		this.repository.save(entity);

	}

}
