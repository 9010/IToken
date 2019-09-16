package cn.com.itoken.web.posts.controller;

import cn.com.itoken.common.dto.BaseResult;
import cn.com.itoken.common.service.domain.TbPostsPost;
import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.common.utils.MapperUtils;
import cn.com.itoken.common.web.constants.WebConstants;
import cn.com.itoken.common.web.controller.BaseController;
import cn.com.itoken.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {

    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid){
        TbPostsPost tbPostsPost = null;

        if(StringUtils.isBlank(postGuid)){
            tbPostsPost = new TbPostsPost();
        }
        else{
            String json = postsService.get(postGuid);

            try {
                tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(tbPostsPost == null){
                tbPostsPost = new TbPostsPost();
            }
        }

        return tbPostsPost;
    }

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
        //初始化
        tbPostsPost.setTimePublished(new Date());
        tbPostsPost.setStatus("0");

        TbSysUser admin = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);

        String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
        String json = postsService.save(tbPostsPostJson, admin.getUserCode());

        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        if(baseResult.getSuccess().endsWith("成功")){
            return "redirect:/index";
        }

        return "redirect:/form";
    }
}
