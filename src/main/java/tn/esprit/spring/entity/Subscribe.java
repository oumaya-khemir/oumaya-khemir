package tn.esprit.spring.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;



@Entity
@Table(name = "Subscribe")

public class Subscribe implements Serializable {


    /**
	 * kk
	 */
	private static final long serialVersionUID = 46463834952660110L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sub_id;

    @ManyToOne
    @JoinColumn(name = "subscription_id",  nullable = false)
    private Subscription subscription;
    
    @ManyToOne
    @JoinColumn(name = "user_id",   nullable = false)
    private Utilisateur user ;
 
    @Column
    private Date DateD;
    @Column
    private Date DateF ;
    @Column
    private boolean paid ;
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur u) {
		this.user = u;
	}


	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}



	public Date getDateD() {
		return DateD;
	}
	public void setDateD(Date dateD) {
		DateD = dateD;
	}
	public Date getDateF() {
		return DateF;
	}
	public void setDateF(Date date) {
		DateF = date;
	}
	public Subscribe() {
		super();
	}
	public Subscribe(int sub_id, Subscription subscription, Utilisateur user, Date dateD, Date dateF,
			boolean paid) {
		super();
		this.sub_id = sub_id;
		this.subscription = subscription;
		this.user = user;
		DateD = dateD;
		DateF = dateF;
		this.paid = paid;
	}
    
   
    
    
}
