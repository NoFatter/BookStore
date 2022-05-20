<template>
  <div>
    <h1>今日推荐</h1>
    <el-carousel :interval="4000" type="card" height="400px">
      <el-carousel-item v-for="item in 3" :key="item">
<!--        <h3 class="medium">-->
<!--          {{item}}-->
<!--        </h3>-->
        <el-image
            :src="require(`@/assets/images/1.jpg`)"
            fit="fill">
        </el-image>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "RecommendBook",
  data(){
    return{
      imgs:[
          "1.jpg",
          "2.jpg",
          "3.jpg",
      ]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
