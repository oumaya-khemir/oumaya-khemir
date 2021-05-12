package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IUserService;
import tn.esprit.spring.service.UserService;

@RestController
public class UserControllerRest {
	@Autowired
	UserService use;
	@Autowired
	IUserService userrep;
	@Autowired
	RoleRepository role;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/all")
	@ResponseBody
	public List<User> getUsers() {
	List<User> list = userrep.getAllUsers();
	return list;
	}
	@DeleteMapping("/dell/{userid}")  
	private String deleteUser(@PathVariable("userid") int userid)   
	{  
		userrep.deleteUser(userid);
		return "utilisateur suprimer";
	} 
	@PutMapping("/mod-user")
	@ResponseBody
	public User updateEmployee(@RequestBody User user) {
	 return userrep.updateUser(user);
	}
	@PostMapping("/file")
    @ResponseBody
    public User  uploddimg (@RequestParam("file") @Nullable MultipartFile file, @RequestParam("user") int User_id ) {
		User user =userrep.findOne(User_id);
        if(file==null) {
            user.setPhotoPath("defaultPic.png");
            userrep.save(user);
        }else {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                String path =  classLoader.getResource(".").getFile();
               
                File f = new File("C:\\upload\\" +"image" + User_id+file.getOriginalFilename());
                file.transferTo(f);
               user.setPhotoPath("image"+User_id+file.getOriginalFilename());
                userrep.save(user);
            } catch (IllegalStateException e) {
           
                e.printStackTrace();
            } catch (IOException e) {
            
                e.printStackTrace();
            }
        }

        return(user);
    }
	@PostMapping("/register")///{idrole}dans l'url et , @PathVariable(name="idrole") int idrole dans la modif 
    public User register(@RequestBody() User user ) throws IOException, NexmoClientException {
		
//	 Role ro=role.findById(idrole).orElse(null);
	//user.getRole().add(ro);
        //return userrep.saveUser(user);
		//VonageClient client = VonageClient.builder().apiKey("3b7b3f72").apiSecret("0CeMpvWzyCmrBdW1").build();
       NexmoClient  client = NexmoClient .builder().apiKey("3b7b3f72").apiSecret("0CeMpvWzyCmrBdW1").build();
      TextMessage message = new TextMessage("Vonage APIs",
               "21655490846",
               "un nouveau compte utilisateur a ete creer"
        );
      SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
       if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
           System.out.println("Message sent successfully.");
       } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
		  return userrep.save(user);
    }
//	@PutMapping(value = "/affecter/{iduser}/{idRole}") 
//    public void affecter(@PathVariable("iduser")int iduser,@PathVariable("idRole") int idRole) {
//		 Role ro=role.findById(idRole).orElse(null);
//		
//		use.affecterUserARole(iduser, idRole);	
//    }
//	 @GetMapping(path = "confirm")
//	    public String confirm(@RequestParam("token") String token) {
//	        return use.confirmToken(token);
//	    }
//	 @GetMapping("/searchuser/{username}")
//	  
//	  @ResponseBody 
//	  public  List<User> search(@PathVariable(value = "username") String topic) {
//		  List<User> list =  userrep.search(topic); 
//		  return list; 
//	 }
}
