import Mock from 'mockjs'
import loginAPI from './login'
import employeeAPI from './employee'

const totalEmpl = 50
const totalProj = 5
const totalTask = 30
const totalTeam = 10
const projList = []
const projEmpl = []
const projTeam = []
const projTasks = []

for (let index = 0; index < totalEmpl; index++) {
  projEmpl.unshift(
    Mock.mock({
      projId: Mock.Random.integer(1, 5),
      employeeId: 1000 + index
    })
  )
}

const allEmpl = projId => {
  const list = projEmpl.filter(el => {
    return el.projId === projId
  })
  return list
}

for (let index = 0; index < totalProj; index++) {
  const empId = Mock.Random.shuffle(allEmpl(index + 1))[0].employeeId
  projList.unshift(
    Mock.mock({
      projId: index + 1,
      projName: '@cword(5,6)',
      employeeId: empId,
      createTime: "@datetime('yyyy-MM-dd')",
      endTime: '',
      'projStatus|1': ['analysis', 'pending', 'active', 'finished'],
      description: '@cparagraph(1,2)'
    })
  )
}

for (let pId = 1; pId <= totalProj; pId++) {
  for (let index = 0; index < totalTask / totalProj; index++) {
    const empIds = projEmpl.filter(proj => {
      return proj.projId === pId
    })
    const empId = Mock.Random.shuffle(empIds)[0].employeeId
    projTasks.unshift(Mock.mock({
      projId: pId,
      taskId: '@increment',
      taskName: '@ctitle(2,3)',
      employeeId: empId,
      createTime: Mock.Random.datetime('yyyy-MM-dd'),
      endTime: '',
      description: Mock.Random.cparagraph(1, 2),
      'taskStatus|1': ['pending', 'active', 'skipped', 'finished']
    }))
  }
}


const randomTeam = projId => {
  const list = Mock.Random.shuffle(allEmpl(projId))
  console.log('shuffle Team', list)
  const team = []
  const max = Mock.Random.integer(3, list.length)
  for (let i = 0; i < max; i++) {
    team.unshift({
      employeeId: list[i].employeeId
    })
  }
  return team
}

for (let index = 0; index < totalTeam; index++) {
  const pId = Mock.Random.integer(1, 5)
  const team = randomTeam(pId)
  const empId = Mock.Random.shuffle(team)[0].employeeId
  projTeam.unshift(Mock.mock({
    projId: pId,
    teamId: Mock.Random.increment() + 1000,
    teamName: Mock.Random.ctitle(3, 4),
    teamMemberId: team,
    employeeId: empId
  }))
}

export default {
  getAllProj: config => {
    console.log('backend getAllProj:', config.body)
    const { userId, token, query } = JSON.parse(config.body)
    const { name, page, limit, status, sort } = query
    const user = loginAPI.getInfo(userId)
    if (user && user.token === token &&
      user.roles.findIndex(el => {
        return el === 'admin' || el === 'projAdmin'
      }) >= 0) {
      let mockList = projList.filter(el => {
        if (status !== 'all' && el.projStatus !== status) {
          return false
        }
        if (name && el.projName.indexOf(name) < 0) {
          return false
        }
        return true
      })
      if (sort === '-id') {
        mockList = mockList.reverse()
      }
      const pageList = mockList.filter((el, index) => {
        return index < limit * page && index >= limit * (page - 1)
      })
      pageList.forEach(proj => {
        const empl = employeeAPI.getInfo(proj.employeeId)
        proj.employeeName = empl.name
      })
      return {
        total: mockList.length,
        items: pageList
      }
    } else {
      return false
    }
  },
  getProjByEmplId: config => {
    console.log('backend getProjByEmplId:', config.body)
    const { userId, token, employeeId } = JSON.parse(config.body)
    const user = loginAPI.getInfo(userId)
    if (user && user.token === token) {
      const projIds = projEmpl.filter(el => {
        return el.employeeId === employeeId
      })
      const mockList = []
      projIds.forEach(id => {
        mockList.unshift(projList.find(el => {
          return el.projId === id.projId
        }))
      })
      mockList.forEach(proj => {
        const owner = employeeAPI.getInfo(proj.employeeId)
        proj.employeeName = owner.name
      })
      return {
        total: mockList.length,
        items: mockList
      }
    }
  },
  getProjEmplById: config => {
    console.log('backend getProjEmplById:', config.body)
    const { userId, token, projId } = JSON.parse(config.body)
    console.log('backend projId', projId)
    const user = loginAPI.getInfo(userId)
    if (user && user.token === token) {
      const empls = projEmpl.filter(el => {
        return el.projId === parseInt(projId)
      })
      console.log('backend emplIds:', empls)
      empls.forEach(el => {
        const emp = employeeAPI.getInfo(el.employeeId)
        el.employeeName = emp.name
      })
      return {
        item: empls
      }
    } else {
      return {
        data: 'failed'
      }
    }
  },
  updateProjEmplById: config => {
    console.log('backend updateProjEmplById:', config.body)
    return {
      data: 'success'
    }
  },
  getProjById: config => {
    console.log('backend getProjById:', config.body)
    const { userId, token, projId } = JSON.parse(config.body)
    const user = loginAPI.getInfo(userId)
    if (user && user.token === token) {
      const projInfo = projList.find(el => {
        return el.projId === parseInt(projId)
      })
      const owner = employeeAPI.getInfo(projInfo.employeeId)
      projInfo.employeeName = owner.name
      console.log('projInfo:', projInfo)
      return {
        item: projInfo
      }
    } else {
      return {
        data: 'failed'
      }
    }
  },
  addProj: config => {
    console.log('backend addProj:', config.body)
    return {
      data: 'success'
    }
  },
  deleteProjById: config => {
    console.log('backend delProjById:', config.body)
    return {
      data: 'success'
    }
  },
  deleteProjByGroup: config => {
    console.log('backend delProjByGroup:', config.body)
    return {
      data: 'success'
    }
  },
  updateProjById: config => {
    console.log('backend updateProjById:', config.body)
    return {
      data: 'success'
    }
  },
  getTeamInfo: teamId => {

  },
  getTeamByProjId: config => {
    console.log('backend getTeamByProjId:', config.body)
    const { userId, token, projId } = JSON.parse(config.body)
    const user = loginAPI.getInfo(userId)
    console.log('getTeam projId', projId)
    if (user && user.token === token) {
      const teamList = projTeam.filter(el => {
        return el.projId === parseInt(projId)
      })
      console.log('teamList', teamList)
      // reassign
      const mockList = []
      teamList.forEach(el => {
        const { projId, teamId, teamName, teamMemberId, employeeId } = el
        console.log('teamMemberIds:', teamMemberId)
        const owner = employeeAPI.getInfo(employeeId)
        const names = []
        mockList.push({
          projId: projId,
          teamId: teamId,
          teamName: teamName,
          teamMember: names,
          employeeName: owner.name
        })
        teamMemberId.forEach(el => {
          const employee = employeeAPI.getInfo(el.employeeId)
          console.log('employee:', employee)
          names.push({
            employeeId: el.employeeId,
            name: employee.name
          })
        })
      })
      return {
        total: mockList.length,
        items: mockList
      }
    } else {
      return false
    }
  },
  getTeamByEmplId: config => {
    console.log('backend getTeamByEmplId:', config.body)
    const { userId, token, employeeId } = JSON.parse(config.body)
    const user = loginAPI.getInfo(userId)
    if (user && user.token === token) {
      const teamList = projTeam.filter(el => {
        return el.teamMemberId.findIndex((el) => {
          return el.employeeId === employeeId
        }) >= 0
      })
      const mockList = []
      teamList.forEach(el => {
        const { teamId, teamName, teamMemberId } = el
        const names = []
        mockList.push({
          teamId: teamId,
          teamName: teamName,
          teamMember: names
        })
        teamMemberId.forEach(el => {
          const name = employeeAPI.getInfo(el.employeeId).name
          names.push({
            employeeId: el.employeeId,
            name: name
          })
        })
      })
      return {
        total: mockList.length,
        items: mockList
      }
    } else {
      return false
    }
  },
  createTeamByProjId: config => {
    console.log('backend createTeamByProjId:', config.body)
    return {
      data: 'success'
    }
  },
  updateTeamByProjId: config => {
    console.log('backend updateTeamByProjId:', config.body)
    return {
      data: 'success'
    }
  },
  deleteTeamByProjId: config => {
    console.log('backend deleteTeamByProjId:', config.body)
    return {
      data: 'success'
    }
  },
  addTaskById: config => {
    console.log('backend addTaskById:', config.body)
    return {
      data: 'success'
    }
  },
  updateTaskById: config => {
    console.log('backend updateTaskById:', config.body)
    return {
      data: 'success'
    }
  },
  deleteTaskById: config => {
    console.log('backend delTaskById:', config.body)
    return {
      data: 'success'
    }
  },
  deleteTaskByGroup: config => {
    console.log('backend delTaskByGroup:', config.body)
    return {
      data: 'success'
    }
  },
  getTaskByProjId: config => {
    console.log('backend getTaskByProjId:', config.body)
    const { userId, token, projId } = JSON.parse(config.body)
    const user = loginAPI.getInfo(userId)
    // need to check user permission
    console.info('task projId:', projId)
    if (user && user.token === token) {
      console.log('projTasks:', projTasks)
      const taskList = projTasks.filter(el => {
        return el.projId === parseInt(projId)
      })
      taskList.forEach(task => {
        const emp = employeeAPI.getInfo(task.employeeId)
        task.employeeName = emp.name
      })
      console.log('backend taskList:', taskList)
      return {
        total: taskList.length,
        items: taskList
      }
    } else {
      return false
    }
  },
  getTaskByEmplId: config => {
    console.log('backend getTaskByEmplId:', config.body)
    return {
      data: 'success'
    }
  },
  deleteTeamByGroup: config => {
    console.log('backend delTeamByGroup:', config.body)
    return {
      data: 'success'
    }
  },
  addTeamById: config => {
    console.log('backend addTeamById:', config.body)
    return {
      data: 'success'
    }
  }

}
