import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

function load (component) {
  // '@' is aliased to src/components
  return () => import(`@/${component}.vue`)
}

import Summary from '../src/components/Summary'
import Introduction from '../src/components/Introduction'
import Login from '../src/components/Login'
export default new VueRouter({
  /*
   * NOTE! VueRouter "history" mode DOESN'T works for Cordova builds,
   * it is only to be used only for websites.
   *
   * If you decide to go with "history" mode, please also open /config/index.js
   * and set "build.publicPath" to something other than an empty string.
   * Example: '/' instead of current ''
   *
   * If switching back to default "hash" mode, don't forget to set the
   * build publicPath back to '' so Cordova builds work again.
   */

  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),

  routes: [
    {
      path: '/',
      component: Summary
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/introduction',
      component: Introduction
    },
    {
      path: '/outline',
      component: load('Outline')
    },
    {
      path: '/rating',
      component: load('Rating')
    },
    {
      path: '/context',
      component: load('Context')
    },
    {
      path: '/teachers',
      component: load('Teachers')
    },
    {
      path: '/campus',
      component: load('Campus')
    },
    {
      path: '/gallery',
      component: load('Gallery')
    },
    {
      path: '/discussion',
      component: load('Discussion')
    },
    {
      path: '/assignment',
      component: load('Assignment')
    },
    {
      path: '/info',
      component: load('Info')
    },
    {
      path: '/test',
      component: load('Test')
    },
    {
      path: '/about',
      component: load('About')
    },
    // Always leave this last one
    { path: '*', component: load('Error404') }]// Not found

})
