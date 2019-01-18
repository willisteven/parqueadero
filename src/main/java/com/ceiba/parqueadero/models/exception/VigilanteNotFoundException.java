package com.ceiba.parqueadero.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "El registro no existe")

public class VigilanteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public VigilanteNotFoundException(String errorMessage) {
		super(errorMessage);

	}
}
