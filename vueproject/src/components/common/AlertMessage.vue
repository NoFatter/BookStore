<template>
  <el-alert
    class="alert-message"
    :closable="false">
    <div slot="title" class="alert-message-wrap">
      <i class="basics icon-info" :style="{'margin-left': revise}"></i>
      <div class="alert-message-content">
        <slot name="content"></slot>
      </div>
    </div>
  </el-alert>
</template>

<script>
  export default {
    name: "AlertMessage",
    props: {
      revise: {
        type: String,
        default: '-10px'
      }
    }
  }
</script>

<style scoped>
  .alert-message {
    border: 1px solid #409EFF;
    background-color: #e6f7ff;
    color: #606266;
    padding: 7px 16px;
  }

  .icon-info {
    color: #409EFF;
    margin-right: 10px;
  }

  .alert-message-wrap {
    display: flex;
    align-items: center;
  }

  .alert-message-content {
    display: flex;
    align-items: center;
    font-size: 14px;
  }
</style>
