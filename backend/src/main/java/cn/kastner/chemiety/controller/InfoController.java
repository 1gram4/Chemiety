package cn.kastner.chemiety.controller;

import cn.kastner.chemiety.domain.Info;
import cn.kastner.chemiety.domain.User;
import cn.kastner.chemiety.repository.CommentRepository;
import cn.kastner.chemiety.repository.InfoRepository;
import cn.kastner.chemiety.repository.PostRepository;
import cn.kastner.chemiety.repository.UserRepository;
import cn.kastner.chemiety.util.NetResult;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.Net;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/infos")
public class InfoController {

    private final InfoRepository infoRepository;

    private final NetResult netResult;

    @Autowired
    public InfoController(InfoRepository infoRepository, NetResult netResult) {
        this.infoRepository = infoRepository;
        this.netResult = netResult;
    }

    @PostMapping
    public NetResult postInfo (Info info, HttpSession session) {
        User user = (User)session.getAttribute(User.CUR_USER);
        if (user != null) {
            if (user.getRoles() == User.Role.TEACHER) {
                if (info.getContent() != null) {
                    info.setUser(user);
                    infoRepository.save(info);
                    netResult.status = 0;
                    netResult.result = info;
                } else {
                    netResult.status = -1;
                    netResult.result = "公告内容为空";
                }
            } else {
                netResult.status = -1;
                netResult.result = "无权限！";
            }
        } else {
            netResult.status = -2;
            netResult.result = "登陆状态过期，请重新登录！";
        }
        return netResult;
    }

    @GetMapping
    public NetResult getInfos () {
        netResult.status = 0;
        netResult.result = infoRepository.findByContentIsNotNullOrderByInfoIdDesc();
        return netResult;
    }

    @GetMapping(value = "/{id}")
    public NetResult getInfo(@PathVariable Long id) {
        Optional<Info> infoOptional = infoRepository.findById(id);
        if (infoOptional.isPresent()) {
            netResult.result = infoOptional.get();
            netResult.status = 0;
        } else {
            netResult.result = "没有这个公告";
            netResult.status = -1;
        }
        return netResult;
    }

    @DeleteMapping(value = "/{id}")
    public NetResult deleteInfo(@PathVariable Long id) {
        Optional<Info> infoOptional = infoRepository.findById(id);
        if (infoOptional.isPresent()) {
            Info info = infoOptional.get();
            infoRepository.delete(info);
            netResult.status = 0;
            netResult.result = info;
        } else {
            netResult.status = -1;
            netResult.result = "没有这个公告";
        }
        return netResult;
    }

    @RequestMapping(value = "/getFirstInfo")
    public NetResult getFirstInfo () {
        List<Info> infos = infoRepository.findByContentIsNotNullOrderByInfoIdDesc();
        if (!infos.isEmpty()) {
            netResult.status = 0;
            netResult.result = infos.get(infos.size() - 1);
        } else {
            netResult.status = -1;
            netResult.result = "数据异常！";
        }
        return netResult;
    }
}
