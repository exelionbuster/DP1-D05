
package acme.features.patron.creditCard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.features.authenticated.patron.AuthenticatedPatronRepository;
import acme.features.patron.banner.PatronBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronCreditCardDeleteService implements AbstractDeleteService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository		repository;

	@Autowired
	AuthenticatedPatronRepository	patronRepository;

	@Autowired
	PatronBannerRepository			bannerRepository;


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

	}

	@Override
	public void delete(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		Patron p;
		Principal principal = request.getPrincipal();
		p = this.repository.findPatronByUserAccountId(principal.getAccountId());
		p.setCreditCard(null);
		entity.setPatron(null);

		Collection<Banner> bs;
		bs = this.bannerRepository.findBannerByCreditCardId(entity.getId());
		if (!bs.isEmpty()) {
			bs.stream().forEach(b -> b.setCreditCard(null));
			bs.stream().forEach(b -> this.bannerRepository.save(b));
		}

		this.patronRepository.save(p);

		this.repository.delete(entity);

	}

}
