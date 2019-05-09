<template>
  <!-- Configure "view" prop for QLayout -->
  <q-layout
    ref="layout"
    view="lHh Lpr fff"
    :left-class="{'bg-grey-2': true}"
  >
  </q-layout>
</template>

<script>
  export default {
    data () {
      return {}
    }
  }
</script>

<style lang="stylus">
  #school-logo
    height 150px
</style>
