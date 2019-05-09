<template lang="pug">
  // Configure "view" prop for QLayout
  div.row.justify-center.layout
    div.row.col-md-8.col-lg-8
      div.col-12
        h4.caption 评分标准
        q-card.bg-white
          q-item(label='' style="padding-top:20px")
            q-tree(
              :model="treeModel",
              contract-html="<i class='material-icons'></i>"
              expand-html="<i class='material-icons'></i>"
            )
</template>

<script>
  import {
    QCard,
    QItem,
    QKnob,
    QIcon,
    QField,
    QTree
  } from 'quasar'
  export default {
    name: 'rating',
    component: {
      QCard,
      QItem,
      QKnob,
      QIcon,
      QField,
      QTree
    },
    data () {
      return {
        treeModel: [
          {
            title: '期末演讲  30%',
            icon: 'record voice over',
            expanded: true,
            children: [
              {
                title: '逻辑  25%',
                expanded: true,
                children: [
                  {
                    title: '演讲逻辑',
                    expanded: true,
                    children: []
                  }
                ]
              },
              {
                title: '内容  25%',
                expanded: true,
                children: [
                  {
                    title: '演讲内容',
                    expanded: true,
                    children: []
                  }
                ]
              },
              {
                title: '讲解  25%',
                expanded: true,
                children: [
                  {
                    title: '讲解水平',
                    expanded: true,
                    children: []
                  }
                ]
              },
              {
                title: '制作  25%',
                expanded: true,
                children: [
                  {
                    title: '制作是否用心',
                    expanded: true,
                    children: []
                  }
                ]
              }
            ]
          },
          {
            title: '考试成绩 70%',
            icon: 'edit',
            expanded: true,
            children: [
              {
                title: '由于课程内容量大面广,所以选择开卷考试 ',
                expanded: true,
                children: []
              }
            ]
          }
        ]
      }
    }
  }
</script>

<style lang="stylus">
  .layout-padding
    padding 50
  .itemTitle
    font-weight bold
  .knob-padding
    padding 40
</style>
