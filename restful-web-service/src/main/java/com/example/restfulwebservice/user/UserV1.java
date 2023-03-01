package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor     // 전체 필드를 생성하는 생성자
//@JsonIgnoreProperties(value={"password", "ssn"})
// @JsonFilter("UserInfo")
@NoArgsConstructor // default 생성자가 만들어짐
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity     // Proejct 실행 시 DB자동 생성
public class UserV1 {
    @Id     // Primary Key Annotation
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Name 은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요.")
    private Date joinDate;

    //@JsonIgnore
    @ApiModelProperty(notes = "사용자의 패스워드를 입력해 주세요.")
    private String password;

    //@JsonIgnore
    @ApiModelProperty(notes = "사용자의 주민등록번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "userv1")     // userv1 테이블에 있는 데이터를 1 : N로 가져옴.
    private List<Post> posts;

    // UserDAOService.java에서 에러가 발생해서 만들어줌
    public UserV1(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name=name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }

}