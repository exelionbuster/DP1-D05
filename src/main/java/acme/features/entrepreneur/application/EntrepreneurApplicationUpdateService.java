
package acme.features.entrepreneur.application;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {
	// Internal interface --------------------

	@Autowired
	EntrepreneurApplicationRepository	repository;

	@Autowired
	AuthenticatedForumRepository		forumRepository;


	// AbstractListService<Entrepreneur, Application> interface ------

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean rejected = request.getModel().getAttribute("status").equals("rejected") && request.getModel().getAttribute("justification").toString().isEmpty();

		if (!errors.hasErrors("status")) {
			errors.state(request, !rejected, "justification", "entrepreneur.application.error.justification");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals("accepted")) {
			Forum forum = this.forumRepository.findOneByInvRoundId(entity.getInvestmentRound().getId());
			Authenticated investor = this.forumRepository.findAuthenticatedByUsername(entity.getInvestor().getUserAccount().getUsername());
			Set<Authenticated> users = new HashSet<Authenticated>(forum.getInvolvedUsers());
			users.add(investor);
			forum.setInvolvedUsers(users);
		}

		this.repository.save(entity);
	}
}
