package com.e.appentusmachinetest.model;


import com.google.gson.annotations.SerializedName;


public class ImageResponsemodel{

	@SerializedName("author")
	private String author;
	@SerializedName("download_url")
	private String downloadUrl;
	@SerializedName("height")
	private int height;
	@SerializedName("id")
	private String id;
	@SerializedName("url")
	private String url;
	@SerializedName("width")
	private int width;

	public void setAuthor(String author){
		this.author = author;
	}
	public String getAuthor(){
		return this.author;
	}
	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}
	public String getDownloadUrl(){
		return this.downloadUrl;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public int getHeight(){
		return this.height;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getWidth(){
		return this.width;
	}



}