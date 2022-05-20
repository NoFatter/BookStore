import util from '@/libs/util'

const methods = {
    columnFormat(value,ary){
         for(let item of ary){
            if (item.value == value) {
                return item.name
              } 
        }
        
        return 'UNKNOW'
    },
    tableColummEmpty(row,column,cellValue){
        if(util.isEmpty(cellValue)){
          return '--'
        }else{
          return cellValue
        }
    },
    navigate(link) {
        this.$router.push(link)
      },
    toast(msg,type){
        var strType = 'success'
        if(type == 2){
            strType = 'error'
        }else if(type == 3){
            strType = 'info'
        }
        this.$message({
            showClose: true,
            message: msg,
            type: strType
          })
    }
}


export default{
    data(){
        return{
            schoolName:'mxmut',
            gl_useful:'useful',
            gl_btn:'button',
            gl_btn_add:'add',
            gl_btn_useful:'myuseful',
        }
    },
    methods: {
        ...methods
    }
}