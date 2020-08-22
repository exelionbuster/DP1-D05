
package acme.features.anonymous.alesBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.AlesBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/ales-bulletin/")
public class AnonymousAlesBulletinController extends AbstractController<Anonymous, AlesBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousAlesBulletinListService	listService;

	@Autowired
	private AnonymousAlesBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
