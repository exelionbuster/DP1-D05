
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

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
public class EntrepreneurInvestmentRoundPublishService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	//	Internal states ------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


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

		request.unbind(entity, model, "finalMode");

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

		// InvestmentRecord no se puede salvar si a menos que las activities budgets wirkprogramme sum up to the total amount of money
		if (request.getModel().getBoolean("finalMode") == true) {
			if (!errors.hasErrors("finalMode")) {
				boolean b = false;
				Double acum = 0.0;
				Collection<Activity> activities = this.repository.findActivityByInvestmentRound(entity.getId());
				for (Activity a : activities) {
					acum += a.getBudget().getAmount();
				}
				b = acum.equals(entity.getAmount().getAmount());
				errors.state(request, b, "finalMode", "entrepreneur.investmentRecord.error.amount");
			}
		}

		// Detectar que las cadenas no son spam

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		entity.setFinalMode(true);
		this.repository.save(entity);

	}

}
