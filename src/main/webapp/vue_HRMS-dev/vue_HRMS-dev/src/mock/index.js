import Mock from 'mockjs'
import loginAPI from './login'
import articleAPI from './article'
import remoteSearchAPI from './remoteSearch'
import transactionAPI from './transaction'

import employeeAPI from './employee'
import projectAPI from './project'
// import trainingAPI from './training'

// 修复在使用 MockJS 情况下，设置 withCredentials = true，且未被拦截的跨域请求丢失 Cookies 的问题
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
// eslint-disable-next-line
Mock.XHR.prototype.send = function () {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false
  }
  this.proxy_send(...arguments)
}

// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
Mock.mock(/\/login\/login/, 'post', loginAPI.loginByUsername)
Mock.mock(/\/login\/logout/, 'post', loginAPI.logout)
Mock.mock(/\/user\/info\.*/, 'get', loginAPI.getUserInfo)

// 文章相关
Mock.mock(/\/article\/list/, 'get', articleAPI.getList)
Mock.mock(/\/article\/detail/, 'get', articleAPI.getArticle)
Mock.mock(/\/article\/pv/, 'get', articleAPI.getPv)
Mock.mock(/\/article\/create/, 'post', articleAPI.createArticle)
Mock.mock(/\/article\/update/, 'post', articleAPI.updateArticle)

// 搜索相关
Mock.mock(/\/search\/user/, 'get', remoteSearchAPI.searchUser)

// 账单相关
Mock.mock(/\/transaction\/list/, 'get', transactionAPI.getList)

// employee
Mock.mock(/\/api\/employee\/info\/all/, 'post', employeeAPI.getAllEmployee)
Mock.mock(/\/api\/employee\/info\/.*[0-9](|\/)$/, 'post', employeeAPI.getEmployeeById)
Mock.mock(/\/api\/employee\/info(|\/)$/, 'post', employeeAPI.getEmployeeById)
Mock.mock(/\/api\/employee\/add/, 'post', employeeAPI.addEmployee)
Mock.mock(/\/api\/employee\/delete/, 'delete', employeeAPI.deleteEmploylee)
Mock.mock(/\/api\/employee\/deletegroup/, 'delete', employeeAPI.deleteGroup)
Mock.mock(/\/api\/employee\/update/, 'post', employeeAPI.updateEmployee)
Mock.mock(/\/api\/employee\/project/, 'post', employeeAPI.getAllEmployeeNameId)

// project
Mock.mock(/\/api\/project\/info\/all/, 'post', projectAPI.getAllProj)
Mock.mock(/\/api\/project\/info$/, 'post', projectAPI.getProjById)
Mock.mock(/\/api\/project\/info\/empl$/, 'post', projectAPI.getProjEmplById)
Mock.mock(/\/api\/project\/info\/empl\/update$/, 'post', projectAPI.updateProjEmplById)
Mock.mock(/\/api\/project\/info\/myproject(|\/)$/, 'post', projectAPI.getProjByEmplId)
Mock.mock(/\/api\/project\/delete/, 'delete', projectAPI.deleteProjById)
Mock.mock(/\/api\/project\/delgroup/, 'delete', projectAPI.deleteProjByGroup)
Mock.mock(/\/api\/project\/add/, 'post', projectAPI.addProj)
Mock.mock(/\/api\/project\/update/, 'post', projectAPI.updateProjById)
// project task
Mock.mock(/\/api\/project\/task\/info\/all/, 'post', projectAPI.getTaskByProjId)
Mock.mock(/\/api\/project\/task\/delete/, 'delete', projectAPI.deleteTaskById)
Mock.mock(/\/api\/project\/task\/delgroup/, 'delete', projectAPI.deleteTaskByGroup)
Mock.mock(/\/api\/project\/task\/myproj(|\/)$/, 'post', projectAPI.getTaskByEmplId)
Mock.mock(/\/api\/project\/task\/add/, 'post', projectAPI.addTaskById)
Mock.mock(/\/api\/project\/task\/update/, 'post', projectAPI.updateTaskById)
// project team
Mock.mock(/\/api\/project\/team\/info/, 'post', projectAPI.getTeamByProjId)
Mock.mock(/\/api\/project\/team\/delete/, 'delete', projectAPI.deleteTeamByProjId)
Mock.mock(/\/api\/project\/team\/delgroup/, 'delete', projectAPI.deleteTeamByGroup)
Mock.mock(/\/api\/project\/team\/myproj(|\/)$/, 'post', projectAPI.getTeamByEmplId)
Mock.mock(/\/api\/project\/team\/add/, 'post', projectAPI.addTeamById)
Mock.mock(/\/api\/project\/team\/update/, 'post', projectAPI.updateTeamByProjId)

// training
// Mock.mock(/\/api\/training\/info\/all/, 'post')
// Mock.mock(/\/api\/training\/info\/.*[0-9]/, 'post')
// Mock.mock(/\/api\/training\/info(|\/)$/, 'get')
// Mock.mock(/\/api\/training\/add/, 'post')
// Mock.mock(/\/api\/training\/update/, 'post')
// Mock.mock(/\/api\/training\/delete/, 'delete')
// Mock.mock(/\/api\/training\/resource\/add/, 'post')
// Mock.mock(/\/api\/training\/resource\/update/, 'post')
// Mock.mock(/\/api\/training\/resource\/delete/, 'delete')

export default Mock
