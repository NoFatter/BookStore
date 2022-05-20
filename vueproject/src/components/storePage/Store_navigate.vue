<template>
  <div>
    <el-menu
        :default-active="activeIndex"
        class="store_navi"
        mode="horizontal"
        background-color="#000000"
        text-color="#ffffff"
        active-text-color="#ffffff">
      <el-menu-item index="3">
        <h1 class="title" @click="_navigate('/Store')">BookStore</h1>
      </el-menu-item>
      <el-submenu index="1" style="float: right" class="navi_menu">
        <template slot="title">欢迎，{{user.nickname||user.username||'暂无用户名'}}</template>
        <el-menu-item index="1-1" @click="_navigate('/collect')">我的收藏</el-menu-item>
        <el-menu-item index="1-2">我的购物车</el-menu-item>
        <el-menu-item index="1-3">我的信息</el-menu-item>
        <el-menu-item index="1-4" @click.native="logout">登出</el-menu-item>
      </el-submenu>
      <el-menu-item index="2" style="float: right">消息中心</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {removeStore} from "@/libs/storage";
import {getCookie, removeCookie} from "@/libs/cookie";

export default {
  name: "Store_navigate.vue",
  mounted() {
    this.init()
  },
  data(){
    return{
      logoUrl:require('../../assets/images/bookstore.png'),
      user: {
        username: '',
        nickname: '',
      },
      activeIndex: '1',
    }
  },
  methods:{
    init() {
      let user = JSON.parse(getCookie("user") || '{}');
      this.user = user;
    },
    logout(){
      removeStore('token')
      removeCookie('user')
      this.navigate("/")
    },
    _navigate(name) {
      // this.$store.commit('SET_DEFAULT_MENU_ACTIVE', index)
      this.navigate(name)
    },
  }
}
</script>

<style scoped>
.store_navi{
  height: 60px;
}
.title{
  float: left;
  color: #FFFFFF;
  margin-bottom: 0;
  margin-top: 0.6%;
  margin-left: 5%;
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
