package org.diving.equipment.api;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		//System.out.println("ceci est un test CORS");
/*        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.putSingle("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH, HEAD");
        headers.putSingle("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");*/
    }

}
