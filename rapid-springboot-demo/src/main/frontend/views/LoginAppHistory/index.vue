<!--output: frontend/views/loginAppHistory/index.vue-->

<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">id</label>
        <el-input v-model="query.id" clearable placeholder="id" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录ID</label>
        <el-input v-model="query.loginId" clearable placeholder="登录ID" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录账号</label>
        <el-input v-model="query.acction" clearable placeholder="登录账号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">角色 1-客户 2-车辆</label>
        <el-input v-model="query.role" clearable placeholder="角色 1-客户 2-车辆" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录IP</label>
        <el-input v-model="query.loginIp" clearable placeholder="登录IP" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录时间</label>
        <el-input v-model="query.loginTime" clearable placeholder="登录时间" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录方式 1-自动登录 2-账号密码登录</label>
        <el-input v-model="query.loginMethod" clearable placeholder="登录方式 1-自动登录 2-账号密码登录" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录介质 1-android 2-IOS 3-PC 4-WAP</label>
        <el-input v-model="query.loginType" clearable placeholder="登录介质 1-android 2-IOS 3-PC 4-WAP" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录token</label>
        <el-input v-model="query.token" clearable placeholder="登录token" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">登录介质的UUID</label>
        <el-input v-model="query.uuid" clearable placeholder="登录介质的UUID" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">当前软件版本</label>
        <el-input v-model="query.appVersion" clearable placeholder="当前软件版本" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">手机设备厂家</label>
        <el-input v-model="query.vendor" clearable placeholder="手机设备厂家" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">手机型号</label>
        <el-input v-model="query.model" clearable placeholder="手机型号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">手机系统版本号</label>
        <el-input v-model="query.sysVersion" clearable placeholder="手机系统版本号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id" prop="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录ID" prop="loginId">
            <el-input v-model="form.loginId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录账号" prop="acction">
            <el-input v-model="form.acction" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="角色 1-客户 2-车辆">
            <el-input v-model="form.role" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录IP">
            <el-input v-model="form.loginIp" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录时间" prop="loginTime">
            <el-input v-model="form.loginTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录方式 1-自动登录 2-账号密码登录" prop="loginMethod">
            <el-input v-model="form.loginMethod" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录介质 1-android 2-IOS 3-PC 4-WAP" prop="loginType">
            <el-input v-model="form.loginType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录token">
            <el-input v-model="form.token" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="登录介质的UUID">
            <el-input v-model="form.uuid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="当前软件版本">
            <el-input v-model="form.appVersion" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="手机设备厂家">
            <el-input v-model="form.vendor" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="手机型号">
            <el-input v-model="form.model" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="手机系统版本号">
            <el-input v-model="form.sysVersion" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="id" />
        <el-table-column prop="loginId" label="登录ID" />
        <el-table-column prop="acction" label="登录账号" />
        <el-table-column prop="role" label="角色 1-客户 2-车辆" />
        <el-table-column prop="loginIp" label="登录IP" />
        <el-table-column prop="loginTime" label="登录时间" />
        <el-table-column prop="loginMethod" label="登录方式 1-自动登录 2-账号密码登录" />
        <el-table-column prop="loginType" label="登录介质 1-android 2-IOS 3-PC 4-WAP" />
        <el-table-column prop="token" label="登录token" />
        <el-table-column prop="uuid" label="登录介质的UUID" />
        <el-table-column prop="appVersion" label="当前软件版本" />
        <el-table-column prop="vendor" label="手机设备厂家" />
        <el-table-column prop="model" label="手机型号" />
        <el-table-column prop="sysVersion" label="手机系统版本号" />
        <el-table-column v-permission="['admin','loginAppHistory:edit','loginAppHistory:del']" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudLoginAppHistory from '@/api/loginAppHistory'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, loginId: null, acction: null, role: null, loginIp: null, loginTime: null, loginMethod: null, loginType: null, token: null, uuid: null, appVersion: null, vendor: null, model: null, sysVersion: null }
export default {
  name: 'LoginAppHistory',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '这个是很牛逼的接口，其实直接引用 table 的介绍就行了,先偷懒', url: 'api/loginAppHistory/list', idField: 'id', sort: 'id,desc', crudMethod: { ...crudLoginAppHistory }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'loginAppHistory:add'],
        edit: ['admin', 'loginAppHistory:edit'],
        del: ['admin', 'loginAppHistory:del']
      },
      rules: {
        id: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        loginId: [
          { required: true, message: '登录ID不能为空', trigger: 'blur' }
        ],
        acction: [
          { required: true, message: '登录账号不能为空', trigger: 'blur' }
        ],
        loginTime: [
          { required: true, message: '登录时间不能为空', trigger: 'blur' }
        ],
        loginMethod: [
          { required: true, message: '登录方式 1-自动登录 2-账号密码登录不能为空', trigger: 'blur' }
        ],
        loginType: [
          { required: true, message: '登录介质 1-android 2-IOS 3-PC 4-WAP不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'id', display_name: 'id' },
        { key: 'loginId', display_name: '登录ID' },
        { key: 'acction', display_name: '登录账号' },
        { key: 'role', display_name: '角色 1-客户 2-车辆' },
        { key: 'loginIp', display_name: '登录IP' },
        { key: 'loginTime', display_name: '登录时间' },
        { key: 'loginMethod', display_name: '登录方式 1-自动登录 2-账号密码登录' },
        { key: 'loginType', display_name: '登录介质 1-android 2-IOS 3-PC 4-WAP' },
        { key: 'token', display_name: '登录token' },
        { key: 'uuid', display_name: '登录介质的UUID' },
        { key: 'appVersion', display_name: '当前软件版本' },
        { key: 'vendor', display_name: '手机设备厂家' },
        { key: 'model', display_name: '手机型号' },
        { key: 'sysVersion', display_name: '手机系统版本号' }
      ]
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
