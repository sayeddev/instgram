package com.sayedlabs.Instgram.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    @Length(max = 100)
    private String name;

    @Indexed(unique = true)
    @Length(max = 100)
    private  String userName;

    @Length(max = 100)
    private String website;

    @Length(max = 100)
    private String email;
    @Length(max = 20)
    private String phone;

    @Length(max = 100)
    private String password;

    private Gender gender;

    @Length(max = 100)
    private String pictureName;

}
