
package acme.features.anonymous.bonaciniBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BonaciniBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBonaciniBulletinCreateService implements AbstractCreateService<Anonymous, BonaciniBulletin> {

	// Internal state

	@Autowired
	private AnonymousBonaciniBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BonaciniBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<BonaciniBulletin> request, final BonaciniBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "city", "text");

	}

	@Override
	public void bind(final Request<BonaciniBulletin> request, final BonaciniBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public BonaciniBulletin instantiate(final Request<BonaciniBulletin> request) {
		assert request != null;

		BonaciniBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result = new BonaciniBulletin();
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<BonaciniBulletin> request, final BonaciniBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<BonaciniBulletin> request, final BonaciniBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
