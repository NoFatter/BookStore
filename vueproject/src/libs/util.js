import {getCookie} from '@/libs/cookie'
import lazyLoading from '@/libs/lazyLoading.js'
import {getAllTreeByUserId} from "@/api/modules/menu"

const util = {}


/**
 * 加载路由
 * @param vm
 */
util.initRouter = function (vm) {
  // 判断用户是否登录
  let user = getCookie('user')
  if (util.isEmpty(user)) {
    return
  }
 const constRoutes = []
 const otherRoutes = []
 const otherRouter = [
   {
     path: '*',
     name: 'error-404',
     meta: {title: '页面不存在-404'},
     component: 'error-page/error-404'
   }
 ]
 getAllTreeByUserId().then(res => {
   
     const menuData = res.data
     
     for (let item of menuData) {
       item.children = item.etc.children

       for(let idxc in item.children){
         let itemc = item.children[idxc]
         itemc.children = itemc.etc.children
       }
     }
     console.log(menuData)
     let firstRouter = {}
     util.initRouterNode(constRoutes, menuData)
     util.initRouterNode(otherRoutes, otherRouter)
     if (constRoutes.length) {
       if (constRoutes[0].children && constRoutes[0].children.length) {
         firstRouter = constRoutes[0].children[0]
         vm.$store.commit('SET_DEFAULT_MENU_ACTIVE', '00')
       } else {
         firstRouter = constRoutes[0]
         vm.$store.commit('SET_DEFAULT_MENU_ACTIVE', '0')
       }
     }
     vm.$store.commit('SET_FIRST_ROUTER', firstRouter)
     // 添加主界面路由
     vm.$store.commit('UPDATE_APP_ROUTER', constRoutes)
     // 添加全局路由
     vm.$store.commit('UPDATE_DEFAULT_ROUTER', otherRoutes)
     // 刷新界面菜单
     vm.$store.commit('UPDATE_MENU_LIST', constRoutes)
     //console.log(firstRouter)
     if (util.isEmpty(firstRouter)) {
       vm.$router.push({path: '/error-401'})
     } else {
       vm.$router.push({path: firstRouter.path})
     }
 })
}
// 生成路由节点
util.initRouterNode = function (routers, data) {
 for (let item of data) {
   let menu = Object.assign({}, item);
   if(menu.component){
     menu.component = lazyLoading(menu.component);
   }
   if (item.children && item.children.length > 0) {
     menu.children = [];
     util.initRouterNode(menu.children, item.children);
   }
   let meta = {};
   // 给页面添加权限、标题、第三方网页链接
   if (menu.level == 2){
     meta.permTypes = [];
     if (menu.children && menu.children.length > 0) {
       
       for(let idxc in menu.children){
         meta.permTypes.push(menu.children[idxc].buttonType)
       }
       
     }
     delete menu.children
     //console.log(menu)
   }
   //meta.permTypes = menu.children ? menu.children : [];
   //meta.name = menu.name ? menu.name : null;
  // meta.url = menu.path ? menu.path : null;
   menu.meta = meta;
   //console.log(meta)
   routers.push(menu);
 }
}
//获取某个对象在数组中的索引值
util.objectIndexInArray = (array, item, param) => {
  let index = -1
  for (let i in array) {
    if (array[i][param] === item[param]) {
      index = i
      break
    }
  }
  return index
}
/**
 * 检测数据是不是除了symbol外的原始数据
 * @param value
 * @returns {boolean}
 */
util.isStatic = (value) => {
  return (
      typeof value === 'string' ||
      typeof value === 'number' ||
      typeof value === 'boolean' ||
      typeof value === 'undefined' ||
      value === null
  )
}

/**
 * 检测数据是不是原始数据
 * @param value
 * @returns {boolean|*}
 */
util.isPrimitive = (value) => {
  return util.isStatic(value) || typeof value === 'symbol'
}

/**
 * 判断数据是不是引用类型的数据 (例如： arrays, functions, objects, regexes, new Number(0),以及 new String(''))
 * @param value
 * @returns {boolean}
 */
util.isObject = (value) => {
  let type = typeof value;
  return value != null && (type == 'object' || type == 'function');
}

/**
 * 检查 value 是否是 类对象。 如果一个值是类对象，那么它不应该是 null，而且 typeof 后的结果是 "object"
 * @param value
 * @returns {boolean}
 */
util.isObjectLike = (value) => {
  return value != null && typeof value == 'object';
}

/**
 * 检查 value 是不是函数
 * @param value
 * @returns {boolean}
 */
util.isFunction = (value) => {
  return Object.prototype.toString.call(value) === '[object Function]'
}

/**
 * 获取数据类型，返回结果为 Number、String、Object、Array等
 * @param value
 * @returns {string}
 */
util.getRawType = (value) => {
  return Object.prototype.toString.call(value).slice(8, -1)
}

/**
 * 判断数据是不是Object类型的数据
 * @param obj
 * @returns {boolean}
 */
util.isPlainObject = (obj) => {
  return Object.prototype.toString.call(obj) === '[object Object]'
}

/**
 * 判断数据是不是数组类型的数据
 * @param arr
 * @returns {boolean}
 */
util.isArray = (arr) => {
  return Object.prototype.toString.call(arr) === '[object Array]'
}

/**
 * 判断数据是不是正则对象
 * @param value
 * @returns {boolean}
 */
util.isRegExp = (value) => {
  return Object.prototype.toString.call(value) === '[object RegExp]'
}

/**
 * 判断数据是不是时间对象
 * @param value
 * @returns {boolean}
 */
util.isDate = (value) => {
  return Object.prototype.toString.call(value) === '[object Date]'
}

/**
 * 判断 value 是不是浏览器内置函数
 * @param value
 * @returns {boolean}
 */
util.isNative = (value) => {
  return typeof value === 'function' && /native code/.test(value.toString())
}

/**
 * 检查 value 是否为有效的类数组长度
 * @param value
 * @returns {boolean}
 */
util.isLength = (value) => {
  return typeof value == 'number' && value > -1 && value % 1 == 0 && value <= Number.MAX_SAFE_INTEGER;
}

/**
 * 检查 value 是否是类数组
 * @param value
 * @returns {boolean|*}
 */
util.isArrayLike = (value) => {
  return value != null && util.isLength(value.length) && !util.isFunction(value);
}

/**
 * 判断数据是否为空
 */
util.isEmpty = function (value) {
  if (value == null) {
    return true;
  }
  if (util.isArrayLike(value)) {
    return !value.length;
  } else if (util.isPlainObject(value)) {
    for (let key in value) {
      if (util.hasOwnProperty.call(value, key)) {
        return false;
      }
    }
    return true;
  }
  return false;
};

/**
 * 记忆函数：缓存函数的运算结果
 * @param fn
 * @returns {function(*=): *}
 */
util.cached = (fn) => {
  let cache = Object.create(null);
  return function cachedFn(str) {
    let hit = cache[str];
    return hit || (cache[str] = fn(str))
  }
}

/**
 * 横线转驼峰命名
 * @param str
 * @returns {*}
 */
util.camelize = (str) => {
  let camelizeRE = /-(\w)/g;
  return str.replace(camelizeRE, function (_, c) {
    return c ? c.toUpperCase() : '';
  })
}

/**
 * 驼峰命名转横线命名：拆分字符串，使用 - 相连，并且转换为小写
 * @param str
 * @returns {string}
 */
util.hyphenate = (str) => {
  let hyphenateRE = /\B([A-Z])/g;
  return str.replace(hyphenateRE, '-$1').toLowerCase()
}

/**
 * 字符串首位大写
 * @param str
 * @returns {string}
 */
util.capitalize = (str) => {
  return str.charAt(0).toUpperCase() + str.slice(1)
}

/**
 * 将属性混合到目标对象中
 * @param to
 * @param _from
 * @returns {*}
 */
util.extend = (to, _from) => {
  for (let key in _from) {
    to[key] = _from[key];
  }
  return to
}

/**
 * 获取浏览器信息
 * @returns {{type: string, version: number}}
 */
util.getExplorerInfo = () => {
  let t = navigator.userAgent.toLowerCase();
  return 0 <= t.indexOf("msie") ? { //ie < 11
    type: "IE",
    version: Number(t.match(/msie ([\d]+)/)[1])
  } : !!t.match(/trident\/.+?rv:(([\d.]+))/) ? { // ie 11
    type: "IE",
    version: 11
  } : 0 <= t.indexOf("edge") ? {
    type: "Edge",
    version: Number(t.match(/edge\/([\d]+)/)[1])
  } : 0 <= t.indexOf("firefox") ? {
    type: "Firefox",
    version: Number(t.match(/firefox\/([\d]+)/)[1])
  } : 0 <= t.indexOf("chrome") ? {
    type: "Chrome",
    version: Number(t.match(/chrome\/([\d]+)/)[1])
  } : 0 <= t.indexOf("opera") ? {
    type: "Opera",
    version: Number(t.match(/opera.([\d]+)/)[1])
  } : 0 <= t.indexOf("Safari") ? {
    type: "Safari",
    version: Number(t.match(/version\/([\d]+)/)[1])
  } : {
    type: t,
    version: -1
  }
}

/**
 * 检测是否为PC端浏览器模式
 * @returns {boolean}
 */
util.isPCBroswer = () => {
  let e = navigator.userAgent.toLowerCase()
      , t = "ipad" == e.match(/ipad/i)
      , i = "iphone" == e.match(/iphone/i)
      , r = "midp" == e.match(/midp/i)
      , n = "rv:1.2.3.4" == e.match(/rv:1.2.3.4/i)
      , a = "ucweb" == e.match(/ucweb/i)
      , o = "android" == e.match(/android/i)
      , s = "windows ce" == e.match(/windows ce/i)
      , l = "windows mobile" == e.match(/windows mobile/i);
  return !(t || i || r || n || a || o || s || l)
}

/**
 * 获取url参数，返回一个对象
 * @returns {any}
 */
util.getUrlParam = () => {
  let url = document.location.toString();
  let arrObj = url.split("?");
  let params = Object.create(null)
  if (arrObj.length > 1) {
    arrObj = arrObj[1].split("&");
    arrObj.forEach(item => {
      item = item.split("=");
      params[item[0]] = item[1]
    })
  }
  return params;
}

/**
 * 全屏
 */
util.toFullScreen = () => {
  let elem = document.body;
  elem.webkitRequestFullScreen
      ? elem.webkitRequestFullScreen()
      : elem.mozRequestFullScreen
      ? elem.mozRequestFullScreen()
      : elem.msRequestFullscreen
          ? elem.msRequestFullscreen()
          : elem.requestFullScreen
              ? elem.requestFullScreen()
              : alert("浏览器不支持全屏");
}

/**
 * 退出全屏
 */
util.exitFullscreen = () => {
  let elem = parent.document;
  elem.webkitCancelFullScreen
      ? elem.webkitCancelFullScreen()
      : elem.mozCancelFullScreen
      ? elem.mozCancelFullScreen()
      : elem.cancelFullScreen
          ? elem.cancelFullScreen()
          : elem.msExitFullscreen
              ? elem.msExitFullscreen()
              : elem.exitFullscreen
                  ? elem.exitFullscreen()
                  : alert("切换失败,可尝试Esc退出");
}

/**
 * 根据客户端宽度适配单位大小
 * @param res
 * @returns {number}
 */
util.adaptiveUnit = (res) => {
  let clientWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
  if (!clientWidth) return;
  let fontSize = 100 * (clientWidth / 1920);
  return res * fontSize;
}

export default util
