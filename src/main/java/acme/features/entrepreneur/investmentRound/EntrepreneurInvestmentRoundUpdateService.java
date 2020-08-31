
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	//	Internal states ------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	private AuthenticatedForumRepository			forumRepository;

	@Autowired
	private EntrepreneurActivityRepository			activityRepository;

	@Autowired
	private AdministratorConfigurationRepository	configurationRepository;


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

			InvestmentRound invR = this.repository.findOneById(entity.getId());
			if (validTicker && !invR.getTicker().equals(entity.getTicker())) {
				errors.state(request, !this.repository.checkUniqueTicker(entity.getTicker()), "ticker", "entrepreneur.investment-round.error.unique-ticker");
			}
		}

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

		if (request.getModel().getBoolean("finalMode") && !errors.hasErrors()) {
			Boolean isSpam = false;
			List<String> spamWords = new ArrayList<String>();
			String content = "";
			Double contentSize;
			Double spamCount = 0.0;
			Double spamThreshold = 0.0;

			spamWords.addAll(Arrays.asList(this.configurationRepository.findSpamWords().split(",")));
			spamWords = spamWords.stream().map(String::trim).map(String::toLowerCase).collect(Collectors.toList());

			content += entity.getTitle().toLowerCase() + entity.getDescription().toLowerCase();
			if (entity.getLink() != null) {
				content += entity.getLink().toLowerCase();
			}

			for (Activity a : activities) {
				content += a.getTitle().toLowerCase();
			}

			for (String spamword : spamWords) {
				Integer loopCount = 0;
				loopCount += content.split(spamword, -1).length - 1;
				spamCount += loopCount * spamword.length();
			}

			contentSize = 1.0 * content.length();

			spamThreshold = this.configurationRepository.findSpamThreshold();

			isSpam = spamCount / contentSize * 100 >= spamThreshold;

			errors.state(request, !isSpam, "finalMode", "entrepreneur.investment-round.form.error.spam");

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

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);

		entity.setCreationDate(creationDate);

		if (entity.isFinalMode()) {
			Forum forum = new Forum();

			String username = request.getPrincipal().getUsername();

			forum.setCreationDate(creationDate);
			forum.setInvestmentRound(entity);
			forum.setOwner(this.forumRepository.findAuthenticatedByUsername(username));
			forum.setInvolvedUsers(new HashSet<Authenticated>());
			forum.setTitle(entity.getTitle() + "'s discussion forum");
			this.forumRepository.save(forum);
		}

		this.repository.save(entity);

	}

}
