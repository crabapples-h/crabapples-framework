<template>
  <div id="app">
    <a-locale-provider :locale="zh_CN">
      <router-view></router-view>
    </a-locale-provider>
  </div>
</template>
<script>
import storage from '@/store/storage'
import zh_CN from 'ant-design-vue/lib/locale-provider/zh_CN';

import { whiteList } from '@/router'

export default {
  name: 'App',
  computed: {
    zh_CN() {
      return zh_CN
    }
  },
  data() {
    return {}
  },
  beforeCreate() {
  },
  created() {
    this.init()
  },
  mounted() {
    const _this = this
    window.addEventListener('beforeunload', function (event) {
      // 页面刷新时获取当前页面的path地址放入缓存中，
      // 如果path地址在白名单中，则说明用户并没有登录，则直接将首页放入缓存
      let LAST_PAGE = _this.$route.path
      let isWhiteList = whiteList.includes(LAST_PAGE)
      if (isWhiteList) {
        localStorage.setItem('LAST_PAGE', '/manage/index')
      } else {
        localStorage.setItem('LAST_PAGE', _this.$route.path)
      }
      // 刷新时做一个标记
      localStorage.setItem('RELOAD_PAGE', 1)
      // event.returnValue = '确定要离开页面吗？'
    })
  },
  methods: {
    init() {
      const token = storage.getToken()
      if (token) {
        this.$store.dispatch('INIT_TOKEN', token)
        this.$store.dispatch('USER_BASE_INFO')
        this.$store.dispatch('ROLES')
        // 初始化动态路由
        this.$store.dispatch('MENUS')
        this.$store.dispatch('PERMISSIONS')
        // 获取刷新标记，如果用户是刷新页面则跳到loading页，如果不是刷新页面则直接跳转到指定页面
        // - 用于处理直接在浏览器输入url的情况，防止输入时跳转到loading页
        // - loading页是从缓存中获取刷新前保存的地址，这样会导致即使输入url访问也会跳到最后一次刷新页面时保存的url
        let isReload = localStorage.getItem('RELOAD_PAGE')
        localStorage.removeItem('RELOAD_PAGE')
        if (!isReload) {
          this.$router.replace('/loading')
        }
      } else {
        console.log('token不存在:', token)
      }
    },
  }
}
</script>

<style>
#app {
    margin: 0;
    padding: 0;
}
</style>
