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

package acme.features.administrator.toolRecord;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorToolRecordCreateService implements AbstractCreateService<Administrator, ToolRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorToolRecordRepository repository;


	// AbstractListService<Authenticated, ToolRecord> interface --------------

	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
		sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());

		model.setAttribute("sectors", sectors);

		request.unbind(entity, model, "title", "activitySector", "inventor", "description", "webSite", "email", "openSource", "stars");

	}

	@Override
	public ToolRecord instantiate(final Request<ToolRecord> request) {
		assert request != null;

		ToolRecord result;

		result = new ToolRecord();

		return result;
	}

	@Override
	public void validate(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Set<String> sectors = new HashSet<String>(Arrays.asList(this.repository.findActivitySectors().split(";")));
		sectors = sectors.stream().map(String::trim).collect(Collectors.toSet());
		if (entity.getActivitySector() != null) {
			sectors.remove(entity.getActivitySector());
		}

		request.getModel().setAttribute("sectors", sectors);

	}

	@Override
	public void create(final Request<ToolRecord> request, final ToolRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
