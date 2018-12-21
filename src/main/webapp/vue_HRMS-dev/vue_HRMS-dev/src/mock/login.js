import { param2Obj } from '@/utils'

const userMap = {
  admin: {
    userId: 0,
    employee: 1000,
    roles: ['admin'],
    token: 'admin',
    introduction: '我是超级管理员',
    hasAvatar: false,
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  projectAdmin: {
    userId: 1,
    employeeId: 1001,
    roles: ['projectAdmin', 'employee'],
    token: 'projectAdmin',
    introduction: '我是projectAdmin',
    hasAvatar: false,
    avatar: '',
    name: 'project Admin'
  },
  employeeAdmin: {
    userId: 2,
    employeeId: 1002,
    roles: ['employeeAdmin', 'employee'],
    token: 'employeeAdmin',
    introduction: '我是employeejAdmin',
    hasAvatar: false,
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'project Admin'
  },
  trainAdmin: {
    userId: 3,
    employeeId: 1003,
    roles: ['trainAdmin', 'employee'],
    token: 'trainAdmin',
    introduction: '我是projectAdmin',
    hasAvatar: false,
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'project Admin'
  },
  employee: {
    userId: 4,
    employeeId: 1005,
    roles: ['employee'],
    token: 'employee',
    introduction: '我是employee',
    hasAvatar: false,
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal employee'
  },
  test: {
    userId: 5,
    employeeId: 1006,
    roles: ['admin', 'employee'],
    token: 'test',
    introduction: 'test',
    hasAvatar: false,
    avatar: '',
    name: 'test'
  }
}

export default {
  loginByUsername: config => {
    const { username } = JSON.parse(config.body)
    return userMap[username]
  },
  getUserInfo: config => {
    const { token } = param2Obj(config.url)
    if (userMap[token]) {
      return userMap[token]
    } else {
      return false
    }
  },
  logout: () => 'success',
  getInfo: userId => {
    let user
    Object.keys(userMap).forEach(element => {
      if (userMap[element].userId === userId) {
        user = userMap[element]
      }
    })
    return user
  }
}
