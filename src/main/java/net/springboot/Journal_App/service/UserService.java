package net.springboot.Journal_App.service;

import lombok.extern.slf4j.Slf4j;
import net.springboot.Journal_App.entity.User;
import net.springboot.Journal_App.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e){
            log.info("error has occurred {}",user.getUserName(), e);
            return false;
        }

    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

   public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

}
//mongodb+srv://imvasi27:PmNqysHj9UN2i2wo@cluster0.pq5qy3u.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0