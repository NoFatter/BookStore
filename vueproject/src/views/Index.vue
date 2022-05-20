<template>
  <div>
    <div class="background">
      <el-image
          style="height: 100%;width: 100%" :src="imgUrl" fit="fill">
      </el-image>
    </div>
    <div class="indexPage">
      <h1 style="margin-bottom: 10%">BookStore</h1>
      <p>读万卷书，行万里路</p>
      <hr>
      <br>
      <el-button-group>
        <el-button @click="goLogin">登录</el-button>
        <el-button @click="goRegister">注册</el-button>
        <el-button @click="goAbout">关于</el-button>
      </el-button-group>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index.vue",
  data(){
    return{
      imgUrl: require('../assets/images/background.jpg'),
    }
  },
  methods:{
    goLogin(){
      this.navigate('/myLogin')
    },
    goRegister(){
      this.navigate('/myRegister')
    },
    goAbout(){
      this.navigate('/myAbout')
    }
  }
}
</script>

<style scoped>
.background{
  position: absolute;
  z-index: -1;
}
.indexPage{
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
.indexPage h1{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
}
.indexPage{
  color: #fff;
  text-align:center;
}
.indexPage button{
  background: transparent;
  color: #fff;
}
</style>