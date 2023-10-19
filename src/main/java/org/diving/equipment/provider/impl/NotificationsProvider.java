package org.diving.equipment.provider.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.diving.equipment.api.vo.token.NotificationsToken;
import org.diving.equipment.provider.INotificationsProvider;

@ApplicationScoped
public class NotificationsProvider implements INotificationsProvider {

	@Inject
	EntityManager em;
	
	@Transactional
	@Override
	public Integer addToken(NotificationsToken token) {
		
		Integer r = 0;
		
		List<NotificationsToken> lstToken = new ArrayList<NotificationsToken>();
		Query q = em.createNativeQuery("select * from equipment.NotificationsToken where token like '" + token.getToken() + "';");
		lstToken = (List<NotificationsToken>) q.getResultList();
		
		if(lstToken.isEmpty()) {
			em.persist(token);
			/*r = em.createNativeQuery("INSERT INTO NotificationsToken (idNotificationsToken, token, idUtilisateur) VALUES (?,?,?)")
				      .setParameter(1, 0)
				      .setParameter(2, token.getToken())
				      .setParameter(3, token.getIdUtilisateur())
				      .executeUpdate();*/
		}
		
		return r;
		
	}



}
