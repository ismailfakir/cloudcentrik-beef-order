package net.cloudcentrik.beeforder.Controller;

import net.cloudcentrik.beeforder.db.UserRepository;
import net.cloudcentrik.beeforder.dto.UserLoginDTO;
import net.cloudcentrik.beeforder.dto.UserRequestDTO;
import net.cloudcentrik.beeforder.dto.UserResponseDTO;
import net.cloudcentrik.beeforder.model.User;
import net.cloudcentrik.beeforder.utils.BeefOrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static net.cloudcentrik.beeforder.utils.BeefOrderUtils.passwordHash;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") String id) {

        return BeefOrderUtils.tryCatchWrap(() -> {
            Optional<User> userData = userRepository.findById(id);

            if (userData.isPresent()) {
                User u = userData.get();
                return new ResponseEntity<>(UserResponseDTO.FromUser(u), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        });
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {

        return BeefOrderUtils.tryCatchWrap(() -> {

            Optional<User> userData = userRepository.findByEmail(userRequestDTO.getEmail());

            if (userData.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {

                User newUser = userRepository.save(convertToUserForSave(userRequestDTO));
                return new ResponseEntity<>(UserResponseDTO.FromUser(newUser), HttpStatus.CREATED);
            }
        });

    }

    @PostMapping("/users/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {

        return BeefOrderUtils.tryCatchWrap(() -> {

            Optional<User> userData = userRepository.findByEmail(userLoginDTO.getEmail());

            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                User existingUser = userData.get();
                boolean isPasswordCorrect = existingUser.getPassword().equals(passwordHash(userLoginDTO.getPassword()));
                if (isPasswordCorrect) {
                    return new ResponseEntity<>(UserResponseDTO.FromUser(existingUser), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }
        });

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") String id, @RequestBody UserRequestDTO userRequestDTO) {

        return BeefOrderUtils.tryCatchWrap(() -> {

            Optional<User> userData = userRepository.findById(id);

            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                User newUser = userRepository.save(convertToUserForUpdate(userRequestDTO,userData.get()));
                return new ResponseEntity<>(UserResponseDTO.FromUser(newUser), HttpStatus.CREATED);
            }
        });

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {

        return BeefOrderUtils.tryCatchWrap(() -> {

            Optional<User> userData = userRepository.findById(id);

            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        });

    }

    private User convertToUserForSave(UserRequestDTO userRequestDTO){
        return User.builder()
                .name(userRequestDTO.getName())
                .mobile(userRequestDTO.getMobile())
                .email(userRequestDTO.getEmail())
                .password(passwordHash(userRequestDTO.getPassword()))
                .active(userRequestDTO.isActive())
                .createdAt(BeefOrderUtils.timeNow())
                .updatedAt(BeefOrderUtils.timeNow())
                .build();
    }

    private User convertToUserForUpdate(UserRequestDTO userRequestDTO,User existingUser){
        return User.builder()
                .id(existingUser.getId())
                .name(userRequestDTO.getName())
                .mobile(userRequestDTO.getMobile())
                .email(userRequestDTO.getEmail())
                .password(passwordHash(userRequestDTO.getPassword()))
                .active(userRequestDTO.isActive())
                .createdAt(existingUser.getCreatedAt())
                .updatedAt(BeefOrderUtils.timeNow())
                .build();
    }


}
