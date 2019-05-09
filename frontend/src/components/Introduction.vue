<template lang="pug">
  div.row.justify-center
    div.row.col-md-8.col-lg-8.col-sm-12
      h4.caption 课程简介
      div.col-12.margin-top
        q-card.bg-white
          q-card-title 参考资料
          q-card-separator
          q-list(style="padding-bottom:30px" )
            div(v-for="(doc,index) in docs")
              q-item {{index+1}}.{{doc}}
      div.col-12.margin-top
        q-card.bg-white
          q-card-title 课程详情
          q-card-separator
          q-list(style="padding-bottom:30px" )
            div(v-for="(intro,index) in intros")
              q-item {{index+1}}.{{intro}}

</template>

<script>
  import {
    QCardMedia,
    QCardTitle,
    QParallax,
    QList
  } from 'quasar'
  export default {
    name: 'introduction',
    component: {
      QCardMedia,
      QCardTitle,
      QParallax,
      QList
    },
    data () {
      return {
        docs: [
          '《化学与社会》吉林大学主编，高教版，2011',
          '《化学与社会》   唐有祺等 主编，高等教育出版社',
          '《化学与社会发展》张胜义，安徽科学技术出版社，2001'
        ],
        intros: [
          '课程所需PPT和资料在课程资料页面自行下载',
          '补充资料由老师随课程上传',
          '课程随堂要求，作业信息，考试信息等都将由老师发布在课程公告页面，请及时查看'
        ]
      }
    }
  }
</script>

<style lang="stylus">
  .layout-padding
    padding-top 50
  .itemTitle
    font-weight bold
</style>
