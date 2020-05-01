package SEF_HR_APP.backend;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class ServiceTemplate<T, K> extends Service<K> {

    public abstract void setValues(T value);
    public abstract K getResponse();

}