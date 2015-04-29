/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elsibay.vertx;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/**
 *
 * @author Tarek El-Sibay
 */
public class ExtendedJsonObject extends JsonObject {
	
	public ExtendedJsonObject() {
		super();
	}
	
	public ExtendedJsonObject(Map<String,Object> map) {
		super(map);
	}
	
	public void setStatus(String status) {
		put("status", status);
	}
	
	public String getStatus() {
		return getString("status");
	}
}
