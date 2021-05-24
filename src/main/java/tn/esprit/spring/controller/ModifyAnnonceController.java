package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "modifyController")
@ELBeanName(value = "modifyController")
@Join(path = "/modifyannonce", to = "/modifyAnnonce.jsf")
public class ModifyAnnonceController {
}
