package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AdvertisementRepository;


@Controller
@RequestMapping("/Advertisement")
public class AdvertisementServiceImp implements IAdvertisementService {

	@Autowired	
	AdvertisementRepository AdRepository;
	

	@Override
	@CrossOrigin
	@PostMapping("/AddAdvertisement")
	public String addAdvertisement(@RequestBody Advertisement ad) {
		Date date = new Date();
		ad.setAddDate(date);
		long a=1;
		User u1=new User();
		ad.setUser(u1);
		ad.setRemoved(false);
		
		AdRepository.save(ad);
		 
		 
		return "Advertisement added with id"+ad.getId();
	}


	@Override
	@CrossOrigin
	@GetMapping("/Objects")
	public List<Advertisement>  retrieveAllAds() {
		List<Advertisement> List= new ArrayList<Advertisement>();
		Iterable<Advertisement> ads =AdRepository.findAll();
		 System.out.println("skander");
		for(Advertisement a: ads){
			if(a.isRemoved()==false){
			List.add(a);
			System.out.println(a.toString());}
		}
		return List ;
	
	}
	
	@CrossOrigin
	@DeleteMapping("/DeleteAd/{id}")
	public String delete(@PathVariable Long id) {
		Optional<Advertisement> ad=AdRepository.findById(id);
		if (ad.isPresent()){
			Advertisement objet = ad.get();
			objet.setRemoved(true);
			AdRepository.save(objet);}
		return "Ad deleted with id : "+id;		
	}

	
}