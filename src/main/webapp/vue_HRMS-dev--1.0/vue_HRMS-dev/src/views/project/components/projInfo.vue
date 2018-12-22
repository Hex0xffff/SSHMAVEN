<template>
  <div class="app-container">
    <el-main>
      <el-tabs type="border-card">
        <!-- project info -->
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-date"/>
            {{ $t('project.baseInfo') }}
          </span>
          <el-card>
            <p>{{ $t('project.projId') }}:{{ projInfo.projId }}</p>
            <p>{{ $t('project.projName') }}:{{ projInfo.projName }}</p>
            <p>{{ $t('project.projStatus') }}:{{ statusMapper(projInfo.projStatus) }}</p>
            <p>{{ $t('project.projDesc') }}:{{ projInfo.description }}</p>
            <p>{{ $t('project.projOwnerName') }}:{{ projInfo.employeeName }}</p>
            <el-button>{{ $t('edit.edit') }}</el-button>
          </el-card>
        </el-tab-pane>
        <!-- project member -->
        <el-tab-pane>
          <span slot="label">
            <svg-icon icon-class="peoples"/>
            {{ $t('project.projMemberName') }}
          </span>
          <!-- project employList -->
          <el-card>
            <el-table :data="projEmplList" @selection-change="handleEmplSelect">
              <el-table-column type="selection" width="30"/>
              <el-table-column :label="$t('employee.id')" prop="employeeId" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeId }}</span>
                </template>
              </el-table-column>
              <el-table-column
                :label="$t('project.projMemberName')"
                prop="employeeName"
                align="center"
              >
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right">
                <template slot="header" slot-scope="scope">
                  <el-button
                    type="primary"
                    size="mini"
                    icon="el-icon-edit"
                    @click="emplFormVisible=true"
                  >{{ $t('edit.add') }}</el-button>
                  <el-button
                    type="warning"
                    icon="el-icon-delete"
                    size="mini"
                    @click="showDelEmplGroupWarn"
                  >{{ $t('edit.groupDel') }}</el-button>
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="showDelEmplWarn(scope.$index,scope.row)"
                  >Delete</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
        <!-- project task -->
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-tickets"></i>
            {{ $t('project.projTask')}}
          </span>
          <!-- task table-->
          <el-card>
            <el-table :data="taskList" @selection-change="handleTaskSelect">
              <el-table-column type="selection" width="30"/>
              <el-table-column :label="$t('project.taskId')" prop="taskId" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.taskId }}</span>
                </template>
              </el-table-column>
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-steps
                    :active="getTaskStatus(props.row.taskStatus)"
                    finish-status="success"
                    simple
                  >
                    <el-step :title="$t('project.active')" icon="el-icon-time"/>
                    <el-step :title="$t('project.finished')" icon="el-icon-success"/>
                  </el-steps>
                  <p>{{ $t('project.taskId') }}:{{ props.row.taskId }}</p>
                  <p>{{ $t('project.taskName') }}:{{ props.row.taskName }}</p>
                  <p>{{ $t('project.createTime') }}:{{ props.row.createTime }}</p>
                  <p>{{ $t('project.taskOwnerName') }}:{{ props.row.employeeName }}</p>
                  <p>{{ $t('project.taskDesc') }}:{{ props.row.description }}</p>
                </template>
              </el-table-column>
              <el-table-column
                :label="$t('project.taskName')"
                width="100"
                prop="taskId"
                align="center"
              >
                <template slot-scope="scope">
                  <span>{{ scope.row.taskName }}</span>
                </template>
              </el-table-column>
              <el-table-column
                :label="$t('project.taskStatus')"
                :filters="[ { text: this.$t('project.pending'),value:'pending'}, {text: this.$t('project.active'),value:'active'}, {text: this.$t('project.finished'),value:'finished'} ]"
                :filter-method="handleTagFilter"
                filter-placement="bottom-end"
                prop="taskStatus"
                align="center"
              >
                <template slot-scope="scope">
                  <!-- hack here -->
                  <el-dropdown
                    trigger="click"
                    placement="bottom-end"
                    @command=" command=> handleStatusChange(scope.row.taskId,command)"
                  >
                    <el-tag
                      :type="scope.row.taskStatus|statusTagFilter"
                      style="cursor:pointer;"
                    >{{ statusMapper(scope.row.taskStatus) }}</el-tag>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item
                        v-for="item in taskStatusOptions"
                        :key="item.key"
                        :command="item.key"
                      >{{ item.label }}</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.taskOwnerName')" prop="taskId" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right" width="300">
                <!-- see https://github.com/ElemeFE/element/pull/13012/files/991ef98f67d5380c26af6d9bf09c1f7dbda5c7e1#diff-f9a0f43892aff2d5d46d321fa34b903cR1530 -->
                <!-- https://github.com/ElemeFE/element/pull/13012 -->
                <template slot="header" slot-scope="slot">
                  <el-button
                    type="primary"
                    size="mini"
                    icon="el-icon-edit"
                    @click="handleCreateTask"
                  >add</el-button>
                  <el-button
                    :loading="downloadLoading"
                    class="filter-item"
                    type="primary"
                    size="mini"
                    icon="el-icon-download"
                  >{{ $t('table.export') }}</el-button>
                  <el-button
                    class="filter-item"
                    type="warning"
                    icon="el-icon-delete"
                    size="mini"
                    @click="showDelTaskGroupWarn"
                  >{{ $t('edit.groupDel') }}</el-button>
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    @click="editTask(scope.$index,scope.row)"
                  >Edit</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="showDelTaskWarn(scope.$index,scope.row)"
                  >Delete</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
        <!-- project team -->
        <el-tab-pane>
          <span slot="label">
            <svg-icon icon-class="tree"/>
            {{ $t('project.projTeam') }}
          </span>
          <!-- team table-->
          <el-card>
            <el-table :data="teamList" @selection-change="handleTeamSelect">
              <el-table-column type="selection" width="30"/>
              <el-table-column :label="$t('project.teamId')" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.teamId }}</span>
                </template>
              </el-table-column>
              <el-table-column type="expand">
                <template slot-scope="props">
                  <p>{{ $t('project.teamId') }}:{{ props.row.teamId }}</p>
                  <p>{{ $t('project.teamName') }}:{{ props.row.teamName }}</p>
                  <p>{{ $t('project.teamOwner') }}:{{ props.row.employeeName }}</p>
                  <p>{{ $t('project.teamMember') }}:</p>
                  <el-card>
                    <span
                      v-for="item in props.row.teamMember"
                      :key="item.employeeId"
                    >{{ item.name }}</span>
                  </el-card>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.teamName')" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.teamName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.teamOwner')" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right" width="300">
                <template slot="header" slot-scope="scope">
                  <el-button
                    type="primary"
                    size="mini"
                    icon="el-icon-edit"
                    @click="handleCreateTeam"
                  >add</el-button>
                  <el-button
                    :loading="downloadLoading"
                    class="filter-item"
                    type="primary"
                    size="mini"
                    icon="el-icon-download"
                  >{{ $t('table.export') }}</el-button>
                  <el-button
                    class="filter-item"
                    type="warning"
                    icon="el-icon-delete"
                    size="mini"
                    @click="showDelTeamGroupWarn"
                  >{{ $t('edit.groupDel') }}</el-button>
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    @click="editTeam(scope.$index,scope.row)"
                  >Edit</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="showDelTeamWarn(scope.$index,scope.row)"
                  >Delete</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
      </el-tabs>

      <!-- add employee into project -->
      <el-dialog :title="$t('project.addEmpl')" :visible.sync="emplFormVisible">
        <el-transfer
          v-model="projEmplIds"
          :titles="[$t('project.projAvaliableEmpl'), $t('project.projMemberName')]"
          :button-texts="[$t('project.rmEmpl'), $t('project.addEmpl')]"
          :format="{
        noChecked: '${total}',
        hasChecked: '${checked}/${total}'
        }"
          :data="transferData"
          style="text-align: left; display: inline-block"
          filterable
          @change="handleChangeProjEmpl"
        >
          <span slot-scope="{ option }">{{ option.key }} - {{ option.label }}</span>
        </el-transfer>
        <div slot="footer" class="dialog-footer">
          <el-button @click="emplFormVisible= false">{{ $t('table.cancel') }}</el-button>
          <el-button type="primary" @click="updateEmpl()">{{ $t('table.confirm') }}</el-button>
        </div>
      </el-dialog>
    </el-main>

    <!-- delete employee warn -->
    <el-dialog :title="$t('edit.warning')" :visible.sync="warnEmplVisible" center>
      <span v-text="$t('edit.askDel')"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="warnEmplVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="danger"
          @click="deleteStatus ==='group'? handleDelEmplByGroup():handleDelEmpl()"
        >{{ $t('table.confirm') }}</el-button>
      </span>
    </el-dialog>

    <!-- delete task warn -->
    <el-dialog :title="$t('edit.warning')" :visible.sync="warnTaskVisible" center>
      <span v-text="$t('edit.askDel')"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="warnTaskVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="danger"
          @click="deleteStatus ==='group'? handleDelTaskGroup():handleDelTask()"
        >{{ $t('table.confirm') }}</el-button>
      </span>
    </el-dialog>

    <!-- delete team warn -->
    <el-dialog :title="$t('edit.warning')" :visible.sync="warnTeamVisible" center>
      <span v-text="$t('edit.askDel')"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="warnTeamVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="danger"
          @click="deleteStatus ==='group'? handleDelTeamGroup():handleDelTeam()"
        >{{ $t('table.confirm') }}</el-button>
      </span>
    </el-dialog>

    <!--add/edit team form -->
    <el-dialog :title="$t('edit.edit')" :visible.sync="teamFormVisible">
      <el-form
        ref="teamForm"
        :rules="teamRules"
        :model="teamTemplate"
        label-position="top"
        label-width="70px"
      >
        <el-form-item :label="$t('project.teamName')">
          <el-input v-model="teamTemplate.teamName"/>
        </el-form-item>
        <el-form-item :label="$t('project.teamOwner')">
          <el-select v-model="teamTemplate.employeeId">
            <el-option
              v-for="item in teamTemplate.teamMember"
              :key="item.employeeId"
              :label="item.name"
              :value="item.employeeId"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-transfer
            v-model="teamMemIds"
            :titles="[$t('project.projAvaliableEmpl'), $t('project.teamMember')]"
            :button-texts="[$t('project.rmFromTeam'), $t('project.addInTeam')]"
            :format="{
        noChecked: '${total}',
        hasChecked: '${checked}/${total}'
        }"
            :data="teamTransferData"
            style="text-align: left; display: inline-block"
            filterable
            @change="handleChangeTeamEmpl"
          >
            <span slot-scope="{ option }">{{ option.key }} - {{ option.label }}</span>
          </el-transfer>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="teamFormVisible= false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="primary"
          @click="editStatus=='create'? createTeam():handleUpdateTeam()"
        >{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>
    <!-- add/edit task form -->
    <el-dialog :title="$t('edit.edit')" :visible.sync="taskFormVisible">
      <el-form
        ref="taskForm"
        :rules="taskRules"
        :model="taskTemplate"
        label-position="top"
        label-width="70px"
      >
        <el-form-item :label="$t('project.taskName')">
          <el-input v-model="taskTemplate.taskName"/>
        </el-form-item>
        <el-form-item :label="$t('project.taskOwnerName')">
          <el-select v-model="taskTemplate.employeeId">
            <el-option
              v-for="item in projEmplList"
              :key="item.employeeId"
              :label="item.employeeName"
              :value="item.employeeId"
            />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('project.taskStatus')">
          <el-select v-model="taskTemplate.taskStatus">
            <el-option
              v-for="item in taskStatusOptions"
              :key="item.key"
              :label="item.label"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="taskFormVisible= false">{{ $t('table.cancel') }}</el-button>
        <el-button
          type="primary"
          @click="editStatus=='create'? createTask():handleUpdateTask()"
        >{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addProjTeam,
  addProjTask,
  getProjectById,
  getProjTeamByProjId,
  getAllTaskByProjId,
  delProjTeamById,
  delProjTaskById,
  updateProject,
  updateProjectTask,
  updateProjectTeam,
  delProjTeamByGroup,
  delProjTaskByGroup,
  getProjEmplById,
  updateProjEmplById
} from '@/api/project'
import { getAllEmployeeNameId } from '@/api/employee'
import { mapGetters } from 'vuex'

export default {
  name: 'ProjInfo',
  filters: {
    statusTagFilter(status) {
      const statusMap = {
        skipped: 'info',
        pending: 'info',
        active: 'warning',
        finished: 'success'
      }
      return statusMap[status]
    }
  },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      deleteEmplId: null,
      teamTransferData: null,
      teamMemIds: null,
      transferData: null,
      projEmplList: null,
      downloadLoading: false,
      teamRules: {
        teamName: [
          {
            required: true,
            trigger: 'blur'
          }
        ],
        employeeName: [
          {
            required: true,
            trigger: 'change'
          }
        ]
      },
      taskRules: {
        taskName: [
          {
            required: true,
            trigger: 'blur'
          }
        ],
        employeeName: [
          {
            required: true,
            trigger: 'blur'
          }
        ],
        taskStatus: [
          {
            required: true,
            trigger: 'change'
          }
        ]
      },
      searchTask: '',
      emplFormVisible: false,
      taskFormVisible: false,
      teamFormVisible: false,
      taskList: null,
      teamList: null,
      employeeList: null,
      warnEmplVisible: false,
      warnTaskVisible: false,
      warnTeamVisible: false,
      deleteStatus: '',
      editStatus: '',
      projInfo: {
        projIndex: undefined,
        projName: '',
        employeeId: '',
        employeeName: '',
        createTime: '',
        endTime: '',
        projStatus: '',
        description: ''
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
      taskStatusOptions: [
        {
          key: 'pending',
          label: this.$t('project.pending')
        },
        {
          key: 'skipped',
          label: this.$t('project.skipped')
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
      taskTemplate: {
        projId: undefined,
        taskId: undefined,
        employeeId: undefined,
        employeeName: undefined,
        taskStatus: '',
        createTime: '',
        endTime: '',
        description: ''
      },
      teamTemplate: {
        projId: undefined,
        teamId: undefined,
        teamName: '',
        teamMember: undefined,
        employeeName: '',
        employeeId: ''
      },
      taskSelection: [],
      teamSelection: [],
      employeeSelection: [],
      projEmplIds: null,
      editProjId: null
    }
  },
  computed: {
    ...mapGetters(['userId', 'token', 'employeeId'])
  },
  created() {
    const id = this.$route.params && this.$route.params.id
    this.getEmplInfo()
      .then(() => {
        this.getProjEmplInfo(id)
        this.transferData = this.genTransferData()
      })
      .catch(err => {
        console.error(err)
      })
    this.getProjInfo(id)
    this.getTeamInfo(id)
    this.getTaskInfo(id)
    this.editProjId = id
  },
  methods: {
    handleTagFilter(value, row) {
      return row.taskStatus === value
    },
    computeIds() {
      const data = []
      this.projEmplList.forEach(el => {
        data.push(el.employeeId)
      })
      return data
    },
    computeTeamMemberIds() {
      const data = []
      this.teamTemplate.teamMember.forEach(el => {
        data.push(el.employeeId)
      })
      return data
    },
    genTransferData() {
      const data = []
      this.employeeList.forEach(el => {
        data.push({
          key: el.employeeId,
          label: el.employeeName,
          disabled: false
        })
      })
      return data
    },
    genProjEmplTransferData() {
      const data = []
      this.projEmplList.forEach(el => {
        data.push({
          key: el.employeeId,
          label: el.employeeName,
          disabled: false
        })
      })
      return data
    },
    getProjEmplInfo(id) {
      getProjEmplById(this.userId, this.token, id)
        .then(response => {
          console.log('frontend projEmpl:', response.data)
          this.projEmplList = response.data.item
          this.projEmplIds = this.computeIds()
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.getFailed'),
            duration: 2000
          })
        })
    },
    // update project employee
    updateEmpl() {
      updateProjEmplById(
        this.userId,
        this.token,
        this.editProjId,
        this.projEmplIds
      )
        .then(response => {
          this.projEmplList = this.employeeList.filter(el => {
            return (
              this.projEmplIds.findIndex(emp => {
                return emp === el.employeeId
              }) >= 0
            )
          })
          this.emplFormVisible = false
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.addSuccess'),
            duration: 2000
          })
        })
        .catch(err => {
          console.error(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.addFailed'),
            duration: 2000
          })
        })
    },
    handleChangeTeamEmpl(val, dir, mvKey) {
      console.log(val, dir, mvKey)
      console.log('teamEmplIds:', this.teamMemIds)
      const newTeamMembers = []
      this.teamMemIds.forEach(id => {
        newTeamMembers.push({
          employeeId: id,
          name: this.projEmplList.find(empl => {
            return empl.employeeId === id
          }).employeeName
        })
      })
      this.teamTemplate.teamMember = newTeamMembers
    },
    handleChangeProjEmpl(val, dir, mvKey) {
      console.log(val, dir, mvKey)
      console.log('projEmplIds:', this.projEmplIds)
    },
    showDelEmplGroupWarn() {
      if (this.employeeSelection.length > 0) {
        this.warnEmplVisible = true
        this.deleteStatus = 'group'
      } else {
        this.$message({
          message: this.$t('edit.noSelect'),
          type: 'warning'
        })
      }
    },
    showDelEmplWarn(index, row) {
      this.deleteStatus = 'item'
      this.warnEmplVisible = true
      this.deleteEmplId = row.employeeId
    },
    handleDelEmpl() {
      this.warnEmplVisible = false
      // delete empl
      const index = this.projEmplList.findIndex(el => {
        return el.employeeId === this.deleteEmplId
      })
      this.projEmplList.splice(index, 1)
      this.projEmplIds = this.computeIds()
      updateProjEmplById(this.userId, this.token, this.projEmplIds)
      // delete task
      const delTasksIds = []
      this.taskList
        .filter(task => {
          return task.employeeId === this.deleteEmplId
        })
        .forEach(task => {
          delTasksIds.push(task.taskId)
        })
      delProjTaskByGroup(this.userId, this.token, delTasksIds).then(() => {
        this.taskList = this.taskList.filter(task => {
          return task.employeeId !== this.deleteEmplId
        })
      })
      // delete team
      this.teamList.forEach(team => {
        if (
          team.teamMember.findIndex(empl => {
            return empl.employeeId === this.deleteEmplId
          }) >= 0
        ) {
          team.teamMember = team.teamMember.filter(empl => {
            return empl.employeeId !== this.deleteEmplId
          })
          this.teamTemplate = team
          updateProjectTeam(this.userId, this.token, this.teamTemplate)
        }
      })

      this.$notify.success({
        title: this.$t('edit.success'),
        message: this.$t('edit.delSuccess'),
        duration: 2000
      })
    },
    handleDelEmplByGroup() {
      this.warnEmplVisible = false
      this.employeeSelection.forEach(el => {
        const index = this.projEmplList.findIndex(empl => {
          return empl.employeeId === el.employeeId
        })
        // delete from emplList
        this.projEmplList.splice(index, 1)
        // delete task
        this.taskList = this.taskList.filter(task => {
          return task.employeeId !== el.employeeId
        })
        // update task
        // delete team
        this.teamList.forEach(team => {
          team.teamMember = el.teamMember.filter(empl => {
            return empl.employeeId !== el.employeeId
          })
        })
        // update team
      })
      this.$notify.success({
        title: this.$t('edit.success'),
        message: this.$t('edit.delSuccess'),
        duration: 2000
      })
    },
    handleEmplSelect(row) {
      this.employeeSelection = row
    },
    handleTaskSelect(row) {
      this.taskSelection = row
    },
    handleTeamSelect(row) {
      this.teamSelection = row
    },
    handleStatusChange(taskId, status) {
      console.log('taskId', taskId)
      const index = this.taskList.findIndex(el => {
        return el.taskId === taskId
      })
      if (index >= 0) {
        this.taskList[index].taskStatus = status
      }
    },
    statusMapper(status) {
      const options = {
        analysis: this.$t('project.analysis'),
        pending: this.$t('project.pending'),
        active: this.$t('project.active'),
        finished: this.$t('project.finished'),
        overdue: this.$t('project.overdue'),
        skipped: this.$t('project.skipped')
      }
      return options[status]
    },
    getTaskStatus(status) {
      const mapper = {
        pending: -1,
        active: 0,
        finished: 2,
        overdue: 0,
        skipped: -1
      }
      return mapper[status]
    },
    getProjInfo(projId) {
      getProjectById(this.userId, this.token, projId)
        .then(response => {
          console.log('frontend projectInfo:', response.data)
          this.projInfo = response.data.item
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.getFailed'),
            duration: 2000
          })
        })
    },
    getTeamInfo(projId) {
      getProjTeamByProjId(this.userId, this.token, projId)
        .then(response => {
          console.log('front teamInfo:', response.data)
          this.teamList = response.data.items
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.getFailed'),
            duration: 2000
          })
        })
    },
    getTaskInfo(projId) {
      getAllTaskByProjId(this.userId, this.token, projId)
        .then(response => {
          this.taskList = response.data.items
          console.log('frontend taskList:', this.taskList)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.getFailed'),
            duration: 2000
          })
        })
    },
    getEmplInfo() {
      return getAllEmployeeNameId(this.userId, this.token)
        .then(response => {
          this.employeeList = response.data.items
          console.log('frontend employeeList:', this.employeeList)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.getFailed'),
            duration: 2000
          })
        })
    },
    resetTaskTemp() {
      this.taskTemplate = {
        projId: this.editProj,
        taskId: undefined,
        employeeId: undefined,
        employeeName: undefined,
        taskStatus: 'pending',
        createTime: '',
        endTime: '',
        description: ''
      }
    },
    resetTeamTemp() {
      this.teamTemplate = {
        projId: this.editProjId,
        teamId: undefined,
        teamName: '',
        teamMember: undefined,
        employeeName: '',
        employeeId: ''
      }
    },
    editProj() {},
    handleUpdateProj() {
      updateProject(this.userId, this.token, this.projTemplate)
        .then(response => {})
        .catch(err => {
          console.log(err)
        })
    },
    editTask(index, row) {
      this.taskTemplate = Object.assign({}, row)
      this.editStatus = 'edit'
      this.taskFormVisible = true
    },
    handleUpdateTask() {
      console.log('handleUpdtaeTask',this.taskTemplate)
      this.taskTemplate.employeeName = this.projEmplList.find(empl => {
        return (empl.employeeId === this.taskTemplate.employeeId)
      }).employeeName
      updateProjectTask(this.userId, this.token, this.taskTemplate)
        .then(response => {
          const index = this.taskList.findIndex(task => {
            return task.taskId === this.taskTemplate.taskId
          })
          this.taskList.splice(index, 1, this.taskTemplate)
          this.taskFormVisible = false
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.updateSuccess'),
            offset: 100,
            duration: 2000
          })
        })
        .catch(err => {
          console.log(err)
        })
    },
    editTeam(index, row) {
      this.teamTemplate = Object.assign({}, row)
      this.teamTransferData = this.genProjEmplTransferData()
      this.teamMemIds = this.computeTeamMemberIds()
      this.editStatus = 'edit'
      this.teamFormVisible = true
    },
    handleUpdateTeam() {
      this.teamTemplate.employeeName = this.projEmplList.find(empl => {
        return (empl.employeeId = this.teamTemplate.employeeId)
      }).employeeName
      updateProjectTeam(this.userId, this.token, this.teamTemplate)
        .then(response => {
          const index = this.teamList.findIndex(team => {
            return team.teamId === this.teamTemplate.teamId
          })
          this.teamList.splice(index, 1, this.teamTemplate)
          this.teamFormVisible = false
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.updateSuccess'),
            offset: 100,
            duration: 2000
          })
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            title: this.$t('edit.failed'),
            message: this.$t('edit.updateFailed'),
            offset: 100,
            duration: 2000
          })
        })
    },
    showDelTaskWarn(index, row) {
      this.warnTaskVisible = true
      this.deleteStatus = 'item'
      this.taskTemplate = Object.assign({}, row)
    },
    showDelTaskGroupWarn() {
      if (this.taskSelection.length > 0) {
        this.warnTaskVisible = true
        this.deleteStatus = 'group'
      } else {
        this.$message({
          message: this.$t('edit.noSelect'),
          type: 'warning'
        })
      }
    },
    showDelTeamWarn(index, row) {
      this.warnTeamVisible = true
      this.deleteStatus = 'item'
      this.teamTemplate = Object.assign({}, row)
    },
    showDelTeamGroupWarn() {
      if (this.teamSelection.length > 0) {
        this.warnTeamVisible = true
        this.deleteStatus = 'group'
      } else {
        this.$message({
          message: this.$t('edit.noSelect'),
          type: 'warning'
        })
      }
    },
    handleDelTaskGroup() {
      this.warnTaskVisible = false
      const taskIds = []
      this.taskSelection.forEach(el => {
        taskIds.unshift(el.taskId)
      })
      delProjTaskByGroup(this.userId, this.token, taskIds)
        .then(response => {
          taskIds.forEach(el => {
            console.log('taskId:', el)
            const index = this.taskList.findIndex((item, i) => {
              return item.taskId === el
            })
            this.taskList.splice(index, 1)
          })
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
    handleDelTask() {
      this.warnTaskVisible = false
      const { taskId } = this.taskTemplate
      delProjTaskById(this.userId, this.token, taskId)
        .then(response => {
          const index = this.taskList.findIndex(el => {
            return el.taskId === taskId
          })
          this.taskList.splice(index, 1)
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
    handleDelTeamGroup() {
      this.warnTeamVisible = false
      const teamIds = []
      this.teamSelection.forEach(el => {
        teamIds.unshift(el.teamId)
      })
      delProjTeamByGroup(this.userId, this.token, teamIds)
        .then(response => {
          teamIds.forEach(el => {
            const index = this.teamList.findIndex((item, i) => {
              return item.teamId === el
            })
            this.teamList.splice(index, 1)
          })
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
    handleDelTeam() {
      this.warnTeamVisible = false
      const { teamId } = this.teamTemplate
      delProjTeamById(this.userId, this.token, this.editProjId, teamId)
        .then(response => {
          const index = this.teamList.findIndex(el => {
            return el.teamId === teamId
          })
          this.teamList.splice(index, 1)
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
    handleCreateTask() {
      this.resetTaskTemp()
      this.editStatus = 'create'
      this.taskFormVisible = true
      this.$nextTick().then(() => {
        this.$refs['taskForm'].clearValidate()
      })
    },
    handleCreateTeam() {
      this.resetTeamTemp()
      this.teamTransferData = this.genProjEmplTransferData()
      this.teamMemIds = []
      this.editStatus = 'create'
      this.teamFormVisible = true
      this.$nextTick().then(() => {
        this.$refs['teamForm'].clearValidate()
      })
    },
    createTask() {
      this.taskFormVisible = false
      addProjTask(this.userId, this.token, this.editProjId, this.taskTemplate)
        .then(response => {
          this.taskTemplate.taskId = response.data.taskId
          this.taskTemplate.employeeName = this.projEmplList.find(empl => {
            return empl.employeeId === this.taskTemplate.employeeId
          }).employeeName
          console.log('frontend add task:',this.taskTemplate)
          this.taskList.unshift(this.taskTemplate)
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.addSuccess'),
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
    },
    createTeam() {
      this.teamFormVisible = false
      this.teamTemplate.employeeName = this.projEmplList.find(empl => {
        return (empl.employeeId = this.teamTemplate.employeeId)
      }).employeeName
      addProjTeam(this.userId, this.token, this.teamTemplate)
        .then(response => {
          // get teamId from backend
          console.log(response.data)
          this.teamTemplate.teamId = response.data.teamId
          this.teamList.unshift(this.teamTemplate)
          this.$notify.success({
            title: this.$t('edit.success'),
            message: this.$t('edit.addSuccess'),
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
    }
  }
}
</script>

