package cn.oneseek.passport.rest;


import cn.oneseek.passport.ApplicationConfiguration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusResource {

    @Inject
    ApplicationConfiguration appConfig;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
        return "ok";
    }

    @GET
    @Path("app-version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAppVersion() {
        return appConfig.getVersion();
    }

}
