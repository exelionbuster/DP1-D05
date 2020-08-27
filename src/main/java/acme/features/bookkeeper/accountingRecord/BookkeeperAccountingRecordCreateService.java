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

package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.features.bookkeeper.investmentRound.BookkeeperInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperAccountingRecordRepository	repository;

	@Autowired
	BookkeeperInvestmentRoundRepository		investmentRoundRepository;


	// AbstractCreateService<Administrator, AccountingRecord> interface --------------

	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate", "status");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body");
		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());

		model.setAttribute("published", false);

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		AccountingRecord result;

		Date creationDate;
		InvestmentRound investmentRound;
		Principal principal;

		creationDate = new Date(System.currentTimeMillis() - 1);
		investmentRound = this.investmentRoundRepository.findOneById(Integer.parseInt((String) request.getModel().getAttribute("investmentRoundId")));
		principal = request.getPrincipal();
		Bookkeeper bookkeeper = this.repository.findOneBookkeeperById(principal.getActiveRoleId());

		result = new AccountingRecord();

		result.setDraft(true);
		result.setCreationDate(creationDate);
		result.setInvestmentRound(investmentRound);
		result.setBookkeeper(bookkeeper);

		return result;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;
		int principalId;

		creationDate = new Date(System.currentTimeMillis() - 1);
		principalId = request.getPrincipal().getActiveRoleId();
		Bookkeeper bookkeeper = this.repository.findOneBookkeeperById(principalId);

		if (request.getModel().getBoolean("published")) {
			entity.setDraft(false);
		}

		entity.setCreationDate(creationDate);
		entity.setBookkeeper(bookkeeper);

		this.repository.save(entity);
	}

}
