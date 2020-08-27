
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		// TODO Auto-generated method stub

	}

}
