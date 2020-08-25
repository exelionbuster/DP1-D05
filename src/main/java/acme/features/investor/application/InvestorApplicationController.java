
package acme.features.investor.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/application/")
public class InvestorApplicationController extends AbstractController<Investor, Application> {

	//	Internal states -------------

	@Autowired
	private InvestorApplicationListService		listService;

	@Autowired
	private InvestorApplicationShowService		showService;

	@Autowired
	private InvestorApplicationCreateService	createService;


	// Constructors----------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
