package tn.esprit.spring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ConfirmationToken;
import tn.esprit.spring.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService1 {
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	public void saveConfiramationtoken(	ConfirmationToken token){
		confirmationTokenRepository.save(token);
	}

	public ConfirmationToken getToken(String token) {
	    return confirmationTokenRepository.findByToken(token);
	}

	public int setConfirmedAt(String token) {
	    return confirmationTokenRepository.updateConfirmedAt(
	            token, LocalDateTime.now());
	}
}
