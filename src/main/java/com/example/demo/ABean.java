package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class ABean implements Serializable {
    String a;
    String bC;
    ArrayList<Object> listString;

}
