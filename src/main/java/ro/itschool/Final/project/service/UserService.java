package ro.itschool.Final.project.service;

import org.springframework.stereotype.Service;
import ro.itschool.Final.project.entity.MyUser;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    MyUser findUserByEmail(String email);

    MyUser findUserByUserName(String username);

    MyUser findUserByRandomToken(String randomToken);

    boolean findUserByUserNameAndPassword(String userName, String password);

    List<MyUser> findAll();

    void deleteById(long id);

    MyUser saveUser(MyUser u);

    MyUser updateUser(MyUser user);

    Optional<MyUser> findById(Long id);

    List<MyUser> searchUser(String keyword);

}