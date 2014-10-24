package com.maestrano.account;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.maestrano.net.MnoApiAccountResource;

public class MnoBill extends MnoObject {
	
	public String id;
	public Date createdAt;
	public Date updatedAt;
	public String status;
	public Float units;
	public Date periodStartedAt;
	public Date periodEndedAt;

	// Mandatory for creation
	public String groupId;
	public Integer priceCents;
	public String currency;
	public String description;
	
	/**
	 * Return all application bills
	 * @return list of bills
	 * @throws IOException
	 */
	public static List<MnoBill> all() throws IOException {
		return MnoApiAccountResource.all(MnoBill.class);
	}
	
	/**
	 * Return all bills matching the criteria passed in argument
	 * @param params
	 * @return list of bills
	 * @throws IOException
	 */
	public static List<MnoBill> all(Map<String,String> params) throws IOException {
		return MnoApiAccountResource.all(MnoBill.class, params);
	}
	
	/**
	 * Retrieve a single bill by id
	 * @param billId
	 * @return a bill if found, null otherwise
	 * @throws IOException
	 */
	public static MnoBill retrieve(String billId) throws IOException {
		return MnoApiAccountResource.retrieve(MnoBill.class, billId);
	}
	
	/**
	 * Create a new Bill
	 * @param params
	 * @return created bill
	 * @throws IOException
	 */
	public static MnoBill create(Map<String,Object> params) throws IOException {
		return MnoApiAccountResource.create(MnoBill.class, params);
	}
	
	/**
	 * Cancel the bill
	 * @return whether the bill was cancelled or not
	 * @throws IOException 
	 */
	public Boolean cancel() throws IOException {
		if (this.id != null && !this.id.isEmpty()) {
			MnoBill newBill = MnoApiAccountResource.delete(MnoBill.class, this.id);
			this.merge(newBill);
			return this.status.equals("cancelled");
		}
		
		return false;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Float getUnits() {
		return units;
	}
	
	public void setUnits(Float units) {
		this.changeAttribute("units", units);
	}
	
	public Date getPeriodStartedAt() {
		return periodStartedAt;
	}
	
	public void setPeriodStartedAt(Date periodStartedAt) {
		this.changeAttribute("periodStartedAt", periodStartedAt);
	}
	
	public Date getPeriodEndedAt() {
		return periodEndedAt;
	}
	
	public void setPeriodEndedAt(Date periodEndedAt) {
		this.changeAttribute("periodEndedAt", periodEndedAt);
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.changeAttribute("groupId", groupId);
	}
	
	public Integer getPriceCents() {
		return priceCents;
	}
	
	public void setPriceCents(Integer priceCents) {
		this.changeAttribute("priceCents", priceCents);
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.changeAttribute("currency", currency);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.changeAttribute("description", description);
	}
	
	
	
}