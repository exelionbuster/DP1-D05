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

package acme.features.anonymous.alesBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.AlesBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousAlesBulletinCreateService implements AbstractCreateService<Anonymous, AlesBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousAlesBulletinRepository repository;


	// AbstractListService<Administrator, UserAccount> interface --------------

	@Override
	public boolean authorise(final Request<AlesBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public AlesBulletin instantiate(final Request<AlesBulletin> request) {
		assert request != null;

		AlesBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new AlesBulletin();

		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<AlesBulletin> request, final AlesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "luckyNumber");

	}

	@Override
	public void bind(final Request<AlesBulletin> request, final AlesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<AlesBulletin> request, final AlesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<AlesBulletin> request, final AlesBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
