<template>
  <div class="app-container">
    <el-main>
      <el-tabs type="border-card">
        <!-- active projects -->
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-time"/>
            {{ $t('project.active') }}
          </span>
          <el-card>
            <!-- project table -->
            <el-table v-loading="listLoading" :data="activeProjList">
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <p>{{ $t('project.projId') }}:{{ scope.row.projId }}</p>
                  <p>{{ $t('project.projName') }}:{{ scope.row.projName }}</p>
                  <p>{{ $t('project.projStatus') }}:{{ statusMapper(scope.row.projStatus) }}</p>
                  <p>{{ $t('project.projDesc') }}:{{ scope.row.description }}</p>
                  <p>{{ $t('project.projOwnerName') }}:{{ scope.row.employeeName }}</p>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projId')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projId }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projStatus')">
                <template slot-scope="scope">
                  <el-tag
                    :type="scope.row.projStatus|statusTagFilter"
                  >{{ statusMapper(scope.row.projStatus) }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projOwnerName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('table.actions')">
                <template slot-scope="scope">
                  <router-link :to="'/project/view/'+scope.row.projId">
                    <el-button type="primary" size="mini">{{ $t('edit.edit') }}</el-button>
                  </router-link>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
        <!-- finished projects -->
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-check"/>
            {{ $t('project.finished') }}
          </span>
          <el-card>
            <!-- project table -->
            <el-table v-loading="listLoading" :data="finishedProjList">
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <p>{{ $t('project.projId') }}:{{ scope.row.projId }}</p>
                  <p>{{ $t('project.projName') }}:{{ scope.row.projName }}</p>
                  <p>{{ $t('project.projStatus') }}:{{ statusMapper(scope.row.projStatus) }}</p>
                  <p>{{ $t('project.projDesc') }}:{{ scope.row.description }}</p>
                  <p>{{ $t('project.projOwnerName') }}:{{ scope.row.employeeName }}</p>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projId')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projId }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projStatus')">
                <template slot-scope="scope">
                  <el-tag
                    :type="scope.row.projStatus|statusTagFilter"
                  >{{ statusMapper(scope.row.projStatus) }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projOwnerName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('table.actions')">
                <template slot-scope="scope">
                  <router-link :to="'/project/view/'+scope.row.projId">
                    <el-button type="primary" size="mini">{{ $t('edit.view') }}</el-button>
                  </router-link>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
        <!-- all projects -->
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-date"/>
            {{ $t('project.allProj') }}
          </span>
          <el-card>
            <!-- project table -->
            <el-table v-loading="listLoading" :data="myProjList">
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <p>{{ $t('project.projId') }}:{{ scope.row.projId }}</p>
                  <p>{{ $t('project.projName') }}:{{ scope.row.projName }}</p>
                  <p>{{ $t('project.projStatus') }}:{{ statusMapper(scope.row.projStatus) }}</p>
                  <p>{{ $t('project.projDesc') }}:{{ scope.row.description }}</p>
                  <p>{{ $t('project.projOwnerName') }}:{{ scope.row.employeeName }}</p>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projId')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projId }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.projName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projStatus')">
                <template slot-scope="scope">
                  <el-tag
                    :type="scope.row.projStatus|statusTagFilter"
                  >{{ statusMapper(scope.row.projStatus) }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column :label="$t('project.projOwnerName')">
                <template slot-scope="scope">
                  <span>{{ scope.row.employeeName }}</span>
                </template>
              </el-table-column>
              <el-table-column :label="$t('table.actions')">
                <template slot-scope="scope">
                  <router-link :to="'/project/view/'+scope.row.projId">
                    <el-button type="primary" size="mini">{{ $t('edit.view') }}</el-button>
                  </router-link>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </div>
</template>

<script>
import { getProjectByEmpId } from '@/api/project'
import { mapGetters } from 'vuex'

export default {
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
      listLoading: false,
      myProjList: null,
      activeProjList: null,
      finishedProjList: null
    }
  },
  computed: {
    ...mapGetters(['userId', 'token', 'employeeId'])
  },
  created() {
    this.getProjList()
  },
  methods: {
    getProjList() {
      this.listLoading = true
      getProjectByEmpId(this.userId, this.token, this.employeeId)
        .then(resp => {
          this.myProjList = resp.data.items
          console.log('frontend myProjList:', this.myProjList)
          this.activeProjList = this.myProjList.filter(proj => {
            return proj.projStatus === 'active'
          })
          this.finishedProjList = this.myProjList.filter(proj => {
            return proj.projStatus === 'finished'
          })
          this.listLoading = false
        })
        .catch(err => {
          console.error(err)
          this.$notify.error({})
        })
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
