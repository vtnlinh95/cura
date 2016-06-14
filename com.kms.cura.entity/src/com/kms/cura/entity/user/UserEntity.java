package com.kms.cura.entity.user;

import com.kms.cura.entity.Entity;

public class UserEntity extends Entity {
<<<<<<< HEAD
	public static final String STATUS_KEY = "statuskey";
	public static final String MESSAGE= "message";
	public static final String TYPE= "type";
	public static final int USER_TYPE = 0;
	private String email;
	private String password;

	public UserEntity(String id, String name, String email, String password) {
		super(id, name);
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
=======
    public static final String STATUS_KEY = "statuskey";
    public static final String MESSAGE = "message";
    private String email;
    private String password;

    public UserEntity(String id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.getId() + "\n" + this.getName() + "\n" + this.email;
    }
>>>>>>> dad7c6a... KATI-32[Search] Search Results Feature

	public int getType() {
		return USER_TYPE;
	}

}
