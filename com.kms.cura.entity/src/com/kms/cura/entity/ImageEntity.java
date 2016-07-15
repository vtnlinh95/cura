package com.kms.cura.entity;

public class ImageEntity extends Entity{
	private String image,path;
	public ImageEntity(String image, String path,String name, String id){
		super(name,id);
		this.image = image;
		this.path = path;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
