package cn.com.itoken.service.posts.controller;

import cn.com.itoken.common.dto.BaseResult;
import cn.com.itoken.common.service.domain.TbPostsPost;
import cn.com.itoken.common.utils.MapperUtils;
import cn.com.itoken.service.posts.service.PostsService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {

    @Autowired
    private PostsService<TbPostsPost> postsService;

    /**
     * 根据id获取文章
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postGuid") String postGuid){
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        tbPostsPost.setAccess("");
        TbPostsPost obj = postsService.select(tbPostsPost);

        return BaseResult.ok(obj);
    }

    /**
     * 保存文章
     * @param tbPostsPostJson
     * @param optsBy
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String tbPostsPostJson,
            @RequestParam(required = true) String optsBy
    ){
        int result = 0;

        TbPostsPost tbPostsPost1 = null;

        try {
            tbPostsPost1 = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(tbPostsPost1 != null){
            //判断主键是否为空，为空则为新增，否则为修改
            if(StringUtils.isBlank(tbPostsPost1.getPostGuid())){
                tbPostsPost1.setPostGuid(UUID.randomUUID().toString());
                result = postsService.insert(tbPostsPost1, optsBy);
            }

            else{
                result = postsService.update(tbPostsPost1, optsBy);
            }

            if(result > 0){
                return BaseResult.ok("保存文章成功");
            }
        }
        return BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error("网络连接", "上传失败")));
    }

    /**
     * 分页查询文章
     * @param pageNum
     * @param pageSize
     * @param tbPostsPostJson
     * @return
     */
    @ApiOperation(value = "文章服务分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataTypeClass = int.class, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataTypeClass = int.class, paramType = "path"),
            @ApiImplicitParam(name = "tbPostsPostJson", value = "对象Josn格式", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String tbPostsPostJson) throws Exception
    {
        TbPostsPost tbPostsPost1 = null;
        if(StringUtils.isNotBlank(tbPostsPostJson)) {
            try {
                tbPostsPost1 = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo pageInfo = postsService.page(pageNum, pageSize, tbPostsPost1);

        //结果集
        List<TbPostsPost> list = pageInfo.getList();

        //封装Cursor
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.ok(list, cursor);
    }
}
