<template lang="pug">
div.row.justify-center.layout-padding
  div.row.col-md-6.col-lg-6.col-sm-12
    q-card.bg-white.col-sm-12.col-md-12.col-lg-12.row.justify-center
      q-card-title.text-center
        | 化学与社会课程网站
      q-card-main
        blockquote.light-paragraph.text-center
          | 化学与社会课程网站是设立于18年5月的网站，旨在辅助化学与社会课程的教学，帮助同学交流与学习
        p(style="margin-top:40px;")
          | 遇到网站或使用问题，欢迎直接在讨论区发布  
      q-card.col-md-10.bg-white.col-sm-11
        q-list(highlight inset-separator)
          q-item(multiline)
            q-item-side(avatar="statics/zsy.jpg")
            q-item-main(
              label="Kastnerorz "
              label-lines="1"
              sublabel="kastnerorz@gmail.com"
              sublabel-lines="2")
            q-item-side(right)
              q-item-tile(stamp)  |･ω･｀)
          q-item(multiline)
            q-item-side(avatar="statics/zyw.jpg")
            q-item-main(
              label="Iris"
              label-lines="1"
              sublabel="913007768zyw@gmail.com"
              sublabel-lines="2")
            q-item-side(right)
              q-item-tile(stamp) |ૂ﻿•ᴗ•⸝⸝)”

      q-card-main  
        p.text-center(style="margin-top:60px;")
          img(src="https://forthebadge.com/images/badges/built-with-love.svg")
          br
          small(style='color:grey;')
            | 上海大学开源社区 
            br
            | 2018 SHU OpenSourceCommnuity
            br
            |  (￣ж￣) 一本满足!! 
</template>
<script>
export default {
  name: 'login',
  data () {
    return {
    }
  },
  computed: {},
  methods: {
  }
}
</script>
<style lang="stylus">
.layout-padding
  padding-top 20px
</style>