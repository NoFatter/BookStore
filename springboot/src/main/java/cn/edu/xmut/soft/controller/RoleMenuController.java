package cn.edu.xmut.soft.controller;

import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import cn.edu.xmut.soft.mapper.RoleMenuMapper;
import cn.edu.xmut.soft.entity.RoleMenu;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.common.BaseController;

/**
 * <p>
 * 角色菜单表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/soft/role-menu")
public class RoleMenuController extends BaseController {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @RequestMapping(method= RequestMethod.POST,value="/getMenuByRoleId")
    public Result getMenuByRoleId(
            String id) {
        Result result = new Result();
        QueryWrapper<RoleMenu> warpper = new QueryWrapper<>(new RoleMenu());
        warpper.eq("role_id",id);

        List<RoleMenu> list= roleMenuMapper.selectList( warpper);
        List<String> listResult = new ArrayList<>();
        for(RoleMenu rm : list){
            listResult.add(rm.getMenuId());
        }
        result.setData(listResult);
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/updateMenuByRoleId")
    public Result updateMenuByRoleId(String id, String ids) {
        Result result = new Result();

        //先晴空数据库记录
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>(new RoleMenu());
        wrapper.eq("role_id",id);
        roleMenuMapper.delete(wrapper);

        //插入新记录
        for (String menuId : ids.split(",")) {
            RoleMenu rm = new RoleMenu();
            rm.setMenuId(menuId);
            rm.setRoleId(id);

            roleMenuMapper.insert(rm);
        }
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize) {
        Result result = new Result();
        Page<RoleMenu> page = new Page<RoleMenu>(pageNum, pageSize);
        RoleMenu parms = new RoleMenu();
        QueryWrapper<RoleMenu> warpper = new QueryWrapper<RoleMenu>(parms);

        result.setData(roleMenuMapper.selectPage(page, warpper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody RoleMenu entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            roleMenuMapper.insert(entity);
        } else {
            roleMenuMapper.updateById(entity);
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
        roleMenuMapper.deleteBatchIds(deleteIds);
        return result;
    }

}
