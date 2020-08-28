
package acme.features.authenticated.forum;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedForumUpdateService implements AbstractUpdateService<Authenticated, Forum> {

	// Internal state

	@Autowired
	AuthenticatedForumRepository repository;


	// AbstractUpdateService<Authenticated, Forum> interface

	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Authenticated user = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		Forum forum = this.repository.findOneById(request.getModel().getInteger("id"));

		return this.repository.isInvolved(forum.getId(), user);
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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

		model.setAttribute("hasMessages", this.repository.hasMessages(entity.getId()));

		request.unbind(entity, model, "title");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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
	public void update(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Set<Authenticated> users = new HashSet<Authenticated>();
		String[] usernames = ((String) request.getModel().getAttribute("users")).split(",");
		for (String user : usernames) {
			users.add(this.repository.findAuthenticatedByUsername(user.trim()));
		}
		entity.setInvolvedUsers(users);

		this.repository.save(entity);
	}

}
