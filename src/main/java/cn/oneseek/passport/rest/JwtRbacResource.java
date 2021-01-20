package cn.oneseek.passport.rest;

import cn.oneseek.passport.domain.model.Email;
import cn.oneseek.passport.domain.model.JwtResult;
import cn.oneseek.passport.domain.model.JwtToken;
import cn.oneseek.passport.domain.model.UserSession;
import cn.oneseek.passport.service.JwtRbacService;
import io.smallrye.jwt.auth.principal.ParseException;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static cn.oneseek.passport.utils.Emails.isValidEmailAddress;


/**
 * 提供有关 jwt 授权相关的应用（REST）接口。
 * TODO:
 */
@Path("/jwt-rbac")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JwtRbacResource {

    private static final Logger LOG = Logger.getLogger(JwtRbacResource.class);

    @Inject
    JwtRbacService jwtRbacService;

    /**
     * 该方法用于刷新激活码 token 的过期时间。
     * TODO: add validation process
     *
     * @param token 之前获得的 token
     * @return 返回一个有效期更新以后的 token，如原来的 token 距离失效还有很长的时间，则可能返回原来的 token。
     * @throws ParseException token 解析失败错误
     */
    @POST
    @Path("refresh/user-session")
    public JwtResult refreshJwtToken(JwtToken token) throws ParseException {
        LOG.infov("Request refresh Jwt - token: {0}", token);

        return jwtRbacService.refreshToken(token.token);
    }

    /**
     * 该方法用于提供 user 相关的 jwt 授权。
     * TODO: add key check
     *
     * @param userSession 代表 用户会话
     * @return 返回相应的 jwt。
     */
    @POST
    @Path("user-session")
    public JwtResult getUserSessionJwt(UserSession userSession) {
        LOG.infov("Request UserSession Jwt - userSession: {0}", userSession);

        if (userSession == null || userSession.getUserName().trim().length() == 0) {
            throw new WebApplicationException(
                    "Should contain a not-empty activationKey in request.", Response.Status.BAD_REQUEST);
        }

        return jwtRbacService.createWithUserSession(userSession);
    }

    /**
     * 该方法用于提供针对 仅提供邮箱地址相关的 jwt 授权。注意，这往往是一个比较短暂的临时授权。
     * TODO: support send email to grant token
     * @param email 用于授权的实体邮箱地址
     * @return 返回相应的临时 jwt
     */
    @POST
    @Path("email-only")
    public JwtResult getEmailOnlyJwt(Email email) {
        LOG.infov("Request EmailOnly Jwt - email: {0}", email);

        if (email == null || email.address.trim().length() == 0) {
            throw new WebApplicationException(
                    "Should contain an email parameter in request.", Response.Status.BAD_REQUEST);
        }

        if (!isValidEmailAddress(email.address)) {
            throw new WebApplicationException(
                    "The email address format isn't valid - " + email.address, Response.Status.BAD_REQUEST);
        }

        return jwtRbacService.createWithEmailOnly(email.address);
    }

}