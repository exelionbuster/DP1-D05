/*
 * AuthenticatedUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.forum;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;


	// AbstractCreateService<Authenticated, Forum> interface --------------

	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "involvedUsers", "owner", "investmentRound");
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Authenticated owner = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		request.getModel().setAttribute("forumOwner", owner.getUserAccount().getUsername());

		Set<Authenticated> users = new HashSet<Authenticated>();
		users.add(owner);
		entity.setInvolvedUsers(users);

		request.getModel().setAttribute("users", "");

		request.getModel().setAttribute("notOwned", false);
		request.getModel().setAttribute("invR", null);

		request.unbind(entity, model, "title", "creationDate");

	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		Forum result;

		result = new Forum();

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.getModel().getAttribute("users") != "") {
			Boolean hasWrongUsers = false;
			String wrongUsers = "";
			String[] usernames = ((String) request.getModel().getAttribute("users")).split(",");
			for (String user : usernames) {
				user = user.trim();
				if (!user.equals("")) {
					if (!this.repository.usernameExists(user) || user.equals(request.getPrincipal().getUsername())) {
						if (!wrongUsers.isEmpty()) {
							wrongUsers += ", ";
						} else {
							hasWrongUsers = true;
						}
						wrongUsers += user;
					}
				}
			}
			if (hasWrongUsers) {
				errors.state(request, false, "users", "authenticated.forum.form.error.wrong-username");
				errors.state(request, false, "users", " " + wrongUsers);
			}
		}
	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Authenticated owner = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		entity.setOwner(owner);

		Set<Authenticated> users = new HashSet<Authenticated>();
		String[] usernames = ((String) request.getModel().getAttribute("users")).split(",");
		for (String user : usernames) {
			users.add(this.repository.findAuthenticatedByUsername(user.trim()));
		}
		entity.setInvolvedUsers(users);

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);
	}

}
