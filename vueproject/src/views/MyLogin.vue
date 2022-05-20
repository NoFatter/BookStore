<template>
  <div>
    <div class="background">
      <el-image
          style="height: 100%;width: 100%" :src="imgUrl" fit="fill">
      </el-image>
    </div>
    <div class="login">
      <h1>登录</h1>
      <el-form :model="formData" status-icon :rules="rules" ref="formData">
        <div class="login_box">
          <label>Username</label>
          <el-form-item prop="username">
            <el-input type="text" v-model="formData.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </div>
        <div class="login_box">
          <label>Password</label><br/>
          <el-form-item prop="password">
            <el-input  type="password" v-model="formData.password" placeholder="请输入密码"></el-input>
          </el-form-item>
        </div>
        <br/>
        <el-button round @click="submit('formData')">登录</el-button>
        <br/>
        <hr/>
        <router-link to="/forgetPassword">>>>我忘记了密码</router-link>丨
        <router-link to="/myRegister">>>>前往注册</router-link>
      </el-form>
    </div>
<!--   <el-button type="primary" @click="clear()">清空localstorage</el-button>-->
  </div>
</template>
<script>
import util from "@/libs/util"
import {login,register} from "@/api/modules/user"
import {setStore,getStore,removeStore} from "@/libs/storage"
import {setCookie} from "@/libs/cookie"
export default {
  name: 'MyLogin',
  data(){
    return{
      imgUrl: require('../assets/images/background.jpg'),
      formData:{
        username:'',
        password:'',
      },
      rules:{
        username: [
          { required: true,message: '请填写用户名', trigger: 'blur' },
        ],
        password: [
          { required: true,message: '请填写密码', trigger: 'blur' },
        ]
      }
    }
  },
  
  methods:{
   clear(){
    removeStore("token")
   },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login({
            ...this.formData
          }).then(res => {
            //res = Result
            setCookie("user",JSON.stringify(res.data.user))
            let token = res.data.token
            setStore("token",token)
            util.initRouter(this)
            //console.log(getStore("token"))
          }).catch(err =>{
            this.toast(err,2)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>
<style >
.background{
  position: absolute;
  z-index: -1;
}
.login{
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
.login h1{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
}
.login_box{
  margin: 10px;
}
.login_box label{
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