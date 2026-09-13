package com.Restdemo.RestDemo.Service;

import com.Restdemo.RestDemo.entity.User;
import com.Restdemo.RestDemo.repository.UserRepository;
import com.Restdemo.RestDemo.dto.Createuserdto;
import com.Restdemo.RestDemo.dto.Userdto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RedisTemplate<String,Object> redistemplate;


    public UserService(UserRepository userRepository,RedisTemplate<String,Object> redistemplate) {
        this.userRepository = userRepository;
        this.redistemplate=redistemplate;
    }

    public void saveuser(String id,String name){
        redistemplate.opsForValue().set(id,name);
    }

    public String getuser(String id){
        return (String)redistemplate.opsForValue().get(id);
    }

    public User createuser(Createuserdto dto){
        User user = new User();
        user.setname(dto.getName());
        user.setemail(dto.getEmail());

        User saved = userRepository.save(user);
        redistemplate.opsForValue().set("user:" + saved.getId(),saved);

        return saved;
    }

    public List<User> getallusers(){
        return userRepository.findAll();
    }

    public User getuserbyid(UUID id){
        String key = "user:" + id;

        User cacheduser = (User)redistemplate.opsForValue().get(key);

        if(cacheduser != null){
            System.out.println("Data from redis");
            return cacheduser;
        }

        User dbuser = userRepository.findById(id).orElse(null);

        if(dbuser != null){
            redistemplate.opsForValue().set(key,dbuser);
            System.out.println("Data from postgresql");
        }

        return dbuser;
    }

    public void deleteuser(UUID id){
        userRepository.deleteById(id);
        redistemplate.delete("user:" + id);
    }
}

// checking changes.
