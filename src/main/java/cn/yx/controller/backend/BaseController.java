package cn.yx.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.yx.config.holder.AdminInfoHolder;
import cn.yx.model.Admin;
import cn.yx.model.ApiResponse;
import cn.yx.service.AdminService;
import cn.yx.util.MD5Util;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午4:13:31
 * @version 1.0
 */

@Controller
@RequestMapping("/backend")
public class BaseController {

    @Resource
    private AdminService adminService;

    @RequestMapping({ "/", "", "index" })
    public String index() {
        return "/demo/list";
    }

    @RequestMapping("/loginAction")
    public String login(Model model, Admin admin, HttpSession session) {
        Admin res = adminService.checkUser(admin);
        if (res == null) {
            model.addAttribute("error", "* 用户名不存在");
            return "/index/login";
        } else if (!admin.getPasswd().equals(res.getPasswd())) {
            model.addAttribute("error", "* 密码错误");
            return "/index/login";
        } else {
            session.setAttribute("admin", res);
            return "redirect:/backend/";
        }
    }

    @RequestMapping("/login")
    public String loginIndex(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "redirect:/backend/";
        }
        return "/index/login";
    }

    @RequestMapping("/test")
    public String upload() {
        return "/index/upload";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        AdminInfoHolder.clear();
        return "/index/login";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public JSONObject register(@RequestParam String account, @RequestParam String password, @RequestParam String name,
            @RequestParam(value = "key", required = false) String key) {
        JSONObject resp = new JSONObject();
        if (key == null || !checkKey(key)) {
            resp.put("code", 304);
            resp.put("msg", "你没有权限添加用户哦");
            return resp;
        }
        try {
            password = MD5Util.toMD5(password);
            adminService.addUser(account, password, name);
            resp.put("code", 200);
            resp.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("code", -1);
            resp.put("msg", "添加失败");
        }
        return resp;
    }

    @RequestMapping("/modifyPwd")
    @ResponseBody
    public JSONObject modifyPwd(@RequestParam String account, @RequestParam String password,
            @RequestParam(value = "key", required = false) String key) {
        JSONObject resp = new JSONObject();
        if (key == null || !checkKey(key)) {
            resp.put("code", 304);
            resp.put("msg", "你没有权限修改密码哦");
            return resp;
        }
        try {
            password = MD5Util.toMD5(password);
            adminService.modifyUser(account, password);
            resp.put("code", 200);
            resp.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("code", -1);
            resp.put("msg", "修改失败");
        }
        return resp;
    }

    private boolean checkKey(String key) {
        Admin admin = new Admin();
        admin.setAccount("yuxuan");
        Admin check = adminService.checkUser(admin);
        if (check != null && check.getPasswd().equals(MD5Util.toMD5(key))) {
            return true;
        }
        return false;
    }
}
