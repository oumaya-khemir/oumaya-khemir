package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Advertisement;


@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long>{
public Optional<Advertisement> findById(Long id);	
}
