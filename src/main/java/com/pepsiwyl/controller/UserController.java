package com.pepsiwyl.controller;

import com.pepsiwyl.pojo.User;
import com.pepsiwyl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 15:05
 */

@Slf4j

@Controller("userController")
@RequestMapping(name = "用户控制器", path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping(name = "登陆控制器", path = "/login")
    public ModelAndView login(User user, HttpSession session) {
        log.info("username:" + user.getUsername() + " password: " + user.getPassword());
        User login = userService.login(user);
        if (login != null) {
            // 登陆成功
            session.setAttribute("user", login.getUsername());   // username保存到Session中
            session.setAttribute("userId", login.getId());       // userId保存到Session中
            return new ModelAndView(new RedirectView("/file/allFile"));  // 重定向请求
        }
        // 登陆失败
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "用户名密码密码错误");
        mav.setViewName("login");
        return mav;
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @GetMapping(name = "注销控制器", path = "/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") != null) session.removeAttribute("user");
        return "redirect:/login.html";
    }

}
