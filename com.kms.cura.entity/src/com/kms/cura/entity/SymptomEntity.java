package com.kms.cura.entity;

/**
 * Created by toanbnguyen on 6/10/2016.
 */
public class SymptomEntity extends Entity {
	public final static String SYMPTOM_LIST = "symptom_list";
	public final static String ID = "id";
	public final static String NAME = "name";
	public SymptomEntity(String id, String name) {
		super(id, name);
	}
}
