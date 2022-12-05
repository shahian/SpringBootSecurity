package com.shahian.springBootSecurity.servic;

import com.shahian.springBootSecurity.model.RegistrationDTO;
import com.shahian.springBootSecurity.model.Role;
import com.shahian.springBootSecurity.model.User;
import com.shahian.springBootSecurity.repository.RoleRepository;
import com.shahian.springBootSecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository personRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NullPointerException("this is null"));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) {
        User user1 = User.builder()
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
        userRepository.save(user1);

    }

    @Transactional
    public User updateUser(Long id, User person) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
//        Person person1=personObj;
        user.setAge(person.getAge());
        user.setFirstName(person.getFirstName());
        user.setLastName(person.getLastName());
        user.setUserName(person.getUserName());
        user.setPassword(person.getPassword());
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%S' Not Found!!!!!", username));
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        }

    }

    public User registration(RegistrationDTO registrationDTO) {
        List<Role> roles = new ArrayList<>();
        try {
            roles.add(roleRepository.getByName(Role.ROLE_USER));
        } catch (Exception e) {

        }
        return registration(registrationDTO,roles);
    }

    public User registration(RegistrationDTO registrationDTO, List<Role> roles) {
        User user = User.builder()
                .firstName(registrationDTO.firstName())
                .userName(registrationDTO.userName())
                .lastName(registrationDTO.lastName())
                .age(registrationDTO.age())
                .password(passwordEncoder.encode(registrationDTO.password()))
                .roles(roles)
                .build();
        userRepository.save(user);
        return user;
    }
}
