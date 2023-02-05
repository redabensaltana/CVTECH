package dev.youcode.cvtheque.user;


import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private  final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder, final UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("userId"));
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long userId) {
        return userRepository.findById(userId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        final User user = new User();
        mapToEntity(userDTO, user);

        Long id  = userRepository.save(user).getUserId();

        return id;
    }

    public void update(final Long userId, final UserDTO userDTO) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }
    // how to fix this error .map not working with the object type of User just working with type option or a list of array when I get user by findByEmail, the functionname's findByEmail is not working with the object type of User
//    public void delete(final Long userId) {
//        final User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException());
//        userRepository.delete(user);
//    }
    public User findUserByEmail(final String email) {
       return userRepository.findByEmail(email) ;
    }

    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserTitle(user.getUserTitle());
        userDTO.setAddress(user.getAddress());
        userDTO.setTel(user.getTel());
        userDTO.setLinkden(user.getLinkden());
        userDTO.setGithub(user.getGithub());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserTitle(userDTO.getUserTitle());
        user.setAddress(userDTO.getAddress());
        user.setTel(userDTO.getTel());
        user.setLinkden(userDTO.getLinkden());
        user.setGithub(userDTO.getGithub());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

}
