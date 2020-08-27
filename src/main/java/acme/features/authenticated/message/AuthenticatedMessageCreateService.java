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

package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository	repository;

	@Autowired
	AuthenticatedForumRepository	forumRepository;


	// AbstractCreateService<Authenticated, Message> interface --------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Authenticated user = this.repository.findUser(request.getPrincipal().getActiveRoleId());

		Forum forum = this.forumRepository.findOneById(Integer.parseInt((String) request.getModel().getAttribute("forumId")));

		return this.forumRepository.isInvolved(forum, user);
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate", "forum", "sender");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

		model.setAttribute("forumId", entity.getForum().getId());

		request.unbind(entity, model, "title", "creationDate", "body");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;
		Authenticated sender;
		Forum forum;
		Date creationDate;

		result = new Message();

		sender = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		forum = this.forumRepository.findOneById(request.getModel().getInteger("forumId"));
		creationDate = new Date(System.currentTimeMillis() - 1);

		result.setTags("");
		result.setSender(sender);
		result.setForum(forum);
		result.setCreationDate(creationDate);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, request.getModel().getBoolean("accept"), "accept", "authenticated.message.form.error.must-accept");
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Authenticated owner = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		entity.setSender(owner);

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);
	}

}
