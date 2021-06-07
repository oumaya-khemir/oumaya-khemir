package tn.esprit.spring.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;


@Entity  
@Table(name = "Subscription")
public class Subscription implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 7098890234731393759L;
@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private int subscription_id;
@Column
private String subscription_offer;
@Column
private String subscription_title;
@Column
private Date subscription_duration;  
@Column
private int subscription_price;
public Subscription() {
}




public Subscription(int subscription_id, String subscription_offer, String subscription_title,
		Date subscription_duration, int subscription_price, List<Subscribe> subscribes) {
	super();
	this.subscription_id = subscription_id;
	this.subscription_offer = subscription_offer;
	this.subscription_title = subscription_title;
	this.subscription_duration = subscription_duration;
	this.subscription_price = subscription_price;
	this.subscribes = subscribes;
}




public java.sql.Date getSubscription_duration() {
	return subscription_duration;
}
public void setSubscription_duration(Date subscription_duration) {
	this.subscription_duration = subscription_duration;
}
public List<Subscribe> getSubscribes() {
	return subscribes;
}
public void setSubscribes(List<Subscribe> subscribes) {
	this.subscribes = subscribes;
}




@OneToMany(mappedBy = "subscription")
private List<Subscribe> subscribes ;


public int getSubscription_id() {
	return subscription_id;
}
public void setSubscription_id(int subscription_id) {
	this.subscription_id = subscription_id;
}
public String getSubscription_offer() {
	return subscription_offer;
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
@Override
public String toString() {
	return "Subscription [subscription_id=" + subscription_id + ", subscription_offer=" + subscription_offer
			+ ", subscription_title=" + subscription_title + ", subscription_price=" + subscription_price + "]";
}


}
