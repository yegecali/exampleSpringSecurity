package com.yegecali.security.service;

import com.yegecali.security.model.Role;
import com.yegecali.security.model.UserAuth;
import com.yegecali.security.repository.RolaRepository;
import com.yegecali.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private RolaRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = userRepository.findByUsername(username);
        if(user == null){
            log.error("el usuario no se encuentra en la base de datos");
            throw new UsernameNotFoundException("el usuario no se encuentra en la base de datos");
        } else {
            log.info("el usuario se encuentra en la base de datos: {}" + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswaord(), authorities);
    }

    @Override
    public UserAuth saveUser(UserAuth user) {
        user.setPasswaord(passwordEncoder.encode(user.getPasswaord()));
        log.info("guardando usuario");
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("guardando role");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("guardando role en usuario");
        UserAuth user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public UserAuth getUser(String username) {
        log.info("obteniendo usuario");
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserAuth> getAll() {
        log.info("obteniendo todos los usuario");
        return userRepository.findAll();
    }

}
