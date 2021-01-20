package cn.oneseek.passport.repository;

import cn.oneseek.passport.domain.model.UserModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {
    public UserModel findByUsername(String username){
        return find("username", username).firstResult();
    }
}
