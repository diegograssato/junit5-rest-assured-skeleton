package com.automation.factory;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class User {

    private String name;
    private String password;
}
