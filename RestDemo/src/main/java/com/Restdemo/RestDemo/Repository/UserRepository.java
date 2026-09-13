package com.Restdemo.RestDemo.Repository;

import com.Restdemo.RestDemo.dto.Createuserdto;
import com.Restdemo.RestDemo.dto.Userdto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserRepository {

    List<Userdto>users = new ArrayList<>();

    public UserRepository() {
        users.add(new Userdto(UUID.randomUUID().toString(), "Ayush", "Ayush@test.com"));
        users.add(new Userdto(UUID.randomUUID().toString(), "Rohan", "Rohan@test.com"));
        users.add(new Userdto(UUID.randomUUID().toString(), "Rohit", "Rohit@test.com"));
    }

    public List<Userdto> findall() {
        return this.users;
    }
    public Userdto finduserbyid(String id){
        for (Userdto user:users){
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }

        return null;
    }


    public Userdto save(Createuserdto createuserdto) {
        Userdto user = new Userdto(UUID.randomUUID().toString(),createuserdto.getName(),createuserdto.getEmail());
        users.add(user);
        return user;

    }

    public Userdto update(Createuserdto updateuserdto, String id) {
        for (Userdto user : users){
            if (user.getId().equals(id)){
                user.setEmail(updateuserdto.getEmail());
                user.setName(updateuserdto.getName());
            }

            return user;
        }

        return null;
    }


    public void deleteuserbyid(String id) {
        users.removeIf((Userdto userdto) -> userdto.getId().equals(id));
    }
}
