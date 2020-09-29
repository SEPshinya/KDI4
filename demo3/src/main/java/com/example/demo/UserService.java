package com.example.demo;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//ユーザー情報 Service
@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

  @Autowired
  private UserRepository userRepository;

//住所一覧 全検索
  public Page<User> getUser(Pageable pageable){
    return userRepository.findAll(pageable);
  }
//住所一覧 住所検索
public Page<User> serch(String address,Pageable pageable){
	    return userRepository.findUsers(address,pageable);
	  }


//新規登録
  public void create(UserRequest UserRequest) {
    User user = new User();
    user.setName(UserRequest.getName());
    user.setAddress(UserRequest.getAddress());
    user.setTel(UserRequest.getTel());
    user.setDelete_flg("0");
    userRepository.save(user);
  }

//編集登録
  public void update(UserUpdateRequest userUpdateRequest) {
    User user = findById(userUpdateRequest.getId());
    user.setAddress(userUpdateRequest.getAddress());
    user.setName(userUpdateRequest.getName());
    user.setTel(userUpdateRequest.getTel());
    user.setDelete_flg("0");
    userRepository.save(user);
  }

//削除
  public void deleteflg(UserUpdateRequest userUpdateRequest) {
    User user = findById(userUpdateRequest.getId());
    user.setAddress(userUpdateRequest.getAddress());
    user.setName(userUpdateRequest.getName());
    user.setTel(userUpdateRequest.getTel());
    user.setDelete_flg("1");
    userRepository.save(user);
  }

//ID検索
  public User findById(Long id){
    return userRepository.findById(id).get();
  }
}

