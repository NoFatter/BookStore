<template>
  <div>
    <el-header>
      <store_navigate></store_navigate>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
import store_navigate from '@/components/storePage/Store_navigate.vue';
import RecommendBook from "@/components/storePage/RecommendBook";
import AllBook from "@/components/storePage/AllBook";
export default {
  name: "StorePage.vue",
  components:{RecommendBook, store_navigate,AllBook}
}
</script>

<style scoped>
.el-header{
  margin: 0;
  padding: 0;
}
</style>
