package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogJPARepository;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.ReplyJPARepository;
import com.spring.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //빈 컨테이너에 적재
public class BlogServiceImpl implements BlogService{
    BlogRepository blogRepository;
    ReplyRepository replyRepository;

    BlogJPARepository blogJPARepository;
    ReplyJPARepository replyJPARepository;

    @Autowired //생성자 주입이 setter주입보다 속도가 빠르다
    public BlogServiceImpl(BlogRepository blogRepository, ReplyRepository replyRepository, BlogJPARepository blogJPARepository, ReplyJPARepository replyJPARepository) {
        this.blogRepository = blogRepository;
        this.replyRepository = replyRepository;
        this.blogJPARepository = blogJPARepository;
        this.replyJPARepository = replyJPARepository;
    }

    @Override
    public Page<Blog> findAll(Long pageNum) {
//        return  blogRepository.findAll(); //Mybatis
//        return blogJPARepository.findAll(); //JPA
        Pageable pageable = PageRequest.of(getCalibratedPageNum(pageNum)-1, 10);
        return blogJPARepository.findAll(pageable);
    }

    @Override
    public Blog findById(long blogId) {
//        return blogRepository.findById(blogId);
        return blogJPARepository.findById(blogId).get(); // JPA의 findById는 Optional(참조형 변수에 대해서 null값을 검사및 처리를 쉽게 할수 있는 제너릭) 을 리턴하기 때문에
        // 일반 객체로 만들기 위해서는 .get()을 붙여줘야한다.
        // JPA에서는 Optional 사용 권장

    }

    @Override
    @Transactional //둘다 실행되던디 둘 다 실행 안되던지 해야하기 떄문에
    public void deleteById(long blogId) {
//        replyRepository.deleteAllByBlogId(blogId);
//        blogRepository.deleteById(blogId);
        replyJPARepository.deleteAllByBlogId(blogId);
        blogJPARepository.deleteById(blogId);
    }

    @Override
    public void save(Blog blog) {
//        blogRepository.save(blog);
        blogJPARepository.save(blog);
    }

    @Override
    public void update(Blog blog) {
        // JPA의 수정은, findById()를 이용해 얻어온 엔터티 클래스의 객체 내부 내용물을 수정한 다음
        // 해당 요소를 save()해서 이뤄진다.

//        blogRepository.update(blog);
        Blog findBlog = blogJPARepository.findById(blog.getBlogId()).get(); // 준영속 상태

        findBlog.setBlogTitle(blog.getBlogTitle());
        findBlog.setBlogContent(blog.getBlogContent());

        blogJPARepository.save(findBlog);
    }

    public int getCalibratedPageNum(Long pageNum){
        if(pageNum == null || pageNum < 1l) return 1;

        int totalPagesCount = (int)Math.ceil(blogJPARepository.count() / 10.0);
        return pageNum > totalPagesCount ? totalPagesCount : pageNum.intValue();
    }
}