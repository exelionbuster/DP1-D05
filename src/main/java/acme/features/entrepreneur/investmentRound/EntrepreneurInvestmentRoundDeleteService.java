
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	//	Internal states ------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	private AuthenticatedForumRepository			forumRepository;
  
  @Autowired
	EntrepreneurActivityRepository					activityRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kind", "title", "description", "amount", "link", "finalMode");

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());

		if (!activities.isEmpty()) {
			model.setAttribute("activities", activities);
		} else {
			model.setAttribute("activities", null);
		}
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int investmentRoundId;

		investmentRoundId = request.getModel().getInteger("id");
		result = this.repository.findOneById(investmentRoundId);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());

		if (!activities.isEmpty()) {
			request.getModel().setAttribute("activities", activities);
		} else {
			request.getModel().setAttribute("activities", null);
		}

		if (!errors.hasErrors()) {
			boolean hasApplications = this.repository.hasApplications(entity.getId());

			errors.state(request, !hasApplications, "link", "entrepreneur.investment-round.error.applications");
		}

		if (errors.hasErrors()) {
			request.getModel().setAttribute("isFinalMode", entity.isFinalMode());
		}

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Collection<Activity> activities = this.activityRepository.findActivitiesByInvestmentRound(entity.getId());
		this.activityRepository.deleteAll(activities);

 		this.forumRepository.delete(this.forumRepository.findOneByInvRoundId(entity.getId()));

		this.repository.delete(entity);

	}

}
