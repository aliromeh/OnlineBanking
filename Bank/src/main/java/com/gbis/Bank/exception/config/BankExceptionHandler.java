package com.gbis.Bank.exception.config;

import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gbis.Bank.exceptions.HttpErrorDetails;
import com.gbis.Bank.exceptions.InsufficientFunds;
import com.gbis.Bank.exceptions.NotFoundException;
import com.gbis.Bank.utils.Constants;

@ControllerAdvice
@RestController
public class BankExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({ InsufficientFunds.class })
	public ResponseEntity<Object> handleIsufficientFundsException(InsufficientFunds ex, WebRequest request) {
		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.FORBIDDEN, ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.NOT_FOUND, ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), request.getDescription(false));
		return handleExceptionInternal(ex, httpErrorDetails, headers, httpErrorDetails.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = Constants.MISSING_PARAMETER + ex.getParameterName();
		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.BAD_REQUEST, error,
				request.getDescription(false));
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(Constants.METHOD_NOT_SUPPORTED);
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.METHOD_NOT_ALLOWED,
				ex.getLocalizedMessage(), builder.toString());
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(Constants.MEDIA_NOT_SUPPORTED);
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + Constants.OBLIGATION + ex.getRequiredType().getName();

		HttpErrorDetails httpErrorDetails = new HttpErrorDetails(Instant.now(), HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				error, request.getDescription(false));
		return new ResponseEntity<Object>(httpErrorDetails, new HttpHeaders(), httpErrorDetails.getStatus());
	}

}
