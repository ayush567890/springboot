package com.Restdemo.RestDemo.Service;

import com.Restdemo.RestDemo.Repository.UserRepository;
import com.Restdemo.RestDemo.dto.Createuserdto;
import com.Restdemo.RestDemo.dto.Userdto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Userdto> getallusers() {
        return this.userRepository.findall();
    }

    public Userdto getuserbyid(String id) {
        return userRepository.finduserbyid(id);
    }

    public Userdto createuser(Createuserdto createuserdto) {
        return userRepository.save(createuserdto);
    }

    public Userdto updateuser(Createuserdto updateuserdto,String id){
        if (userRepository.finduserbyid(id)==null){
            return null;
        }

        return userRepository.update(updateuserdto,id);
    }

    public void deleteuserbyid(String id) {
        userRepository.deleteuserbyid(id);
    }
}
