package net.cloudcentrik.beeforder.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private boolean active;
}
