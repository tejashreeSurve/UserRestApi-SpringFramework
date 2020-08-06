package com.josh.usersrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.usersrestapi.model.UserEntity;
import com.josh.usersrestapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

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
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("tanvi");
        userEntity.setLastName("surve");
        userEntity.setBirthdate(LocalDate.parse("2009-10-19"));
        userEntity.setEmail("teju@gmail.com");
        userEntity.setPassword("teju123");
        UserEntity result = userRepository.save(userEntity);
        System.out.println(new ObjectMapper().writeValueAsString(result));
        assertThat(result.getFirstName()).isEqualTo(userEntity.getFirstName());

    }

    @Test
    public void checkIfEmailExist(){
        String email = "teju123@gmail.com";
        UserEntity userEntity = userRepository.findByEmail(email);
        assertNotNull(userEntity);
        assertThat(userEntity.getEmail()).isEqualTo(email);
    }

    @Test
    public void checkIfEmailNotExist(){
        String email = "teju@gmail.com";
        UserEntity userEntity = userRepository.findByEmail(email);
        assertNull(userEntity);
    }

    @Test
    public void updateUser()throws  Exception{
        UserEntity userEntity = userRepository.findByEmail("tanvi@gmail.com");
        System.out.println(new ObjectMapper().writeValueAsString(userEntity));
        userEntity.setFirstName("Tanvir");
        UserEntity editedUser = userRepository.save(userEntity);
        System.out.println(new ObjectMapper().writeValueAsString(editedUser));
        assertThat(userEntity.getId()).isEqualTo(editedUser.getId());
    }

    @Test
    public void userList()throws  Exception{
        List<UserEntity> userList = userRepository.findAll();
        System.out.println(new ObjectMapper().writeValueAsString(userList));
        assertThat(userList).size().isGreaterThan(0);
    }
}
