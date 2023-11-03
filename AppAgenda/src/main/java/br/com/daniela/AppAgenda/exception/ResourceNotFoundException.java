package br.com.daniela.AppAgenda.exception;

public class ResourceNotFoundException  extends RuntimeException{

  public ResourceNotFoundException(String message) {
		super(message);
	}

}
