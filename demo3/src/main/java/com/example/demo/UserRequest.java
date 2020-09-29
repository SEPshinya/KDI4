package com.example.demo;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
//ユーザー情報 リクエストデータ
@Data
public class UserRequest implements Serializable {
//名前
@NotNull(message="名前は必須項目です")
@Size(max=20,message="名前は全角２０文字以内で入力してください")
  public String name;

//住所
@NotNull(message="住所は必須項目です")
@Size(max=40,message="住所は全角４０文字以内で入力してください")
  public String address;

//電話
  public String tel;

//排除フラグ
  public String delete_flg;
}

