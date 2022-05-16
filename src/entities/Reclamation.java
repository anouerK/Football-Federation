/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Rahmouni
 */
public class Reclamation {
	private int idr;
	private String objet;
	private String desc;
	private String status;

	public Reclamation() {
	}

	public Reclamation(String objet, String descR, String status) {
		this.objet = objet;
		this.desc = descR;
		this.status = status;
	}

	public Reclamation(int id, String objet, String descR, String status) {
		this.idr = id;
		this.objet = objet;
		this.desc = descR;
		this.status = status;
	}

	public Reclamation(int id, String objet, String status) {
		this.idr = id;
		this.objet = objet;
		this.status = status;
	}

	public int getId() {
		return idr;
	}

	public void setId(int id) {
		this.idr = id;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String descR) {
		this.desc = descR;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}