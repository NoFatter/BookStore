<template>
  <el-dialog :title="title" :visible.sync="visible" :close-on-click-modal="false" :before-close="close">
    <div>
      <el-tree
        ref="tree"
        :data="menus"
        :props="props"
        :check-strictly="true"
        :default-checked-keys="menuIds"
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
  import {updateMenuByRoleId,getMenuByRoleId} from "@/api/modules/role_menu";
  import {getAllTree} from "@/api/modules/menu";
  export default {
    name: "role-menu",
    props: {
      visible: {
        type: Boolean
      },
      role: {
        type: Object
      }
    },
    data() {
      return {
        loading: false,
        title: '分配角色菜单',
        props: {
          label: 'title',
          children: 'children'
        },
        menuIds: [],
        menus:[],
        refresh: false
      }
    },
    mounted() {
      this.title = `分配「${this.role.name}」菜单`
      this.handleMenus()
      this.handleMenuIds()
    },
    methods: {
      handleMenus() {
        this.menus=[];
        getAllTree().then(res => {
          if (res.success) {
            for(let idx in res.data){
              let item = res.data[idx]
              item.children = item.etc.children
              for(let idxc in item.children){
                let itemc = item.children[idxc]
                itemc.children = itemc.etc.children
              }
              this.menus.push(item)
            }
            console.log(this.menus)
            this.handleMenuIds()
          }else{
            this.toast(res.messsage,2)
          }
        })
        //console.log(this.menus)

      },
      handleMenuIds() {
        this.menuIds=[]
        getMenuByRoleId({
          id: this.role.id
        }).then(res => {
          if(res.success){
            //console.log(res.data);
            res.data.forEach(item => {
              this.menuIds.push(item)
            })
          }else{
            this.toast(res.message,'error')
          }
          this.$refs.tree.setCheckedKeys(this.menuIds)
        })

        //console.log(this.menuIds)
      },
      close() {
        this.$emit('close', this.refresh)
      },
      submit() {
        const checkedKeys = this.$refs.tree.getCheckedKeys()
        //console.log(checkedKeys)
        this.loading = true
        updateMenuByRoleId({
          id: this.role.id,
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
