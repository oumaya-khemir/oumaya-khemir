package tn.esprit.spring.service;

import tn.esprit.spring.entity.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    Optional<Advertisement> getAnnonce(long id);

    List<Advertisement> getAnnonces();

    void addAnnonce(Advertisement annonce);
}
