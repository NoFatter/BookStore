package cn.edu.xmut.soft.controller;

import cn.edu.xmut.soft.entity.Dic;
import cn.edu.xmut.soft.mapper.DicMapper;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.edu.xmut.soft.mapper.DictypeMapper;
import cn.edu.xmut.soft.entity.Dictype;
import cn.edu.xmut.common.BaseController;

/**
 * <p>
 * 字典类型表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-09
 */
@RestController
@RequestMapping("/soft/dictype")
public class DictypeController extends BaseController {

    @Autowired
    private DictypeMapper dictypeMapper;

    @Autowired
    private DicMapper dicMapper;

    //获取所有的字典类型对应的所有的字典数据
    // <key, List<Dic>>   {useful} => {1-可用；0-不可用；2-保密}
    @GetMapping("/getAllDic")
    public Result getAllDic(){
        Result result = new Result();
        Map<String, List<Dic>> map =
                new HashMap<>();

        QueryWrapper<Dictype> warpper = new QueryWrapper<Dictype>(new Dictype());
        //获得所有的字典类型
        List<Dictype> list = dictypeMapper.selectList(warpper);

        //获得每个字典类型下的所有的字典数据
        for(Dictype dictype : list){
            QueryWrapper<Dic> dicWrapper = new QueryWrapper<Dic>(new Dic());
            dicWrapper.eq("dictype_id",dictype.getId());
            //获得所有的字典类型
            List<Dic> listDic = dicMapper.selectList(dicWrapper);

            if(listDic.size()>0){
                map.put(dictype.getValue(),listDic);
            }
        }

        result.setData(map);
        result.success("获取所有字典数据成功");
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize) {
        Result result = new Result();
        Page<Dictype> page = new Page<Dictype>(pageNum, pageSize);
        Dictype parms = new Dictype();
        QueryWrapper<Dictype> warpper = new QueryWrapper<Dictype>(parms);

        result.setData(dictypeMapper.selectPage(page, warpper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody Dictype entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            dictypeMapper.insert(entity);
        } else {
            dictypeMapper.updateById(entity);
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
        dictypeMapper.deleteBatchIds(deleteIds);
        return result;
    }

}
