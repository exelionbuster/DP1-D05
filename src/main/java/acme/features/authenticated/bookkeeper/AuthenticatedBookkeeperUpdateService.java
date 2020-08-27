
package acme.features.authenticated.bookkeeper;

import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedBookkeeperUpdateService implements AbstractUpdateService<Authenticated, Bookkeeper> {

	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bind(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bookkeeper findOne(final Request<Bookkeeper> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final Request<Bookkeeper> request, final Bookkeeper entity) {
		// TODO Auto-generated method stub

	}

}
