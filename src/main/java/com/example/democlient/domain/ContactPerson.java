package com.example.democlient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPerson {


    private String id;

    private String name;

    private String position;

    private String phone;

    private String email;

    private String company;

    private String department;

}
