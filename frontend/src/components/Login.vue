<template lang="pug">
div.row.justify-center
  div.row.col-md-6.col-lg-6.col-sm-12
    q-card.bg-white.col-sm-12.col-md-12.col-lg-12(style="height:300px;margin-top:100px")
        q-card-title 登录
        q-card-main
          q-input(v-model='username' float-label='请输入学号/工号')
          q-input(v-model='password' type='password' float-label='请输入密码' @keyup.Enter.native='login')
          q-btn(color='primary' @click='login' style="margin-top:30px;") 登录
</template>

<script>
import axios from 'axios'
import md5 from 'md5'
export default {
  name: 'login',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  computed: {},
  methods: {
    login () {
      axios({
        url: 'http://chemiety.kastner.cn/api/v1/login',
        method: 'POST',
        params: {
          username: this.username,
          password: md5(this.password)
        },
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        withCredentials: true
      })
        .then(res => {
          console.log(res.data)
          axios({
            url: 'http://chemiety.kastner.cn/api/v1/getUserInfo',
            method: 'POST',
            headers: {
              'Content-Type': 'application/json;charset=utf-8'
            },
            withCredentials: true
          })
            .then(res => {
              if (res.data.result.roles === 'TEACHER') {
                window.location.href = 'http://chemiety.kastner.cn/admin/index'
                this.setCookie('role', 'TEACHER', 1)
              }
              else {
                window.location.href = 'http://chemiety.kastner.cn/'
                this.setCookie('role', 'STUDENT', 1)
              }
              console.log(res.data)
            })
            .catch(err => {
              console.log(err)
            })
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="stylus">
</style>
