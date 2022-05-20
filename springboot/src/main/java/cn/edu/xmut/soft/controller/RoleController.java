package cn.edu.xmut.soft.controller;

import cn.edu.xmut.common.Constant;
import cn.edu.xmut.utils.DateTool;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import cn.edu.xmut.soft.mapper.RoleMapper;
import cn.edu.xmut.soft.entity.Role;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.common.BaseController;

/**
 * <p>
 * 角色表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-09
 */
@RestController
@RequestMapping("/soft/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleMapper roleMapper;


    @RequestMapping(method= RequestMethod.GET,value="/getAll")
    public Result getAll() {
        Result result = new Result();
        Role parms = new Role();
        QueryWrapper<Role> warpper = new QueryWrapper<>(parms);



        result.setData(roleMapper.selectList(warpper) );
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize,
        String name,
        Integer useful) {
        Result result = new Result();
        Page<Role> page = new Page<Role>(pageNum, pageSize);
        Role parms = new Role();
        QueryWrapper<Role> warpper = new QueryWrapper<Role>(parms);

        if(name != null && !"".equals(name)){
            warpper.like("name",name);
        }
        if(useful != null){
            warpper.eq("useful",useful);
        }

        result.setData(roleMapper.selectPage(page, warpper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody Role entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            entity.setUseful(Constant.GL_USEFUL);
            entity.setCreateTime(DateTool.getCurrTime());
            roleMapper.insert(entity);
        } else {
            roleMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.DELETE,value="/delete")
    public Result delete( String ids) {
        Result result = new Result();
        List<String> deleteIds = new ArrayList<String>();
        for (String id : ids.split(",")) {
            deleteIds.add(id);
        }
        roleMapper.deleteBatchIds(deleteIds);
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/updateUseful")
    public Result updateUseful( String ids,Integer useful) {
        Result result = new Result();

        for (String id : ids.split(",")) {

            //修改值
            Role role = new Role();
            role.setUseful(useful);

            //修改条件
            UpdateWrapper<Role> roleUpdateWrapper = new UpdateWrapper<>();
            roleUpdateWrapper.eq("id", id);

            roleMapper.update(role, roleUpdateWrapper);
        }
        return result;
    }
}
