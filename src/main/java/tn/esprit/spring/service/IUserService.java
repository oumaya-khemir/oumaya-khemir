package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;

public interface IUserService {
	public User findOne(long id);
	public User save(User u);
	public List<User> findAll();
	public  User updateUser(User user);
	public List<User> getAllUsers();
	public User getUser(String verificationToken);
	public VerificationToken getVerificationToken(String VerificationToken);
	public void createVerificationToken(User user, String token);
	public List<User> getUserSelonChoix(String choix, String cle);
	public List<User> getUserSelonEmail(String choix, String cle);
	public int getmbreUsersbyPointfideletInf100();
	public int getmbreUsersbyPointfideletBetwen100300();
	public int getmbreUsersbyPointfideletSup();
	public int nbuser();
	public float moyennenbpointfiedelete();
	public String deleteUser(long userid);
	public String saveUser(User user);
}
