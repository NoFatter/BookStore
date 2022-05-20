<template>
  <div>
    <el-table
        v-loading="loading"
        :border="border"
        :stripe="stripe"
        :header-cell-style="headerCellStyle"
        highlight-current-row
        :data="tableData"
        element-loading-text="加载中..."
        :tree-props="treeProps"
        :row-key="rowKey"
        :default-expand-all='defaultExpandAll'
        @selection-change="handleSelectionChange"
        @row-click="handleRowClick"
        @sort-change="handleSortChange">
        <el-table-column
          type="selection"
          width="55"
          >
      <!--<el-table-column
          align="center"
          type="index">-->
      </el-table-column>
      <slot name="tb-columns"></slot>
    </el-table>
    <el-row class="mt-15">
      <el-col class="flex justify-end">
        <el-pagination
            background
            layout="total, prev, pager, next, sizes, jumper"
            :total="total"
            :page-size="page.pageSize"
            :page-sizes="pageSizes"
            :current-page='current'
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import util from "@/libs/util";

  export default {
    name: "PageTable",
    props: {
      headerCellStyle: {
        type: Object,
        default: () => {
          return {background: '#fafafa', color: '#606266'}
        }
      },
      reserveSelection: {
        type: Boolean,
        default: false
      },
      border: {
        type: Boolean,
        default: false
      },
      stripe: {
        type: Boolean,
        default: false
      },
      pageSize: {
        type: Number,
        default: 5
      },
      pageSizes: {
        type: Array,
        default: () => {
          return [5, 10, 20, 30]
        }
      },
      treeProps: {
        type: Object
      },
      rowKey: {
        type: String,
        default: 'id'
      },
      defaultExpandAll: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        loading: true,
        tableData: [],
        total: 0,
        page: {
          pageSize: 8,
          pageNum: 1,
        },
        sort: {
          sort: 'create_time',
          order: 'DESC'
        },
        current: 1
      }
    },
    mounted() {
      this.page.pageSize = this.pageSize
      this.loadTableData()
    },
    watch: {
      tableData() {
        //数据变化表示加载已完成，关闭table加载动画
        this.loading = false
      },
      pageSize() {
        this.page.pageSize = this.pageSize
      }
    },
    methods: {
      //分页相关事件处理
      handleSizeChange(val) {
        this.page.pageSize = val;
        this.loadTableData()
      },
      handleCurrentChange(val) {
        this.page.pageNum = val;
        this.current = val
        this.loadTableData()
      },
      handleSelectionChange(selection) {
        this.$emit('selection-change', selection)
      },
      handleRowClick(row, event, column) {
        this.$emit('row-click', row, event, column)
      },
      //排序事件
      handleSortChange(column) {
        const prop = column.prop,
            order = column.order == 'ascending' ? 'ASC' : column.order == 'descending' ? 'DESC' : column.order
        if (prop) {
          this.sort = {
            sort: prop,
            order: order
          }
        } else {
          this.sort = {
            sort: 'create_time',
            order: 'DESC'
          }
        }
        this.loadTableData()
      },
      refresh() {
        this.page.pageNum = 1
        this.$emit("refresh", () => {
          this.loadTableData()
        })
      },
      loadTableData(type) {
        if (!util.isEmpty(type) && type == 'reload') {
          this.page.pageNum = 1
          this.current = 1
        }
        this.loading = true
        this.$emit("load-table-data", {
          ...this.page,
          ...this.sort,
        }, (data) => {
          this.loading = false
          this.tableData = data ? data.data : []
          this.total = data ? data.total : 0
        })
      }
    }
  }
</script>
