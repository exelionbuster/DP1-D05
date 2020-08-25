
package acme.features.patron.creditCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.features.authenticated.patron.AuthenticatedPatronRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCreditCardCreateService implements AbstractCreateService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository		repository;

	@Autowired
	AuthenticatedPatronRepository	patronRepository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "number", "brand", "cvv");

	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {

		CreditCard res;

		res = new CreditCard();

		Patron p;
		Principal principal = request.getPrincipal();
		p = this.repository.findPatronByUserAccountId(principal.getAccountId());

		res.setPatron(p);

		return res;

	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date date = null;
		String ccDate = (String) request.getModel().getAttribute("ccDate");
		Boolean checkformat = true;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
			dateFormat.setLenient(false);
			date = dateFormat.parse(ccDate);
		} catch (ParseException ex) {
		}
		if (date == null) {
			checkformat = false;
		}

		if (!errors.hasErrors("ccDate")) {
			errors.state(request, checkformat, "ccDate", "patron.credit-card.form.error.date-format");
		}

		if (checkformat) {
			Date now = new Date(System.currentTimeMillis());
			if (!errors.hasErrors("ccDate")) {
				Boolean isAfter = date.after(now);
				errors.state(request, isAfter, "ccDate", "patron.credit-card.form.error.past-expiration-date");
			}
		}

	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		String ccDate = (String) request.getModel().getAttribute("ccDate");
		Date date = null;
		try {
			date = format.parse(ccDate);
		} catch (ParseException e) {
		}
		if (date != null) {
			entity.setExpirationDate(date);
		}

		Patron p;
		Principal principal = request.getPrincipal();
		p = this.repository.findPatronByUserAccountId(principal.getAccountId());
		p.setCreditCard(entity);

		this.patronRepository.save(p);

		this.repository.save(entity);

	}

}
