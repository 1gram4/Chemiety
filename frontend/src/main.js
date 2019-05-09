// === DEFAULT / CUSTOM STYLE ===
// WARNING! always comment out ONE of the two require() calls below.
// 1. use next line to activate CUSTOM STYLE (./src/themes)
// require(`./themes/app.${__THEME}.styl`)
// 2. or, use next line to activate DEFAULT QUASAR STYLE
require(`quasar/dist/quasar.${__THEME}.css`)
// ==============================

// Uncomment the following lines if you need IE11/Edge support
// require(`quasar/dist/quasar.ie`)
// require(`quasar/dist/quasar.ie.${__THEME}.css`)

import axios from 'axios'
import Vue from 'vue'
import router from './router'
import Quasar, * as All from 'quasar'
// 设置cookie
Vue.prototype.setCookie = (cName, value, expiredays) => {
  var exdate = new Date()
  exdate.setDate(exdate.getDate() + expiredays)
  document.cookie = cName + '=' + escape(value) + ((expiredays == null) ? '' : ';expires=' + exdate.toGMTString())
}
// 获取cookie
function getCookie (name) {
  var arr, reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
  arr = document.cookie.match(reg)
  if (arr) {
    return (arr[2])
  }
  else {
    return null
  }
}
Vue.prototype.getCookie = getCookie
// 删除cookie
Vue.prototype.delCookie = (name) => {
  var exp = new Date()
  exp.setTime(exp.getTime() - 1)
  var cval = getCookie(name)
  if (cval != null) {
    document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString()
  }
}
axios.defaults.withCredentials = true
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(Quasar, {
  components: All,
  directives: All
}) // Install Quasar Framework

if (__THEME === 'mat') {
  require('quasar-extras/roboto-font')
}
import 'quasar-extras/material-icons'
// import 'quasar-extras/ionicons'
// import 'quasar-extras/fontawesome'
// import 'quasar-extras/animate'

Quasar.start(() => {
  /* eslint-disable no-new */
  new Vue({
    el: '#q-app',
    router,
    render: h => h(require('./App').default)
    // watch: {
    //   '$route': 'checkLogin'
    // },
    // created () {
    //   this.checkLogin()
    // },
    // methods: {
    //   checkLogin () {
    //     // 检查是否存在session
    //     // cookie操作方法在源码里有或者参考网上的即可
    //     if (!this.getCookie('role')) {
    //       // 如果没有登录状态则跳转到登录页
    //       this.$router.push('/login')
    //     }
    //     // else {
    //     //   // 否则跳转到登录后的页面
    //     //   this.$router.push('/')
    //     // }
    //   }
    // }
  })
})
