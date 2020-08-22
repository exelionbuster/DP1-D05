
package acme.features.anonymous.alesBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.AlesBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAlesBulletinListService implements AbstractListService<Anonymous, AlesBulletin> {

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
	public Collection<AlesBulletin> findMany(final Request<AlesBulletin> request) {
		assert request != null;

		Collection<AlesBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<AlesBulletin> request, final AlesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment", "luckyNumber");

	}

}
