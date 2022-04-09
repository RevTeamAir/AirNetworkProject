package com.revature.AirNetwork.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class JsonResponse {
    Boolean success;
    String message;
    Object data;
}
