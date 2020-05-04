package SEF_HR_APP.backend;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * 
 * @param <T> information sent from frontend to backend
 * @param <K> information sent from backend to frontend
 */
public abstract class ServiceTemplate<T, K> extends Service<K> {

    /**
     * Set the data that is handled by the backend
     * @param value
     */
    public abstract void setValues(T value);
    /**
     * Get the response data from the backend
     * @return
     */
    public abstract K getResponse();

}