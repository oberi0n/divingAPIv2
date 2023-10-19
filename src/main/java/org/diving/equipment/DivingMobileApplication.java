package org.diving.equipment;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@OpenAPIDefinition(
        tags = {
                @Tag(name="Equipment", description="Equipment management"),
                @Tag(name="Security", description="Security Management"),
                @Tag(name="Audit", description="Audit"),
                @Tag(name="User", description="Users management"),
                @Tag(name="Event", description="Events management"),
                @Tag(name="Notifications", description="Notifications management"),
        },
        
       // security = {
        //	@SecurityRequirement(name = "Bearer"),
//        	@SecurityRequirement(name = "OpenID")
  //      },
        servers = {
        		@Server(url ="http://82.64.133.26:12378",description = "Production server - external http"),
        		@Server(url ="http://5.196.225.93:12378",description = "Test server - external http"),
        		@Server(url ="https://oberi0n.xyz:12379",description = "Test server - external https"),
        		@Server(url ="http://192.168.1.45:12378",description = "Production server - internal"),
        		@Server(url ="http://localhost:12378",description = "Local Development server")
        		},
        info = @Info(
                title="Diving Mobile API",
                version = "0.1.1",
                contact = @Contact(
                        name = "Nicolas NOEL",
                        url = "https://",
                        email = "ncls.noel@gmail.com"),
                license = @License(
                        name = "Nicolas NOEL",
                        url = "https://www..com"))
//        ,
//        security = {
//		        @SecurityRequirement( name = "dev_timekeeperOAuth2" ,scopes = { "profile", "role"})}
        
) 
/*@SecurityScheme(
        securitySchemeName = "dev_timekeeperOAuth2",
        type = SecuritySchemeType.OAUTH2,
        description = "authentication for OAuth2 access",
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://192.168.1.113:8080/auth/realms/quarkus/protocol/openid-connect/auth")
        )
)*/
public class DivingMobileApplication extends Application {
	  @Produces
	  public ObjectMapper getMapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    return mapper;
	  }
}
