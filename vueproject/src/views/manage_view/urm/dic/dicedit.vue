<template>
  <el-dialog :title="title" :visible="visible" :close-on-click-modal="false"
             :before-close="handleClose" width="500px" append-to-body>
    <el-form :model="formData" :rules="rules" ref="formData" label-width="80px">
      <el-form-item label="字典name" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="字典value" prop="value">
        <el-input v-model="formData.value"></el-input>
      </el-form-item>
     
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="text" @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="handleSubmit"
                 :loading="loading">{{loading?'提交中...':'确认提交'}}</el-button>
    </span>
  </el-dialog>
</template>

<script>
 import {add} from '@/api/modules/dic'
  export default {
    name: "edit",
    props: {
      visible: {
        type: Boolean
      },
      title: {
        type: String,
        default: '新增'
      },
      defaultFormData: {
        type: Object
      },
      dictypeId:{
        type:String
      }
    },
    data() {
      return {
        loading: false,
        
        formData: {
          name: '',
          value:''
        },
       
        
        refresh: false,
        rules:{
             name: [
                { required: true,message: '请填写字典name', trigger: 'blur' },
               
              ],
            value: [
                { required: true,message: '请填写字典value', trigger: 'blur' }
              ]
        }
      }
    },
    mounted() {
     
      if (JSON.stringify(this.defaultFormData) == "{}") {

      }else{
        //console.log('before:'+this.formData)
        this.formData = JSON.parse(JSON.stringify(this.defaultFormData))
       // console.log('after:'+this.formData)
      }
    },
    methods: {
      
      handleClose() {
        this.$emit('close',this.refresh)
      },
      handleSubmit() {

        this.$refs.formData.validate(valid => {
          if (valid) {
            
             add({    
                ...this.formData,
                dictypeId:this.dictypeId
              }).then(res =>{
                  this.refresh = true
                  this.handleClose()
              }).catch(err =>{
                  this.toast(err,2)
              })
           
          }
        })


       
        
      }
    }
  }
</script>

<style scoped>

</style>
