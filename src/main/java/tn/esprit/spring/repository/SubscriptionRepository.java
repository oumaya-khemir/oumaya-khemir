package tn.esprit.spring.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dari.tn.model.Subscription;

@Repository

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>  
{


	public Optional<Subscription> findById(int id);

	@Query(value="SELECT * from subscription s where s.subscription_title like %:word% or s.subscription_offer like %:word% or"
			+ " s.subscription_price like %:word%",nativeQuery =true)
	public List<Subscription> searchEvent(@Param("word") String word);



}  
