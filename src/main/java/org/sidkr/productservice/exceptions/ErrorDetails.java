package org.sidkr.productservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDetails {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String details;
    private final String path;
    private List<ValidationErrors> validationErrorsList;
}
