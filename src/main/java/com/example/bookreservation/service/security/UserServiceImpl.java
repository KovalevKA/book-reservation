package com.example.bookreservation.service.security;

import com.example.bookreservation.dto.security.UserDTO;
import com.example.bookreservation.entity.Status;
import com.example.bookreservation.entity.security.Role;
import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.RoleRepository;
import com.example.bookreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AbstractMapper<User, UserDTO> mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            AbstractMapper<User, UserDTO> mapper,
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> result = userRepository.findAll();
        return mapper.toDTOs(result);
    }

    @Override
    public UserDTO getById(Long id) {
        return mapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found user with user.id = " + id))
        );
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found user with user.id = " + id));
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }


    @Override
    public UserDTO create(UserDTO dto) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        User newUser = mapper.toEntity(dto);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(roles);
        newUser.setStatus(Status.ACTIVE);
        User regUser = userRepository.save(newUser);
        return mapper.toDTO(regUser);
    }

    @Override
    public UserDTO editById(Long id, UserDTO dto) throws IllegalAccessException {
        throw new IllegalAccessException("Can't be use");
    }
}
