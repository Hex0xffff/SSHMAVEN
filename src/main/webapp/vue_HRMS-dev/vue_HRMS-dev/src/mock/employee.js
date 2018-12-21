import Mock from 'mockjs'
import login from './login'
const total = 50
const employeeList = []

for (let i = 0; i < total; i++) {
  employeeList.push(
    Mock.mock({
      userId: i + 5 + 1000,
      employeeId: i + 1000,
      name: Mock.Random.cname(),
      address: Mock.Random.county(true),
      gender: Mock.Random.natural(0, 1),
      'job|1': ['frontend', 'backend', 'java', 'algorithm', 'database'],
      phone: '@integer(1e11,2e11)',
      idNumber: Mock.Random.id(),
      'status|1': ['participation', 'intership', 'dimission']

    }))
}

export default {
  getAllEmployee: config => {
    const { query, userId, token } = JSON.parse(config.body)
    const { name, gender, page, limit, sort } = query
    // check token
    const user = login.getInfo(userId)
    if (user && user.token === token) {
      let mockList = employeeList.filter(item => {
        if (gender !== -1 && item.gender !== gender) return false
        if (name && item.name.indexOf(name) < 0) return false
        return true
      })
      if (sort === '-id') {
        mockList = mockList.reverse()
      }
      const pageList = mockList.filter((item, index) => {
        return index < limit * page && index >= limit * (page - 1)
      })
      return {
        total: mockList.length,
        items: pageList
      }
    } else {
      // error
      return false
    }
  },
  getEmployeeById: config => {
    const { userId, employeeId, token } = JSON.parse(config.body)
    // check token
    const user = login.getInfo(userId)
    if (user && user.token === token) {
      return {
        item: employeeList.find(el => {
          return el.employeeId === employeeId
        })
      }
    } else {
      // error
      return false
    }
  },
  addEmployee: config => ({
    data: 'success'
  }),
  updateEmployee: config => ({
    data: 'success'
  }),
  deleteEmploylee: config => ({
    data: 'success'
  }),
  deleteGroup: config => ({
    data: 'success'
  }),
  // inner communication
  getInfo: employeeId => {
    return employeeList.find(el => {
      return el.employeeId === parseInt(employeeId)
    })
  },
  getAllEmployeeNameId: config => {
    const { userId, token } = JSON.parse(config.body)
    const user = login.getInfo(userId)
    if (user && user.token === token) {
      const mockList = []
      employeeList.forEach(el => {
        mockList.unshift(
          {
            employeeId: el.employeeId,
            employeeName: el.name
          }
        )
      })
      return {
        items: mockList
      }
    } else {
      return false
    }
  }
}
