
package acme.features.patron.banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.features.patron.creditCard.PatronCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronBannerUpdateService implements AbstractUpdateService<Patron, Banner> {

	@Autowired
	PatronBannerRepository							repository;

	@Autowired
	PatronCreditCardRepository						creditCardRepository;

	@Autowired
	private AdministratorConfigurationRepository	configurationRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean res;
		int bannerId;
		Banner banner;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneById(bannerId);
		patron = banner.getPatron();
		principal = request.getPrincipal();
		res = patron.getUserAccount().getId() == principal.getAccountId();

		return res;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creditCard");

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

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors()) {
			Boolean isSpam = false;
			List<String> spamWords = new ArrayList<String>();
			String content = "";
			Double contentSize;
			Double spamCount = 0.0;
			Double spamThreshold = 0.0;

			spamWords.addAll(Arrays.asList(this.configurationRepository.findSpamWords().split(",")));
			spamWords = spamWords.stream().map(String::trim).map(String::toLowerCase).collect(Collectors.toList());

			content += entity.getPicture().toLowerCase() + entity.getSlogan().toLowerCase() + entity.getTarget().toLowerCase();
			;

			for (String spamword : spamWords) {
				Integer loopCount = 0;
				loopCount += content.split(spamword, -1).length - 1;
				spamCount += loopCount * spamword.length();
			}

			contentSize = 1.0 * content.length();

			spamThreshold = this.configurationRepository.findSpamThreshold();

			isSpam = spamCount / contentSize * 100 >= spamThreshold;

			errors.state(request, !isSpam, "target", "patron.banner.form.error.spam");
		}

		if (errors.hasErrors()) {
			if (entity.getPatron().getCreditCard() != null) {
				request.getModel().setAttribute("hasCreditCard", true);

				int id = entity.getPatron().getCreditCard().getId();
				String number = entity.getPatron().getCreditCard().getNumber();

				request.getModel().setAttribute("creditCardId", id);
				request.getModel().setAttribute("creditCardNumber", number);
			} else {
				request.getModel().setAttribute("creditCardId", null);
				request.getModel().setAttribute("hasCreditCard", false);
			}

			if (entity.getCreditCard() != null) {
				request.getModel().setAttribute("bannerHasCC", true);
			} else {
				request.getModel().setAttribute("bannerHasCC", false);
			}
		}
	}

	@Override
	public void update(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		String creditCardId = (String) request.getModel().getAttribute("creditCardId");
		if (creditCardId != "") {
			Integer creditId = new Integer(creditCardId);
			entity.setCreditCard(this.creditCardRepository.findOneById(creditId));
		} else {
			entity.setCreditCard(null);
		}
		this.repository.save(entity);

	}

}
