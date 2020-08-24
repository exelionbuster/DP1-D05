
package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	//	Internal states -------------

	@Autowired
	private EntrepreneurApplicationListService	listService;

	@Autowired
	private EntrepreneurApplicationShowService					showService;

	@Autowired
	private EntrepreneurApplicationUpdateService				updateService;


	//	Constructors ------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
