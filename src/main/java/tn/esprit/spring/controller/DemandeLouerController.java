package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.DemandeLouer;
import tn.esprit.spring.repository.DemandeLouerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller(value = "demandLouerList")
@ELBeanName(value = "demandLouerList")
@Join(path = "/advertisement", to = "/advertisement.jsf")
@Component
public class DemandeLouerController {

    @Autowired
    private DemandeLouerRepository demandeLouerRepository;
    private List<DemandeLouer> demandeLouers;
    private List<DemandeLouer> demandeLouerList;
    private DemandeLouer demandeLouer;

    public DemandeLouer getDemandeLouer() {
        return demandeLouer;
    }

    public void addDemandeLouer(DemandeLouer demandeLouer) {
        this.demandeLouer = demandeLouer;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dlg2').show();");
    }

    public List<DemandeLouer> getDemandeLouerList() {

        return demandeLouerList;
    }

    public void setDemandeLouerList(List<DemandeLouer> demandeLouerList) {
        this.demandeLouerList = demandeLouerList;
    }

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        List<DemandeLouer> allByUser = demandeLouerRepository.findAllByUser(LoginController.userDetails.getId());
        demandeLouers = allByUser.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<DemandeLouer> getDemandeLouers() {
        System.out.println("------------------------------------------------------------------");
        List<DemandeLouer> allByUser = demandeLouerRepository.findAllByUser(LoginController.userDetails.getId());
        demandeLouers = allByUser.stream()
                .distinct()
                .collect(Collectors.toList());
        return demandeLouers;
    }

    public void setDemandeLouers(List<DemandeLouer> demandeLouers) {
        this.demandeLouers = demandeLouers;
    }

    @Transactional
    public void deleteDemande(long user, Advertisement annonce) {
        System.out.println(user);
        System.out.println(annonce);
        demandeLouerRepository.deleteAllByUserAndAnnonce(user, annonce);
        loadData();
    }
}
