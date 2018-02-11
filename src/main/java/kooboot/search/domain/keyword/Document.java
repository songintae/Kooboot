package kooboot.search.domain.keyword;

import java.util.Iterator;

import org.json.simple.JSONObject;

import kooboot.util.StringUtil;

public class Document {
	private String place_url;
	private String category_group_name;
	private String place_name;
	private String distance;
	private String address_name;
	private String road_address_name;
	private String id;
	private String phone;
	private String category_group_code;
	private String category_name;
	private String x;
	private String y;
	
	public Document() {
	}
	
	public Document(String place_url, String category_group_name, String place_name, String distance,
			String address_name, String road_address_name, String id, String phone, String category_group_code,
			String category_name, String x, String y) {
		this.place_url = place_url;
		this.category_group_name = category_group_name;
		this.place_name = place_name;
		this.distance = distance;
		this.address_name = address_name;
		this.road_address_name = road_address_name;
		this.id = id;
		this.phone = phone;
		this.category_group_code = category_group_code;
		this.category_name = category_name;
		this.x = x;
		this.y = y;
	}


	public String getPlace_url() {
		return place_url;
	}
	public void setPlace_url(String place_url) {
		this.place_url = place_url;
	}
	public String getCategory_group_name() {
		return category_group_name;
	}
	public void setCategory_group_name(String category_group_name) {
		this.category_group_name = category_group_name;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getRoad_address_name() {
		return road_address_name;
	}
	public void setRoad_address_name(String road_address_name) {
		this.road_address_name = road_address_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCategory_group_code() {
		return category_group_code;
	}
	public void setCategory_group_code(String category_group_code) {
		this.category_group_code = category_group_code;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	public void pareseDocumentResponse(JSONObject response){
		Iterator itr = response.keySet().iterator();
		String key = null;
		while(itr.hasNext()){
			key = itr.next().toString();
			try{
				Document.class.getMethod("set"+StringUtil.StringFirstUpper(key),String.class).invoke(this, (String)response.get(key));
			}catch(Exception e){
				
			}
		}
		
	}
}
