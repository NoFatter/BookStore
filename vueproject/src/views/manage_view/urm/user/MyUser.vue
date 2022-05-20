<template>
  <div>
    <!--查询界面-->
    <div>
      <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
          <el-col :xl="6" :md="8" :sm="24">
            <el-form-item label="用户名" prop="username" >
              <el-input  placeholder="请输入用户名" v-model="searchForm.username" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="6" :md="8" :sm="24">
            <el-form-item label="状态" prop="useful" >
               <el-select v-model="searchForm.useful" placeholder="请选择状态" clearable>
                <el-option
                  v-for="item in usefulAry"
                  :key="item.name"
                  :label="item.name"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xl="6||24" :md="8||24" :sm="24">
            <el-form-item>
              <div :style=" { float: 'right', overflow: 'hidden' } || {} ">
                <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
              </div>
            </el-form-item>
          </el-col>
        </el-form>
    </div> 
    <div>
        <el-button type="primary" @click="add()" v-has="this.gl_btn_add">新增</el-button>
        <el-button type="primary" @click="setUsefuls(0)">批量禁用</el-button>
        <el-button type="primary" @click="setUsefuls(1)">批量启用</el-button>
    </div>
      <page-table @load-table-data="loadTableData" ref="dataTable" @selection-change="selection"
                        row-key="id">
          <template slot="tb-columns">
            <el-table-column  align="center" prop="username" label="用户名" :formatter="tableColummEmpty"></el-table-column>
            <el-table-column align="center" prop="useful" label="状态" :formatter="usefulFormat"></el-table-column>
            <el-table-column align="center" prop="createTime" label="创建时间" sortable></el-table-column>
            <el-table-column align="center"  label="操作" >
              <template slot-scope="scope">
                 <el-button type="primary" @click="edit(scope.row)" >编辑</el-button>|
                <el-dropdown>
                  <el-link :underline="false" type="primary">更多<i class="el-icon-arrow-down el-icon--right"></i>
                  </el-link>
                  <el-dropdown-menu slot="dropdown">
                     <el-dropdown-item @click.native="setUseful(scope.row.id,1)" v-if="scope.row.useful===0" >启用</el-dropdown-item>
                    <el-dropdown-item @click.native="setUseful(scope.row.id,0)" v-else >停用</el-dropdown-item>
                    <el-dropdown-item @click.native="setRole(scope.row)"  >划分角色</el-dropdown-item>
                    <el-dropdown-item @click.native="delUser(scope.row.id)">删除用户</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </template>
            </el-table-column>
          </template>
        </page-table>
  <Edit :visible='visible' v-if='visible' :title='title' :defaultFormData='obj' @close='close'></Edit>
<user-role  :visible="visible_u_r" v-if="visible_u_r" :user="user" @close="close"></user-role>
  </div>
  
</template>
<script>

import PageTable from '@/components/PageTable'
import Edit from './edit'
import UserRole from './user-role'
import {deleteUser, page, updateUseful} from '@/api/modules/user'
export default {
  name: 'myUser',
  components:{PageTable,Edit,UserRole},
  data(){
    return{
      visible:false,
      visible_u_r:false,
      title:'新增',
      obj:{},
      ids:[],
      user:{},
      searchForm:{
        username:'',
        useful:''
      },
      usefulAry:[]
    }
  },
  mounted(){
    this.usefulAry = this.$store.state.dicMap[this.gl_useful]
  },
  methods:{
    setRole(user){
      this.user = user
      this.visible_u_r = true
    },
    usefulFormat(row,column,cellValue){
        return this.columnFormat(cellValue,this.usefulAry)
    },
    
    setUsefuls(useful){
      if(this.ids.length <= 0){
        this.toast('请先选择记录',2)
        return
      }
      this.myUpdateUseful(this.ids.join(','),useful)
    },
    setUseful(id,useful){
      this.myUpdateUseful(id,useful)
    },
    myUpdateUseful(ids,useful){
        updateUseful({
        ids:ids,
        useful:useful
      }).then(res =>{
        this.toast(res.message)
        this.$refs.dataTable.loadTableData('reload')
      }).catch(err =>{
           this.toast(err,2)
      })
    },
    edit(obj){
      
        this.visible = true
        this.title = '编辑'
        this.obj = obj
    },
    add(){
        this.visible = true
        this.title = '新增'
        this.obj = {}
    },
    close(flag){
        this.visible = false
        this.visible_u_r = false
        if(flag){
          this.$refs.dataTable.loadTableData('reload')
        }
    },
    selection(val){
      this.ids = []
        val.forEach(item => {
            this.ids.push(item.id)
        })
       // console.log(this.ids)
    },
   search(){
      this.$refs.dataTable.loadTableData('reload')
    },
   loadTableData(filter,callback){
       
        filter = {
          ...filter,
          ...this.searchForm
        }
        //console.log(filter)

        page(filter).then(res => {
          //res = result
         // console.log(res)

          this.total = res.data.total
          let tableData = {
            ...res.data,
            data: res.data.records
          }
          callback(tableData)
        })
    },
    delUser(id){
      this.myDel(id)
    },
    myDel(ids){
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser({
          ids:ids
        }).then(res =>{
          this.toast(res.message)
          this.$refs.dataTable.loadTableData('reload')
        }).catch(err =>{
          this.toast(err,2)
        })
      }).catch(() => {
      });
    }
  }
}
</script>
<style scoped>
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .formItem {
    vertical-align: middle;
  }
</style>