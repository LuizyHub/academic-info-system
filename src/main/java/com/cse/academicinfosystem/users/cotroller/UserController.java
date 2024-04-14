package com.cse.academicinfosystem.users.cotroller;

import com.cse.academicinfosystem.users.dto.UserAddDto;
import com.cse.academicinfosystem.users.dto.UserLoginDto;
import com.cse.academicinfosystem.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") UserLoginDto user,
                            @RequestParam(required = false, defaultValue = "false") boolean error,
                            Model model){

        if (error) {
            model.addAttribute("error", true);
        }
        log.info("GET /login");
        log.info("user = {}", user);
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Validated UserLoginDto user) {
        log.info("POST /login");
        log.info("user = {}", user);
        return "loginForm";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") UserAddDto userAddDto) {
        log.info("GET /register");
        log.info("userAddDto = {}", userAddDto);
        return "addUserForm";
    }



    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Validated UserAddDto userAddDto, BindingResult bindingResult) {

        log.info("POST /register");
        log.info("userAddDto = {}", userAddDto);
        if (!userAddDto.getPassword().equals(userAddDto.getPasswordConfirm())) {
            bindingResult.reject("passwordConfirm", "비밀번호가 일치하지 않습니다.");
        }

        if (userService.isUsernameExist(userAddDto.getUsername())) {
            bindingResult.reject("username", "이미 사용중인 아이디입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "addUserForm";
        }

        userService.addUser(userAddDto);

        return "redirect:/login";
    }
}