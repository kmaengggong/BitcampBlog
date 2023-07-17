package com.spring.blog.controller;

import com.spring.blog.entity.Blog;
import com.spring.blog.exception.NotFoundBlogException;
import com.spring.blog.service.BlogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //빈 컨테이너 등록(Component)과 url을 분석할 수 있는 로직(RequestMapping)이 추가되어 있다
@RequestMapping("/blog")
@Log4j2 // sout이 아닌 로깅을 통한 디버깅을 위해 선언
public class BlogController {
    final private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping({"/list/{pageNum}","/list"})
    public String findAll(Model model, @PathVariable(required = false) Long pageNum){
        Page<Blog> pageInfo = blogService.findAll(pageNum);

        final int PAGE_BTN_NUM = 10;

        int currentPageNum = pageInfo.getNumber() + 1;
        int endPageNum = (int)Math.ceil(currentPageNum / (double)PAGE_BTN_NUM) * PAGE_BTN_NUM;
        int startPageNum = endPageNum - PAGE_BTN_NUM + 1;

        endPageNum = Math.min(endPageNum, pageInfo.getTotalPages());

        model.addAttribute("currentPageNum", currentPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("pageInfo", pageInfo);

        return "/blog/list";
    }

    @RequestMapping("/detail/{blogId}")
    public String detail(@PathVariable long blogId, Model model){
        Blog blog = blogService.findById(blogId);
        if (blog == null){
            try{
                throw new NotFoundBlogException("없는 blogId 조회 조회번호 : " + blogId);
            }catch (NotFoundBlogException e){
//                System.out.println(e.getMessage());
                e.printStackTrace();
                return "blog/NotFoundBlogIdExceptionResultPage";
            }
        }else{
            model.addAttribute("blog", blog);
        }
        return "/blog/detail";
    }

    // 홈페이지와 실제 등록 url은 같은 url을 쓰도록 한다
    //대신 폼 페이지는 GET방식으로 접속했을때 연결
    // 폼에서 작성완료된 내용을 POST방시그로 제출해 저장하도록 만든다
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(){
        return "/blog/blog-form";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(Blog blog){
        blogService.save(blog);
        return "redirect:/blog/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(long blogId){
        log.info(blogId);   // sout은 로그파일에 안찍히지만 log.info는 찍힌다.
        // log4j에 보안적인 취약점이 있었다
        blogService.deleteById(blogId);
        return "redirect:/blog/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, int blogId){
        Blog blog = blogService.findById(blogId);
        model.addAttribute("blog",blog);
        return "/blog/update-form";
    }
    @RequestMapping(value = "/updateOk", method = RequestMethod.POST)
    public String update(Blog blog){
        blogService.update(blog);
        return "redirect:/blog/detail/" + blog.getBlogId();
    }
}