package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.UserFavoris;
import tn.esprit.spring.repository.UserFavorisRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller(value = "favList")
@ELBeanName(value = "favList")
@Join(path = "/fav", to = "/fav.jsf")
@Component
public class FavoriLouerController {

    @Autowired
    private UserFavorisRepository UserFavorisRepository;
    private List<UserFavoris> UserFavoriss;
    private List<UserFavoris> UserFavorisList;

    public List<UserFavoris> getUserFavorisList() {
        return UserFavorisList;
    }

    public void setUserFavorisList(List<UserFavoris> UserFavorisList) {
        this.UserFavorisList = UserFavorisList;
    }

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        List<UserFavoris> allByUser = UserFavorisRepository.findAllByUser(LoginController.userDetails.getId());
        UserFavoriss = allByUser.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<UserFavoris> getUserFavoriss() {
        return UserFavoriss;
    }

    public void setUserFavoriss(List<UserFavoris> UserFavoriss) {
        this.UserFavoriss = UserFavoriss;
    }

    @Transactional
    public void delUserFavoris(int id, Advertisement advertisement) {
        this.UserFavorisRepository.deleteAllByUserAndAnnonce(id, advertisement);
        loadData();
    }

}
