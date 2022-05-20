<template>
<div>
  <div class="background">
    <el-image
        style="height: 100%;width: 100%" :src="imgUrl" fit="fill">
    </el-image>
  </div>
  <div class="aboutPage">
    <div>
      <el-avatar :src="myAvatar"></el-avatar>
    </div>
    <h4>NoFat</h4>
    <p>1910705220 软件2班 李昱韬©</p>
    <el-button icon="el-icon-d-arrow-left" @click="goBack">点我返回</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: "About.vue",
  data(){
    return{
      imgUrl:require('../assets/images/background.jpg'),
      myAvatar:require('../assets/images/Nofat.png'),
    }
  },
  methods:{
    goBack(){
      this.navigate("/");
    }
  }
}
</script>

<style scoped>
.aboutPage{
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
.aboutPage h4{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
}
.aboutPage p{
  color: #fff;
  text-align: center;
}
.aboutPage button{
  background: transparent;
  color: #fff;
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