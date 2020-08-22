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

package acme.features.authenticated.forums;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;


	// AbstractListService<Authenticated, Inquiry> interface --------------

	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		int forumId;
		Authenticated user;

		user = this.repository.findUser(request.getPrincipal().getActiveRoleId());

		forumId = request.getModel().getInteger("id");
		Collection<Authenticated> users = this.repository.findOneById(forumId).getInvolvedUsers();

		return users.contains(user);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String forumOwner = entity.getOwner().getUserAccount().getUsername();
		String users = "";

		Set<Authenticated> involvedUsers = new HashSet<Authenticated>(entity.getInvolvedUsers());

		for (Authenticated a : involvedUsers) {
			if (!users.isEmpty()) {
				users += ", ";
			}
			users += a.getUserAccount().getUsername();
		}

		if (entity.getInvestmentRound() == null) {
			model.setAttribute("invR", null);
		} else {
			model.setAttribute("invR", entity.getInvestmentRound().getTicker());
		}

		model.setAttribute("forumOwner", forumOwner);
		model.setAttribute("users", users);

		request.unbind(entity, model, "title", "creationDate");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
