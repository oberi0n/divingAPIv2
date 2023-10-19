package org.diving.equipment.provider.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.event.Evenement;
import org.diving.equipment.api.vo.event.EvenementHistorique;
import org.diving.equipment.api.vo.utilisateur.Utilisateur;
import org.diving.equipment.provider.IEventProvider;

@ApplicationScoped
public class EventProvider implements IEventProvider {

	@Inject
	EntityManager em;

	@Override
	@Transactional
	public String createEvent(Evenement e) {
		Query query = em.createNativeQuery("select ev.* from Evenement ev where ev.Equipment_idEquipment = ?1 and ev.dateFin is null", Evenement.class );
		query.setParameter(1, e.getEquipment());
		List<Evenement> le = new ArrayList<Evenement>();
		le = query.getResultList();
		if(le.isEmpty()) {
			em.persist(e);
			return "ok";
		}else
			return "ko";
		
	}

	@Override
	@Transactional
	public void updateEvent(Evenement e) {
		//em.merge(e);
		Query query = em.createNativeQuery("update Evenement ev set ev.dateFin = ?3 where ev.Utilisateur_idUtilisateur = ?1 and ev.Equipment_idEquipment = ?2 and ev.dateFin is null", Evenement.class );
		
		query.setParameter(1, e.getUtilisateur().getIdUtilisateur());
		query.setParameter(2, e.getEquipment().getIdEquipment());
		query.setParameter(3, e.getDateFin());

		query.executeUpdate();
	}

	@Override
	@Transactional
	public Evenement searchEvent(Utilisateur utilisateur, Equipment equipment) {
		try {
			//Query query = em.createQuery("select event from Evenement event where utilisateur = ?1 and equipment = ?2 and dateFin is null" );
			Query query = em.createNativeQuery("select ev.* from Evenement ev where ev.Utilisateur_idUtilisateur = ?1 and ev.Equipment_idEquipment = ?2 and ev.dateFin is null", Evenement.class );
			
			query.setParameter(1, utilisateur);
			query.setParameter(2, equipment);

			Evenement e = new Evenement();
			e = (Evenement) query.getResultList().get(0);
			
			return e;			
		}catch(IndexOutOfBoundsException ie) {
			return null;
		}

	}
	
	@Override
	public List<EvenementHistorique> getHistoriqueByEquipment(Equipment equipment) {
		try {
			
			Query query = em.createNativeQuery("select ev.* from Evenement ev where ev.Equipment_idEquipment = ?2", EvenementHistorique.class );
			
			query.setParameter(2, equipment);

			List<EvenementHistorique> e = new ArrayList<EvenementHistorique>();
			e = query.getResultList();
			
			return e;			
		}catch(IndexOutOfBoundsException ie) {
			return null;
		}
	}

}
