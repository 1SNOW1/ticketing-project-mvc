package com.cydeo.dto;


import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{

    @NotBlank //not blank, no space only, no empty string
    @Size(max = 15 , min = 2)
    private String firstName;

    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Pattern(regexp = ("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")  )
    private String PassWord;

    @NotBlank
    @Email
    private String userName;


    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @NotNull
    private RoleDTO role;

    @NotNull
    private Gender gender;
}
/*
    Has minimum 8 characters in length. Adjust it by modifying {8,}
    At least one uppercase English letter. You can remove this condition by removing (?=.*?[A-Z])
    At least one lowercase English letter.  You can remove this condition by removing (?=.*?[a-z])
    At least one digit. You can remove this condition by removing (?=.*?[0-9])
    At least one special character,  You can remove this condition by removing (?=.*?[#?!@$%^&*-])
    private String passWord;
    */