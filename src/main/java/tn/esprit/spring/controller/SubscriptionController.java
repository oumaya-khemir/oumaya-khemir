package tn.esprit.spring.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.Query;

import dari.tn.model.Subscribe;
import dari.tn.model.Subscription;
import dari.tn.model.Utilisateur;
import dari.tn.repository.UtilisateurRepository;
import dari.tn.service.SubscriptionService;

@RestController
@Scope(value = "session")
@Controller(value = "subscriptionController")
@ELBeanName(value = "subscriptionController")
@Join(path = "/", to = "/login.jsf")public class SubscriptionController{
	@Autowired  
	SubscriptionService abonnementService;
	public int subscription_id;
	public String subscription_offer;
	public String subscription_title;
	public Date subscription_duration;
	public int subscription_price;
	UtilisateurRepository urep;
	Connection connection;
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
	public List<Subscription> ListSubs=new ArrayList<Subscription>();
	
	public Connection getConnection(){  
		try{  
		Class.forName("com.mysql.cj.jdbc.Driver");     
		connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
		}catch(Exception e){  
		System.out.println(e);  
		}  
		return connection;  
		}  
	
	public List<Subscription> getAllSubs()   
	{  
		 System.out.println("done");
    if (word== null){
		ListSubs = abonnementService.getAllSubscriptions();}
		else {
			ListSubs=abonnementService.Search(word);
		}
	return ListSubs;  

	}  
	
	public int getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	public String getSubscription_offer() {
		return subscription_offer;
	}

	public void setSubscription_duration(Date subscription_duration) {
		this.subscription_duration = subscription_duration;
	}
	public Date getSubscription_duration() {
		return subscription_duration;
	}

	public void setSubscription_offer(String subscription_offer) {
		this.subscription_offer = subscription_offer;
	}
	public String getSubscription_title() {
		return subscription_title;
	}

	public void setSubscription_title(String subscription_title) {
		this.subscription_title = subscription_title;
	}

	public int getSubscription_price() {
		return subscription_price;
	}

	public void setSubscription_price(int subscription_price) {
		this.subscription_price = subscription_price;
	}

	public List<Subscription> getListSubs() {
		return ListSubs;
	}

	public void setListSubs(List<Subscription> listSubs) {
		ListSubs = listSubs;
	}

	@GetMapping("/get/{abonnement_id}")  
	public Subscription getSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	return abonnementService.getSubscriptionById(abonnement_id);  
	}  

	
	@DeleteMapping("/delete/{abonnement_id}")  
	public void deleteSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	abonnementService.delete(abonnement_id);  
	}  
	
	@PostMapping("/subscribe")
	@ResponseBody
	public String saveSubscription(@RequestBody Subscription abonnement)   
	{  
	abonnementService.saveOrUpdate(abonnement);
	return "/Subs.xhtml?faces-redirect=true";
	}  
	
	@PutMapping("/update")  
	public Subscription update(@RequestBody Subscription abonnement)   
	{  
	abonnementService.saveOrUpdate(abonnement);  
	return abonnement;  
	}  
	
    public  String AddS(@RequestBody int idS, int long1, Date dateD, Date dateF){
    	 abonnementService.AddSubTo(idS,long1,dateD,dateF);
    	return "/Subs.xhtml?faces-redirect=true";

    }
    
    @GetMapping("/End/{idS}")
    public Subscribe End(@PathVariable int idS){
        return abonnementService.EndAbo(idS);
    }
	
  
    
    
	public String addSubscription()  {
		
		Subscription sub=new Subscription();
		sub.setSubscription_title(subscription_title);
		sub.setSubscription_price(subscription_price);
		sub.setSubscription_offer(subscription_offer);
		sub.setSubscription_duration(subscription_duration);
		
		abonnementService.Add(sub);
		return "/Subs.xhtml?faces-redirect=true";

	}
	
	public String updateSubscription(int id)
	 {
	 abonnementService.updateSub(id, null);
	 return "/updateSub.xhtml?faces-redirect=true";
	 }
	
	public String edit(int id){  
		Subscription sub = null;  
		System.out.println(id);  
		try{  
		connection = getConnection();  
		Statement stmt=getConnection().createStatement();    
		ResultSet rs=stmt.executeQuery("select * from subscription where subscription_id = "+(id));  
		rs.next();  
		sub = new Subscription();  
		sub.setSubscription_id(rs.getInt("subscription_id"));  
		sub.setSubscription_title(rs.getString("subscription_title"));  
		sub.setSubscription_price(rs.getInt("subscription_price"));  
		sub.setSubscription_offer(rs.getString("subscription_offer"));  
		sessionMap.put("editSub", sub);  
		connection.close();  
		}catch(Exception e){  
		System.out.println(e);  
		}         
		return "/updateSub.xhtml?faces-redirect=true";  
		}
	
	public String updateSubb(Subscription sub){  
		//int result = 0;  
		try{  
		connection = getConnection();    
		PreparedStatement stmt=connection.prepareStatement(  
		"update Subscription set subscription_offer=?,subscription_price=?,subscription_title=?,subscription_duration=? where subscription_id=?");    
		stmt.setString(1,sub.getSubscription_offer());    
		stmt.setInt(2,sub.getSubscription_price());    
		stmt.setString(3,sub.getSubscription_title());
		stmt.setDate(4,sub.getSubscription_duration());
		stmt.setInt(5,sub.getSubscription_id());    
		stmt.executeUpdate();  
		connection.close();  
		}catch(Exception e){  
		System.out.println();  
		}  
		return "/Subs.xhtml?faces-redirect=true";        
		}  
	
	public String word ;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Subscription> Search(String word) {

		return abonnementService.Search(word);
		
	}

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("amount", 50 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "index";
    }

   
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        abonnementService.chargeNewCard(token, amount);
        return "result";
    }

    }
	

    