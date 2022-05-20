<template>
  <el-dialog :title="title" :visible="visible" :close-on-click-modal="false"
             :before-close="handleClose" width="500px">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="80px">
      <el-form-item label="书名" prop="title">
        <el-input v-model="formData.title"></el-input>
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input v-model="formData.price"></el-input>
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
 import {add} from '@/api/modules/book'
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
    },
    data() {
      return {
        loading: false,
        formData: {
          title: '',
          price:''
        },
        refresh: false,
        rules:{
             title: [
                { required: true,message: '请填写书名', trigger: 'blur' },
                { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }

              ],
            price: [
                { required: true,message: '请填写价格', trigger: 'blur' }
              ]
        },
      }
    },
    mounted() {
      if (JSON.stringify(this.defaultFormData) == "{}") {

      }else{
        this.formData = JSON.parse(JSON.stringify(this.defaultFormData))
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
                ...this.formData
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
