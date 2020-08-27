
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	@Autowired
	PatronBannerRepository respository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean res;
		int bannerId;
		Banner banner;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		banner = this.respository.findOneById(bannerId);
		patron = banner.getPatron();
		principal = request.getPrincipal();
		res = patron.getUserAccount().getId() == principal.getAccountId();

		return res;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target");

		if (entity.getPatron().getCreditCard() != null) {
			model.setAttribute("hasCreditCard", true);

			int id = entity.getPatron().getCreditCard().getId();
			String number = entity.getPatron().getCreditCard().getNumber();

			model.setAttribute("creditCardId", id);
			model.setAttribute("creditCardNumber", number);

		} else {

			model.setAttribute("creditCardId", null);
			model.setAttribute("hasCreditCard", false);
		}

		if (entity.getCreditCard() != null) {
			model.setAttribute("bannerHasCC", true);
		} else {
			model.setAttribute("bannerHasCC", false);
		}

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner res;
		int id;
		id = request.getModel().getInteger("id");

		res = this.respository.findOneById(id);
		return res;
	}

}
