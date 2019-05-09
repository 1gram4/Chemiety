package cn.kastner.chemiety.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping(value = "/loginPage")
    public String loginPage () {
        return "login";
    }

    @RequestMapping(value = "/logoutPage")
    public String logoutPage () {
        return "logout";
    }

    @RequestMapping(value = "/errorPage")
    public String error () {
        return "error";
    }

    @RequestMapping(value = "/admin/index")
    public String index () {
        return "admin";
    }

    @RequestMapping(value = "/admin/posts")
    public String checkPost () {
        return "checkPost";
    }

    @RequestMapping(value = "/admin/infos")
    public String info () {
        return "info";
    }

    @RequestMapping(value = "/admin/uploadEduFilePage")
    public String uploadEduFile () {
        return "uploadEduFile";
    }

    @RequestMapping(value = "/admin/uploadShowFilePage")
    public String uploadShowFile () {
        return "uploadShowFile";
    }

    @RequestMapping(value = "/admin/addWebsitePage")
    public String setReference () {
        return "addWebsite";
    }


}
