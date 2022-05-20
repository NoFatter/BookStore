<template>
  <div>
    
      <div slot="search-panel">
        <el-button type="primary" icon="el-icon-plus" @click="addMenu">添加子节点</el-button>
        <el-button icon="el-icon-plus" @click="addRootMenu">添加顶部菜单</el-button>
        <el-button icon="el-icon-delete" @click="del">批量删除</el-button>
      </div>
      <div slot="table-panel">
        <el-row>
          <el-col :span="8">
            <alert-message class="mt-15" revise="-15px">
              <div slot="content">
                <el-link :underline="false" class="selected-tag">当前选择：</el-link>
                <el-link type="primary" :underline="false">{{formData.title}}</el-link>
                <el-link type="info" :underline="false" class="deselected" @click="cancel"
                         v-if="formData.title">取消选择
                </el-link>
              </div>
            </alert-message>
            <el-input placeholder="请输入内容" v-model="query.title" @change="search" prefix-icon="el-icon-search"

                      class="mt-15 mb-15" clearable></el-input>
            <div class="tree-bar" :style="{maxHeight: maxHeight}">
              <el-tree
                ref="tree"
                :data="menuTree"
                :props="props"
                node-key="id"
                show-checkbox
                highlight-current
                @node-click="bindNodeClick">
              </el-tree>
            </div>
          </el-col>
          <el-col :span="12" class="ml-30">
            <el-form :model="formData" :rules="rules" ref="formData" label-width="120px">
              <el-form-item label="类型">
                <div>
                  <i
                    :class="formData.level==1?'el-icon-menu':(formData.level==2?'el-icon-menu':'el-icon-paperclip')"></i>
                  <span>{{formData.level==1?'顶级菜单':(formData.level==2?'菜单页面':'操作按钮')}}</span>
                </div>
              </el-form-item>
              
              <el-form-item label="名称" prop="title">
                <el-input v-model="formData.title"></el-input>
              </el-form-item>
              <el-form-item label="路径" prop="path" v-if="formData.level != 3">
                <el-input v-model="formData.path"></el-input>
              </el-form-item>

              <el-form-item label="组件" prop="component" v-if="formData.level != 3">
              <el-input  v-model="formData.component"></el-input>
            </el-form-item>
            <el-form-item v-if="formData.level != 3">
                <span class="ml-10">页面的路径，比如system/user/index</span>
              </el-form-item>

             <el-form-item label="备注" prop="description" v-if="formData.level != 3">
              <el-input type="textarea" v-model="formData.description"></el-input>
            </el-form-item>
           

              <el-form-item label="排序值" prop="sort">
                <el-input-number v-model="formData.sort" controls-position="right"
                                 style="width: 100px"></el-input-number>
                
             
                <span class="ml-10">值越小越靠前</span>
              </el-form-item>
             
            </el-form>
          </el-col>
          <el-col :span="20" class="flex justify-end ml-30">
            <el-button type="primary" icon="el-icon-edit" :loading="loading" @click="submit">{{loading?'保存中...':'保存'}}</el-button>
            <el-button class="ml-5" icon="el-icon-refresh-left" @click="reset">重置</el-button>
          </el-col>
        </el-row>
      </div>
   
    <edit :visible="visible" v-if="visible"  :parent="formData" :level="level"
              @close="close"></edit>
  </div>

</template>

<script>
import AlertMessage from "@/components/common/AlertMessage";
  import edit from "./edit";
  import util from '@/libs/util'
  import {
    getAllTree,
    add,
    dele
  } from "@/api/modules/menu";

  export default {
    name: "index",
    components: { edit,AlertMessage},
    data() {
      return {
        props: {
          label: 'title'
        },
        maxHeight: '500px',
        visible: false,
        addLevelType: 'top',//新增的页面等级
        menuTree: [],
        formData: {
          name: "",
          
          path: "",
          parentId: "",
          sort: 0,
          level: 1,
          component:'',
          description:""
        },
        rules: {
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          //path: [
          //  {required: true, message: '请输入路径', trigger: 'blur'}
          //],
         
          
        },
        parent: {},
        level: 1,
        showParent: false,
        query: {
          name: null
        },
        loading: false
      }
    },
    mounted() {
      let height = document.documentElement.clientHeight;
      this.maxHeight = Number(height - 280) + "px";
      this.getAllTreePermission()
    },
    methods: {
     
      getAllTreePermission(title) {
        this.menuTree = [];
        getAllTree({title:title}).then(res => {
          if (res.success) {
            
            for(let idx in res.data){
              let item = res.data[idx]
              
              item.children = item.etc.children

              for(let idxc in item.children){
                let itemc = item.children[idxc]

                itemc.children = itemc.etc.children
              }

              this.menuTree.push(item)
            }
            
           

          }else{
            this.toast(res.messsage,2)
          }
        })
      },
     
      search() {
        if (util.isEmpty(this.query.title)) {
          this.getAllTreePermission()
        } else {
          this.getAllTreePermission(this.query.title)
        }
      },
      reset(){
        this.$refs['formData'].resetFields()
      },
      bindNodeClick(v) {
        
        this.formData = JSON.parse(JSON.stringify(v))
        this.level = this.formData.level
        //console.log(this.formData)
      },
      cancel() {
        this.$refs['formData'].resetFields()
        this.$refs.tree.setCheckedKeys([])
        this.formData.id = ""
        delete this.formData.id
      },
      del() {
        const checkedKeys = this.$refs.tree.getCheckedKeys()
        
        if (!checkedKeys.length) {
          this.toast("抱歉，不能删除，暂未选择菜单哦～", 'warning');
        } else {
          this.$confirm(`该操作将批量删除您选择的菜单, 是否继续?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            
            dele({
              ids: checkedKeys.join(',')
            }).then(res => {
              if (res.success) {
                 this.toast(res.message);
                this.search()
              }else{
                this.toast(res.messsage,'error')
              }
              
            })
          }).catch(() => {})
        }
      },
      // 新增一级页面
      addRootMenu() {
        this.level = 1
        this.visible = true
      },
      // 新增子页面
      addMenu() {
        if (util.isEmpty(this.formData.id)) {
          this.toast("请先点击选择一个菜单节点", 'warning')
          return;
        }
        this.level = Number(this.formData.level) + 1
        if (this.level > 3) {
          this.toast("抱歉，不能添加啦，仅支持两级菜单哦~", 'warning')
          return
        }
        this.visible = true
      },
      close(val) {
        this.visible = false
        if (val) {
          this.getAllTreePermission()
        }
      },
      submit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.loading = true
            add(this.formData).then(res => {
              this.loading = false
              
              if (res.success) {
                 this.toast(res.message);
                 this.getAllTreePermission()
              }else{
                this.toast(res.messsage,'error')
              }
            })
          }
        })
      }
    }
  }
</script>

<style scoped>
  .tree-bar {
    overflow: auto;
    margin-top: 5px;
  }

  .tree-bar::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }

  .tree-bar::-webkit-scrollbar-thumb {
    border-radius: 4px;
    -webkit-box-shadow: inset 0 0 2px #d1d1d1;
    background: #e4e4e4;
  }
  .ml-10{
    margin-left: 10px;
  }
</style>
