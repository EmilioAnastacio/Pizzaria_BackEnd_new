package br.com.projeto.pizzaria.service;

import br.com.projeto.pizzaria.config.JwtServiceGenerator;
import br.com.projeto.pizzaria.convert.FuncionarioDTOConvert;
import br.com.projeto.pizzaria.dto.LoginDTO;
import br.com.projeto.pizzaria.dto.UserDTO;
import br.com.projeto.pizzaria.convert.UsuarioDTOConvert;
import br.com.projeto.pizzaria.entity.User;
import br.com.projeto.pizzaria.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtServiceGenerator jwtService;

    @Autowired
    private UsuarioDTOConvert usuarioDTOConvert;

    @Autowired
    private FuncionarioDTOConvert funcionarioDTOConvert;

    @Autowired
    private AuthenticationManager authenticationManager;


    public UserDTO logar(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        User user = loginRepository.findByUsername(loginDTO.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return toUserDTO(user, jwtToken);
    }


    public UserDTO toUserDTO(User user, String token){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }


}
