<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        :placeholder="$t('project.projName')"
        class="filter-item"
        style="width: 200px;"
        @key.enter.native="handleFilter"
      />
      <el-select
        v-model="listQuery.status"
        :placeholder="$t('project.projStatus')"
        class="filter-item"
        style="width: 130px;"
      >
        <el-option
          v-for="item in statusOptions"
          :key="item.key"
          :label="item.label"
          :value="item.key"
        />
      </el-select>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t('table.search') }}</el-button>
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreateProject"
      >{{ $t('table.add') }}</el-button>
      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >{{ $t('table.export') }}</el-button>
      <el-button
        class="filter-item"
        type="warning"
        icon="el-icon-delete"
        width="50"
        @click="ShowDelGroupWarn"
      >{{ $t('edit.groupDel') }}</el-button>
    </div>

    <el-main>
      <!-- project list -->
      <el-table
        v-loading="listLoading"
        :data="projectList"
        :key="tableKey"
        @sort-change="changeSort"
        @selection-change="handleSelectChange"
      >
        <el-table-column type="selection" width="30"/>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-steps :active="getProjStatus(props.row.projStatus)" finish-status="success" simple>
              <el-step :title="$t('project.analysis')" icon="el-icon-edit"/>
              <el-step :title="$t('project.active')" icon="el-icon-time"/>
              <el-step :title="$t('project.finished')" icon="el-icon-success"/>
            </el-steps>
            <p>{{ $t('project.projName') }}:{{ props.row.projName }}</p>
            <p>{{ $t('project.createTime') }}:{{ props.row.createTime }}</p>
            <p>{{ $t('project.projOwnerName') }}:{{ props.row.employeeName }}</p>
            <p>{{ $t('project.projDesc') }}:{{ props.row.description }}</p>
          </template>
        </el-table-column>
        <el-table-column
          :label="$t('project.projId')"
          width="100"
          prop="projId"
          sortable="custom"
          align="center"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.projId }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('project.projName')" align="center" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.projName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          :label="$t('project.projStatus')"
          :filters="[ {text: this.$t('project.analysis'),value: 'analysis'}, { text: this.$t('project.pending'), value: 'pending' }, { text: this.$t('project.active'), value: 'active' }, { text: this.$t('project.finished'), value: 'finished' }]"
          :filter-method="handleStatusFilter"
          filter-placement="bottom-end"
          align="center"
          width="200"
          prop="projStatus"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.projStatus|statusTagFilter"
            >{{ statusMapper(scope.row.projStatus) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column :label="$t('project.projOwnerName')" prop="employeeName">
          <template slot-scope="scope">
            <span>{{ scope.row.employeeName }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.actions')" fixed="right" width="200">
          <template slot-scope="scope">
            <router-link :to="'/project/edit/'+scope.row.projId">
              <el-button type="primary" size="mini">{{ $t('edit.edit') }}</el-button>
            </router-link>
            <el-button
              size="mini"
              type="danger"
              @click="ShowDelWarn(scope.$index,scope.row)"
            >{{ $t('edit.delete') }}</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-if="total>0"
        :total="total"
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="getProjList"
      />
    </el-main>

    <!-- add dialog -->
    <el-dialog :title="$t('edit.edit')" :visible.sync="dialogFormVisible">
      <el-form
        ref="form"
        :rules="projRules"
        :model="projTemplate"
        label-position="top"
        label-width="70px"
      >
        <el-form-item :label="$t('project.projName')">
          <el-input v-model="projTemplate.projName"/>
        </el-form-item>
        <el-form-item :label="$t('project.projStatus')">
          <el-select v-model="projTemplate.projStatus">
            <el-option
              v-for="item in projStatusOptions"
              :key="item.key"
              :label="item.label"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('project.projDesc')">
          <el-input v-model="projTemplate.description"/>
        </el-form-item>
        <el-form-item :label="$t('project.createTime')">
          <el-input v-model="projTemplate.createTime"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="createProj()">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>

    <!-- warning dialog -->
    <el-dialog :title="$t('edit.warning')" :visible.sync="warnDialogVisible" center>
      <span v-text="$t('edit.askDel')"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="warnDialogVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="danger"
          @click="deleteStatus ==='group'? handleDelGroup():handleDelete()"
        >{{ $t('table.confirm') }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import waves from '@/directive/waves'
import { mapGetters } from 'vuex'
import {
  getAllProject,
  addProject,
  deleteProjectById,
  delProjByGroup
} from '@/api/project'
export default {
  directives: {
    waves
  },
  components: {
    Pagination
  },
  filters: {
    statusTagFilter(status) {
      const statusMap = {
        analysis: '',
        pending: 'info',
        active: 'warning',
        finished: 'success'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      downloadLoading: false,
      selectionGroup: [],
      projectList: null,
      dialogFormVisible: false,
      total: 0,
      tableKey: 0,
      listLoading: true,
      warnDialogVisible: false,
      deleteStatus: '',
      listQuery: {
        name: '',
        status: 'all',
        page: 1,
        limit: 10,
        sort: '+id'
      },
      projStatusOptions: [
        {
          key: 'analysis',
          label: this.$t('project.analysis')
        },
        {
          key: 'pending',
          label: this.$t('project.pending')
        },
        {
          key: 'active',
          label: this.$t('project.active')
        },
        {
          key: 'finished',
          label: this.$t('project.finished')
        }
      ],
      statusOptions: [
        {
          key: 'all',
          label: this.$t('project.allStatus')
        },
        {
          key: 'analysis',
          label: this.$t('project.analysis')
        },
        {
          key: 'pending',
          label: this.$t('project.pending')
        },
        {
          key: 'active',
          label: this.$t('project.active')
        },
        {
          key: 'finished',
          label: this.$t('project.finished')
        }
      ],
      projTemplate: {
        projIndex: undefined,
        projName: '',
        employeeId: '',
        employeeName: '',
        createTime: Date.now(),
        endTime: '',
        projStatus: '',
        description: ''
      },
      projRules: {
        projName: [
          {
            required: true,
            trigger: 'blur'
          }
        ],
        projStatus: [
          {
            required: true,
            trigger: 'change'
          }
        ],
        description: [
          {
            required: true,
            trigger: 'blur'
          }
        ]
      }
    }
  },
  computed: {
    ...mapGetters(['name', 'userId', 'token'])
  },
  created() {
    this.getProjList()
  },
  methods: {
    getProjStatus(status) {
      const mapper = {
        analysis: 0,
        pending: -1,
        active: 1,
        finished: 3
      }
      return mapper[status]
    },
    resetProjTemp() {
      this.projTemplate = {
        projId: undefined,
        projName: '',
        employeeId: '',
        employeeName: '',
        createTime: Date.now(),
        endTime: '',
        projStatus: '',
        description: ''
      }
    },
    statusMapper(status) {
      const options = {
        analysis: this.$t('project.analysis'),
        pending: this.$t('project.pending'),
        active: this.$t('project.active'),
        finished: this.$t('project.finished'),
        overdue: this.$t('project.overdue'),
        denied: this.$t('project.denied')
      }
      return options[status]
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getProjList()
    },
    getProjList() {
      this.listLoading = true
      getAllProject(this.userId, this.token, this.listQuery)
        .then(response => {
          this.projectList = response.data.items
          this.total = response.data.total
          this.listLoading = false
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: 'load failed',
            duration: 2e3
          })
        })
    },
    handleCreateProject() {
      this.resetProjTemp()
      this.dialogFormVisible = true
      this.$nextTick().then(() => {
        this.$refs['form'].clearValidate()
      })
    },
    changeSort(data) {
      const { prop, order } = data
      if (prop === 'projId') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    createProj() {
      this.$refs.form.validate(valid => {
        // get projId from backend
        const info = this.projTemplate
        addProject(this.userId, this.token, info)
          .then(response => {
            this.projTemplate.projId = response.data.projId
            this.projectList.unshift(this.projTemplate)
            this.total = this.projectList.length
            this.dialogFormVisible = false
            this.$notify.success({
              title: this.$t('edit.success'),
              message: this.$t('edit.addSuccess'),
              offset: 100,
              duration: 2000
            })
          })
          .catch(err => {
            console.log(err)
            this.$notify.error({
              title: this.$t('edit.failed'),
              message: this.$t('edit.addFailed'),
              duration: 2000
            })
          })
      })
    },
    ShowDelWarn(index, row) {
      this.deleteStatus = 'item'
      this.warnDialogVisible = true
      this.projTemplate = Object.assign({}, row)
    },
    handleDelete() {
      this.warnDialogVisible = false
      const { projId } = this.projTemplate
      deleteProjectById(this.userId, this.token, projId)
        .then(response => {
          const index = this.projectList.findIndex(el => {
            return el.projId === projId
          })
          this.projectList.splice(index, 1)
          this.total = this.projectList.length
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.delSuccess'),
            duration: 2000
          })
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.delFailed'),
            duration: 2000
          })
        })
    },
    handleSelectChange(row) {
      this.selectionGroup = row
    },
    ShowDelGroupWarn() {
      if (this.selectionGroup.length > 0) {
        this.deleteStatus = 'group'
        this.warnDialogVisible = true
      } else {
        this.$message({
          message: this.$t('edit.noSelect'),
          type: 'warning'
        })
      }
    },
    handleDelGroup() {
      this.warnDialogVisible = false
      const projIds = []
      this.selectionGroup.forEach(el => {
        projIds.unshift(el.projId)
      })
      delProjByGroup(this.userId, this.token, projIds)
        .then(response => {
          projIds.forEach(el => {
            const index = this.projectList.findIndex((item, i) => {
              return item.projId === el
            })
            this.projectList.splice(index, 1)
          })
          this.total = this.projectList.length
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.delSuccess'),
            duration: 2000
          })
        })
        .catch(err => {
          console.error(new Error(err))
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.delFailed'),
            duration: 2000
          })
        })
    },
    handleStatusFilter(value, row) {
      return row.projStatus === value
    },
    handleDownload() {}
  }
}
</script>
