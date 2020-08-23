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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorApplicationRepository repository;


	// AbstractCreateService<Administrator, Application> interface --------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "ticker", "statement", "status", "offer");
		model.setAttribute("id", entity.getInvestmentRound().getId());

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;
		Investor investor;
		Principal principal;

		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(request.getModel().getInteger("investmentRoundId"));
		principal = request.getPrincipal();
		int principalId = principal.getAccountId();

		investor = this.repository.findOneInvestorByUserAccountId(principalId);

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new Application();
		result.setCreationDate(moment);
		result.setInvestor(investor);
		result.setInvestmentRound(investmentRound);

		String status = "pending";
		result.setStatus(status);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//TODO: para que sirve esto?
		//		errors.state(request, !this.repository.checkUniqueReference(entity.getReferenceNumber()), "referenceNumber", "worker.application.error.unique-reference");

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}

}
