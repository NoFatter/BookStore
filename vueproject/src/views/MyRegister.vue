<template>
  <div>
    <div class="background">
      <el-image
          style="height: 100%;width: 100%" :src="imgUrl" fit="fill">
      </el-image>
    </div>
    <div class="register">
      <h1>注册</h1>
      <el-form :model="formData" status-icon :rules="rules" ref="formData">
        <div class="register_box">
          <label>Username</label><br/>
          <el-form-item prop="username">
            <el-input type="text" v-model="formData.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </div>
        <div class="register_box">
          <label>Password</label><br/>
          <el-form-item prop="password">
            <el-input  type="password" v-model="formData.password" placeholder="请输入密码"></el-input>
          </el-form-item>
        </div>
        <div class="register_box">
          <label>Repeat Password</label><br/>
          <el-form-item prop="repeatPassword">
            <el-input  type="password" v-model="formData.repeatPassword" placeholder="再次输入密码"></el-input>
          </el-form-item>
        </div>
        <div class="register_box">
          <label>昵称</label><br/>
          <el-form-item prop="nickname">
            <el-input  type="nickname" v-model="formData.nickname" placeholder="输入昵称"></el-input>
          </el-form-item>
        </div>
        <br/>
        <el-button round @click="submit('formData')">注册</el-button>
        <br/>
        <hr/>
        <router-link to="/myLogin">
          <span class="el-icon-refresh-left" ></span>我已拥有账号，返回登录页面
        </router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
import {register} from "@/api/modules/user";
import {setCookie} from "@/libs/cookie";
import {setStore} from "@/libs/storage";
import util from "@/libs/util";

export default {
  name: "MyRegister",
  data(){
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.formData.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      imgUrl: require('../assets/images/background.jpg'),
      formData:{
        username:'',
        password:'',
        repeatPassword:'',
        nickname: '',
      },
      rules:{
        username: [
          { required: true,message: '请填写用户名', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' },
        ],
        password: [
          { required: true,message: '请填写密码', trigger: 'blur' },
          { min: 6, message: '长度至少为6位', trigger: 'blur' },
        ],
        repeatPassword: [
          { require: true,message:'请再次输入密码',trigger:'blur'},
          {  validator: validatePass, trigger: 'blur' },
        ],
        nickname: [
          {require:true,message:'请填写昵称',trigger: 'blur'},
          {min: 3,max:9,message: '长度在3到9个字符之间',trigger: 'blur'},
        ],
      }
    }
  },
  methods:{
    submit(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          register({
            ...this.formData
          }).then(res => {
            alert("注册成功")
            this.navigate("/MyLogin")
          }).catch(err =>{
            this.toast(err,2)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.background{
  position: absolute;
  z-index: -1;
}
.register{
  position: absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  /*实现块元素百分比下居中*/
  width:450px;
  padding:50px;
  background: rgba(0,0,0,.8);
  box-sizing:border-box;
  box-shadow: 0px 15px 25px rgba(0,0,0,.5);
  border-radius:15px;
}
.register h1{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
}
.register_box{
  margin: 10px;
}
.register_box label{
  top:0px;
  left:0px;
  padding:10px 0;
  font-size: 16px;
  color:#fff;
  pointer-events:none;
  transition:.5s;
}
a{
  text-decoration: none;
}
a:link{
  color: #ffffff;
}
a:visited{
  color: #ffffff;
}
a:hover{
  color: #ffffff;
}
a:active{
  color: #ffffff;
}
</style>