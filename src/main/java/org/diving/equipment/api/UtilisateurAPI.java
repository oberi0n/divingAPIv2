package org.diving.equipment.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.diving.equipment.api.vo.utilisateur.Utilisateur;
import org.diving.equipment.provider.IUtilisateurProvider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.cache.Cache;
//import org.eclipse.microprofiScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
//publicateurProvider utilisateurProvider;

@Tag(name = "User")
@Path("/utilisateur")
//@Authenticated
//@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class UtilisateurAPI {
	
	@Inject
	IUtilisateurProvider utilisateurProvider;
	
	@GET
	@Path("/all")
	@RolesAllowed("user")
	@Operation(summary = "Get the global list of the users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Successfully return users", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Utilisateur.class)))
	@Cache
//	@SecurityRequirement(name="jwt", scopes = {})
	public Response getListUtilisateur() {
		List<Utilisateur> e = new ArrayList<Utilisateur>();
		e = utilisateurProvider.getAllUtilisateurs();
		return Response.ok(e, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/licence")
	@RolesAllowed("user")
	@Operation(summary = "Get the details for a dedicated user using its name")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Successfully return equipment", content = @Content(schema = @Schema(implementation = Utilisateur.class)))
	@APIResponse(responseCode = "404", description = "No content")
	@Cache
//	@SecurityRequirement(name="jwt", scopes = {})
	public Response getListEquipmentByUrl(@QueryParam(value = "url") String url) {

		Utilisateur e = new Utilisateur();
		e = utilisateurProvider.getUtilisateurByUrl(url);
		if(e == null)
			return Response.status(404).build();
		else 
			return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/id")
	@RolesAllowed("user")
	@Operation(summary = "Get the details for a dedicated user using its name")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Successfully return equipment", content = @Content(schema = @Schema(implementation = Utilisateur.class)))
	@APIResponse(responseCode = "404", description = "No content")
	@Cache
//	@SecurityRequirement(name="jwt", scopes = {})
	public Response getUtilisateurById(@QueryParam(value = "id") Integer id) {

		Utilisateur e = new Utilisateur();
		e = utilisateurProvider.getUtilisateurById(id);
		if(e == null)
			return Response.status(404).build();
		else 
			return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	
	@GET
	@Path("/detail")
	@RolesAllowed("user")
	@Operation(summary = "Get the details for a dedicated user using its name")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Successfully return equipment", content = @Content(schema = @Schema(implementation = Utilisateur.class)))
	@Cache
//	@SecurityRequirement(name="jwt", scopes = {})
	public Response getListEquipmentByTag(@QueryParam(value = "nom") String nom) {

		Utilisateur e = new Utilisateur();
		e = utilisateurProvider.getUtilisateurByName(nom);

		return Response.ok(e, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/equipment")
	@RolesAllowed("user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get the specific user for a dedicated equipment")
	@APIResponse(responseCode = "200", description = "Successfully return user for a specific equipment", content = @Content(schema = @Schema(implementation = Utilisateur.class)))
	@APIResponse(responseCode = "404", description = "No content")
	@APIResponse(responseCode = "500", description = "Something went really wrong")
	@Cache
	//@SecurityRequirement(name="jwt", scopes = {})
	public Response getUtilisateurByEquipment(@QueryParam(value = "tag") String tag) {
		try {
			Utilisateur u = new Utilisateur();
			u = utilisateurProvider.getUtilisateurByTagEquipment(tag);
			return Response.ok(u, MediaType.APPLICATION_JSON).build();
		} catch (IndexOutOfBoundsException ie) {
			return Response.status(404).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}

	}
}
