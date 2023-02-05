package dev.youcode.cvtheque.response;

import java.util.ArrayList;
import java.util.Collection;

public class DataResponse extends Response{
    private Collection data = new ArrayList();

    public DataResponse(String message, Integer status, Object data) {
        super(message, status);
        setData(data);
    }

    public DataResponse(String message, Object data) {
        super(message);
        setData(data);
    }

    public DataResponse() {
    }

    public Collection getData() {
        return data;
    }

    public void setData(Object data) {
        if(data instanceof Collection<?>){
            this.data = (Collection) data;
        }else {
            this.data.add(data);
        }
    }
}
