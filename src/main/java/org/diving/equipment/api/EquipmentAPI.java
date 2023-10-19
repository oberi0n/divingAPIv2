package org.diving.equipment.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.equipment.EquipmentNonDispo;
import org.diving.equipment.api.vo.equipment.EquipmentUtilisateur;
import org.diving.equipment.api.vo.equipment.ValidateEquipmentRequest;
import org.diving.equipment.api.vo.event.Evenement;
import org.diving.equipment.provider.IEquipmentProvider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.cache.Cache;

@Tag(name = "Equipment")
@Path("/equipment")
//@Authenticated
//@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class EquipmentAPI {

	@Inject
	IEquipmentProvider equipmentProvider;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"user", "admin"})
	//@SecurityRequirement(name = "dev_timekeeperOAuth2", scopes = "profile")
	@Operation(summary = "Get the global list of equipment")
	@APIResponse(responseCode = "200", description = "Successfully return equipments", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Equipment.class)))
	// @SecurityRequirement(name="jwt", scopes = {})
	public Response getListEquipment() {

		List<Equipment> lstEquip = new ArrayList<Equipment>();
		lstEquip = equipmentProvider.getAllEquipment();
		return Response.ok(lstEquip, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/detail")
	@RolesAllowed({ "user", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the details for a dedicated equipment using its tag")
	@APIResponse(responseCode = "200", description = "Successfully return equipment", content = @Content(schema = @Schema(implementation = Equipment.class)))
	@Cache
	@SecurityRequirement(name = "jwt", scopes = {})
	public Response getListEquipmentByTag(@QueryParam(value = "tag") String tag) {

		Equipment e = new Equipment();
		e = equipmentProvider.getEquipmentByTagEquipement(tag);
		/*
		 * lstEquip.add(new Equipment(1, "test", "marque", "taille", "numeroSerie"));
		 * lstEquip.add(new Equipment(2, "test123", "marque123", "12taille",
		 * "2513numeroSerie"));
		 */
		return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/dispo")
	@RolesAllowed({ "user", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the available equipments")
	@APIResponse(responseCode = "200", description = "Successfully return equipments", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Equipment.class)))
	@Cache
	//@SecurityRequirement(name = "jwt", scopes = {})
	public Response getListEquipmentDispo() {
		List<Equipment> e = new ArrayList<Equipment>();
		e = equipmentProvider.getEquipmentAvailable();
		/*
		 * lstEquip.add(new Equipment(1, "test", "marque", "taille", "numeroSerie"));
		 * lstEquip.add(new Equipment(2, "test123", "marque123", "12taille",
		 * "2513numeroSerie"));
		 */
		return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/nondispo")
	@RolesAllowed({ "user", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the borrowed equipments")
	@APIResponse(responseCode = "200", description = "Successfully return equipments", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EquipmentNonDispo.class)))
	@APIResponse(responseCode = "404", description = "No equipment found")
	@Cache
	//@SecurityRequirement(name = "jwt", scopes = {})
	public Response getListEquipmentNonDispo() {
		List<EquipmentNonDispo> e = new ArrayList<EquipmentNonDispo>();
		e = equipmentProvider.getEquipmentBorrowed();
		if (e.isEmpty())
			return Response.status(404).build();
		else
			return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/utilisateur")
	@RolesAllowed({ "user", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the specific equipment for a dedicated user")
	@APIResponse(responseCode = "200", description = "Successfully return equipment for a specific user", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EquipmentUtilisateur.class)))
	@APIResponse(responseCode = "404", description = "No content")
	@APIResponse(responseCode = "500", description = "Something went really wrong")
	@Cache
	//@SecurityRequirement(name = "jwt", scopes = {})
	public Response getListEquipmentByUser(@QueryParam(value = "tag") String tag) {
		try {
			List<EquipmentUtilisateur> e = new ArrayList<EquipmentUtilisateur>();
			e = equipmentProvider.getEquipmentByTagUtilisateur(tag);
			if (e.size() == 0)
				return Response.status(404).build();
			else
				return Response.ok(e, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}

	}
	
	

	@PUT
	@Path("/new")
	@RolesAllowed("admin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Create an equipment")
	@APIResponse(responseCode = "204", description = "Successfully created")
	@Cache
	//@SecurityRequirement(name = "jwt", scopes = {})
	public Response createEquipment(ValidateEquipmentRequest req) {
		Equipment e = new Equipment();
		e.setIdEquipment(0);
		e.setLibelle(req.getLibelle());
		e.setMarque(req.getMarque());
		e.setNumeroSerie(req.getNumeroSerie());
		e.setTag(req.getTag());
		e.setTaille(req.getTaille());
		e.setPhotoB64(req.getPhotoB64());
		equipmentProvider.createEquipment(e);
		return Response.status(204).build();

	}

	@DELETE
	@Path("/delete/{tag}")
	@RolesAllowed("admin")
	@Operation(summary = "Delete an equipment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "204", description = "Successfully delete equipment")
	@APIResponse(responseCode = "404", description = "Equipment not found")
	@Cache
	//@SecurityRequirement(name = "jwt", scopes = {})
	public Response deleteEquipment(@PathParam(value = "tag") String tag) {

		Equipment e = equipmentProvider.getEquipmentByTagEquipement(tag);

		if (e != null) {
			equipmentProvider.deleteEquipment(e);
			return Response.status(204).build();
		} else {
			return Response.status(404).build();
		}

	}

}
