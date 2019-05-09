<template lang="pug">
  //if you want automatic padding use "layout-padding" class
  div.row.justify-center.layout
    div.row.col-md-8.col-lg-8.col-sm-12
      h4.caption 课程大纲
      div.col-12
        q-card.bg-white(v-for="(item, index) in chapters" :key="index")
          q-item.bg-white
            q-item-side
              big
                q-item-tile(icon="assignment", color="secondary")
            q-item-main
              q-item-tile.card-title(label='', color="secondary") {{ item.name }}
            q-item-side
              q-item-tile(right='', icon='keyboard_arrow_down')
          q-item.bg-white(link='', to='/outline')
            q-item-side
            q-item-main
              q-item-tile.item(label='', v-for="(title, index) in item.directories" :key="index") {{ index+1 }}. {{ title }}

          //q-stepper(vertical='', done="", v-model="currentStep", ref="stepper", color='secondary').bg-white
            q-step(:title='chapter.name',
                    :default="(index == 0) ? true : false",
                    v-for="(chapter, index) in chapters",
                    :key="chapter.name",
                    :name='index')
              // q-list(no-border="")
                q-item(v-for="(directory, index) in chapter.directories" :key="directory.index")
                  q-item-main
                    q-item-tile {{index + 1}} {{directory}}
              // q-stepper-navigation
                q-btn(color="secondary", @click="$refs.stepper.next()") Next
                q-btn(color="secondary", @click="$refs.stepper.previous()", flat="") Back

</template>

<script>
import {
  QStepper,
  QStep,
  QCard,
  QStepperNavigation
} from 'quasar'
export default {
  name: 'outline',
  components: {
    QStepper,
    QStep,
    QStepperNavigation,
    QCard
  },
  data () {
    return {
      currentStep: 1,
      chapters: [
        {
          name: '第一讲  奇妙的微观世界——物质结构基础',
          summary: '第一章主要讲了',
          directories: [
            '微观粒子的波粒二象性',
            '波函数和薛定谔方程',
            '单电子原子的原子结构',
            '多电子原子的原子结构',
            '元素周期表',
            '元素性质的周期性'
          ]
        },
        {
          name: '第二讲  建设美好的家园——化学与环境',
          summary: '第二章主要讲了',
          directories: [
            '概述',
            '大气',
            '水资源'
          ]
        },
        {
          name: '第三讲  人类社会发展的动力——能源',
          summary: '第三章主要讲了',
          directories: [
            '概述',
            '煤',
            '石油',
            '天然气与可燃冰',
            '化学电池',
            '太阳能',
            '氢能',
            '核能',
            '生物质能'
          ]
        },
        {
          name: '第四讲  舌尖上的化学——食品中的化学',
          summary: '第四章主要讲了',
          directories: [
            '农药和化肥',
            '碳水化合物',
            '脂肪和油',
            '蛋白质',
            '维生素和矿物质',
            '香精和香料',
            '食品添加剂',
            '转基因食品'
          ]
        },
        {
          name: '第五讲  工业味精——表面活性剂',
          summary: '第五章主要讲了',
          directories: [
            '表面活性剂',
            '洗涤品与化学',
            '牙膏中的化学',
            '琳琅满目的化妆品'
          ]
        },
        {
          name: '第六讲  文明社会的标志——材料',
          summary: '第六章主要讲了',
          directories: [
            '绪论',
            '高分子的基本概念',
            '聚合物的分类和命名',
            '聚合反应',
            '塑料',
            '纤维',
            '橡胶',
            '高分子发展简史'
          ]
        },
        {
          name: '第七讲  健康卫士——药物与人类健康',
          summary: '第七章主要讲了',
          directories: [
            '引言',
            '药物的发展历史',
            '对含碳分子的研究',
            '手性分子与药物',
            '药物分类',
            '药物设计原理',
            '毒品与戒毒药',
            '抑郁症与药物治疗'
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
.item
  padding 5px 0 5px 0
</style>
