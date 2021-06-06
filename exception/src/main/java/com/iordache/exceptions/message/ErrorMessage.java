package com.iordache.exceptions.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private int status;
    private String message;
    private long timeStamp;

}
