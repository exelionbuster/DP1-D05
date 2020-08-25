
package acme.features.patron.creditCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronCreditCardUpdateService implements AbstractUpdateService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean res;
		int creditCardId;
		CreditCard creditCard;
		Patron patron;
		Principal principal;

		creditCardId = request.getModel().getInteger("id");
		creditCard = this.repository.findOneById(creditCardId);
		patron = creditCard.getPatron();
		principal = request.getPrincipal();
		res = patron.getUserAccount().getId() == principal.getAccountId();

		return res;
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

		String expirationDate = "";
		Date date = entity.getExpirationDate();
		Integer year = date.getYear() + 1900;
		Integer month = date.getMonth() + 1;
		if (month < 10) {
			expirationDate += "0";
		}
		expirationDate += month;
		expirationDate += "/";
		expirationDate += year;

		model.setAttribute("ccDate", expirationDate);

		request.unbind(entity, model, "holderName", "number");

	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneById(id);

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
	public void update(final Request<CreditCard> request, final CreditCard entity) {
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

		this.repository.save(entity);

	}

}
