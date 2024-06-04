<template>
  <div>
    <a-layout-footer></a-layout-footer>
  </div>
</template>

<script>

export default {
  name: "C-PageFooter",
  data() {
    return {};
  },
  activated() {
  },
  mounted() {
  },
  methods: {

  }
}
</script>

<style scoped lang="less">
@import "~@public/color.less";
.ant-layout-footer {
  background: linear-gradient(to right,@primary-color, @primary-2);
  color: #fff;
  height: 8vh;
}
</style>
