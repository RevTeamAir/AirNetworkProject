package com.revature.AirNetwork.models;

import lombok.*;
import org.springframework.stereotype.Component;

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
