package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.UserFavoris;

import java.util.List;

public interface UserFavorisRepository extends JpaRepository<UserFavoris, Long> {
    List<UserFavoris> findAllByUser(long iduser);
    void deleteAllByUserAndAnnonce(long user, Advertisement advertisement);
}
