package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.DemandeLouer;

import java.util.List;

public interface DemandeLouerRepository extends JpaRepository<DemandeLouer, Long> {
    boolean existsByUserAndAnnonce(int user, int annonce);
    List<DemandeLouer> findAllByUser(long user);
    void deleteAllByUserAndAnnonce(long user, Advertisement annonce);
}
