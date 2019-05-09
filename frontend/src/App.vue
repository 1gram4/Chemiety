<template>
  <!-- Configure "view" prop for QLayout -->
  <q-layout
    ref="layout"
    view="lHh Lpr fff"
    :left-breakpoint=100000
    :left-class="{'bg-grey-2': true}"
  >
    <q-toolbar slot="header" color="blue-grey-10">
      <!-- toggles QLayout left side -->
      <q-btn flat @click="$refs.layout.toggleLeft()">
        <q-icon name="menu"/>
      </q-btn>
      <q-toolbar-title>
        化学与社会
      </q-toolbar-title>
    </q-toolbar>
    <div slot="left">
      <!--
        Use <q-side-link> component
        instead of <q-item> for
        internal vue-router navigation
      -->

      <q-list no-border link inset-delimiter>
        <q-item-separator/>
        <q-side-link item to="/">
          <q-item-side icon="home"/>
          <q-item-main label="课程首页"/>
        </q-side-link>
        <q-item-separator/>
        <q-list-header>课程概要</q-list-header>
        <q-side-link  item to="/introduction">
          <q-item-side icon="school"/>
          <q-item-main label="课程简介" sublabel="上课时间、地点、所需课本"/>
        </q-side-link>
        <q-side-link item to="/outline">
          <q-item-side icon="subject"/>
          <q-item-main label="课程大纲" sublabel="课程范围"/>
        </q-side-link>
        <q-side-link item to="/info">
          <q-item-side icon="card membership"/>
          <q-item-main label="课程公告" sublabel="老师发布的课程公告"/>
        </q-side-link>
        <q-side-link  item to="/rating">
          <q-item-side icon="done"/>
          <q-item-main label="评分标准" sublabel="平时成绩占比、考核方式"/>
        </q-side-link>
        <q-side-link  item to="/context">
          <q-item-side icon="view list"/>
          <q-item-main label="资料下载" sublabel="课件、教学视频"/>
        </q-side-link>
        <q-side-link  item to="/teachers">
          <q-item-side icon="people"/>
          <q-item-main label="教师队伍" sublabel="教师简历"/>
        </q-side-link>
        <q-item-separator/>
        <q-list-header>课外学习</q-list-header>
        <q-side-link  item to="/campus">
          <q-item-side icon="book"/>
          <q-item-main label="学习园地" sublabel="老师推荐阅读资料"/>
        </q-side-link>
        <q-side-link  item to="/gallery">
          <q-item-side icon="slideshow"/>
          <q-item-main label="成果展示" sublabel="学生PPT、视频等"/>
        </q-side-link>
        <q-side-link  item to="/discussion">
          <q-item-side icon="question answer"/>
          <q-item-main label="讨论区" sublabel="问题发布与讨论"/>
        </q-side-link>
        <q-item-separator/>
        <q-list-header>考试</q-list-header>
        <q-side-link  item to="/test">
          <q-item-side icon="assignment turned in"/>
          <q-item-main label="课程考试" sublabel="考试资料"/>
        </q-side-link>
        <q-list-header>关于</q-list-header>
        <q-side-link  item to="/about">
          <q-item-side icon="favorite border"/>
          <q-item-main label="关于网站" sublabel="关于"/>
        </q-side-link>
      </q-list>
    </div>
    <transition name="fade">
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </transition>
  </q-layout>
</template>

<script>
  /*
   * Root component
   */
  export default {
    name: 'index',
    methods: {
      login () {
        this.$router.push({path: '/login'})
      }
    }
  }
</script>

<style></style>
