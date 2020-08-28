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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

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
		Forum forum = this.repository.findMessageForum(request.getModel().getInteger("forumId"));

		return this.forumRepository.isInvolved(forum.getId(), user);
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		Integer forumId = Integer.parseInt((String) request.getModel().getAttribute("forumId"));

		result = this.repository.findForumMessages(forumId);

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String msgSender = entity.getSender().getUserAccount().getUsername();

		model.setAttribute("msgSender", msgSender);

		request.unbind(entity, model, "title", "creationDate");
	}

}
