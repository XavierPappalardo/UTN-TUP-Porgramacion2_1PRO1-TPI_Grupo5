package Exceptions;

/**
 * Se lanza cuando se intenta crear o editar un Usuario con un mail
 * que ya existe en la colección (el mail debe ser único según las reglas
 * de negocio definidas en la consigna).
 *
 * Ejemplo de uso:
 *   throw new MailDuplicadoException("juan@gmail.com");
 */
public class MailDuplicadoException extends Exception {

    private final String mail;

    /**
     * @param mail El correo electrónico que ya estaba registrado.
     */
    public MailDuplicadoException(String mail) {
        super("El mail '" + mail + "' ya está registrado. Ingrese uno diferente.");
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
