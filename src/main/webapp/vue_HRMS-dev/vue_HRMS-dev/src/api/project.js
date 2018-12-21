import request from '@/utils/request'

export function getProjectById(userId, token, projId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId
  }
  return request({
    method: 'post',
    url: '/project/info',
    data: data
  })
}

export function getAllProject(userId, token, query) {
  const data = {
    userId: userId,
    token: token,
    query: query
  }
  return request({
    method: 'post',
    url: '/project/info/all',
    data: data
  })
}

export function deleteProjectById(userId, token, projId) {
  const data = {
    userId: userId,
    token: token,
    projectId: projId
  }
  return request({
    method: 'delete',
    url: '/project/delete',
    data: data
  })
}

export function addProject(userId, token, projInfo) {
  const data = {
    userId: userId,
    token: token,
    projectInfo: projInfo
  }
  return request({
    method: 'post',
    url: '/project/add',
    data: data
  })
}

export function updateProject(userId, token, projInfo) {
  const data = {
    userId: userId,
    token: token,
    projectInfo: projInfo
  }
  return request({
    method: 'post',
    url: '/project/update',
    data: data
  })
}

export function delProjByGroup(userId, token, projIds) {
  const data = {
    userId: userId,
    token: token,
    projIds: projIds
  }
  return request({
    method: 'delete',
    url: '/project/delgroup',
    data: data
  })
}

export function addProjTask(userId, token, projId, taskInfo) {
  const data = {
    userId: userId,
    token: token,
    projectId: projId,
    taskInfo: taskInfo
  }
  return request({
    method: 'post',
    url: '/project/task/add',
    data: data
  })
}

export function delProjTaskById(userId, token, projId, taskId) {
  const data = {
    userId: userId,
    token: token,
    projectId: projId,
    taskId: taskId
  }
  return request({
    method: 'delete',
    url: '/project/task/delete',
    data: data
  })
}

export function delProjTaskByGroup(userId, token, projId, taskIds) {
  const data = {
    userId: userId,
    token: token,
    projectId: projId,
    taskIds: taskIds
  }
  return request({
    method: 'delete',
    url: '/project/task/delgroup',
    data: data
  })
}

export function delProjTeamByGroup(userId, token, projId, teamIds) {
  const data = {
    userId: userId,
    token: token,
    projectId: projId,
    teamIds: teamIds
  }
  return request({
    method: 'delete',
    url: '/project/team/delgroup',
    data: data
  })
}

export function updateProjectTask(userId, token, taskInfo) {
  const data = {
    userId: userId,
    token: token,
    taskInfo: taskInfo
  }
  return request({
    method: 'post',
    url: '/project/task/update',
    data: data
  })
}

export function updateProjectTeam(userId, token, teamInfo) {
  const data = {
    userId: userId,
    token: token,
    teamInfo: teamInfo
  }
  return request({
    method: 'post',
    url: '/project/team/update',
    data: data
  })
}

export function addProjTeam(userId, token, projId, teamInfo) {
  const data = {
    userId: userId,
    token: token,
    projId: projId,
    teamInfo: teamInfo
  }
  return request({
    method: 'post',
    url: '/project/team/add',
    data: data
  })
}

export function getAllProjTeam(userId, token) {
  const data = {
    userId: userId,
    token: token
  }
  return request({
    method: 'post',
    url: '/project/team/all',
    data: data
  })
}

export function getProjTeamByProjId(userId, token, projId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId
  }
  return request({
    method: 'post',
    url: '/project/team/info',
    data: data
  })
}

export function getProjectByEmpId(userId, token, employeeId) {
  const data = {
    userId: userId,
    token: token,
    employeeId: employeeId
  }
  return request({
    method: 'post',
    url: '/project/info/myproject',
    data: data
  })
}

export function getTaskByEmpId(userId, token, employeeId) {
  const data = {
    userId: userId,
    token: token,
    employeeId: employeeId
  }
  return request({
    method: 'post',
    url: '/project/task/info/mytask',
    data: data
  })
}

export function getAllTaskByProjId(userId, token, projId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId
  }
  return request({
    method: 'post',
    url: '/project/task/info/all',
    data: data
  })
}

export function delProjTeamById(userId, token, projId, teamId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId,
    teamId: teamId
  }
  return request({
    method: 'delete',
    url: '/project/team/delete',
    data: data
  })
}

export function getProjEmplById(userId, token, projId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId
  }
  return request({
    method: 'post',
    url: '/project/info/empl',
    data: data
  })
}

export function updateProjEmplById(userId, token, projId, emplIds) {
  const data = {
    userId: userId,
    token: token,
    projId: projId,
    emplIds: emplIds
  }
  return request({
    method: 'post',
    url: '/project/info/empl',
    data: data
  })
}

export function delProjEmplByGroup(userId, token, projId, emplIds) {
  const data = {
    userId: userId,
    token: token,
    projId: projId,
    emplIds: emplIds
  }
  return request({
    method: 'delete',
    url: '/project/info/empl/delgroup',
    data: data
  })
}
export function delProjEmplById(userId, token, projId, emplId) {
  const data = {
    userId: userId,
    token: token,
    projId: projId,
    emplIds: emplId
  }
  return request({
    method: 'delete',
    url: '/project/info/empl/delete',
    data: data
  })
}
