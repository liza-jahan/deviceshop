package com.example.deviceshop.model.response;


import com.example.deviceshop.dto.ErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private String status;
    private HttpStatus code;
    private String dateTime;
    private List<ErrorDTO> errors;
    private T results;
    public void setStatus(String status) {
        this.status = status;
    }

}
