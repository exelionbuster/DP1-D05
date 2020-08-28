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

package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.features.authenticated.accountingRecord.AuthenticatedAccountingRecordRepository;
import acme.features.authenticated.activity.AuthenticatedActivityRepository;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInvestmentRoundRepository	repository;

	@Autowired
	AuthenticatedActivityRepository			activityRepository;

	@Autowired
	AuthenticatedAccountingRecordRepository	accountingRecordRepository;

	@Autowired
	AuthenticatedForumRepository			forumRepository;


	// AbstractListService<Authenticated, Inquiry> interface --------------

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());
		Collection<AccountingRecord> accountingRecords = this.accountingRecordRepository.findAllByInvestmentRoundId(entity.getId());

		if (!activities.isEmpty()) {
			model.setAttribute("activities", activities);
		} else {
			model.setAttribute("activities", null);
		}

		if (!accountingRecords.isEmpty()) {
			model.setAttribute("accountingRecords", accountingRecords);
		} else {
			model.setAttribute("accountingRecords", null);
		}

		Boolean isInvestor = false;

		if (this.repository.isInvestor(request.getPrincipal().getAccountId())) {
			isInvestor = true;
		}
		model.setAttribute("isInvestor", isInvestor);

		Forum forum = this.forumRepository.findOneByInvRoundId(entity.getId());
		if (forum != null) {
			Authenticated user = this.forumRepository.findUser(request.getPrincipal().getActiveRoleId());
			Boolean isInvolved = this.forumRepository.isInvolved(forum.getId(), user);

			model.setAttribute("forumId", forum.getId());
			model.setAttribute("isInvolved", isInvolved);
		}

		request.unbind(entity, model, "ticker", "creationDate", "kind", "title", "description", "amount", "link");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
