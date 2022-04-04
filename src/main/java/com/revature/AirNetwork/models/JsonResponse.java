package com.revature.AirNetwork.models;

import lombok.*;

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
