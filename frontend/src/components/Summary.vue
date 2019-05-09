<template lang="pug">
div.row.justify-center.layout
  div.row.col-md-10.col-lg-10
    div.col-sm-12.col-md-4.col-lg-4
      //info card
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="warning", color='warning')
          q-item-main
              q-item-tile.card-title(label='') 公告
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-card-main
          br
          p {{ content }}
        q-card-separator.card-separator
        q-item(link='', to='/info')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看所有公告
      // summary card
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="school")
          q-item-main
              q-item-tile.card-title(label='') 课程简介
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-item
          q-item-side
          q-item-main
            q-item-tile(label='') 课程名称
            q-item-tile(sublabel='') 化学与社会
        q-item(link='', to='/teachers')
          q-item-side
          q-item-main
            q-item-tile(label='') 教师名称
            q-item-tile(sublabel='') 陆文聪、李敏杰等
        q-card-separator.card-separator
        q-item(link='', to='/introduction')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看课程详情
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="assignment")
          q-item-main
            q-item-tile.card-title(label='') 考试
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-item
          q-item-side
          q-item-main
            q-item-tile(label='') 考试方式
            q-item-tile(sublabel='') 期末演讲(30%) + 开卷考试(70%)
        q-item
          q-item-side
          q-item-main
            q-item-tile(label='') 演讲评分
            q-item-tile(sublabel='') 逻辑 + 内容 + 讲解 + 制作
        q-card-separator.card-separator
        q-item(link='', to='/rating')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看评分详情

    div.col-sm-12.col-md-4.col-lg-4
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="subject")
          q-item-main
              q-item-tile.card-title(label='') 课程安排
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-item(link='', to='/outline')
          q-item-side
          q-item-main
            q-item-tile(label='') 每周学习内容
            q-item-tile(sublabel='', color='secondary') 请自行查看课程大纲
        q-item(link='', to='/info')
          q-item-side
          q-item-main
            q-item-tile(label='') 每周通知
            q-item-tile(sublabel='' color='secondary') 请自行查看每周公告
        q-card-separator.card-separator
        q-item(link='', to='/outline')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看完整课程安排
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="view list")
          q-item-main
            q-item-tile.card-title(label='') 成果展示
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-list-header 优秀成果
          p(v-if='shows.length == 0') 暂无成果可以显示
        q-item(v-for='show in shows', :key="slides.name")
          q-item-side
            q-item-tile(icon='insert chart')
          q-item-main
            q-item-tile(label='') {{show.name}}
          q-item-side
            q-btn(icon='file download', flat='' round='' small='' @click='downloadFile(show.fileId)')
        q-card-separator.card-separatorr
        q-item(link='', to='/gallery')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看更多学生成果展示

    div.col-sm-12.col-md-4.col-lg-4
      // gallery card

      //context card
      q-card.bg-white
        q-item
          q-item-side
            big
              q-item-tile(icon="view list")
          q-item-main
              q-item-tile.card-title(label='') 课程内容
          q-item-side
            q-item-tile(right='', icon='more vert')
        q-list-header 课件
          p(v-if='slides.length == 0') 暂无课件可以显示
        q-item(v-for='slide in slides', :key="slides.name")
          q-item-side
            q-item-tile(icon='insert chart')
          q-item-main
            q-item-tile(label='') {{slide.name}}
            q-item-tile(sublabel='') {{slide.description}}
          q-item-side
            q-btn(icon='file download', flat='' round='' small='' @click='downloadFile(slide.fileId)')
        q-list-header 其他文档
          p(v-if='docs.length == 0') 暂无文档可以显示
        q-item(v-for='doc in docs' :key='docs.desciption')
          q-item-side
            q-item-tile(icon='insert drive file')
          q-item-main
            q-item-tile(label='') {{doc.name}}
            q-item-tile(sublabel='') {{doc.description}}
          q-item-side
            q-btn(icon='file download',flat='' round='' small='' @click='downloadFile(doc.fileId)')
        q-card-separator.card-separator
        q-item(link='', to='/context')
          q-item-side
            q-item-tile(icon="arrow forward")
          q-item-main
            q-item-tile(label='') 查看所有课程内容





</template>

<script>
import api from '../api/api'
export default {
  name: 'summaryAll',
  data () {
    return {
      content: '',
      slides: [],
      docs: [],
      shows: []
    }
  },
  created () {
    this.getFirstInfo()
    this.getSlides()
    this.getShow()
  },
  methods: {
    getFirstInfo () {
      api.getAllInfo()
        .then(res => {
          this.content = res.data.result[0].title
        })
    },
    getSlides () {
      api.getAllFiles('edu').then(res => {
        this.slides = res.data.result.filter(item => item.type === 'SLIDE')
        this.docs = res.data.result.filter(item => item.type === 'DOCUMENT')
      }).catch(err => {
        console.log(err)
      })
    },
    getShow () {
      api.getAllFiles('show').then(res => {
        this.shows = res.data.result
      }).catch(err => {
        console.log(err)
      })
    },
    downloadFile (id) {
      window.open('http://chemiety-admin.kastner.cn/api/v1/uploadFiles?id=' + id)
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
</style>
