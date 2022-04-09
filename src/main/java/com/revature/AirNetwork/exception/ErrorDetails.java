package com.revature.AirNetwork.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Setter
@Getter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;


}
