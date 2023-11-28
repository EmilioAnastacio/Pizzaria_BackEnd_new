package br.com.projeto.pizzaria.service;

import br.com.projeto.pizzaria.convert.FuncionarioDTOConvert;
import br.com.projeto.pizzaria.dto.LoginDTO;
import br.com.projeto.pizzaria.convert.UsuarioDTOConvert;
import br.com.projeto.pizzaria.entity.Login;
import br.com.projeto.pizzaria.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioDTOConvert usuarioDTOConvert;

    @Autowired
    private FuncionarioDTOConvert funcionarioDTOConvert;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = loginRepository.findByUsername(username);

        return  new User(userDetails.getUsername(), userDetails.getPassword(), true,true,true,true,userDetails.getAuthorities());
    }

    public LoginDTO criar(LoginDTO loginDTO){

        Login login = toLogin(loginDTO);

        loginRepository.save(login);

        return toLoginDTO(login);
    }

    public List<LoginDTO> buscarTodos(){
        List<Login> loginListBanco = loginRepository.findAll();
        List<LoginDTO> loginDTOList = new ArrayList<>();

        for(int i = 0; i< loginListBanco.size(); i++){
            loginDTOList.add(toLoginDTO(loginListBanco.get(i)));
        }

        return loginDTOList;
    }

    /*
    public List<LoginDTO> findByNome(String nome){
        List<Login> loginList = loginRepository.findUserNameByNomeDeLogin(nome);
        List<LoginDTO> loginDTOList = new ArrayList<>();

        for(int i = 0; i < loginList.size(); i++){
            loginDTOList.add(toLoginDTO(loginList.get(i)));
        }
        return loginDTOList;
    }
*/

    public LoginDTO editar(Long id, LoginDTO loginDTO){
        Login loginBanco = this.loginRepository.findById(id).orElse(null);

        Assert.isTrue(loginBanco != null, "Login nao encontrado");

        loginRepository.save(toLogin(loginDTO));

        return loginDTO;
    }

    public String deletar(Long id){
        Login loginBanco = this.loginRepository.findById(id).orElse(null);

        Assert.isTrue(loginBanco != null, "Login nao encontrado");

        loginRepository.delete(loginBanco);

        return "Login deletado com sucesso";
    }

    public Login toLogin(LoginDTO loginDTO){
        Login login = new Login();

        login.setId(loginDTO.getId());
        login.setUsername(loginDTO.getUsername());
        login.setPassword(loginDTO.getPassword());
        //login.setUsuario(usuarioDTOConvert.convertUsuarioDTOToUsuario(loginDTO.getUsuarioDTO()));
        //login.setFuncionario(funcionarioDTOConvert.convertFuncionarioDTOToFuncionario(loginDTO.getFuncionarioDTO()));
        return login;
    }

    public LoginDTO toLoginDTO(Login login){
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setId(login.getId());
        loginDTO.setUsername(login.getUsername());
        loginDTO.setPassword(login.getPassword());
        //loginDTO.setUsuarioDTO(usuarioDTOConvert.convertUsuarioToUsuarioDTO(login.getUsuario()));
        //loginDTO.setFuncionarioDTO(funcionarioDTOConvert.convertFuncionarioToFuncionarioDTO(login.getFuncionario()));
        return loginDTO;
    }


}
