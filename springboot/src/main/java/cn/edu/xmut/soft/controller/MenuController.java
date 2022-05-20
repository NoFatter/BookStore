package cn.edu.xmut.soft.controller;

import cn.edu.xmut.soft.entity.*;
import cn.edu.xmut.soft.mapper.*;
import cn.edu.xmut.utils.JwtUtil;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.common.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 菜单表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-09
 */
@RestController
@RequestMapping("/soft/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DicMapper dicMapper;

    @RequestMapping(method= RequestMethod.GET,value="/getAllTreeByUserId")
    public Result getAllTreeByUserId() {

        Result result = JwtUtil.validateToken(request.getHeader("token"));
        if(result.isSuccess()){
            QueryWrapper<Menu> menuWarpper = new QueryWrapper<>();
            String userId = result.getData().toString();



            User user = userMapper.selectById(userId);
            if(user==null){
                result.fail(userId+",对应的用户不存在");
                return result;
            }else{



            //1、获取该用户所属的所有角色
            QueryWrapper<UserRole> urWarpper = new QueryWrapper<UserRole>();
            urWarpper.eq("user_id",user.getId());
            List<UserRole> listUserRole = userRoleMapper.selectList(urWarpper);


            //保存用户所有的menuid
            List<String> listMenuId = new ArrayList<>();

            //2、获取该用户所属的所有角色的所有menu
            for(UserRole ur : listUserRole){
                String roleId = ur.getRoleId();
                QueryWrapper<RoleMenu> rmWarpper = new QueryWrapper<RoleMenu>();
                rmWarpper.eq("role_id",roleId);
                List<RoleMenu> listRoleMenu = roleMenuMapper.selectList(rmWarpper);

                for(RoleMenu rm : listRoleMenu){
                    if(listMenuId.indexOf(rm.getMenuId())<0){
                        listMenuId.add(rm.getMenuId());
                    }
                }

            }

            //3、获取menuTree
            List<Menu> listMenu = new ArrayList<>();

            for(String id : listMenuId){
                QueryWrapper<Menu> menuParentWarpper = new QueryWrapper<>();
                menuParentWarpper.eq("id",id);

                Menu menu = menuMapper.selectOne(menuParentWarpper);
                if(menu != null){
                    listMenu.add(menu);
                }
            }

            //3.1、获得第一级菜单
            List<Menu> listParent = new ArrayList<>();
            for(Menu menu : listMenu){
                if(menu.getLevel()==1){
                    int size = listParent.size();

                    int i =0;
                    for(;i<size;i++){
                        Menu tmp = listParent.get(i);

                        if(tmp.getSort()>menu.getSort()){
                            listParent.add(i,menu);
                            break;
                        }
                    }

                    if(i==size){
                        listParent.add(menu);
                    }

                    //listParent.add(menu);
                }
            }
            //3.2、获得第二级菜单
            for(Menu menuParent : listParent){
                List<Menu> listTwo = new ArrayList<>();
                for(Menu menu : listMenu){
                    if(menu.getParentId().equals(menuParent.getId())){

                        int size = listTwo.size();
                        int i=0;
                        for(;i<size;i++){
                            Menu tmp = listTwo.get(i);

                            if(tmp.getSort()>menu.getSort()){
                                listTwo.add(i,menu);
                                break;
                            }
                        }

                        if(i==size){
                            listTwo.add(menu);
                        }
                        //listTwo.add(menu);
                    }
                }

                //3.3、获得第三级菜单
                for(Menu menuTwo : listTwo) {
                    List<Menu> listThree = new ArrayList<>();
                    for (Menu menu : listMenu) {
                        if (menu.getParentId().equals(menuTwo.getId())) {
                            listThree.add(menu);
                        }
                    }

                    menuTwo.put("children",listThree);
                }

                menuParent.put("children",listTwo);
            }
            result.setData(listParent);


        }
    }


        return result;

    }

    private void getSons(List<Menu> listParent) {
        for(Menu menu:listParent){
            QueryWrapper<Menu> menuParentWarpper = new QueryWrapper<Menu>();
            menuParentWarpper.eq("parent_id",menu.getId());
            menuParentWarpper.orderByAsc("sort");
            List<Menu> listChildren = menuMapper.selectList(menuParentWarpper);

            for(Menu menuc : listChildren){
                QueryWrapper<Menu> menuChildrenWarpper = new QueryWrapper<Menu>();
                menuChildrenWarpper.eq("parent_id",menuc.getId());
                menuChildrenWarpper.orderByAsc("sort");
                List<Menu> listChildrenC = menuMapper.selectList(menuChildrenWarpper);

                menuc.put("children",listChildrenC);
            }

            menu.put("children",listChildren);
        }
    }

    @RequestMapping(method= RequestMethod.POST,value="/getAllTree")
    public Result getAllTree(
            String title) {
        Result result = new Result();

        Menu parms = new Menu();
        QueryWrapper<Menu> warpper = new QueryWrapper<Menu>(parms);

        if(title !=null && !"".equals(title)){
            warpper.like("title",title);
        }

        warpper.eq("level","1");
        warpper.orderByAsc("sort");
        List<Menu> listParent = menuMapper.selectList(warpper);
        for(Menu menu:listParent){
            QueryWrapper<Menu> menuParentWarpper = new QueryWrapper<Menu>();
            menuParentWarpper.eq("parent_id",menu.getId());
            menuParentWarpper.orderByAsc("sort");
            List<Menu> listChildren = menuMapper.selectList(menuParentWarpper);

            for(Menu menuc : listChildren){
                QueryWrapper<Menu> menuChildrenWarpper = new QueryWrapper<Menu>();
                menuChildrenWarpper.eq("parent_id",menuc.getId());
                menuChildrenWarpper.orderByAsc("sort");
                List<Menu> listChildrenC = menuMapper.selectList(menuChildrenWarpper);

                menuc.put("children",listChildrenC);
            }
            menu.put("children",listChildren);
        }
        result.setData(listParent);

        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize) {
        Result result = new Result();
        Page<Menu> page = new Page<Menu>(pageNum, pageSize);
        Menu parms = new Menu();
        QueryWrapper<Menu> warpper = new QueryWrapper<Menu>(parms);

        result.setData(menuMapper.selectPage(page, warpper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody Menu entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            if(entity.getLevel()==1){
                entity.setParentId("-1");
            }
            if(entity.getLevel()==3) {//如果是button菜单，获取其title
                Dic dic = dicMapper.selectById(entity.getButtonType());

                if(dic !=null){
                    entity.setTitle(dic.getName());
                    entity.setButtonType(dic.getValue());
                }

            }

            menuMapper.insert(entity);
        } else {
            menuMapper.updateById(entity);
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
        menuMapper.deleteBatchIds(deleteIds);
        return result;
    }

}
