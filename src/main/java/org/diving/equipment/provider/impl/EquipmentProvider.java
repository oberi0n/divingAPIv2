 package org.diving.equipment.provider.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.equipment.EquipmentNonDispo;
import org.diving.equipment.api.vo.equipment.EquipmentUtilisateur;
import org.diving.equipment.api.vo.event.Evenement;
import org.diving.equipment.provider.IEquipmentProvider;

@ApplicationScoped
public class EquipmentProvider implements IEquipmentProvider {

	@Inject
	EntityManager em;

	@Transactional
	@Override
	public List<Equipment> getAllEquipment() {
		List<Equipment> lstEquip = new ArrayList<Equipment>();
		Query q = em.createNativeQuery("select * from equipment.Equipment;");
		lstEquip = q.getResultList();
		return lstEquip;
	}

	@Transactional
	@Override
	public Equipment getEquipmentByTagEquipement(String tag) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Equipment> q = cb.createQuery(Equipment.class);
			Root<Equipment> c = q.from(Equipment.class);
			// ParameterExpression<String> p = cb.parameter(String.class);
			q.select(c).where(cb.like(c.get("tag"), tag));
			TypedQuery<Equipment> query = em.createQuery(q);

			Equipment e = new Equipment();
			
			e = (Equipment) query.getResultList().get(0);
			return e;	
		}catch(IndexOutOfBoundsException ie) {
			return null;
		}
		
	}

	@Transactional
	@Override
	public Equipment getEquipmentById(Integer id) {
		
		Equipment e = em.find(Equipment.class,id);
		
		return e;
	}

	@Transactional
	@Override
	public List<EquipmentUtilisateur> getEquipmentByTagUtilisateur(String tag) {
		
		Query query = em.createNativeQuery("SELECT e.*, ev.dateDebut FROM equipment.Evenement ev inner join equipment.Utilisateur u on u.idUtilisateur = ev.Utilisateur_idUtilisateur inner join equipment.Equipment e on e.idEquipment = ev.Equipment_idEquipment where u.tag like ?1 and dateFin is null", EquipmentUtilisateur.class); 
		query.setParameter(1, tag) ;
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Equipment> getEquipmentAvailable() {
		//Query query = em.createNativeQuery("SELECT e.* FROM equipment.Equipment e where e.idEquipment not in (SELECT ev.Equipment_idEquipment FROM equipment.Evenement ev where ev.dateFin is null)", Equipment.class);
		Query query = em.createNativeQuery("SELECT e.idEquipment, e.libelle, e.marque, e.taille, e.numeroSerie, e.dateAchat, e.dateDernierEntretien, e.dateRequalif, e.fabriquant, e.numeroAffiche, e.statutTIV, e.tag, '' photoB64 FROM equipment.Equipment e where e.idEquipment not in (SELECT ev.Equipment_idEquipment FROM equipment.Evenement ev where ev.dateFin is null);", Equipment.class);
		//Query query = em.createNativeQuery("SELECT e.* FROM equipment.Evenement ev inner join equipment.Utilisateur u on u.idUtilisateur = ev.Utilisateur_idUtilisateur inner join equipment.Equipment e on e.idEquipment = ev.Equipment_idEquipment where ev.dateFin is not null", Equipment.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public List<EquipmentNonDispo> getEquipmentBorrowed() {
		Query query = em.createNativeQuery("SELECT e.idEquipment, e.libelle, e.marque, e.taille, e.numeroSerie, e.dateAchat, e.dateDernierEntretien, e.dateRequalif, e.fabriquant, e.numeroAffiche, e.statutTIV, e.tag, '' photoB64, u.nom, u.prenom, ev.dateDebut FROM equipment.Evenement ev inner join equipment.Utilisateur u on u.idUtilisateur = ev.Utilisateur_idUtilisateur inner join equipment.Equipment e on e.idEquipment = ev.Equipment_idEquipment where ev.dateFin is null", EquipmentNonDispo.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void createEquipment(Equipment e) {
		em.persist(e);		
	}
	
	@Transactional
	@Override
	public void deleteEquipment(Equipment e) {
		
		em.createNativeQuery("delete from Equipment where idEquipment = ?")
        .setParameter(1, e.getIdEquipment())
        .executeUpdate();
		
	}


	
}
