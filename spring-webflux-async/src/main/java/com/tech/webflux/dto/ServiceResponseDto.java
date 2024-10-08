package com.tech.webflux.dto;

import org.springframework.http.HttpHeaders;

import com.tech.webflux.util.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ServiceResponseDto<T> {

	private T responseObject;
    private HttpHeaders responseHTTPHeaders;
    private Constants.StatusCodes status;

}
