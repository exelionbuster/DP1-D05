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

package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository	repository;

	@Autowired
	AuthenticatedForumRepository	forumRepository;


	// AbstractListService<Authenticated, Message> interface --------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Authenticated user = this.repository.findUser(request.getPrincipal().getActiveRoleId());

		Message msg = this.repository.findOneById(request.getModel().getInteger("id"));

		return this.forumRepository.isInvolved(msg.getForum().getId(), user);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Boolean owned = false;
		String msgSender = entity.getSender().getUserAccount().getUsername();

		if (entity.getTags().isEmpty()) {
			model.setAttribute("msgTags", null);
		} else {
			model.setAttribute("msgTags", entity.getTags());
		}

		if (entity.getSender().equals(this.repository.findUser(request.getPrincipal().getActiveRoleId()))) {
			owned = true;
		}

		model.setAttribute("msgSender", msgSender);
		model.setAttribute("owned", owned);

		request.unbind(entity, model, "title", "creationDate", "body");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
