package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "acceuilController")
@ELBeanName(value = "acceuilController")
@Join(path = "/", to = "/acceuil.jsf")
public class AcceuilController {

}
