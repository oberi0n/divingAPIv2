package org.diving.equipment.provider;

import java.util.List;

import org.diving.equipment.api.vo.utilisateur.Utilisateur;

public interface IUtilisateurProvider {
	
	public List<Utilisateur> getAllUtilisateurs();

	public Utilisateur getUtilisateurByName(String nom);
	
	public Utilisateur getUtilisateurById(Integer id);
	
	public Utilisateur getUtilisateurByTagEquipment(String tag);
	
	public Utilisateur getUtilisateurByUrl(String url);
	
}
