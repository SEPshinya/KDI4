package com.example.demo;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;



//更新データ
@Data
public class UserUpdateRequest implements Serializable {

	@NotEmpty(message="名前は必須項目です")
	@Size(min=2,max=20,message="名前は全角２０文字以内で入力してください")
	  public String name;

	//住所
	@NotEmpty(message="住所は必須項目です")
	@Size(min=2,max=40,message="住所は全角４０文字以内で入力してください")
	  public String address;
	//電話

	  public String tel;

	//排除フラグ
	  public String delete_flg;
	  //ユーザーID
	  private Long id;
}