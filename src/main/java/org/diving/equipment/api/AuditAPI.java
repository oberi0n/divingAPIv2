package org.diving.equipment.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Audit")
@Path("/ping")
//@Authenticated
public class AuditAPI {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @SecurityRequirement(name = "dev_timekeeperOAuth2", scopes = "role")
    @RolesAllowed("user")
    //@PermitAll
    public String pingPong() {
        return "pong";
    }
}