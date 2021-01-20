package cn.oneseek.passport.rest;

import cn.oneseek.passport.domain.model.JwtResult;
import cn.oneseek.passport.domain.model.UserSession;
import cn.oneseek.passport.service.JwtRbacService;
import cn.oneseek.passport.service.UserService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @Inject
    JwtRbacService jwtRbacService;

    @Inject
    UserService userService;
    @POST
    @Transactional
    public JwtResult login(@QueryParam("username") String username, @QueryParam("password") String password){
        LOG.infov("{0},{1}",username , password);

        userService.login(username, password);
        return jwtRbacService.createWithUserSession(UserSession.of(username));
    }
}
