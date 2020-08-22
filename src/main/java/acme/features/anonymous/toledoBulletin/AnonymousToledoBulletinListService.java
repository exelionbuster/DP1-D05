
package acme.features.anonymous.toledoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.ToledoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousToledoBulletinListService implements AbstractListService<Anonymous, ToledoBulletin> {

	@Autowired
	AnonymousToledoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ToledoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ToledoBulletin> request, final ToledoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment", "email");

	}

	@Override
	public Collection<ToledoBulletin> findMany(final Request<ToledoBulletin> request) {
		assert request != null;

		Collection<ToledoBulletin> res;

		res = this.repository.findMany();

		return res;
	}

}
