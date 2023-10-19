package org.diving.equipment.api.vo.token;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NotificationsToken")
public class NotificationsToken implements Serializable{
	
	@Id
	private Integer idNotificationsToken;
	private String token;
	private Integer idUtilisateur;
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdNotificationsToken() {
		return idNotificationsToken;
	}

	public void setIdNotificationsToken(Integer idNotificationsToken) {
		this.idNotificationsToken = idNotificationsToken;
	}



	public NotificationsToken(Integer idNotificationsToken, String token, Integer idUtilisateur) {
		super();
		this.idNotificationsToken = idNotificationsToken;
		this.token = token;
		this.idUtilisateur = idUtilisateur;
	}

	public NotificationsToken() {
		super();
	}

}
