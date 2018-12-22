<template>
  <el-container id="user-container">
    <el-header>
      <h1>{{ $t('route.userSetting') }}</h1>
    </el-header>
    <el-row :gutter="20" type="flex" justify="center">
      <el-col :span="6">

        <el-card>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-upload
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                class="avatar-uploader"
                action="http://localhost:8080/api/user/avatar">
                <img v-if="!hasAvatar" :src="'data:image/;base64,'+getIdIcon" class="avatar">
                <img v-if="hasAvatar" :src="avatar" class="avatar">
              </el-upload>
            </el-col>
            <el-col :span="8">
              <span>{{ name }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <el-form ref="form" :model="form" :rules="userRule" status-icon>
            <el-form-item :label="$t('user.newPwd')" prop="newPwd">
              <el-input v-model="form.newPwd" type="password" auto-complete="off"/>
            </el-form-item>
            <el-form-item :label="$t('user.confirm')" prop="checkPwd">
              <el-input v-model="form.checkPwd" type="password" auto-complete="off"/>
            </el-form-item>
            <el-form-item :label="$t('user.oldPwd')" prop="oldPwd">
              <el-input v-model="form.oldPwd" type="password" auto-complete="off"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit">{{ $t('user.submit') }}</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import Identicon from 'identicon.js'
import { mapGetters } from 'vuex'
export default {
  name: 'User',
  data() {
    const validatePwd = (rule, value, callback) => {
      console.log('value:' + value)
      if (!value) {
        const err = this.$t('user.missPwd')
        callback(new Error(err))
      } else if (this.form.checkPwd !== '') {
        this.$refs.form.validateField('checkPwd')
      }
      setTimeout(() => {
        const strongRegex = new RegExp(
          '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[-!$%^&*()_+|~=`{}\\[\\]:\\/;<>?,.@#]).{6,}'
        )
        console.log(strongRegex)
        if (!strongRegex.test(value)) {
          const err = this.$t('user.illegalPwd')
          callback(new Error(err))
        } else {
          callback()
        }
      }, 1000)
    }
    const isSame = (rule, value, callback) => {
      if (!value) {
        const err = this.$t('user.missPwd')
        callback(new Error(err))
      } else if (value !== this.form.newPwd) {
        const err = this.$t('user.notSame')
        callback(new Error(err))
      } else {
        callback()
      }
    }
    return {
      form: {
        userId: '',
        newPwd: '',
        checkPwd: '',
        oldPwd: ''
      },
      userRule: {
        newPwd: {
          required: true,
          validator: validatePwd,
          trigger: 'blur'
        },
        checkPwd: {
          required: true,
          validator: isSame,
          trigger: 'blur'
        },
        oldPwd: {
          required: true,
          validator: validatePwd,
          trigger: 'blur'
        }
      }
    }
  },
  computed: {
    ...mapGetters(['userId', 'name', 'hasAvatar', 'avatar']),
    getIdIcon() {
      const hash = btoa(this.name + 'haveNoaVAtaR')
      const data = new Identicon(hash, { size: 100 }).toString()
      return data
    }
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.avatar = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('Avatar picture must be JPG format!')
      }
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!')
      }
      return isJPG && isLt2M
    },
    submit() {}
  }
}
</script>

<style ref="stylesheet/scss" lang="scss" >
.avatar-uploader {
  .el-upload {
    border: 2px dashed #666666;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .el-upload:hover {
    border-color: #409eff;
  }
  .avatar {
    width: 80px;
    height: 80px;
    display: block;
  }
}
</style>
