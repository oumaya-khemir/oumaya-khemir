package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "listuserstabController")
@ELBeanName(value = "listuserstabController")
@Join(path = "/ListUsersTab", to = "/listusersTab.jsf")
public class ListUserTabController {

}
