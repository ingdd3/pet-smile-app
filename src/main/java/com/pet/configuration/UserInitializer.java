package com.pet.configuration;

import com.pet.model.Person;
import com.pet.model.User;
import com.pet.repository.IPersonRepository;
import com.pet.repository.IUserRepository;
import com.pet.util.PasswordEncoderUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@Order(1)
public class UserInitializer {

    private final IUserRepository userRepository;

    private final IPersonRepository personRepository;

    public UserInitializer(IUserRepository userRepository, IPersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void init() {
        Person person = new Person(1L,"Amelia", "Doe", "1234567890", LocalDate.now());
        User user = new User();
        user.setUsername("amelia@pet.com");
        user.setPassword(PasswordEncoderUtil.encodePassword("12345678"));
        user.setPerson(person);
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            this.personRepository.save(person);
            this.userRepository.save(user);
        }
    }

}
