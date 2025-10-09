package com.foo.mod1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

  @Autowired
  protected UserService service;

  @GetMapping
  public Account findByUsername(String username) {
    return service.find(username);
  }
  
  @PostMapping("/login")
  public Map<String,Object> login(String username,String password){
    Map<String, Object> map = new HashMap<String, Object>();
    if(isValid(username, password)) {
      map.put("status", 200);
      map.put("success", true);
      map.put("username", "张三");
    }else {
      map.put("status", 400);
      map.put("success", false);
    }
    return map;
  }

  @PostMapping
  public Account save(Account a) {
    return service.save(a);
  }

  private boolean isValid(String name,String password) {
    return service.passwordCheck(name, password);
  }

}
