package com.josh.usersrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServicesTest {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void saveUser()throws Exception{
        User user = new User();
        user.setId(1);
        user.setFirstName("tanvi");
        user.setLastName("surve");
        user.setBirthdate(LocalDate.parse("2009-10-19"));
        user.setEmail("teju@gmail.com");
        user.setPassword("teju123");
        User result = userRepository.save(user);
        System.out.println(new ObjectMapper().writeValueAsString(result));
        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());

    }

    @Test
    public void checkIfEmailExist(){
        String email = "Teju123@gmail.com";
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        assertNotNull(user);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void checkIfEmailNotExist(){
        String email = "teju@gmail.com";
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        assertNull(user);
    }

    @Test
    public void updateUser()throws  Exception{
        Optional<User> optionalUser = userRepository.findByEmail("teju1223@gmail.com");
        User user = optionalUser.get();
        System.out.println(new ObjectMapper().writeValueAsString(user));
        user.setFirstName("Tanvir");
        User editedUser = userRepository.save(user);
        System.out.println(new ObjectMapper().writeValueAsString(editedUser));
        assertThat(user.getId()).isEqualTo(editedUser.getId());
    }

    @Test
    public void userList()throws  Exception{
        List<User> userList = userRepository.findAll();
        System.out.println(new ObjectMapper().writeValueAsString(userList));
        assertThat(userList).size().isGreaterThan(0);
    }
}
