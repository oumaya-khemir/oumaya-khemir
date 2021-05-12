package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.entity.Advertisement;


public interface IAdvertisementService {
	public List<Advertisement> retrieveAllAds();

	public String addAdvertisement(Advertisement ad);
	
}
