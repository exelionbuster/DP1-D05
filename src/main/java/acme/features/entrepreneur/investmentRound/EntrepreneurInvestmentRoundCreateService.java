
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	//	Internal states ------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "ticker", "creationDate", "kind", "title", "description", "amount", "link", "finalMode");

	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Principal principal;
		int userAccountId;
		Entrepreneur entrepreneur;

		principal = request.getPrincipal();
		userAccountId = principal.getActiveRoleId();
		entrepreneur = this.repository.findEntrepreneurById(userAccountId);

		result = new InvestmentRound();
		result.setEntrepreneur(entrepreneur);

		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		// TODO Auto-generated method stub

	}

}
