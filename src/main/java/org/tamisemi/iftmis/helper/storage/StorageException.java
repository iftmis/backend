package org.tamisemi.iftmis.helper.storage;

/**
 * @author : Nickyrabit
 **/
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
