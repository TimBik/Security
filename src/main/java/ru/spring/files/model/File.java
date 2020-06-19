package ru.spring.files.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class File {
    private Long id;
    private String fileName;
}
