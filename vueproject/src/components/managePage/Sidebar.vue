<template>
  <aside class="aside">
    <div class="menu-toggle" @click.prevent="bindCollapseChange">
      <i class="basics icon-outdent" v-show="!isCollapse" title="收起"></i>
      <i class="basics icon-indent" v-show="isCollapse" title="展开"></i>
    </div>
    <el-menu
      class="el-menu-vertical"
      background-color="#FFFFFF"
      text-color="#000000"
      :collapse="isCollapse"
      :unique-opened="true">
        <template v-for="(item,i) in menuList">
        <el-submenu :index="i.toString()" :key="i" v-if="item.children&&item.children.length>=1">

          <template slot="title">
            <i :class="_icon(item.icon)"></i>
            <span slot="title" class="submenu">{{item.title}}</span>
          </template>

          <el-menu-item :index="(`${i}${j}`).toString()" :key="parseInt(`${i}${j}`)"
                        @click="_navigate(child.path)" v-for="(child,j) in item.children">
            <span class="child-title">{{child.title}}</span>
          </el-menu-item>
        </el-submenu>

        <el-menu-item :index="i.toString()" :key="i" @click="_navigate(item.path)" v-else>
          <i :class="_icon(item.icon)"></i>
          <span slot="title">{{item.title}}</span>
        </el-menu-item>
        </template>
    </el-menu>
  </aside>
</template>
<script>
  import '@/assets/styles/basics-icon.css'
  import {mapState, mapMutations} from 'vuex'
  import util from '@/libs/util'

  export default {
    data() {
      return {
        menus: [],
        isCollapse: false
      }
    },
    computed: {
      menuList() {
        return this.$store.state.app.menuList
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      init() {
       // this.$store.commit('GET_DEFAULT_MENU_ACTIVE')
      },
      parseInt(str) {
        return parseInt(str)
      },
      bindCollapseChange() {
        this.isCollapse = !this.isCollapse;
      },
      _navigate(name) {
       // this.$store.commit('SET_DEFAULT_MENU_ACTIVE', index)
        this.navigate(name)
      },
      _icon(icon) {
        if (util.isEmpty(icon)) {
          return 'el-icon-menu'
        } else if (icon.startsWith('self-')) {
          return `iconfont ${icon}`
        } else {
          return `el-icon-${icon}`
        }
      }
    }
  }
</script>

<style scoped>
  .aside{
    background: #FFFFFF;
    overflow-y: scroll;
    height: 500px;
  }
  i {
    font-size: 16px !important; /*no*/
    color: #FFFFFF;
  }
  .iconfont {
    vertical-align: middle;
    margin-right: 5px; /*no*/
    width: 24px; /*no*/
    text-align: center;
  }

  .menu-toggle i {
    font-size: 16px !important; /*no*/
  }

  .submenu {
    font-weight: bold;
  }

  .child-title {
    padding-left: 12px; /*no*/
  }
</style>
