
package acme.features.authenticated.investor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedInvestorUpdateService implements AbstractUpdateService<Authenticated, Investor> {

	@Autowired
	private AuthenticatedInvestorRepository repository;


	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
		sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());
		sectors.remove(entity.getActivitySector());

		model.setAttribute("sectors", sectors);

		request.unbind(entity, model, "firmName", "activitySector", "profile");
	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor res;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		res = this.repository.findOneInvestorByUserAccountId(userAccountId);

		return res;
	}

	@Override
	public void validate(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
		sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());
		if (entity.getActivitySector() != null) {
			sectors.remove(entity.getActivitySector());
		}

		request.getModel().setAttribute("sectors", sectors);

	}

	@Override
	public void update(final Request<Investor> request, final Investor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
