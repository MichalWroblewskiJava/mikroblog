package com.example.mikroblog.mikroblog.model;

import lombok.Data;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class PostFrom {

    private String postContent;
}
