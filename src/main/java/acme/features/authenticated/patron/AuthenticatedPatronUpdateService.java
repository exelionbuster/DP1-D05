
package acme.features.authenticated.patron;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedPatronUpdateService implements AbstractUpdateService<Authenticated, Patron> {

	@Autowired
	private AuthenticatedPatronRepository repository;


	@Override
	public boolean authorise(final Request<Patron> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Patron> request, final Patron entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
		sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());
		sectors.remove(entity.getActivitySector());

		model.setAttribute("sectors", sectors);

		if (entity.getCreditCard() != null) {
			model.setAttribute("hasCreditCard", true);
			model.setAttribute("creditCardId", entity.getCreditCard().getId());
		} else {
			model.setAttribute("hasCreditCard", false);
		}

		request.unbind(entity, model, "organisation", "activitySector", "profile");
	}

	@Override
	public Patron findOne(final Request<Patron> request) {
		assert request != null;

		Patron res;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		res = this.repository.findOnePatronByUserAccountId(userAccountId);

		return res;
	}

	@Override
	public void validate(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (errors.hasErrors()) {

			Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
			sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());
			if (entity.getActivitySector() != null) {
				sectors.remove(entity.getActivitySector());
			}
			request.getModel().setAttribute("sectors", sectors);

			if (entity.getCreditCard() != null) {
				request.getModel().setAttribute("hasCreditCard", true);
				request.getModel().setAttribute("creditCardId", entity.getCreditCard().getId());
			} else {
				request.getModel().setAttribute("hasCreditCard", false);
			}

		}

	}

	@Override
	public void update(final Request<Patron> request, final Patron entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
