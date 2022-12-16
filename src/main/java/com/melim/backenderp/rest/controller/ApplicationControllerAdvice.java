package com.melim.backenderp.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.melim.backenderp.exception.RegraNegocioException;
import com.melim.backenderp.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleResponseStatusException(ResponseStatusException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
}
