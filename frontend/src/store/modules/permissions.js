/**
 * TODO
 *
 * @author Mr.He
 * 2021/8/11 1:15
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
import storage from '@/store/storage'
import commonApi from '@/api/CommonApi'

const permissions = {
  state: {
    menus: '',
    permissions: '',
    routers: '',
  },
  mutations: {
    MENUS: (state, payload) => {
      state.menus = payload
      storage.setUserMenus(payload)
    },
    PERMISSIONS: (state, payload) => {
      state.permissions = payload
      storage.setPermissions(payload)
    },
  },
  actions: {
    MENUS(object, data) {
      commonApi.getUserMenus().then(({ status, data }) => {
        if (status !== 200) {
          return
        }
        if (data !== null) {
          let formatMenus = function (data) {
            return data.map(e => {
              if (e.showFlag !== 0) {
                return {}
              }
              if (e.menusType !== 2) {
                let icon = e.icon.substring(e.icon.indexOf('"') + 1, e.icon.lastIndexOf('"'))
                let menus = {
                  id: e.id,
                  pid: e.pid,
                  name: e.name,
                  icon: icon,
                  path: e.path,
                  sort: e.sort,
                  link: e.link,
                  menusType: e.menusType,
                  filePath: e.filePath,
                  permission: e.permission,
                  hidden: false
                }
                if (e.children && e.children.length > 0) {
                  let children = formatMenus(e.children).filter(e => {
                    return !!e
                  })
                  if (children && children.length) {
                    menus.children = children
                  }
                }
                return menus
              }
              return undefined

            }).sort((a, b) => {
              if (a && b && a.sort && b.sort) {
                return a.sort - b.sort
              }
            })
          }
          const menus = formatMenus(data)
          object.commit('MENUS', menus)
          object.commit('INIT_ROUTER', menus)
        }
      }).catch(function (error) {
        console.error('出现错误:', error)
      })
    },
    PERMISSIONS(object, data) {
      commonApi.getUserPermissions().then(({ status, data }) => {
        if (status !== 200) {
          return
        }
        if (data !== null) {
          object.commit('PERMISSIONS', data)
        }
      }).catch(function (error) {
        console.error('出现错误:', error)
      })
    },
  },
}
export default permissions
