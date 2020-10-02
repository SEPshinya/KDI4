package com.example.demo;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
//ユーザー情報 リクエストデータ
@Data
public class UserRequest implements Serializable {
//名前
@NotEmpty(message="名前は必須項目です")
@Size(max=20,message="名前は全角２０文字以内で入力してください")
  public String name;

//住所
@NotEmpty(message="住所は必須項目です")
@Size(max=40,message="住所は全角４０文字以内で入力してください")
  public String address;

//電話
 @Pattern(regexp = "^([0-9]{3}-[0-9]{4}-[0-9]{4}|)$" , message = "000-0000-0000の形式で入力してください")
  public String tel;



//排除フラグ
  public String delete_flg;
}

