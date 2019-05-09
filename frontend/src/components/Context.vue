<template lang="pug">
  //if you want automatic padding use "layout-padding" class
  div.row.justify-center.layout-padding
    div.row.col-md-8.col-lg-8
      q-card.bg-white.small-margin.col-12
        q-card-title 课件下载
          span(slot="subtitle") 老师上课的PPT
        q-card-separator
        q-list
          div(v-for="(slide,index) in slides")
            q-item(:id="slide",link=''  @click='downloadFile(slide.fileId)')
              q-item-side {{index+1}}
              q-item-main(label-lines="1" :label='slide.title') {{slide.name}}
              q-item-side {{slide.date}}
      q-card.bg-white.small-margin.col-12
        q-card-title 文档下载
          span(slot="subtitle") 文档资料下载
        q-card-separator
        q-list
          div(v-for="(doc,index) in documents")
            q-item(:id="doc",link=''  @click='downloadFile(doc.fileId)')
              q-item-side {{index+1}}
              q-item-main(label-lines="1" :label='doc.title') {{doc.name}}
              q-item-side {{doc.date}}
      q-card.bg-white.small-margin.col-12
        q-card-title 往期优秀作品
          span(slot="subtitle") 学生演讲优秀的作品展示
        q-card-separator
        q-list
          div(v-for="(show,index) in shows")
            q-item(:id="show",link=''  @click='downloadFile(show.fileId)')
              q-item-side {{index+1}}
              q-item-main(label-lines="1" :label='show.title') {{show.name}}
              q-item-side {{show.date}}
</template>

<script>
import api from '../api/api'
export default {
  data () {
    return {
      edus: [],
      shows: [
        {
          url: '',
          name: '',
          date: ''
        }
      ]
    }
  },
  computed: {
    slides () {
      let sls = []
      this.edus.forEach(slide => {
        if (slide.type === 'SLIDE') {
          sls.push(slide)
        }
      })
      return sls
    },
    documents () {
      let docs = []
      this.edus.forEach(doc => {
        if (doc.type === 'DOCUMENT') {
          docs.push(doc)
        }
      })
      return docs
    }
  },
  created () {
    this.getEdus()
    this.getShows()
  },
  methods: {
    getEdus () {
      api.getAllFiles('edu')
        .then(res => {
          this.edus = res.data.result
        })
    },
    getShows () {
      api.getAllFiles('show')
        .then(res => {
          this.shows = res.data.result
        })
    },
    downloadFile (id) {
      window.open('http://chemiety-admin.kastner.cn/api/v1/uploadFiles?id=' + id)
    },
    toLink (url) {
      window.location.href = url
    }
  }
}
</script>

<style lang="stylus">
.layout-padding
  padding-top 50
.small-margin
  margin-bottom 20px
</style>
