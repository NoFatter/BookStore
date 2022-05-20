<template>
  <el-dialog :title="title" :visible="visible" :close-on-click-modal="false"
             :before-close="handleClose" width="500px">
    <el-form :model="formData"  ref="formData" label-width="80px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
     <el-form-item label="年龄" prop="age">
        <el-input  v-model="formData.age"></el-input>
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
          name: ''
        },
       
        
        refresh: false
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
        this.$emit('close')
      },
      handleSubmit() {
        if(this.title==='新增'){
            this.$emit('addList',this.formData)
        }else{
          this.$emit('updateList',this.formData)
        }
        
      }
    }
  }
</script>

<style scoped>

</style>
