package tn.esprit.spring.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Advertisement")
public class Advertisement {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
    private Date addDate;
    private float area;
    private int nbRoom;
    private boolean garage;
    private boolean swimmingPool;
    private boolean garden;
    private boolean removed;
    private float price;
    private String address;
    public enum State{
    	Sold,Available,Blocked
    	}
    @Enumerated(EnumType.STRING)
    private State state;
    public enum Type{
    	Sell,Rent,Exchange
    	}
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToOne
    private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public int getNbRoom() {
		return nbRoom;
	}
	public void setNbRoom(int nbRoom) {
		this.nbRoom = nbRoom;
	}
	public boolean isGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public boolean isSwimmingPool() {
		return swimmingPool;
	}
	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}
	public boolean isGarden() {
		return garden;
	}
	public void setGarden(boolean garden) {
		this.garden = garden;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Advertisement() {
		
	}
	
	public boolean isRemoved() {
		return removed;
	}
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", addDate=" + addDate + ", area=" + area + ", nbRoom=" + nbRoom
				+ ", garage=" + garage + ", swimmingPool=" + swimmingPool + ", garden=" + garden + ", removed="
				+ removed + ", price=" + price + ", address=" + address + ", state=" + state + ", type=" + type
				+ ", user=" + user + "]";
	}
	
	
    
    
}

