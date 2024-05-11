package com.firebase.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Employee {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean isDeleted;
}
