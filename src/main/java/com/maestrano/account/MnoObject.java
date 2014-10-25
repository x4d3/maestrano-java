package com.maestrano.account;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.maestrano.net.MnoApiAccountClient;

class MnoObject {
	public Map<String,Object> changedAttributes;
	public Map<String,Object> orginalAttributes;
	
	public MnoObject() {
		changedAttributes = new HashMap<String,Object>();
		orginalAttributes = new HashMap<String,Object>();
	}
	
	public String toString() {
		return MnoApiAccountClient.GSON.toJson(this);
	}
	
	protected void changeAttribute(String attrName, Object value) {
		try {
			
			Field f = this.getClass().getDeclaredField(attrName);
			Object currentVal = f.get(this);
			f.set(this,value);
			
			if (this.orginalAttributes.get(attrName) == null) {
				this.orginalAttributes.put(attrName, currentVal);
			}
			
			if (this.orginalAttributes.get(attrName) != null 
					&& this.orginalAttributes.get(attrName).equals(value)) {
				this.changedAttributes.remove(attrName);
				this.orginalAttributes.remove(attrName);
			} else {
				this.changedAttributes.put(attrName, value);
			}
		
		} catch (Exception e) {}
	}
	
	protected void merge(Object obj) {
		Field[] fs = this.getClass().getDeclaredFields();
		
		for (Field f : fs) {
			try {
				f.set(this,f.get(obj));
			} catch (Exception e) {}
		}
	}
}
