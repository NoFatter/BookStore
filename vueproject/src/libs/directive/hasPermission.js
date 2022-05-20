const hasPermission = {
  install(Vue, options) {
    Vue.directive('has', {
      inserted(el, binding, vnode) {
       
        let permTypes = vnode.context.$route.meta.permTypes
        //console.log(binding.value)
        //console.log(permTypes)
        if (!permTypes.includes(binding.value)) {
          el.parentNode.removeChild(el)
        }
      }
    })
  }
}

export default hasPermission
