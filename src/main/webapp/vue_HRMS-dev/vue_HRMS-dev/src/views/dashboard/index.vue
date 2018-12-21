<template>
  <div class="dashboard-container">
    <component :is="currentRole"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'
import employeeDashboard from './employee'

export default {
  name: 'Dashboard',
  components: { adminDashboard, employeeDashboard },
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters(['roles'])
  },
  created() {
    if (!this.roles.includes('admin')) {
      this.currentRole = 'employeeDashboard'
    }
  }
}
</script>
