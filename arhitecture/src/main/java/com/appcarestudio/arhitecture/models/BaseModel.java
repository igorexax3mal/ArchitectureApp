package com.appcarestudio.arhitecture.models;



import java.io.Serializable;

/**
 * Created by IGOR on 20.03.2018.
 */

public class BaseModel implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

}
