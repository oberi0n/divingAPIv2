package org.diving.equipment.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.diving.equipment.api.vo.auth.UserConnection;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.cache.NoCache;

import io.quarkus.security.identity.SecurityIdentity;

@Tag(name = "Security")
@Path("/users")
//@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class UsersResource {

	@Inject
	SecurityIdentity identity;
	
/*	@Inject
    JsonWebToken jwt;*/

	
	@GET
	@Path("/me")
	@NoCache
	@Produces(MediaType.APPLICATION_JSON)
	@SecurityRequirement(name = "dev_timekeeperOAuth2", scopes = "role")
	//@RolesAllowed({"user","admin"})
	//@SecurityRequirement(name="jwt", scopes = {})
	public Response me() {
		UserConnection u = new UserConnection();
		u.setName(identity.getPrincipal().getName());
		u.setRole(identity.getRoles());
	
		return Response.ok(u, MediaType.APPLICATION_JSON).build();
		
	}
	
	
	@POST
	@Path("/authenticate")
	@NoCache
	@Produces(MediaType.APPLICATION_JSON)
	//@RolesAllowed({"user","admin"})
	//@SecurityRequirement(name="jwt", scopes = {})
	public Response authenticate() {
		
		UserConnection u = new UserConnection();
		u.setName(identity.getPrincipal().getName());
		u.setRole(identity.getRoles());
		
		return Response.ok(u, MediaType.APPLICATION_JSON).build();
		
		
		//return new User(identity);
	}

}
