<template>
  <div>
    <div>
      <el-divider>有时伤害，有时相助，人们总是在不经意的时候与他人的人生紧密相连</el-divider>
      <el-row>
        <el-card class="commodity"
                 v-for="(book,index) in books"
                 :key="book.id">
          <div class="book_card">
            <h3>{{book.title||"null"}}</h3>
            <el-image
                style="width: 100px; height: 120px"
                :src="require(`@/assets/images/book_img/${book.etc.img}`)"
                fit="fill">
            </el-image>
            <p class="author">{{book.etc.author||"null"}}</p>
            <p class="money">￥{{book.price||"null"}}</p>
            <el-button class="getDetail" @click="getDetail(book)">查看详情>></el-button>
            <br>
            <el-button
                round
                icon="el-icon-star-off"
                v-if="isCollects[index]"
                @click="delUserCollect(book.id,index)"
                class="collect-button-remove">
            </el-button>
            <el-button
                round
                icon="el-icon-star-on"
                v-else @click="addUserCollect(book.id,index)"
                class="collect-button-add">
            </el-button>
          </div>
        </el-card>
      </el-row>
    </div>
    <div>
      <el-divider>青春是一个短暂的美梦, 当你醒来时, 它早已消失无踪</el-divider>
      <el-pagination
          class="selectPage"
          @current-change="handleCurrentChange"
          :current-page="page"
          background
          layout="prev, next"
          :total="total"
          :page-size="pageSize"
          v-if="total!==0">
      </el-pagination>
    </div>
    <detail :visible='visible' v-if='visible' :title='title' :defaultFormData='obj' @close='close'>
    </detail>
  </div>
</template>

<script>
import {getAllBookInfos, page} from "@/api/modules/book_info";
import {add, delCollect, getCollectByUserId} from "@/api/modules/user_collect";
import Vue from "vue";
import Detail from "@/components/storePage/Detail";
export default {
  name: "AllBook",
  components:{Detail},
  mounted() {
    this.InitPages();
  },
  data(){
    return{
      obj:{},
      title:'',
      visible:false,
      total:0,
      books:[],
      collects:[],
      isCollects:[],
      formData:{
        bookId:'',
        userId:'',
      },
      page:1,
      pageSize:6,
      searchForm:{
        title:'',
      },
    }
  },
  methods: {
    handleCurrentChange(val){
      this.page = val;
      this.InitPages();
      this.isCollects=[];
    },
    getTotal(){
      getAllBookInfos().then(res=>{
        this.total = res.data.length
      }).catch(err=>{
        this.total(err,2)
      })
    },
    InitPages(){
      let self = this;
      page({
        pageNum:this.page,
        pageSize:this.pageSize,
        title: this.title,
      }).then(res=>{
        self.books = res.data;
        return getCollectByUserId();
      })
      .then(res=>{
        self.collects = res.data;
      })
      .then(function (){
        for(let book of self.books) {
          let res = false;
          for (let collect of self.collects) {
            if (book.id === collect.bookId) {
              res = true;
              break;
            }
          }
          self.isCollects.push(res);
        }
        return self.getTotal();
      }).catch(err=>{
        this.toast(err,2)
      })
    },
    addUserCollect(id,index){
      this.formData.bookId = '';
      this.formData.bookId = id;
      add({
        ...this.formData
      }).then(res=>{
        Vue.set(this.isCollects,index,true)
        this.toast("已加入收藏！")
      }).catch(err=>{
        this.toast(err,2)
      });
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
    },
    getDetail(obj){
      console.log(obj)
      this.obj = obj;
      this.visible = true;
      this.title = obj.title;
    },
    close(){
      this.visible = false;
    }
  }
}
</script>

<style scoped>
.commodity{
  margin-right: 5%;
  margin-left: 5%;
  margin-bottom: 2%;
  width: 400px;
  height: 340px;
  float: left;
}
.collect-button-remove{
  background-color: #F9D964;
  position: relative;
  left: 200px;
  bottom: 290px;
}
.collect-button-add{
  background-color: #FFFFFF;
  position: relative;
  left: 200px;
  bottom: 290px;
}
.collect-button-add:hover{
  color: #F9D964;
 }
/*.author{*/
/*  text-align: left;*/
/*}*/
.money{
  text-align: right;
  color: #ffbf00;
  font-weight: bold;
}
.getDetail{
  float: right;
}
</style>
