package org.diving.equipment.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.diving.equipment.api.vo.token.NotificationsToken;
import org.diving.equipment.provider.INotificationsProvider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.cache.Cache;

@Tag(name = "Notifications")
@Path("/notification")
public class NotificationsAPI {

	@Inject
	INotificationsProvider nt;

	@PUT
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Add the token to be notified")
	@APIResponse(responseCode = "204", description = "Successfully added")
	@Cache
	public Response createToken(NotificationsToken token) {
		token.setIdUtilisateur(0);
		nt.addToken(token);
		return Response.status(204).build();
	}


}
