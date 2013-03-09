package br.ufpb.so.pageReplacement;

/**
 * @author Joao Helis Bernardo
 *
 */
public class PageNotFoundException extends PageReplacementException {

	private static final long serialVersionUID = -8387296496056251697L;

	public PageNotFoundException(String message) {
		super(message);
	}
}
