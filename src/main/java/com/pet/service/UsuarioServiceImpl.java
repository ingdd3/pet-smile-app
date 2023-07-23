package com.pet.service;

import com.pet.model.User;
import com.pet.repository.IUserRepository;
import com.pet.util.PasswordEncoderUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("userService")
public class UsuarioServiceImpl {

    @Resource
    private final IUserRepository userRepository;

    public UsuarioServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, String> verifyLogin(String username, String password) {
        Map<String, String> result = new HashMap<>();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(username.isEmpty() || password.isEmpty()){
            result.put("Campos vacíos: ", "Verifica que los campos no sean vacíos.");
            return result;
        }
        if (userOptional.isEmpty()) {
            result.put("Usuario no encontrado: ", "Verifica el nombre de usuario ingresado.");
            return result;
        }
        User user = userOptional.get();
        if (!PasswordEncoderUtil.checkPassword(password, user.getPassword())) {
            result.put("Contraseña incorrecta", "Verifica la contraseña ingresada.");
            return result;
        }
        return result;
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }
}