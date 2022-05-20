<template>
  <div>
    <el-row class="container">
      <!--头部-->
      <el-col :span="24" class="top-bar-wrap">
        <div class="flex align-center">
          <div class="top-bar-title" @click="navToHome()">
          </div>
        </div>
        <div class="account top-bar-btn">
          <img src="@/assets/images/avatar.png"
               class="avatar">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link text-white">
              欢迎，{{user.nickname||user.username||'暂无用户名'}} <i class="el-icon-caret-bottom"></i>
            </span>
             <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div @click="visible.up = true">修改密码</div>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/display.css';
  import {getCookie,removeCookie} from '@/libs/cookie'
  import {removeStore} from "@/libs/storage";

export default {
    //components: {UpdatePassword, IconFullScreen},

  data() {
    return {
      visible: {
        up: false, // updatePassword
      },
      user: {
        username: '',
        nickname: '',
      },
      isFullScreen: false
      }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      let user = JSON.parse(getCookie("user") || '{}');
      this.user = user;
    },
    logout() {
      removeStore('token')
      removeCookie('user')
      this.navigate("/")
    },
    navToHome() {
      this.navigate(this.$store.state.app.firstRouter.path)
    },
    close(val) {
      this.visible.up = false
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  display: flex;
  align-items: center;
  cursor: pointer;
  -moz-user-select: none; /*火狐*/
  -webkit-user-select: none; /*webkit浏览器*/
  -ms-user-select: none; /*IE10*/
  -khtml-user-select: none; /*早期浏览器*/
  user-select: none;
  border: none;
  outline: none;
}
.container{
}
.account {
  float: right;
  padding-right: 12px;
}
.avatar {
  height: 30px;
  width: 30px;
  margin-right: 10px;
  margin-left: 30px;
  border-radius: 50%;
}

</style>
