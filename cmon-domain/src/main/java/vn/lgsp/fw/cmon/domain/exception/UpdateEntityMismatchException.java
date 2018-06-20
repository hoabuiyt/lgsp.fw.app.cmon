package vn.lgsp.fw.cmon.domain.exception;

public class UpdateEntityMismatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public UpdateEntityMismatchException(Class<?> clazz, Long id, Long eid) {
        super(UpdateEntityMismatchException.generateMessage(clazz.getSimpleName(),id, eid));
    }

    private static String generateMessage(String entity, Long id, Long eid) {
        return String.format("Request id='%s' mismatch with entityid='%s'",id, eid);
    }

}
