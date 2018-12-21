<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        :placeholder="$t('employee.name')"
        class="filter-item"
        style="width: 200px;"
        @keyup.enter.native="handleFilter"
      />
      <el-select
        v-model="listQuery.gender"
        :placeholder="$t('employee.gender')"
        clearable
        class="filter-item"
        style="width: 130px"
      >
        <el-option :label="$t('employee.allGender')" :value="-1"/>
        <el-option :label="$t('employee.male')" :value="0"/>
        <el-option :label="$t('employee.female')" :value="1"/>
      </el-select>
      <el-select
        v-model="listQuery.sort"
        :placeholder="$t('table.type')"
        clearable
        class="filter-item"
        style="width: 130px"
      >
        <el-option
          v-for="item in sortOptions"
          :key="item.key"
          :label="item.label"
          :value="item.key"
        />
      </el-select>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >{{ $t('table.add') }}</el-button>
      <el-button
        type="primary"
        class="filter-item"
        size="medium"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t('table.search') }}</el-button>
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
        @click="showDelWarnGroup"
      >{{ $t('edit.groupDel') }}</el-button>
      <el-checkbox
        v-model="showEditor"
        class="filter-item"
        style="margin-left:15px;"
        @change="tableKey=tableKey+1"
      >{{ $t('edit.lastEditor') }}</el-checkbox>
    </div>
    <el-main>
      <el-table
        v-loading="listLoading"
        :data="employeeList"
        :key="tableKey"
        @sort-change="changeSort"
        @selection-change="handleSelectChange"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column type="expand">
          <template slot-scope="props">
            <p>{{ $t('employee.id') }}: {{ props.row.employeeId }}</p>
            <p>{{ $t('employee.job') }}: {{ props.row.job }}</p>
            <p>{{ $t('employee.jobStatus') }}: {{ statusMaper(props.row.status) }}</p>
            <p>{{ $t('employee.name') }}: {{ props.row.name }}</p>
            <p>{{ $t('employee.phone') }}: {{ props.row.phone }}</p>
            <p>{{ $t('employee.address') }}: {{ props.row.address }}</p>
            <p>{{ $t('employee.idNumber') }}: {{ props.row.idNumber }}</p>
          </template>
        </el-table-column>
        <el-table-column
          :label="$t('employee.id')"
          width="100"
          prop="id"
          sortable="custom"
          align="center"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.employeeId }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('employee.name')" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          :label="this.$t('employee.gender')"
          :filters="[ {text:this.$t('employee.male'),value:0},{text: this.$t('employee.female'),value:1}]"
          :filter-method="handleGenderFilter"
          filter-placement="bottom-end"
          width="100"
          prop="gender"
        >
          <template slot-scope="scope">
            <span>{{ getGenderLabel(scope.row.gender) }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="this.$t('employee.job')" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.job }}</span>
          </template>
        </el-table-column>
        <el-table-column
          :label="$t('employee.jobStatus')"
          :filters="[ { text: this.$t('employee.intership'),value:'intership'}, {text: this.$t('employee.participation'),value:'participation'}, {text: this.$t('employee.dimission'),value:'dimission'} ]"
          :filter-method="handleTagFilter"
          filter-placement="bottom-end"
          prop="status"
          width="100"
        >
          <template slot-scope="scope">
            <el-tag :type="scope.row.status|statusTagFilter">{{ statusMaper(scope.row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column :label="this.$t('employee.phone')" width="120">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="showEditor" :label="$t('edit.lastEditor')" width="100" center>
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>{{ $t('employee.name') }}:{{ scope.row.editName }}</p>
              <p>{{ $t('employee.id') }}:{{ scope.row.editId }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.editName }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column v-if="showEditor" :label="$t('edit.editTime')"/>
        <el-table-column :label="$t('table.actions')" fixed="right" width="200">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="handleUpdate(scope.row)"
            >{{ $t('edit.edit') }}</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="showDelWarn(scope.$index,scope.row)"
            >{{ $t('edit.delete') }}</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-if="total>0"
        :total="total"
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="getList"
      />
    </el-main>

    <el-dialog :title="$t('edit.edit')" :visible.sync="dialogFormVisible">
      <el-form
        ref="form"
        :rules="dialogRules"
        :model="temp"
        label-position="top"
        label-width="70px"
      >
        <el-form-item :label="$t('employee.job')">
          <el-input v-model="temp.job"/>
        </el-form-item>
        <el-form-item :label="$t('employee.jobStatus')">
          <el-select v-model="temp.status">
            <el-option v-for="s in statusOptions" :key="s.key" :label="s.label" :value="s.key"/>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('employee.name')">
          <el-input v-model="temp.name"/>
        </el-form-item>
        <el-form-item :label="$t('employee.gender')">
          <el-select v-model="temp.gender" placeholder="Select">
            <el-option :label="$t('employee.male')" :value="0"/>
            <el-option :label="$t('employee.female')" :value="1"/>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('employee.address')">
          <el-input v-model="temp.address"/>
        </el-form-item>
        <el-form-item :label="$t('employee.phone')">
          <el-input v-model="temp.phone"/>
        </el-form-item>
        <el-form-item :label="$t('employee.idNumber')">
          <el-input v-model="temp.idNumber"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="primary"
          @click="dialogStatus === 'create'? createData():updateData() "
        >{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>
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
import {
  addEmployee,
  getAllEmployee,
  updateEmployee,
  deleteEmployee,
  deleteGroup
} from '@/api/employee'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import waves from '@/directive/waves' // Waves directive
import { mapGetters } from 'vuex'

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
        intership: '',
        participation: 'success',
        dimission: 'warning'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      showEditor: false,
      downloadLoading: false,
      tableKey: 0,
      listQuery: {
        name: '',
        page: 1,
        gender: -1,
        limit: 10,
        sort: '+id'
      },
      sortOptions: [
        {
          label: this.$t('table.idAscending'),
          key: '+id'
        },
        {
          label: this.$t('table.idDescending'),
          key: '-id'
        }
      ],
      statusOptions: [
        {
          key: 'participation',
          label: this.$t('employee.participation')
        },
        {
          key: 'intership',
          label: this.$t('employee.intership')
        },
        {
          key: 'dimission',
          label: this.$t('employee.dimission')
        }
      ],
      employeeList: null,
      total: 0,
      listLoading: true,
      temp: {
        employeeId: undefined,
        name: '',
        address: '',
        gender: '',
        job: '',
        phone: '',
        idNumber: '',
        status: ''
      },
      dialogFormVisible: false,
      warnDialogVisible: false,
      deleteStatus: '',
      dialogStatus: '',
      dialogRules: {
        gender: [
          {
            reuqired: true,
            trigger: 'change'
          }
        ],
        job: [
          {
            reuqired: true,
            trigger: 'blur'
          }
        ],
        phone: [
          {
            reuqired: true,
            trigger: 'blur'
          }
        ],
        idNumber: [
          {
            reuqired: true,
            trigger: 'blur'
          }
        ],
        address: [
          {
            reuqired: true,
            trigger: 'blur'
          }
        ]
      },
      selectionGroup: []
    }
  },
  computed: {
    ...mapGetters(['userId', 'name', 'token'])
  },
  created() {
    this.getList()
  },
  methods: {
    handleSelectChange(row) {
      this.selectionGroup = row
    },
    handleGenderFilter(value, row) {
      return row.gender === value
    },
    getGenderLabel(gender) {
      if (gender === 0) {
        return this.$t('employee.male')
      } else {
        return this.$t('employee.female')
      }
    },
    handleTagFilter(value, row) {
      return row.status === value
    },
    statusMaper(status) {
      const map = {
        intership: this.$t('employee.intership'),
        participation: this.$t('employee.participation'),
        dimission: this.$t('employee.dimission')
      }
      return map[status]
    },
    resetTemplate() {
      this.temp = {
        employeeId: undefined,
        name: '',
        address: '',
        gender: '',
        job: '',
        phone: '',
        idNumber: '',
        status: '',
        editTime: '',
        editByName: ''
      }
    },
    getList() {
      this.listLoading = true
      getAllEmployee(this.listQuery, this.userId, this.token)
        .then(response => {
          this.employeeList = response.data.items
          this.total = response.data.total
          this.listLoading = false
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: 'load failed',
            duration: 2000
          })
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: this.$t('edit.success'),
        type: 'success'
      })
      row.jobStatus = status
    },
    changeSort(data) {
      const { prop, order } = data
      if (prop === 'id') {
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
    handleCreate() {
      this.resetTemplate()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick().then(() => {
        this.$refs['form'].clearValidate()
      })
    },
    createData() {
      this.$refs.form.validate(valid => {
        // get employeeId from backend
        this.temp.employeeId = this.total + 1
        this.temp.lastEdit = this.name
        const info = this.temp
        addEmployee(this.userId, info, this.token)
          .then(() => {
            this.employeeList.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify.success({
              title: this.$t('edit.success'),
              message: this.$t('edit.addSuccess'),
              offset: 100,
              duration: 2000
            })
          })
          .catch(err => {
            console.error(new Error(err))
            this.$notify({
              title: this.$t('edit.failed'),
              message: this.$t('edit.addFailed'),
              type: 'error',
              offset: 100,
              duration: 2000
            })
          })
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick().then(() => {
        this.$refs['form'].clearValidate()
      })
    },
    updateData() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const info = Object.assign({}, this.temp)
          updateEmployee(this.userId, info, this.token)
            .then(response => {
              for (const el of this.employeeList) {
                if (el.id === this.temp.id) {
                  const index = this.employeeList.indexOf(el)
                  this.employeeList.splice(index, 1, this.temp)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: this.$t('edit.success'),
                message: this.$t('edit.updateSuccess'),
                offset: 100,
                duration: 2000
              })
            })
            .catch(err => {
              console.error(new Error(err))
              this.$notify.error({
                title: this.$t('edit.failed'),
                message: this.$t('edit.updateFailed'),
                offset: 100,
                duration: 2000
              })
            })
        }
      })
    },
    showDelWarnGroup() {
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
      const epIds = []
      this.selectionGroup.forEach(el => {
        epIds.unshift(el.employeeId)
      })
      deleteGroup(this.userId, epIds, this.token)
        .then(response => {
          epIds.forEach(el => {
            const index = this.employeeList.findIndex((item, i) => {
              return item.employeeId === el
            })
            this.employeeList.splice(index, 1)
          })
          this.total = this.employeeList.length
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
    showDelWarn(index, row) {
      this.deleteStatus = 'item'
      this.warnDialogVisible = true
      this.temp = Object.assign({}, row)
    },
    handleDelete() {
      this.warnDialogVisible = false
      const { employeeId } = this.temp
      deleteEmployee(this.userId, employeeId, this.token)
        .then(response => {
          const index = this.employeeList.findIndex((item, i) => {
            return item.employeeId === employeeId
          })
          this.employeeList.splice(index, 1)
          this.total = this.employeeList.length
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
    handleDownload() {}
  }
}
</script>
