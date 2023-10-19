package org.diving.equipment.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.event.Evenement;
import org.diving.equipment.api.vo.event.EvenementHistorique;
import org.diving.equipment.api.vo.event.ValidateEventRequest;
import org.diving.equipment.api.vo.utilisateur.Utilisateur;
import org.diving.equipment.provider.IEquipmentProvider;
import org.diving.equipment.provider.IEventProvider;
import org.diving.equipment.provider.IUtilisateurProvider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.cache.Cache;

@Tag(name = "Event")
@Path("/event")
//@Authenticated
//@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class EventAPI {

	@Inject
	IEventProvider eventProvider;

	@Inject
	IUtilisateurProvider utilisateurProvider;

	@Inject
	IEquipmentProvider equipmentProvider;

	@PUT
	@Path("/new")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Create an equipment lending for a specific user")
	@APIResponse(responseCode = "204", description = "Successfully create event")
	@Cache
	//@SecurityRequirement(name="jwt", scopes = {})
	public Response createEvent(ValidateEventRequest req) {

		Utilisateur u = utilisateurProvider.getUtilisateurById(req.getIdUtilisateur());

		Equipment e = equipmentProvider.getEquipmentByTagEquipement(req.getTagEquipment());
		
		if (e != null) {
			Evenement evExistant = eventProvider.searchEvent(u, e);
			if(evExistant == null) {
				Evenement ev = new Evenement();
				java.util.Date date = new java.util.Date();
				ev.setDateDebut(new Date(date.getTime()));
				ev.setDateFin(null);
				ev.setEquipment(e);
				ev.setUtilisateur(u);
				String ret = eventProvider.createEvent(ev);
				if("ko".equals(ret)) {
					return Response.status(404).build();
				}else
					return Response.status(204).build();
			} else 
				return Response.status(204).build();
		} else {
			return Response.status(404).build();
		}

	}

	@DELETE
	@Path("/delete")
	@RolesAllowed("admin")
	@Operation(summary = "Update the restitution date of an equipment for a user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "204", description = "Successfully update event", content = @Content(schema = @Schema(implementation = Equipment.class)))
	@APIResponse(responseCode = "404", description = "Equipment not found or event not found")
	@Cache
	//@SecurityRequirement(name="jwt", scopes = {})
	//@Authenticated
	public Response updateEvent(ValidateEventRequest req) {

		Utilisateur u = utilisateurProvider.getUtilisateurById(req.getIdUtilisateur());
		Equipment e = equipmentProvider.getEquipmentByTagEquipement(req.getTagEquipment());		
		
		if (e != null) {
			Evenement ev = eventProvider.searchEvent(u, e);
			if (ev != null) {
				java.util.Date date = new java.util.Date();
				ev.setDateFin(new Date(date.getTime()));
				// System.out.println(ev.getUtilisateur().getNom());
				eventProvider.updateEvent(ev);

				return Response.status(204).build();
			} else {
				return Response.status(404).build();
			}

		} else {
			return Response.status(404).build();

		}
	}

	@GET
	@Path("/historique")
	@RolesAllowed({ "user", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the specific historic for a dedicated equipment")
	@APIResponse(responseCode = "200", description = "Successfully return historic of equipment", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EvenementHistorique.class)))
	@APIResponse(responseCode = "404", description = "No content")
	@APIResponse(responseCode = "500", description = "Something went really wrong")
	@Cache
	public Response getHistoriqueByEquipment(@QueryParam(value = "tag") String tag) {
		try {
			List<EvenementHistorique> le = new ArrayList<EvenementHistorique>();
			
			Equipment e = equipmentProvider.getEquipmentByTagEquipement(tag);
			
			le = eventProvider.getHistoriqueByEquipment(e);
			if (le.size() == 0)
				return Response.status(404).build();
			else
				return Response.ok(le, MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			return Response.status(500).build();
		}
	}

	
}
