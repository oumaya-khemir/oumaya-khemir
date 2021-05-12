package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ConfirmationToken;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.VerificationTokenRepository;


@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository  userRepository;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
//	@Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
    ConfirmationTokenService1 confirmationTokenService;
	@Autowired
	EmailSender emailSender;
	/*Chercher un utilisateur*/
	public User findOne(long id){
	return userRepository.findById(id);
	}
	public User save(User u) {
		return userRepository.save(u);
	}
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/*Update d'un user*/
	public  User updateUser(User user)	{
		return userRepository.save(user);
		
	}
	
	/*get all Users*/
	public List<User> getAllUsers(){
		 List<Long> listUsersId=userRepository.ListeUsers();
	
			List<User> listUsers = new ArrayList();
		 User u = new User();
		 for(Long  a : listUsersId)
		 {
			 u=findOne(a);
			// if(u.getEtatAcc().equals("waiting"))
			// {
				 listUsers.add(u);
			// }
		 }
		return  listUsers;		
	}
	
	public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
	
	public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
	
	public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

	

	
	public List<User> getUserSelonChoix(String choix, String cle)
	{
		return userRepository.getUserSelonChoix(cle);
	}
	public List<User> getUserSelonEmail(String choix, String cle)
	{
		return userRepository.getUserSelonEmail(cle);
	}

	public int getmbreUsersbyPointfideletInf100(){
		return userRepository.nombreUsersbyPointfideletInf100();
	};
	public int getmbreUsersbyPointfideletBetwen100300(){
		return userRepository.nombreUsersbyPointfideletbetwen100et300();
	};
	
	public int getmbreUsersbyPointfideletSup(){
		return userRepository.nombreUsersbyPointfideletSup300();
	};
	public int nbuser() {
		return userRepository.findAll().size();
	}
	public float moyennenbpointfiedelete(){
		return userRepository.moyenneNpointFidelet();
	}
	@Override
	public String deleteUser(long userid) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userid);
		return "";
	}
//	@Override
//	public String saveUser(User user) {
//		String hashPW =bCryptPasswordEncoder.encode(user.getPassword());
//	     user.setPassword(hashPW);
//	     userRepository.save(user);
//	     String token = UUID.randomUUID().toString();
//	     ConfirmationToken confiramationtoken = new ConfirmationToken(
//	    		token,
//	    		LocalDateTime.now(),
//	    		LocalDateTime.now().plusMinutes(15),
//	    		user
//	    		
//	    		 );
//	     confirmationTokenService.saveConfiramationtoken(confiramationtoken);
//	     
//	     String link = "http://localhost:8089/confirm?token=" + token;
//	     emailSender.send(
//	    		 user.getEmail(),
//	         //    buildEmail(user.getUsername(), link));    	
//	          return token;
//}
	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
