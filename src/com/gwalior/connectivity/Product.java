package com.gwalior.connectivity;

import java.util.List;

public class Product {
	
	private long pid;
	private String image;
	private String prname;
	private long oprice;
	private long nprice;
	private long discount;
	private String category;
	private String s_category;
	private String para;
	private String link;
	private int admin;
	private int status;
	private Category cid;
	private List<Description> description;
	
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrname() {
		return prname;
	}
	public void setPrname(String prname) {
		this.prname = prname;
	}
	public long getOprice() {
		return oprice;
	}
	public void setOprice(long oprice) {
		this.oprice = oprice;
	}
	public long getNprice() {
		return nprice;
	}
	public void setNprice(long nprice) {
		this.nprice = nprice;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getS_category() {
		return s_category;
	}
	public void setS_category(String s_category) {
		this.s_category = s_category;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Category getCid() {
		return cid;
	}
	public void setCid(Category cid) {
		this.cid = cid;
	}
	public List<Description> getDescription() {
		return description;
	}
	public void setDescription(List<Description> description) {
		this.description = description;
	}
	
	
}
