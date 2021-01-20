package cn.oneseek.passport.service;

import cn.oneseek.passport.domain.model.UserModel;
import cn.oneseek.passport.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public void register(String username, String password){
        if(userRepository.findByUsername(username)!=null){
            throw new WebApplicationException("用户名已存在");
        }
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        password = UserModel.encryptPassword(password);
        userModel.setPassword(password);
        userRepository.persist(userModel);
    }

    @Transactional
    public void login(String username, String password){
        UserModel userModel = userRepository.findByUsername(username);
        if(!UserModel.encryptPassword(password).equals(userModel.getPassword())){
            throw new WebApplicationException("The password is incorrect");
        }
    }
}
