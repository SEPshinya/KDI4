package com.example.demo;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;



//更新データ
@Data
@EqualsAndHashCode(callSuper=false)
public class UserUpdateRequest extends UserRequest implements Serializable {


private Long id;

}