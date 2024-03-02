package models;

public class command {
int id;
int userId;
String product;

public command(int id, int userId, String product) {
	this.id = id;
	this.userId = userId;
	this.product = product;
}
public command(int userId, String product) {
	this.userId = userId;
	this.product = product;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}

}
