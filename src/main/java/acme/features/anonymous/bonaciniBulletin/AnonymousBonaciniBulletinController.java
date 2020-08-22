
package acme.features.anonymous.bonaciniBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.BonaciniBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("anonymous/bonacini-bulletin/")
public class AnonymousBonaciniBulletinController extends AbstractController<Anonymous, BonaciniBulletin> {

	@Autowired
	private AnonymousBonaciniBulletinListService	listService;

	@Autowired
	private AnonymousBonaciniBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
