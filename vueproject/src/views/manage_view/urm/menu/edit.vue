<template>
  <el-dialog :title="titleMap[level]" :visible.sync="visible" :close-on-click-modal="false" :before-close="close">
    <div>
      <el-form :model="formData" :rules="rules" ref="formData" label-width="120px">
        <div v-if="level!=1">
          <el-form-item label="上级节点">
            <i class="el-icon-s-grid"></i>{{parent.title}}
          </el-form-item>
        </div>
        <el-form-item label="类型">
          <div>
            <i :class="level==1?'el-icon-menu':(level==2?'el-icon-menu':'el-icon-paperclip')"></i>
            <span>{{level==1?'顶级菜单':(level==2?'菜单页面':'操作按钮')}}</span>
          </div>
        </el-form-item>
        <el-form-item label="名称" prop="title" v-if="formData.level != 3">
          <el-input v-model="formData.title"></el-input>
        </el-form-item>
        <el-form-item label="路径" prop="path" v-if="formData.level != 3">
          <el-input v-model="formData.path"></el-input>
        </el-form-item>
         <el-form-item label="组件" prop="component" v-if="formData.level != 3">
              <el-input  v-model="formData.component"></el-input>
            </el-form-item>
        <el-form-item v-if="formData.level != 3">
                <span class="ml-10">页面的路径，比如system/user/index，默认layout/basic</span>
              </el-form-item>
        
       <el-form-item label="备注" prop="description" v-if="formData.level != 3"> 
        <el-input type="textarea" v-model="formData.description"></el-input>
      </el-form-item>

      <el-form-item label="按钮权限类型" prop="buttonType" v-if="formData.level == 3">
          <el-select v-model="formData.buttonType" placeholder="请选择">
            <el-option
              v-for="item in buttonTypes"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="排序值" prop="sort">
          <el-input-number v-model="formData.sort" controls-position="right"
                           style="width: 100px"></el-input-number>
          <span style="margin-left:5px">值越小越靠前</span>
        </el-form-item>
       
      </el-form>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="text" @click="close">取 消</el-button>
      <el-button type="primary" @click="submit" :loading="loading">{{loading?'提交中...':'确认提交'}}</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {add} from "@/api/modules/menu";

  export default {
    name: "eit",
    props: {
      visible: {
        type: Boolean
      },
      level: {
        type: Number
      },
      parent: {
        type: Object
      }
    },
    data() {
      return {
        loading: false,
        titleMap: {
          1: '添加顶级菜单',
          2: '添加子节点',
          3: '添加子节点'
        },
        showParent: false,
        buttonTypes: [],
        formData: {
          buttonType: "",
          title: "",
          icon: "",
          path: "",
          parentId: "",
          description:"",
          sort: 0,
          level: 0,
          component:'',
          status: 1
        },
        rules: {
          title: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          //path: [
          //  {required: true, message: '请输入路径', trigger: 'blur'}
          //],
          description: [
            {required: true, message: '请输入产品描述', trigger: 'blur'}
          ],
          icon: [
            {required: true, message: '请选择图标', trigger: 'blur'}
          ],
          buttonType: [
            {required: true, message: '请选择按钮类型', trigger: 'blur'}
          ]
         
        },
        refresh: false
      }
    },
    mounted() {
      this.formData.level = this.level
      
      this.buttonTypes = this.$store.state.dicMap[this.gl_btn]
      //console.log( this.formData.level)
    },
    methods: {
      close() {
        this.$emit('close', this.refresh)
      },
      submit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.loading = true
            this.formData.parentId = this.parent.id
            add(this.formData).then(res => {
              this.loading = false
              if (res.success) {
                this.refresh = true
                this.toast(res.message);
                this.close()
              }else{
                this.toast(res.message,'error')
              }
            })

          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
