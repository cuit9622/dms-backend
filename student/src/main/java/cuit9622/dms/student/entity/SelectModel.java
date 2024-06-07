package cuit9622.dms.student.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class SelectModel implements Serializable {

    Integer page;


    Integer pageSize;


    String name;

}
