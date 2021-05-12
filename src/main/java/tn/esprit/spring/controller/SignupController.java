package tn.esprit.spring.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.AuthProvider;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;

@Controller(value = "signupController")
@ELBeanName(value = "signupController")
@Join(path = "/signup", to = "/signup.jsf")
@Component
public class SignupController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	

	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String address;
    private Date dateN;
	private String tel;

 

	public SignupController() {
		try
		{
		System.out.println(LoginController.userDetails.getFirstName());
		} catch(Exception e){
			System.out.println("Error, please connect to get acces to user details");
		}
	}


	public String doSignup() throws IOException {
		String navigateTo = "null";
		if (userRepository.existsByUsername(username)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Username is already taken!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}

		else if (userRepository.existsByEmail(email)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Email is already in use!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}
		else
		{
			User user = new User(username, 
					 email,
					 encoder.encode(password),firstName
					 ,lastName,address,dateN,tel
					 );
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			roles.add(userRole);
			user.setRoles(roles);
			user.setNbMission(0);
			user.setNbMission(0);
			user.setInteret("None");
			user.setProvider(AuthProvider.local);
			user.setProviderId("local");
			user.setDisponible("None");
			user.setEtatD("none");
			user.setLieuxTravail("None");
			user.setPrime(0);
			user.setPrime(0);
			user.setEnabled(true);
			user.setSignupDay(LocalDate.now());
			userRepository.save(user);
			
		
			
			//String appUrl = "";
			//User registered= user;
			//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, appUrl));
			navigateTo="login.xhtml?faces-redirect=true";
			FacesMessage facesMessage =

					new FacesMessage("Registered successfully, please verify your account!");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		
	}
	
	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDateN() {
		return dateN;
	}


	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}



	
	
	
	

}
