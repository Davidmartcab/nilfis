package com.nilfis.nilfis.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class BaseErrorResponse implements Serializable {
    private String status;
    private Integer error_code;
}
