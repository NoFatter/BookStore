<template>
 <el-dialog :title="title" :visible="visible_d" :close-on-click-modal="false"
             :before-close="handleClose" width="500px">
  <div >
   


           <!--查询界面-->
    <!-- <div>
      <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
          <el-col :xl="6" :md="8" :sm="24">
            <el-form-item label="书名" prop="title" >
              <el-input  placeholder="请输入书名" v-model="searchForm.title" clearable></el-input>
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
    </div> -->
    <div>
        <el-button type="primary" @click="add()">新增</el-button>
       <el-button type="primary" @click="dels()" >批量删除</el-button>
    </div>
   
      <page-table @load-table-data="loadTableData" ref="dataTable" @selection-change="selection"
                        row-key="id">
          <template slot="tb-columns">
            <el-table-column  align="center" prop="name" label="字典类型name" :formatter="tableColummEmpty"></el-table-column>
            <el-table-column align="center" prop="value" label="字典类型value"  :formatter="tableColummEmpty"></el-table-column>
           
            <el-table-column align="center"  label="操作" >
              <template slot-scope="scope">
                 <el-button type="primary" @click="edit(scope.row)" >编辑</el-button>|
                <el-dropdown>
                  <el-link :underline="false" type="primary">更多<i class="el-icon-arrow-down el-icon--right"></i>
                  </el-link>
                  <el-dropdown-menu slot="dropdown">
                   <el-dropdown-item @click.native="mgnDic(scope.row.id)" >字典管理</el-dropdown-item>
                   <el-dropdown-item @click.native="del(scope.row.id)" >删除</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
               </template>
            </el-table-column>
          </template>
        </page-table>
  <Edit :visible='visible' v-if='visible' :dictypeId='dictypeId' :title='title' :defaultFormData='obj' @close='close'></Edit>
  </div>
 </el-dialog>
</template>
<script>

import PageTable from '@/components/PageTable'
import Edit from './dicedit'

import {page,dele} from '@/api/modules/dic'
export default {
  name: 'myDic',
  props: {
      visible_d: {
        type: Boolean
      },
      dictypeId: {
        type: String,
      }
      
    },
  components:{PageTable,Edit},
  data(){
    return{
      visible:false,
      title:'字典管理',
      obj:{},
      ids:[],
      searchForm:{
        title:''
      },
      refresh: false,
    }
  },
  mounted(){
    
    
  },
  methods:{
     handleClose() {
        this.$emit('close',this.refresh)
      },
    dels(){
      if(this.ids.length <= 0){
        this.toast('请先选择记录',2)
        return
      }
      this.myDel(this.ids.join(','))
    },
    del(id){
      this.myDel(id)
    },

    myDel(ids){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          dele({
            ids:ids
          }).then(res =>{
            this.toast(res.message)
            this.$refs.dataTable.loadTableData('reload')
          }).catch(err =>{
              this.toast(err,2)
          })
        }).catch(() => {
        });
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
          ...this.searchForm,
          dictypeId:this.dictypeId
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