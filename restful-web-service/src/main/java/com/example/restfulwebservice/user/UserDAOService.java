package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDAOService {
    private static List<UserV1> users = new ArrayList<>();
    private static int usersCount = 3;

    static{
        users.add(new UserV1(1, "Kenneth", new Date(), "pass1", "111111-11111111"));
        users.add(new UserV1(2, "Alice", new Date(), "pass1", "111111-11111112"));
        users.add(new UserV1(3, "Elena", new Date(), "pass1", "111111-11111113"));
    }
    public List<UserV1> findAll(){
        return users;
    }

    public UserV1 save(UserV1 user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public UserV1 updateUser(int id, UserV1 user) {
        UserV1 updateUser = findOne(id);

        if (findOne(id) != null) {
            updateUser.setName(user.getName());
            updateUser.setJoinDate(new Date());
            return updateUser;
        }
        return null;
    }


    public UserV1 findOne(int id){
        for(UserV1 user: users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public UserV1 deleteById(int id){
        Iterator<UserV1> iterator = users.iterator();
        while(iterator.hasNext()){
            UserV1 user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
