<template>
  <div>
    <RecommendBook></RecommendBook>
    <AllBook></AllBook>
  </div>

</template>

<script>
import RecommendBook from "@/components/storePage/RecommendBook";
import AllBook from "@/components/storePage/AllBook";

export default {
  name: "firstPage.vue",
  components:{RecommendBook,AllBook}
}
</script>

<style scoped>

</style>
