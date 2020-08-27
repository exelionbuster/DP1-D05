
package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	// Internal state

	@Autowired
	AuthenticatedForumRepository	repository;

	@Autowired
	AuthenticatedMessageRepository	messageRepository;


	// AbstractDeleteService<Authenticated, Forum> interface

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.getModel().setAttribute("hasMessages", this.repository.hasMessages(entity.getId()));

		request.unbind(entity, model);
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Forum result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getInvestmentRound() != null) {
			errors.state(request, false, "invR", "authenticated.forum.form.error.has-inv-round");
			request.getModel().setAttribute("hasMessages", this.repository.hasMessages(entity.getId()));
		}
	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		this.messageRepository.deleteAll(this.messageRepository.findForumMessages(entity.getId()));

		this.repository.delete(entity);
	}

}
