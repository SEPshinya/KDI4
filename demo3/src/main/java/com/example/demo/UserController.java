package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

//検索機能
  @GetMapping(value ="/serch")
  public String serch(@PageableDefault(page=0,size=10)Pageable pageable,@RequestParam String address,Model model) {
    Page<User> user = userService.serch(address,pageable);
    model.addAttribute("page",user);
    model.addAttribute("userlist", user.getContent());
    return "/serch";
  }

//新規登録画面を表示------------------------------------------------------------------------------------------------
  @RequestMapping(value = "/add",method = RequestMethod.GET)
  public String Add(Model model) {
    model.addAttribute("userRequest", new UserRequest());
    return "/add";
  }

//新規登録確認画面を表示
  @RequestMapping(value = "/addcheck",method = RequestMethod.POST)
  public String addcheck(@Validated @ModelAttribute("UserRequest")UserRequest form, BindingResult result,User user ,Model model) {
	    if (result.hasErrors()) {
	        List<String> errorList = new ArrayList<String>(); //エラー表示
	        for (ObjectError error : result.getAllErrors()) {
	          errorList.add(error.getDefaultMessage());
	        }
	        model.addAttribute("validationError", errorList);
	        model.addAttribute("userRequest", new UserRequest());
	        return "add";
	      }else{
	    	  return "/addcheck";
	      }
  }
  //登録
  @RequestMapping(value = "create", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute UserRequest userRequest, Model model) {
    userService.create(userRequest);
    return "redirect:/list";
  }

//編集画面を表示-----------------------------------------------------------------------------------------------------
  @RequestMapping(value ="/edit/{id}",method = RequestMethod.GET)
  public String edit(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("userUpdateRequest", user);
    return "edit";
  }

//編集確認画面
  @RequestMapping(value = "/editcheck",method = RequestMethod.POST)
  public String editcheck(@Validated @ModelAttribute("UserUpdateRequest")UserUpdateRequest form,BindingResult result,Long id, Model model) {
	    if (result.hasErrors()) {
	        List<String> errorList = new ArrayList<String>(); //エラー表示
	        for (ObjectError error : result.getAllErrors()) {
	          errorList.add(error.getDefaultMessage());
	        }
			model.addAttribute("validationError", errorList);
	        model.addAttribute("userUpdateRequest",new UserUpdateRequest());
	        return "/edit";
	      }else{
	    	 return "editcheck";
	      }
  }

  // ユーザー情報の登録
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model, UserRequest UserRequest) {
    userService.update(userUpdateRequest);
    return "redirect:/list";
  }

  //排除---------------------------------------------------------------------------------------------------------------
  @RequestMapping(value ="/delete/{id}",method = RequestMethod.GET)
  public String delete(@PathVariable Long id,Model model ) {
    User user = userService.findById(id);
    model.addAttribute("UserUpdateRequest", user);
    return "/delete";
    }

  @RequestMapping(value = "deletecommit", method = RequestMethod.POST)
  public String deletecommit(@ModelAttribute("userUpdateRequest")UserUpdateRequest form,UserUpdateRequest userUpdateRequest) {
    userService.deletecommit(userUpdateRequest);
    return "redirect:/list";
  }

}
