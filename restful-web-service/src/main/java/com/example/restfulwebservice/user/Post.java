package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id     // Primary Key
    @GeneratedValue     // 자동으로 생성될 수 있는 Annotation
    private Integer id;

    private String description;

    // User : Post -> 1 : N : (0 ~ N), Main : Sub -> Parent : Child
    @ManyToOne(fetch = FetchType.LAZY)      // 현재 POST라는 Entity 요청에서 많은 요청이 올 수 있는데 여기서 하나만 오도록 설정(N : 1)
    @JsonIgnore
    private UserV1 userv1;
}