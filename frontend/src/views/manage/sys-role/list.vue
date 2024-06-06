<template>
  <div>
    <a-button @click="showAdd" v-auth:sys:roles:add>添加角色</a-button>
    <a-divider/>
    <add-role :visible="show.add" @cancel="closeAdd" ref="addForm"/>
    <add-role :visible="show.edit" @cancel="closeEdit" ref="editForm"/>
    <role-detail :visible="show.detail" @cancel="closeDetail" ref="detailForm"/>
    <a-table :data-source="dataSource" rowKey="id" :columns="columns" :pagination="pagination">
      <span slot="action" slot-scope="text, record">
        <template v-auth:sys:roles:del>
          <c-pop-button title="确定要删除吗" text="删除" type="danger" size="small" @click="remove(record)"/>
          <a-divider type="vertical"/>
        </template>
        <template v-auth:sys:roles:edit>
          <a-button type="primary" size="small" @click="showEdit(record)">编辑</a-button>
          <a-divider type="vertical"/>
        </template>
        <a-button type="primary" size="small" @click="showDetail(record)">查看菜单</a-button>
      </span>
    </a-table>
  </div>
</template>

<script>
import commonApi from '@/api/CommonApi'
import {buildTree} from '@/utils/ListUtils'
import {SysApis} from '@/api/Apis'
import SystemMinix from '@/minixs/SystemMinix'
import AddRole from '@/views/manage/sys-role/add.vue'
import RoleDetail from '@/views/manage/sys-role/detail.vue'

export default {
  name: 'role-list',
  mixins: [SystemMinix],
  components: {AddRole, RoleDetail},
  data() {
    return {
      columns: [
        {
          dataIndex: 'name',
          title: '角色',
          key: 'name',
          width: '50%'
        },
        {
          title: '操作',
          key: 'action',
          scopedSlots: {customRender: 'action'},
          width: '50%'
        },
      ],
      url: {
        list: SysApis.rolePage,
        delete: SysApis.delRoles,
        roleMenus: SysApis.roleMenus,
      },
    }
  },
  activated() {
  },
  mounted() {
  },
  methods: {
    closeAdd() {
      this.show.add = false
      this.refreshData()
      commonApi.refreshSysData()
    },
    closeEdit() {
      this.show.edit = false
      this.refreshData()
      commonApi.refreshSysData()
    },
    showDetail(e) {
      let url = `${this.url.roleMenus}/${e.id}`
      console.log(url)
      this.$http.get(url).then(result => {
        console.log(result.data)
        let dataSource = buildTree(result.data, '')
        console.log(dataSource)
        this.show.detail = true
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

localhost:9999/videoability/mtbase/store/mtEmployee/setFacePhoto/1782996283654230018?facePhoto=http://localhost:9999/videoability//sys/common/static//s3obj/maotai/%E4%BA%8C%E5%AF%B8%E7%99%BD%E5%BA%952023-%E7%8E%8B%E4%B8%BD_1717579204298.jpg
localhost:9999/videoability//mtbase/store/mtEmployee/setFacePhoto/1782996283654230018?facePhoto=/s3obj/maotai/%E4%BA%8C%E5%AF%B8%E7%99%BD%E5%BA%952023-%E5%BE%90%E9%83%A6%E9%92%B0_1717645281809.jpg

http://localhost:9999/videoability/mtbase/store/mtEmployee/setFacePhoto/1782996283654230018?facePhoto=/s3obj/maotai/%E4%BA%8C%E5%AF%B8%E7%99%BD%E5%BA%952023-%E7%8E%8B%E4%B8%BD_1717645572408.jpg