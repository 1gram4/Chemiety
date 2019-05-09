<template lang="pug">
div.row.justify-center.layout
  div.row.col-md-8.col-lg-8
    div.col-12
      h4.caption 课程公告
      //info card
      div.card-padding(v-for= "(item,index) in info")
        q-card.bg-white
          q-card-title
            big
              q-icon(name="face" color="info" style="padding-right: 20px;")
            |{{ item.title }}
          q-card-separator
          q-item.content-padding
            q-item-main
                q-item-tile.text(label='') {{ item.content }}
          q-card-separator
          q-card-main.text-faded 发布者：{{item.user.name}}&nbsp&nbsp&nbsp发布时间：{{item.createDate}}
          q-card-separator


</template>

<script>
import api from '../api/api'
// import axios from 'axios'
export default {
  name: 'info',
  data () {
    return {
      info: []
    }
  },
  created () {
    this.getAllInfo()
  },
  methods: {
    getAllInfo () {
      api.getAllInfo().then(res => {
        this.info = res.data.result
        console.log(this.info)
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style lang="stylus">
  .card-separator
    margin-bottom 0
  .card-title
    font-size 18px
  .layout
    padding 10px 0 0 0
    background-color rgb(244,244,244)
  .text
    word-wrap:break-word;
  .card-padding
    padding-bottom 10px
  .content-padding
    padding-bottom 20px
    padding-top 20px
</style>
