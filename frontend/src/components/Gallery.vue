<template lang="pug">
  div.row.justify-center.layout-padding
    div.row.col-md-10.col-lg-10
      div.col-12
        h4.caption 成果展示
        div.row
          q-search.on-right.col-lg-4.col-md-4.col-sm-4.col-xs-12(float-label="查找主题" v-model='keyword')
        div.row.xs-gutter
          div.col-lg-6.col-md-6.col-sm-12
            q-card.col-12.bg-white
              q-card-media
                q-gallery-carousel(:src="slide1")
              q-card-title 主题：车厢调度
                span(slot="subtitle") @2017冬 第四小组 路人甲
                q-rating(slot="right", :max="5", v-model="stars", color="amber", icon="star")
              q-card-main
                p 栈和队列的应用
                p.text-faded 开始用队列完成进入，利用临时栈完成调度
              q-card-separator
              q-card-actions
                q-btn(flat='', icon="thumb up") 99
                q-btn(flat='', icon="thumb down") 2
                q-btn(flat='', icon="file download") 下载该课件

            q-card.col-12.bg-white
              q-card-media
                q-gallery-carousel(:src="slide2")
              q-card-title 主题：Running + Music = ?
                span(slot="subtitle") @2016春 第二小组 路人甲
                q-rating(slot="right", :max="5", v-model="stars", color="amber", icon="star")
              q-card-main
                p 当音乐和运动相结合
                p.text-faded 开始用队列完成进入，利用临时栈完成调
              q-card-separator
              q-card-actions
                q-btn(flat='', icon="thumb up") 99
                q-btn(flat='', icon="thumb down") 2
                q-btn(flat='', icon="file download") 下载该课件
          div.col-lg-6.col-md-6.col-sm-12
            q-card.col-12.bg-white
              q-card-media
                q-gallery-carousel(:src="slide2")
              q-card-title 主题：Running + Music = ?
                span(slot="subtitle") @2016春 第二小组 路人甲
                q-rating(slot="right", :max="5", v-model="stars", color="amber", icon="star")
              q-card-main
                p 当音乐和运动相结合
                p.text-faded 开始用队列完成进入，利用临时栈完成调
              q-card-separator
              q-card-actions
                q-btn(flat='', icon="thumb up") 99
                q-btn(flat='', icon="thumb down") 2
                q-btn(flat='', icon="file download") 下载该课件


</template>

<script>
  export default {
    name: 'gallery',
    data () {
      return {
        stars: 3.5,
        slide1: [
          'statics/车厢调度/Slide1.JPG',
          'statics/车厢调度/Slide2.JPG',
          'statics/车厢调度/Slide3.JPG',
          'statics/车厢调度/Slide4.JPG',
          'statics/车厢调度/Slide5.JPG',
          'statics/车厢调度/Slide6.JPG',
          'statics/车厢调度/Slide7.JPG',
          'statics/车厢调度/Slide8.JPG'
        ],
        slide2: [
          'statics/Run+Music/Slide1.JPG',
          'statics/Run+Music/Slide2.JPG',
          'statics/Run+Music/Slide3.JPG',
          'statics/Run+Music/Slide4.JPG',
          'statics/Run+Music/Slide5.JPG',
          'statics/Run+Music/Slide6.JPG',
          'statics/Run+Music/Slide7.JPG',
          'statics/Run+Music/Slide8.JPG'
        ],
        keyword: ''
      }
    }
  }
</script>

<style lang="stylus">
  .layout-padding
    padding-top 50
</style>
