package tn.esprit.spring.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.UserService;

@Controller(value = "profileController")
@ELBeanName(value = "profileController")
@Join(path = "/profil", to = "/profil.jsf")
@Component
public class ProfileController {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;


	
	private String Password;
	private String VerifyPass;

	
	
	
	public String updateUser()
	{
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		u.setFirstName(LoginController.userDetails.getFirstName());
		u.setLastName(LoginController.userDetails.getLastName());
		u.setAddress(LoginController.userDetails.getAddress());
		u.setEmail(LoginController.userDetails.getEmail());
		u.setTel(LoginController.userDetails.getTel());
		u.setDateN(LoginController.userDetails.getDateN());
		userService.updateUser(u);
		FacesMessage facesMessage =

				new FacesMessage("User Updated with Sucess");

				FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		return "profil.xhtml?faces-redirect=true";
		
	}
	
	public String updatePassword()
	{
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		if(Password.equals(VerifyPass))
		{
			u.setPassword(encoder.encode(Password));
			userService.updateUser(u);
			FacesMessage facesMessage =

					new FacesMessage("Password Updated with Sucess");

					FacesContext.getCurrentInstance().addMessage("form2:btn1",facesMessage);
		}
		else
		{
			FacesMessage facesMessage =

					new FacesMessage("Password not match!");

					FacesContext.getCurrentInstance().addMessage("form2:btn1",facesMessage);
		}
		
		return "profil.xhtml?faces-redirect=true";
		
	}
	
	public String updateProfileImage()
	{

		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
	
		
		return "profil.xhtml?faces-redirect=true";
		
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getVerifyPass() {
		return VerifyPass;
	}

	public void setVerifyPass(String verifyPass) {
		VerifyPass = verifyPass;
	}


	
}
