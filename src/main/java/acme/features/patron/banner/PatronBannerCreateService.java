
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
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	@Autowired
	PatronBannerRepository							repository;

	@Autowired
	private AdministratorConfigurationRepository	configurationRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Banner res;

		res = new Banner();

		Patron p;
		Principal principal = request.getPrincipal();
		p = this.repository.findPatronByUserAccountId(principal.getAccountId());

		res.setPatron(p);

		return res;
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
	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
