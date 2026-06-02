package utils;

import dto.RegistrationRequestDTO;
import dto.UserDTO;
import org.learning_management_system.showcase.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    public static User registrationRequestToUser(RegistrationRequestDTO registrationRequestDTO) {
        User user = new User();
        user.setUsername(registrationRequestDTO.getUsername());
        user.setEmail(registrationRequestDTO.getEmail());
        user.setPassword(registrationRequestDTO.getPassword());
        return user;
    }

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setMatrikelNumber(user.getMatrikelNumber());
        return userDTO;
    }
}
