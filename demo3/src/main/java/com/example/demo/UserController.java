package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
//ユーザー情報 Service

  @Autowired
  UserService userService;

//一覧画面を表示----------------------------------------------------------------------------------------------------
  @GetMapping(value = "/list")
  public String List(@PageableDefault(page=0,size=10)Pageable pageable,Model model) {
	Page<User> userPage=userService.getUser(pageable);
    model.addAttribute("page",userPage);
    model.addAttribute("userlist",userPage.getContent());
    return "/list";
  }

//検索機能----------------------------------------------------------------------------------------------------------
  @RequestMapping(value ="/list/serch",method = RequestMethod.GET)
  //@GetMapping("{id}")
  public String findUsers(@PageableDefault(page=0,size=10)Pageable pageable,@PathVariable String address, Model model) {
    Page<User> userPage= userService.findUsers(address,pageable);
    model.addAttribute("page",userPage);
    model.addAttribute("userlist",userPage.getContent());
    return "/list/serch";
  }

//新規登録画面を表示------------------------------------------------------------------------------------------------
  @RequestMapping(value = "/add",method = RequestMethod.GET)
  public String Add(Model model) {
    model.addAttribute("userRequest", new UserRequest());
    return "/add";
  }

//新規登録確認画面を表示
  @RequestMapping(value = "/addcheck",method = RequestMethod.POST)
  public String addcheck(@ModelAttribute("UserRequest")UserRequest form) {
    return "/addcheck";
  }
  @RequestMapping(value = "create", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute UserRequest userRequest, Model model) {
    // ユーザー情報の登録
    userService.create(userRequest);
    return "redirect:/list";
  }


//編集画面を表示-----------------------------------------------------------------------------------------------------
  @RequestMapping(value ="/edit/{id}",method = RequestMethod.GET)
  //@GetMapping("{id}")
  public String edit(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("userUpdateRequest", user);
    return "edit";
  }

//編集確認画面
  @RequestMapping(value = "/editcheck",method = RequestMethod.POST)
  public String editcheck(@ModelAttribute("UserUpdateRequest")UserUpdateRequest form) {
    return "/editcheck";
  }
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model, UserRequest UserRequest) {
    // ユーザー情報の登録
    userService.update(userUpdateRequest);
    return "redirect:/list";
  }

  //排除---------------------------------------------------------------------------------------------------------------
  @RequestMapping(value ="/delete/{id}",method = RequestMethod.GET)
  //@GetMapping("{id}")
  public String delete(@PathVariable Long id, Model model ) {
    User user = userService.findById(id);
    model.addAttribute("UserUpdateRequest", user);
    return "/delete";
  }
  @RequestMapping(value = "deleteflg", method = RequestMethod.POST)
  public String delete(@ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model, UserRequest UserRequest) {
    // ユーザー情報の削除（delete_flg1）
    userService.deleteflg(userUpdateRequest);
    return "redirect:/list";
  }

}
//--------------------------------------------------------------------------------------------------------------------