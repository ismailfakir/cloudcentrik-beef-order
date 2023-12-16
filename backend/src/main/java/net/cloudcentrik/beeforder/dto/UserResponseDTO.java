package net.cloudcentrik.beeforder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.cloudcentrik.beeforder.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String name;
    private String mobile;
    private String email;
    private boolean active;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updatedAt;

    public static UserResponseDTO FromUser(User user) {
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getMobile(), user.getEmail(), user.isActive(), user.getCreatedAt(), user.getUpdatedAt()
        );
    }
}
