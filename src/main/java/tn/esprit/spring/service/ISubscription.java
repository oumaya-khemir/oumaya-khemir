package tn.esprit.spring.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import dari.tn.model.Subscription;


public  interface ISubscription {

	List<Subscription> getAllSubscriptions();
    Subscription getSubscritpion(int abonnement_id);
    void deleteSubscritpion(int abonnement_id);
    int saveSubscritpion(Subscription abonnement);
    Subscription update(Subscription abonnement);
    ResponseEntity<?> Add(Subscription s);


}
