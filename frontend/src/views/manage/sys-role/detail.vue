<template>
  <a-drawer :title="title" width="50%" :visible="visible" @close="closeForm">
    <a-form-model :model="datasource" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol"
                  ref="roleForm" :disabled="true">
      <a-form-model-item label="ID" style="display: none">
        <a-input v-model="datasource.id" disabled placeholder="新建时自动生成"/>
      </a-form-model-item>
      <a-form-model-item label="名称" prop="name">
        <a-input v-model="datasource.name"/>
      </a-form-model-item>
      <a-form-model-item label="菜单">
        <a-tree-select
            v-if="false"
            :tree-data="menusOptions"
            v-model="datasource.hasMenusIds"
            tree-checkable
            :show-checked-strategy="SHOW_TYPE"
            :show-line="show.treeLine"
            :checkStrictly="false"
            :replace-fields="replaceFields"/>
        <a-tree
            v-model="datasource.menuList"
            :checkable="true"
            :default-expand-all="true"
            :check-strictly="false"
            :tree-data="menusOptions"
            :replace-fields="replaceFields"/>
      </a-form-model-item>
    </a-form-model>
    <div class="drawer-bottom-button">
      <a-button :style="{ marginRight: '8px' }" @click="closeForm">关闭</a-button>
      <a-button type="primary" @click="submit">保存</a-button>
    </div>
  </a-drawer>
</template>

<script>
import {SysApis} from '@/api/Apis'
import SystemMinix from '@/minixs/SystemMinix'

export default {
  name: 'add-role',
  mixins: [SystemMinix],
  props: {
    title: {
      type: String,
      default: '标题'
    },
    visible: {
      type: Boolean,
      default: false
    },
    cancel: {
      type: Function,
    },
  },
  watch: {
    visible(value, oldValue) {
      if (value) {
        this.loadRoleMenus()
      }
    }
  },
  data() {
    return {
      replaceFields: {
        children: 'children', title: 'name', key: 'id'
      },
      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'change'},
          {min: 2, max: 16, message: '长度为2-16个字符', trigger: 'change'},
          {whitespace: true, message: '请输入名称', trigger: 'change'}
        ],
      },
      show: {
        treeLine: true,
      },
      menusOptions: [],
      url: {
        save: SysApis.saveRoles,
        menuList: SysApis.menuList,
        roleMenus: SysApis.roleMenus,
      },
      datasource: {
        menuList: []
      },
    }
  },
  activated() {
    this.getMenusList()
  },
  mounted() {
  },
  methods: {
    loadRoleMenus() {
      if (this.datasource.id) {
        this.$http.get(`${this.url.roleMenus}/${this.datasource.id}`).then(result => {
          let hasMenusIds = []
          result.data.forEach(e => {
            hasMenusIds.push(e.id)
          })
          this.datasource.menuList = hasMenusIds
        })
      }
    },
    getMenusList() {
      this.$http.get(this.url.menuList).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message)
          return
        }
        if (result.data !== null) {
          this.menusOptions = result.data
        }
      }).catch(function (error) {
        console.error('出现错误:', error)
      }).finally(() => {

      })
    },
    tree2list(list, data = []) {
      list.forEach(r => {
        data.push({id: r.id, name: r.name, pid: r.pid, sort: r.sort})
        this.tree2list(r.children, data)
      })
      return data
    },
    closeForm() {
      this.datasource = {
        menuList: []
      }
      this.$emit('cancel')
    },
    submit() {
      this.$refs.roleForm.validate(valid => {
        if (valid) {
          this.$http.post(this.url.save, this.datasource).then(result => {
            if (result.status !== 200) {
              this.$message.error(result.message)
            }
          }).catch(function (error) {
            console.error('出现错误:', error)
          }).finally(() => {
            this.closeForm()
          })
        }
      })
    },
  }
}
</script>

<style scoped>
.drawer-bottom-button {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 100%;
  border-top: 1px solid #e9e9e9;
  padding: 10px 16px;
  background: #fff;
  text-align: right;
  z-index: 1;
}
</style>
