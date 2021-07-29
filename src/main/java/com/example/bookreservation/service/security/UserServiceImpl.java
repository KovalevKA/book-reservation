package com.example.bookreservation.service.security;

import com.example.bookreservation.dto.security.AuthRequestDTO;
import com.example.bookreservation.dto.security.RoleDTO;
import com.example.bookreservation.dto.security.UserDTO;
import com.example.bookreservation.entity.Status;
import com.example.bookreservation.entity.security.Role;
import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.RoleRepository;
import com.example.bookreservation.repository.UserRepository;
import com.example.bookreservation.security.jwt.JwtTokenProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AbstractMapper<Role, RoleDTO> roleMapper;
    private final AbstractMapper<User, UserDTO> mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(
        AbstractMapper<Role, RoleDTO> roleMapper,
        AbstractMapper<User, UserDTO> mapper,
        UserRepository userRepository,
        RoleRepository roleRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        JwtTokenProvider jwtTokenProvider) {
        this.roleMapper = roleMapper;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Map<Object, Object> login(AuthRequestDTO dto) {
        User user = getByName(dto.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException(
                "User with username " + dto.getUsername() + " not found");
        }
        String token = jwtTokenProvider.createToken(dto.getUsername(), user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", dto.getUsername());
        response.put("token", token);
        return response;
    }

    @Override
    public Map<Object, Object> registation(AuthRequestDTO dto) {
        if (getByName(dto.getUsername()) != null) {
            throw new EntityExistsException("Can't create user. Usermane exist");
        }
        UserDTO userDTO = new UserDTO(dto.getUsername(),
            bCryptPasswordEncoder.encode(dto.getPassword()));
        userDTO = create(userDTO);
        String token = jwtTokenProvider
            .createToken(userDTO.getUsername(), roleMapper.toEntities(userDTO.getRoles()));
        Map<Object, Object> response = new HashMap<>();
        response.put("username", dto.getUsername());
        response.put("token", token);
        return response;
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
            .orElseThrow(
                () -> new EntityNotFoundException("Can't found user with user.id = " + id));
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
