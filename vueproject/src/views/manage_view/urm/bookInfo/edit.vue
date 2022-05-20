<template>
  <el-dialog :title="title" :visible="visible" :close-on-click-modal="false"
             :before-close="handleClose" width="500px">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="80px">
      <el-form-item label="书名" prop="title">
        <p>{{formData.title}}</p>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="formData.etc.author"></el-input>
      </el-form-item>
      <el-form-item label="介绍" prop="info">
        <el-input type="textarea" v-model="formData.etc.info"></el-input>
      </el-form-item>
      <el-form-item label="图片" prop="img">
        <el-upload
            class="upload-demo"
            action="api/soft/bookImg/save"
            :limit="1"
            :on-exceed="handleExceed"
            :on-success="handleSuccess">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
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
import {save} from "@/api/modules/book_info";
export default {
  name: "edit",
  props:{
    visible:{
      type:Boolean,
    },
    title:{
      type:String,
      default:'编辑',
    },
    defaultFormData: {
      type: Object
    },
    rules:{
    },
  },
  data(){
    return{
      loading: false,
      formData: {
        title: '',
        etc:{
          bookId:'',
          author:'',
          updateTime:'',
          createUser:'',
          info:'',
          img:'',
        },
      },
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
  methods:{
    handleClose() {
      this.$emit('close')
    },
    handleSubmit(){
      save({
        ...this.formData.etc
      }).then(res=>{
        this.handleClose();
        this.toast("编辑成功！");
      }).catch(err=>{
        this.toast(err,2)
      })
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleSuccess(res){
      this.formData.etc.img = res.data;
    }
  }
}
</script>

<style scoped>

</style>
