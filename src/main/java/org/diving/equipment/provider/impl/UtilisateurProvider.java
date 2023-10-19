package org.diving.equipment.provider.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.diving.equipment.api.vo.utilisateur.Utilisateur;
import org.diving.equipment.provider.IUtilisateurProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@ApplicationScoped
public class UtilisateurProvider implements IUtilisateurProvider {

	@Inject
	EntityManager em;
	
	@Transactional
	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		
		
		Query q = em.createNativeQuery("select * from Utilisateur where visible like 'P';", Utilisateur.class);
		
		return q.getResultList();
	}

	@Transactional
	@Override
	public Utilisateur getUtilisateurByName(String nom) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Utilisateur> q = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> c = q.from(Utilisateur.class);
		// ParameterExpression<String> p = cb.parameter(String.class);
		q.select(c).where(cb.like(c.get("nom"), nom + "%"));
		TypedQuery<Utilisateur> query = em.createQuery(q);

		Utilisateur e = new Utilisateur();
		/*
		 * Query query = em.
		 * createQuery("select idEquipment,libelle, marque, taille,numeroSerie, tag  from equipment where tag = ?1"
		 * ); query.setParameter(1, tag) ;
		 */

		e = (Utilisateur) query.getResultList().get(0);
		return e;
	}

	@Transactional
	@Override
	public Utilisateur getUtilisateurById(Integer id) {

		Utilisateur e = em.find(Utilisateur.class, id);

		return e;
	}

	@Transactional
	@Override
	public Utilisateur getUtilisateurByTagEquipment(String tag) {

		Query query = em.createNativeQuery(
				"SELECT u.* FROM equipment.Evenement ev inner join equipment.Utilisateur u on u.idUtilisateur = ev.Utilisateur_idUtilisateur inner join equipment.Equipment e on e.idEquipment = ev.Equipment_idEquipment where e.tag like ?1 and dateFin is null",
				Utilisateur.class);
		query.setParameter(1, tag);

		return (Utilisateur) query.getResultList().get(0);
	}

	@Transactional
	@Override
	public Utilisateur getUtilisateurByUrl(String url) {

		try {
			
			//Désactivation de la securité
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }

	            @Override
	            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	                // Not implemented
	            }

	            @Override
	            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	                // Not implemented
	            }
	        } };

			SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			
			
			URL url2 = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) url2.openConnection();
			con.setRequestMethod("GET");
			
			int status = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			in.close();
			con.disconnect();
			

			Document doc = Jsoup.parseBodyFragment(content.toString());
		    String link = doc.select("div > p").first().html();
		    
		    System.out.println("-------------- " + link);
			
		    
		    String nomPrenom[] = link.split(" ");
		    
		    // On cherche en DB parmi les résultats trouvés 
		    String whereClause = "where "; for (int i = 1; i < nomPrenom.length; i++) { whereClause += "nom like ?" + i; if ((i + 1) < nomPrenom.length) { whereClause += " or "; } } 
		    String sql = "SELECT u.* FROM equipment.Utilisateur u " + whereClause;
			 
			// Exécution de la requête 
		    Query query = em.createNativeQuery(sql,Utilisateur.class); 
		    for (int i = 1; i < nomPrenom.length; i++) { 
		    	String param = nomPrenom[i]; 
		    	query.setParameter(i, param); 
		    }
			 
			 // On ne retourne que le premier résultat return (Utilisateur)
			 return (Utilisateur)query.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			// client.close();
		}

	}

}
