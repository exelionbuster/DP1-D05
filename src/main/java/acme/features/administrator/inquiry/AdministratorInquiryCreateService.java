/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.inquiry;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;


	// AbstractListService<Authenticated, Inquiry> interface --------------

	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "contactEmail");

	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		Inquiry result;

		result = new Inquiry();

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// El deadline debe ser futuro, no puede ser anterior al día de hoy

		Calendar calendar;
		Date minimunDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimunDeadline = calendar.getTime();
			Boolean isAfter = entity.getDeadline().after(minimunDeadline);
			errors.state(request, isAfter, "deadline", "administrator.inquiry.form.error.past-deadline");
		}

		//minMoney debe ser inferior a maxMoney, además ambos no puede ser negativos

		if (!errors.hasErrors("minMoney") && entity.getMinMoney() != null && entity.getMaxMoney() != null) {
			Boolean balance = entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount();
			errors.state(request, balance, "minMoney", "administrator.inquiry.form.error.max-money.less");
		}

		if (!errors.hasErrors("minMoney")) {
			Boolean isPositive = entity.getMinMoney().getAmount() > 0;
			errors.state(request, isPositive, "minMoney", "administrator.inquiry.form.error.min-money.negative");
		}

		if (!errors.hasErrors("maxMoney")) {
			Boolean isPositive = entity.getMaxMoney().getAmount() > 0;
			errors.state(request, isPositive, "maxMoney", "administrator.inquiry.form.error.max-money.negative");
		}

	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}

}
