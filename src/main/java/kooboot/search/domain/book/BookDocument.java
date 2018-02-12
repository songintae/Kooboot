package kooboot.search.domain.book;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kooboot.search.domain.Document;
import kooboot.util.StringUtil;

public class BookDocument extends Document {
	private List<String> authors = new ArrayList<String>();
	private List<String> translators = new ArrayList<String>();
	private String barcode;
	private String category;
	private String contents;
	private String datetime;
	private String ebook_barcode;
	private String isbn;
	private Long price;
	private String publisher;
	private Long sale_price;
	private String sale_yn;
	private String status;
	private String thumbnail;
	private String title;
	private String url;
	
	public void addAuthors(String author){
		this.authors.add(author);
	}
	
	public void addTranslators(String translator){
		this.translators.add(translator);
	}
	
	public List<String> getAuthors(){
		return Collections.unmodifiableList(this.authors);
	}
	
	public List<String> getTranslators(){
		return Collections.unmodifiableList(this.translators);
	}
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getContents() {
		return contents;
	}



	public void setContents(String contents) {
		this.contents = contents;
	}



	public String getDatetime() {
		return datetime;
	}



	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}



	public String getEbook_barcode() {
		return ebook_barcode;
	}



	public void setEbook_barcode(String ebook_barcode) {
		this.ebook_barcode = ebook_barcode;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public Long getPrice() {
		return price;
	}



	public void setPrice(Long price) {
		this.price = price;
	}



	public String getPublisher() {
		return publisher;
	}



	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}



	public Long getSale_price() {
		return sale_price;
	}



	public void setSale_price(Long sale_price) {
		this.sale_price = sale_price;
	}



	public String getSale_yn() {
		return sale_yn;
	}



	public void setSale_yn(String sale_yn) {
		this.sale_yn = sale_yn;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getThumbnail() {
		return thumbnail;
	}



	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}
	
}
