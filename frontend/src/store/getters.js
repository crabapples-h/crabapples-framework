/**
 * TODO
 *
 * @author Mr.He
 * 2021/8/12 23:54
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
const getters = {
  TOKEN(state) {
    return state.user.token
  },
  USER_BASE_INFO: state => state.user.info,
  MENUS(state) {
    return [...state.permissions.menus]
  },
  ROUTERS: state => state.user.routers,
  PERMISSIONS(state) {
    console.log('getter--->', state)
    return {}
  },
  LOAD_FINISH(state) {
    return state.sys.loadFinish
  }
}
export default getters
