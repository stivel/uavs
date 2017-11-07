package com.uavs.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uavs.framework.controller.AppBaseController;

@Controller
public class IndexController extends AppBaseController{
    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     * @throws
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) throws Exception {
        return "video/video-list";
    }
    
    /**
     * 视频播放页
     *
     * @param request
     * @param model
     * @return
     * @throws
     */
    @RequestMapping("/toVideoPage")
    public String toVideoPage(HttpServletRequest request, Model model) throws Exception {
        return "video/video-page";
    }
}
