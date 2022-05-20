<template>
  <el-dialog :title="title" :visible.sync="visible" :close-on-click-modal="false" :before-close="close">
    <div>
      <el-tree
        ref="tree"
        :data="roles"
        :props="props"
        :check-strictly="true"
        :default-checked-keys="roleIds"
        node-key="id"
        show-checkbox
        default-expand-all
        highlight-current>
      </el-tree>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="text" @click="close">取 消</el-button>
      <el-button type="primary" @click="submit" :loading="loading">{{loading?'提交中...':'确认提交'}}</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {updateRoleByUserId,getRoleByUserId} from "@/api/modules/user_role";
  import {getAll} from "@/api/modules/role";
  export default {
    name: "user-role",
    props: {
      visible: {
        type: Boolean
      },
      user: {
        type: Object
      }
    },
    data() {
      return {
        loading: false,
        title: '分配用户角色',
        props: {
          label: 'name', //显示的名称用role的name字段进行显示
          children: 'children' //子节点对应的roles中的字段
        },
        roleIds: [], // 默认选中的记录id,表示当前user拥有那些role
        roles:[], // 所有的角色
        refresh: false
      }
    },
    mounted() {
      this.title = `分配「${this.user.username}」角色`
      this.handleRoles()
      this.handleRoleIds()
    },
    methods: {
      handleRoles() {
        this.roles=[];
        getAll().then(res => {
          if (res.success) {
            this.roles = res.data
          }else{
            this.toast(res.messsage,2)
          }
        })
      },
      handleRoleIds() {
        this.roleIds=[]
        getRoleByUserId({
          id: this.user.id
        }).then(res => {
          for(let idx in res.data){
            let item = res.data[idx]
              this.roleIds.push(item.roleId)
            }
           this.$refs.tree.setCheckedKeys(this.roleIds)
        }).catch(err =>{
          this.toast(err,2)
        })

      },
      close() {
        this.$emit('close', this.refresh)
      },
      submit() {
        const checkedKeys = this.$refs.tree.getCheckedKeys()
        //console.log(checkedKeys)
        this.loading = true
        updateRoleByUserId({
          id: this.user.id,
          ids: checkedKeys.join(',')
        }).then(res => {
          this.loading = false
          if(res.success){
            this.refresh = true
            this.toast(res.message)
            this.close()
          }else{
            this.toast(res.message,'error')
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
