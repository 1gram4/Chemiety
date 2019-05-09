package cn.kastner.chemiety.controller;

import cn.kastner.chemiety.repository.UserRepository;
import cn.kastner.chemiety.domain.User;
import cn.kastner.chemiety.util.NetResult;
import cn.kastner.chemiety.util.StringMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NetResult netResult;

    /**
     * @param loginUser 登陆用户
     * @param session 会话
     * @return 0:正常 -1:用户不存在 -2:密码错误 -3:内部错误
     */
    @RequestMapping(value = "/login")
    public NetResult login(User loginUser, HttpSession session) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (null == user) {
            netResult.status = -1;
            netResult.result = "用户不存在！";
        } else {
//            String passwordMD5 = StringMD5.stringToMD5(loginUser.getPassword());
            System.out.println(user.getPassword());
            System.out.println(loginUser.getPassword());
            if (loginUser.getPassword() != null) {
                if (loginUser.getPassword().equals(user.getPassword())) {
                    netResult.status = 0;
                    netResult.result = "登陆成功！";
                    session.setAttribute(User.CUR_USER, user);
                } else {
                    netResult.status = -2;
                    netResult.result = "密码错误！";
                }
            } else {
                netResult.status = -3;
                netResult.result = "内部错误！";
            }
        }
        return netResult;
    }


    /**
     * @param session 会话
     * @return 0正常 -1 异常
     */
    @RequestMapping(value = "/logout")
    public NetResult logout(HttpSession session) {
        User currentUser = (User)session.getAttribute(User.CUR_USER);
        if (null != currentUser) {
            session.removeAttribute(User.CUR_USER);
            netResult.status = 0;
            netResult.result = "注销成功";
        } else {
            netResult.status = -1;
            netResult.result = "未登录或登陆状态已经过期，无需注销";
        }
        return netResult;
    }

    @RequestMapping(value = "/addUser")
    public NetResult addUser(User user) {
        User exUser = userRepository.findByUserId(user.getUserId());
        if (exUser == null) {
            user.setGender(User.Gender.MALE);
            user.setRoles(User.Role.STUDENT);
            String password = user.getPassword();
            password = String.valueOf(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setPassword(password);
            userRepository.save(user);
            netResult.status = 0;
            netResult.result = "创建成功！";
            return netResult;
        } else {
            netResult.status = -1;
            netResult.result = "数据库中已有！";
            return netResult;
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public NetResult getUserInfo (HttpSession session) {
        User user = (User) session.getAttribute(User.CUR_USER);
        if (user != null) {
            netResult.status = 0;
            netResult.result = user;
        } else {
            netResult.status = -1;
            netResult.result = "未登录或登录已过期！";
        }
        return netResult;
    }

}
