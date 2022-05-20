<template>
  <div>
    <div>
      <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
        <el-col :xl="6" :md="8" :sm="24">
          <el-form-item label="书名" prop="title" >
            <el-input  placeholder="请输入书名" v-model="searchForm.title" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6||24" :md="8||24" :sm="24">
          <el-form-item>
            <div :style=" { float: 'right', overflow: 'hidden' } || {} ">
              <el-button icon="el-icon-search" type="primary">搜索</el-button>
            </div>
          </el-form-item>
        </el-col>
      </el-form>
    </div>

    <div>
      <el-table
          :data="bookInfos"
          style="width: 100%">
        <el-table-column
            align="center"
            prop="title"
            label="书名"
            width="400px">
        </el-table-column>
        <el-table-column
            align="center"
            prop="etc.author"
            label="作者"
            width="400px">
        </el-table-column>
        <el-table-column
            align="center"
            prop="etc.updateTime"
            label="更新时间"
            width="500px">
        </el-table-column>
        <el-table-column
            align="center"
            label="操作"
            width="180px">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-pagination
          @current-change="handleCurrentChange"
          :current-page="page"
          background
          layout="total,prev, pager, next"
          :total="total"
          :page-size="pageSize"
          v-if="total!==0">
      </el-pagination>
    </div>
    <Edit :visible='visible' v-if='visible' :title='title' :defaultFormData='obj' @close='close'></Edit>
  </div>
</template>

<script>
import Edit from '@/views/manage_view/urm/bookInfo/edit'
import {getAllBookInfos, page} from "@/api/modules/book_info";
export default {
  name: "BookInfo",
  components:{Edit},
  data(){
    return{
      total:0,
      title: '',
      visible:false,
      obj:{},
      searchForm:{
        title:'',
      },
      bookInfos:[],
      page:1,
      pageSize:5,
    }
  },
  mounted() {
    this.loadTable();
  },
  methods:{
    handleCurrentChange(val){
      this.page = val;
      this.loadTable();
    },
    loadTable(){
      page({
        pageNum:this.page,
        pageSize:this.pageSize,
        title: this.searchForm.title,
      }).then(res=>{
        this.bookInfos = res.data;
        this.getTotal();
      }).catch(err=>{
        this.total(err,2)
      })
    },
    getTotal(){
      getAllBookInfos().then(res=>{
        this.total = res.data.length
      }).catch(err=>{
        this.total(err,2)
      })
    },
    edit(obj){
      this.visible = true;
      this.title = '编辑';
      this.obj = obj;
    },
    close(){
      this.visible = false;
      this.loadTable();
    },
  }
}
</script>

<style scoped>


</style>
