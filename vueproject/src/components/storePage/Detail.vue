<template>
  <el-dialog :title="title" :visible="visible" :close-on-click-modal="false"
             :before-close="handleClose" width="800px">
    <div v-if="loadingOver">
      <el-image
          style="width: 100px; height: 120px"
          :src="require(`@/assets/images/book_img/${formData.etc.img}`)"
          fit="fill">
      </el-image>
      <p>{{formData.etc.author}}</p>
      <p>{{formData.etc.info}}</p>
      <el-button @click="addCollect()" v-if="getIsCollect">加入收藏</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {add} from "@/api/modules/user_collect";
export default {
  name: "Detail.vue",
  props:{
    title:{
      type:String,
      default:'',
    },
    visible:{
      type:Boolean,
    },
    defaultFormData:{
      type:Object,
    }
  },
  mounted() {
    if (JSON.stringify(this.defaultFormData) === "{}") {
    }else{
      this.formData = JSON.parse(JSON.stringify(this.defaultFormData))
    }
    this.loadingOver = true;
  },
  data(){
    return{
      loadingOver:false,
      formData: {
        title: '',
        price:'',
        etc:{},
      },
    }
  },
  methods:{
    handleClose() {
      this.$emit('close')
    },
    addCollect(){
      add({
        bookId:this.formData.id
      }).then(res=>{
        this.toast(res.message);
      })
    }
  }
}
</script>

<style scoped>

</style>
