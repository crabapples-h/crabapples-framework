<template>
  <div class="loginApi-bg">
    <div class="loginApi-div">
      <div class="title" id="login-title">用户登录</div>
      <a-input id="login-username" autocomplete="off" placeholder="用户名" type="text" v-model="username"
               class="input-text"></a-input>
      <a-input id="login-password" autocomplete="off" placeholder="密码" type="password" v-model="password"
               class="input-text"></a-input>
      <a-button id="login-button" style="width:100%;" type="primary" @click="submit" class="loginApi-button">立即登录
      </a-button>
    </div>
  </div>
</template>

<script>
import {driver} from "driver.js";

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
    }
  },
  activated() {
  },
  mounted() {
    const driverObj = driver({
      showProgress: true,
      animate: true,
      allowClose: false,
      // overlayColor: 'pink',
      // overlayOpacity:'0.5'
    });
    driverObj.setSteps([
      {
        element: '#login-title',
        popover: {
          title: '第一步',
          description: '这是第一步',
          prevBtnText: '上一步',
          nextBtnText: '下一步'
        }
      },
      {
        element: '#login-username',
        popover: {title: '第二步', description: '这是第二步', prevBtnText: '上一步', nextBtnText: '下一步'}
      },
      {
        element: '#login-password',
        popover: {title: '第三步', description: '这是第三步', prevBtnText: '上一步', nextBtnText: '下一步'}
      },
      {
        element: '#login-button',
        popover: {title: '第四步', description: '这是第四步', prevBtnText: '上一步', nextBtnText: '完成'}
      },
    ])
    // driverObj.drive();
  },
  methods: {
    submit() {
      const _this = this
      let data = {
        username: _this.username,
        password: _this.password
      }
      // dispatch调用的是/store/modules里几个js文件中actions里的方法
      this.$store.dispatch('LOGIN', data).then(result => {
        this.$store.dispatch('USER_BASE_INFO')
        this.$store.dispatch('ROLES')
        this.$store.dispatch('MENUS')
        this.$store.dispatch('PERMISSIONS')
        // this.$store.commit('INIT_ROUTER', null)
        this.$router.replace('/loading')
      })
    },
  }
}
</script>
<style lang="less" scoped>
@import "~@public/color.less";

.loginApi-bg {
  background: url(~@assets/login-background.png) no-repeat center;
  background-size: 100%;
  overflow: hidden;
  height: 100vh;
}

.loginApi-div {
  margin: 120px auto 0 auto;
  min-height: 420px;
  max-width: 420px;
  padding: 40px;
  background-color: #ffffff;
  border-radius: 4px;
  box-sizing: border-box;
}

.title {
  margin: 10px 0 0 -58px;
  padding: 18px 10px 18px 60px;
  background: @primary-color;
  position: relative;
  color: #fff;
  font-size: 16px;
}

.input-text {
  height: 50px;
  box-sizing: border-box;
  margin: 16px 0;
}

.loginApi-button {
  height: 50px;
  border: none;
}
</style>
