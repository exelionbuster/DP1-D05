
package acme.features.anonymous.toledoBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.ToledoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousToledoBulletinCreateService implements AbstractCreateService<Anonymous, ToledoBulletin> {

	@Autowired
	private AnonymousToledoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ToledoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ToledoBulletin> request, final ToledoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<ToledoBulletin> request, final ToledoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "email");

	}

	@Override
	public ToledoBulletin instantiate(final Request<ToledoBulletin> request) {
		assert request != null;

		ToledoBulletin res;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		res = new ToledoBulletin();
		res.setMoment(moment);

		return res;
	}

	@Override
	public void validate(final Request<ToledoBulletin> request, final ToledoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<ToledoBulletin> request, final ToledoBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
