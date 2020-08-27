/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.investor.application;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorApplicationRepository			repository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;


	// AbstractCreateService<Administrator, Application> interface --------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean res;
		int id = request.getModel().getInteger("investmentRoundId");
		InvestmentRound ir = this.investmentRoundRepository.findOneById(id);
		res = ir.isFinalMode();

		return res;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "offer");
		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		Date creationDate;
		InvestmentRound investmentRound;
		Principal principal;

		creationDate = new Date(System.currentTimeMillis() - 1);
		investmentRound = this.investmentRoundRepository.findOneById(Integer.parseInt((String) request.getModel().getAttribute("investmentRoundId")));
		principal = request.getPrincipal();
		Investor investor = this.repository.findOneInvestorByUserAccountId(principal.getAccountId());

		result = new Application();
		result.setCreationDate(creationDate);
		result.setStatus("pending");
		result.setInvestmentRound(investmentRound);
		result.setInvestor(investor);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (errors.hasErrors("offer")) {
			errors.state(request, false, "offer", "investor.application.form.error.null-currency");
		} else {
			errors.state(request, entity.getOffer().getCurrency().equals("€"), "offer", "investor.application.form.error.null-currency");
		}

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
				String checkActivitySector = entity.getInvestor().getActivitySector();
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
					errors.state(request, validTicker, "ticker", "investor.application.error.ticker.activity-sector");
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
					errors.state(request, validTicker, "ticker", "investor.application.error.ticker.year");
				}

				Boolean correctDigits = false;
				if (Pattern.matches(regexp1, part3)) {
					correctDigits = true;
				} else {
					errors.state(request, validTicker, "ticker", "investor.application.error.ticker.digits");
				}

				if (correctActivitySector && correctYear && correctDigits) {
					validTicker = true;
				} else {
					errors.state(request, validTicker, "ticker", "investor.application.error.ticker");
				}
			} else {
				errors.state(request, tieneTresPartes, "ticker", "investor.application.error.ticker");
			}

			if (validTicker) {
				errors.state(request, !this.repository.checkUniqueTicker(entity.getTicker()), "ticker", "investor.application.error.unique-ticker");
			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;
		int principalId;

		creationDate = new Date(System.currentTimeMillis() - 1);
		principalId = request.getPrincipal().getAccountId();
		Investor investor = this.repository.findOneInvestorByUserAccountId(principalId);

		entity.setCreationDate(creationDate);
		entity.setInvestor(investor);

		this.repository.save(entity);
	}

}
