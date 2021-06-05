package com.iordache.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserErrorMessage {

    private int status;
    private String message;
    private long timeStamp;

}
