<template>
  <div>
    <div>
      <h1>我的收藏夹</h1>
    </div>
    <div>
      <el-card class="collect_book"
               v-for="(book,index) in books"
               :key="book.id"
                v-if="isCollects[index]">
        <el-row>
          <el-col :span="10">
            <div class="book_basic">
              <h2 class="book_title">{{book.title}}</h2>
              <p class="book_author">作者：{{book.etc.author}}</p>
              <p>
                <el-image
                    style="width: 100px; height: 120px;float: left"
                    :src="require(`@/assets/images/book_img/${book.etc.img}`)"
                    fit="fill">
                </el-image>
              </p>
            </div>
          </el-col>
          <el-col :span="10">
            <h3>书籍介绍</h3>
            <P>{{book.etc.info}}</P>
            <br>
            <br>
            <p class="book_price">￥{{book.price}}</p>
            <el-button
                round
                icon="el-icon-star-off"
                @click="delUserCollect(book.id,index)"
                class="collect-button-remove">
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import {delCollect, getCollectByUserId} from "@/api/modules/user_collect";
import {getBookInfoByBookId} from "@/api/modules/book_info";
import Vue from "vue";

export default {
  name: "collect.vue",
  mounted() {
    this.initPage();
  },
  data () {
    return {
      collects:[],
      bookIds:[],
      bookIds_String:"",
      books:[],
      count: 0,
      isCollects:[],
    }
  },
  methods: {
    initPage(){
      let self = this;
      getCollectByUserId().then(res=>{
        self.collects = res.data;
        for(let collect of self.collects){
          self.bookIds.push(collect.bookId)
        }
      }).then(function (){
        for(let i=0;i<self.bookIds.length;i++){
          getBookInfoByBookId({
            id:self.bookIds[i]
          }).then(res=>{
            self.books.push(res.data[0])
            self.isCollects.push(true);
          })
        }
      })
      .catch(err=>{
        this.toast(err,2)
      })
    },
    delUserCollect(id,index){
      delCollect({
        bookId:id,
      }).then(res=>{
        Vue.set(this.isCollects,index,false)
        this.toast("已移除收藏")
      }).catch(err=>{
        this.toast(err,2)
      })
    }
  }
}
</script>

<style scoped>
.collect_book{
  margin-bottom: 5%;
}
.book_basic{
  text-align: left;
}
.book_price{
  color: #ffbf00;
  font-weight: bold;
  text-align: right;
}
.collect-button-remove{
  background-color: #F9D964;
  position: relative;
  left: 600px;
}
</style>
