<template>
  <div class="app-container">
    <el-tabs>
      <!-- basic information -->
      <el-tab-pane>
        <span slot="label">
          <i class="el-icon-info"/>
          {{ $t('project.baseInfo') }}
        </span>
        <el-card>
          <p>{{ $t('project.projId') }}:{{ projInfo.projId }}</p>
          <p>{{ $t('project.projName') }}:{{ projInfo.projName }}</p>
          <p>{{ $t('project.projStatus') }}:{{ statusMapper(projInfo.projStatus) }}</p>
          <p>{{ $t('project.projDesc') }}:{{ projInfo.description }}</p>
          <p>{{ $t('project.projOwnerName') }}:{{ projInfo.employeeName }}</p>
          <p>{{ $t('project.teamMember') }}:</p>
          <el-card>
            <el-tag
              v-for="item in projEmplList"
              :key="item.employeeId"
            >{{ item.employeeName }}</el-tag>
          </el-card>
        </el-card>
      </el-tab-pane>
      <!-- my tasks -->
      <el-tab-pane>
        <span slot="label">
          <i class="el-icon-tickets"/>
          {{ $t('project.myTask') }}
        </span>
        <el-card>
          <el-table :data="myTaskList">
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
                <span>
                  <el-tag
                    :type="scope.row.taskStatus|statusTagFilter"
                    style="cursor:pointer;"
                  >{{ statusMapper(scope.row.taskStatus) }}</el-tag>
                <el-dropdown
                v-if="scope.row.employeeId===employeeId"
                  trigger="click"
                  placement="bottom-end"
                  @command=" command=> handleStatusChange(scope.row.taskId,command)"
                >
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item
                      v-for="item in taskStatusOptions"
                      :key="item.key"
                      :command="item.key"
                    >{{ item.label }}</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                </span>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-tab-pane>
      <!-- all tasks -->
      <el-tab-pane>
        <span slot="label">
          <i class="el-icon-date"/>
          {{ $t('project.projTask') }}
        </span>
        <el-card>
          <el-table :data="allTaskList">
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
                <span>
                  <el-tag
                    :type="scope.row.taskStatus|statusTagFilter"
                  >{{ statusMapper(scope.row.taskStatus) }}</el-tag>
                  <el-dropdown
                    v-if="scope.row.employeeId===employeeId"
                    trigger="click"
                    placement="bottom-end"
                    @command=" command=> handleStatusChange(scope.row.taskId,command)"
                  >
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item
                        v-for="item in taskStatusOptions"
                        :key="item.key"
                        :command="item.key"
                      >{{ item.label }}</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </span>
              </template>
            </el-table-column>
            <el-table-column :label="$t('project.taskOwnerName')" prop="taskId" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.employeeName }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-tab-pane>
      <!-- my teams -->
      <el-tab-pane>
        <span slot="label">
          <i class="el-icon-document"/>
          {{ $t('project.projTeam') }}
        </span>
        <el-card>
          <el-table :data="allTeamList">
            <el-table-column type="expand">
              <template slot-scope="props">
                <p>{{ $t('project.teamId') }}:{{ props.row.teamId }}</p>
                <p>{{ $t('project.teamName') }}:{{ props.row.teamName }}</p>
                <p>{{ $t('project.teamOwner') }}:{{ props.row.employeeName }}</p>
                <p>{{ $t('project.teamMember') }}:</p>
                <el-card>
                  <el-tag
                    v-for="item in props.row.teamMember"
                    :key="item.employeeId"
                  >{{ item.name }}</el-tag>
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
          </el-table>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {
  getProjectById,
  getAllTaskByProjId,
  getProjTeamByProjId,
  getProjEmplById
} from '@/api/project'

import { mapGetters } from 'vuex'
export default {
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
  data() {
    return {
      dataLoading: true,
      projInfo: null,
      myTaskList: null,
      allTaskList: null,
      myTeamList: null,
      allTeamList: null,
      projId: undefined,
      projEmplList: null,
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
      ]
    }
  },
  computed: {
    ...mapGetters(['userId', 'token', 'employeeId'])
  },
  created() {
    this.dataLoading = true
    this.projId = this.$route.params && this.$route.params.id
    this.getProjInfo(this.projId)
    this.getTaskInfo(this.projId)
    this.getTeamInfo(this.projId)
    this.getProjEmplInfo(this.projId)
    this.dataLoading = false
  },
  methods: {
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
    getProjInfo(id) {
      getProjectById(this.userId, this.token, id)
        .then(resp => {
          this.projInfo = resp.data.item
        })
        .catch(err => {
          console.error(err)
          this.$notify.error({})
        })
    },
    getProjEmplInfo(id) {
      getProjEmplById(this.userId, this.token, id)
        .then(response => {
          console.log('frontend projEmpl:', response.data)
          this.projEmplList = response.data.item
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
    getTaskInfo(id) {
      getAllTaskByProjId(this.userId, this.token, id)
        .then(resp => {
          this.allTaskList = resp.data.items
          this.myTaskList = this.allTaskList.filter(task => {
            return task.employeeId === this.employeeId
          })
        })
        .catch(err => {
          console.error(err)
          this.$notify.error({})
        })
    },
    getTeamInfo(id) {
      getProjTeamByProjId(this.userId, this.token, id)
        .then(resp => {
          this.allTeamList = resp.data.items
          this.myTeamList = this.allTeamList.filter(team => {
            return (
              team.teamMember.findIndex(epl => {
                return epl.employeeId === this.employeeId
              }) >= 0
            )
          })
        })
        .catch(err => {
          console.error(err)
          this.$notify.error({})
        })
    },
    handleTagFilter(value, row) {
      return row.taskStatus === value
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
    }
  }
}
</script>

