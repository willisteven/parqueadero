package com.ceiba.parqueadero.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Se produjo un error en el servicio")
public class VigilanteInternalServerErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public VigilanteInternalServerErrorException(String errorMessage) {
		super(errorMessage);

	}
}
