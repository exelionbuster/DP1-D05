
package acme.features.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kind", "title", "description", "amount", "link");

		Set<String> kinds = new HashSet<String>(Arrays.asList(this.repository.findInvRoundKinds().split(";")));
		kinds = kinds.stream().map(String::trim).collect(Collectors.toSet());

		model.setAttribute("kinds", kinds);

		model.setAttribute("isFinalMode", false);
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		Principal principal;
		int userAccountId;
		Entrepreneur entrepreneur;

		principal = request.getPrincipal();
		userAccountId = principal.getActiveRoleId();
		entrepreneur = this.repository.findEntrepreneurById(userAccountId);

		result = new InvestmentRound();
		result.setCreationDate(creationDate);
		result.setEntrepreneur(entrepreneur);

		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			Boolean validTicker = false;
			//validación
			String ticker = entity.getTicker();
			String[] parts = ticker.split("-");
			Boolean tieneTresPartes = false;
			if (parts.length == 3) {
				tieneTresPartes = true;
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String regexp1 = "\\d{6}";

				Boolean correctActivitySector = false;
				String checkActivitySector = entity.getEntrepreneur().getActivitySector();
				if (checkActivitySector.length() == 1) {
					checkActivitySector = checkActivitySector + "XX";
				} else if (checkActivitySector.length() == 2) {
					checkActivitySector = checkActivitySector + "X";
				} else {
					checkActivitySector = checkActivitySector.substring(0, 3);
				}

				if (part1.equals(checkActivitySector.toUpperCase())) {
					correctActivitySector = true;
				} else {
					errors.state(request, validTicker, "ticker", "entrepreneur.investment-round.error.ticker.activity-sector");
				}

				Boolean correctYear = false;
				int year = entity.getCreationDate().getYear();
				String checkYear = "" + year + "";

				if (checkYear.length() >= 3) {
					checkYear = checkYear.substring(checkYear.length() - 2, checkYear.length());
				}

				if (part2.equals(checkYear)) {
					correctYear = true;
				} else {
					errors.state(request, validTicker, "ticker", "entrepreneur.investment-round.error.ticker.year");
				}

				Boolean correctDigits = false;
				if (Pattern.matches(regexp1, part3)) {
					correctDigits = true;
				} else {
					errors.state(request, validTicker, "ticker", "entrepreneur.investment-round.error.ticker.digits");
				}

				if (correctActivitySector && correctYear && correctDigits) {
					validTicker = true;
				} else {
					errors.state(request, validTicker, "ticker", "entrepreneur.investment-round.error.ticker");
				}
			} else {
				errors.state(request, tieneTresPartes, "ticker", "entrepreneur.investment-round.error.ticker");
			}

			if (validTicker) {
				errors.state(request, !this.repository.checkUniqueTicker(entity.getTicker()), "ticker", "entrepreneur.investment-round.error.unique-ticker");
			}
		}

		Set<String> kinds = new HashSet<String>(Arrays.asList(this.repository.findInvRoundKinds().split(";")));
		kinds = kinds.stream().map(String::trim).collect(Collectors.toSet());
		if (entity.getKind() != null) {
			kinds.remove(entity.getKind());
		}

		request.getModel().setAttribute("kinds", kinds);

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);

		entity.setCreationDate(creationDate);

		this.repository.save(entity);

	}

}
