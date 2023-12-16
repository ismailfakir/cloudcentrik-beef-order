package net.cloudcentrik.beeforder.dto;

import lombok.Data;

@Data
public class BookUpdateRequestDTO {

    private String id;

    private String name;

    private String description;

    private String isbn;

    private String authorName;

}