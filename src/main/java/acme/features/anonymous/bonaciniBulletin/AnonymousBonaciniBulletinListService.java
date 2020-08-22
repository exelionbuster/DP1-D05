
package acme.features.anonymous.bonaciniBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BonaciniBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousBonaciniBulletinListService implements AbstractListService<Anonymous, BonaciniBulletin> {

	// Internal state

	@Autowired
	AnonymousBonaciniBulletinRepository repository;


	// AbstractListService interface

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

		request.unbind(entity, model, "moment", "author", "text", "city");

	}

	@Override
	public Collection<BonaciniBulletin> findMany(final Request<BonaciniBulletin> request) {
		assert request != null;

		Collection<BonaciniBulletin> result;
		result = this.repository.findMany();

		return result;
	}

}
