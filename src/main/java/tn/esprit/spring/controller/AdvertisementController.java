package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "adsController")
@ELBeanName(value = "adsController")
@Join(path = "/listeannonce", to = "/listeAnnonce.jsf")
public class AdvertisementController {
}
