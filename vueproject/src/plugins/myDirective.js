// export function myFocus(el){
//     el.focus()
// }

// export function myDrag(el){
//     function myMD(el, ev){
//         //组件的原始的left 和top
//       var ox = el.offsetLeft
//       var oy = el.offsetTop
//       //点击事件发生时的 left和top
//       var ex = ev.clientX
//       var ey = ev.clientY

//        function myMM(ev){
//           //鼠标移动的事件发生时的 left 和top
//           var cx = ev.clientX
//           var cy = ev.clientY

//           var left = ox - ex + cx
//           var top = oy - ey + cy

//           el.style.left = left + "px";
//           el.style.top = top + "px";

//           //console.log(left+":"+top)
//       }

//       function myMU(ev){
//           document.removeEventListener('mousemove',myMM,false)
//           document.removeEventListener('mouseup',myMU,false)
//       }
      
//       document.addEventListener('mousemove',myMM,false)
//       document.addEventListener('mouseup',myMU,false)
//   }

//   el.addEventListener('mousedown',myMD.bind(null,el),false)
// }


function myMD(el, ev){
    //组件的原始的left 和top
    var ox = el.offsetLeft
    var oy = el.offsetTop
    //点击事件发生时的 left和top
    var ex = ev.clientX
    var ey = ev.clientY

    function myMM(ev){
        //鼠标移动的事件发生时的 left 和top
        var cx = ev.clientX
        var cy = ev.clientY

        var left = ox - ex + cx
        var top = oy - ey + cy

        el.style.left = left + "px";
        el.style.top = top + "px";

        //console.log(left+":"+top)
    }

    function myMU(ev){
        document.removeEventListener('mousemove',myMM,false)
        document.removeEventListener('mouseup',myMU,false)
    }
    
    document.addEventListener('mousemove',myMM,false)
    document.addEventListener('mouseup',myMU,false)
}
 


const myDirective ={
    myFocus(el){
        el.focus()
    },
    myDrag(el){
        el.addEventListener('mousedown',myMD.bind(null,el),false)
    }
}

export default{
    ... myDirective
}