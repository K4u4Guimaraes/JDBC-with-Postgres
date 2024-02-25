package model;

public class Phone {
	
	private long idPhone;
	private String numberUser;
	private String type;
	
	private long idUser;
	
	
	public Phone() {
		
	}
	
	public void setIdPhone(long idPhone) {
		this.idPhone = idPhone;
	}
	
	public long getIdPhone() {
		return idPhone;
	}
	
	public void setNumber(String numberUser) {
		this.numberUser = numberUser;
	}
	
	public String getNumber() {
		return numberUser;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public long getIdUser() {
		return idUser;
	}

}
