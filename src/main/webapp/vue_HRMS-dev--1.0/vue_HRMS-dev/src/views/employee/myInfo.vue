<template>
  <el-container class="app-container">
    <el-main>
      <el-card style="margin: 0 auto;width:260px;">
        <div slot="header">
          <h5 v-text="this.$t('employee.myInfo')"/>
          <span>{{ myInfo.name }}</span>
        </div>
        <p>{{ $t('employee.id') }}:{{ myInfo.employeeId }}</p>
        <p>{{ $t('employee.job') }}:{{ myInfo.job }}</p>
        <p>{{ $t('employee.jobStatus') }}:{{ myInfo.status }}</p>
        <p>{{ $t('employee.address') }}:{{ myInfo.address }}</p>
        <p>{{ $t('employee.phone') }}:{{ myInfo.phone }}</p>
      </el-card>
    </el-main>
  </el-container>
</template>
<script>
import { mapGetters } from 'vuex'
import { getEmployeeById } from '@/api/employee'

export default {
  data() {
    return {
      myInfo: {
        employeeId: '',
        job: '',
        status: '',
        name: '',
        gender: '',
        phone: '',
        address: '',
        idNumber: ''
      }
    }
  },
  computed: {
    ...mapGetters(['name', 'token', 'userId', 'employeeId'])
  },
  created() {
    this.getInfo()
  },
  methods: {
    getInfo() {
      getEmployeeById(this.userId, this.employeeId, this.token)
        .then(response => {
          this.myInfo = response.data.item
        })
        .catch(err => {
          console.log(err)
          this.$message({
            message: this.$t('edit.failed'),
            type: 'error'
          })
        })
    }
  }
}
</script>
