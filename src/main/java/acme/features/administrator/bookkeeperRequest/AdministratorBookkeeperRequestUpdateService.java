
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequests.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestRepository repository;
	//
	//	@Autowired
	//	AdministratorBookkeeperRepository			bookkeeperRepository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement", "accepted");

		model.setAttribute("isAccepted", entity.isAccepted());

	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest res;
		int id = request.getModel().getInteger("id");
		res = this.repository.findOne(id);

		return res;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (errors.hasErrors()) {
			request.getModel().setAttribute("isAccepted", entity.isAccepted());
		}

	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.isAccepted()) {
			String firmName = entity.getFirmName();
			String responsibilityStatement = entity.getResponsibilityStatement();
			Bookkeeper b = new Bookkeeper();
			b.setFirmName(firmName);
			b.setResponsibilityStatement(responsibilityStatement);
			Authenticated auth = entity.getAuthenticated();
			int userAccountId = auth.getUserAccount().getId();
			UserAccount ua = this.repository.findOneUserAccountById(userAccountId);
			b.setUserAccount(ua);
			this.repository.save(b);
		}

		this.repository.save(entity);

	}

}
