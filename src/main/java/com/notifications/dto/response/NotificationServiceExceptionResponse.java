package com.notifications.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NotificationServiceExceptionResponse {
    private int statusCode;
    private String StatusMsg;
    private LocalDateTime errorTimeStamp;
    private String path;
}
