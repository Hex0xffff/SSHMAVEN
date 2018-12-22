import request from '@/utils/request'

export function getEmployeeById(userId, epId, token) {
  const data = {
    userId: userId,
    employeeId: epId,
    token: token
  }
  return request({
    method: 'post',
    url: '/employee/info',
    data: data
  })
}

export function getAllEmployee(listQuery, userId, token) {
  const data = {
    query: listQuery,
    userId: userId,
    token: token
  }
  return request({
    method: 'post',
    url: '/employee/info/all',
    data: data
  })
}

export function deleteEmployee(userId, epId, token) {
  const data = {
    userId: userId,
    employeeId: epId,
    token: token
  }
  return request({
    method: 'delete',
    url: '/employee/delete',
    data: data
  })
}

export function deleteGroup(userId, epIds, token) {
  const data = {
    userId: userId,
    group: epIds,
    token: token
  }
  return request({
    method: 'delete',
    url: '/employee/deletegroup',
    data: data
  })
}

export function addEmployee(userId, epInfo, token) {
  const data = {
    userId: userId,
    employeeInfo: epInfo
  }
  return request({
    method: 'post',
    url: '/employee/add',
    data: data
  })
}

export function updateEmployee(userId, epInfo, token) {
  const data = {
    userId: userId,
    employeeInfo: epInfo,
    token: token
  }
  return request({
    method: 'post',
    url: '/employee/update',
    data: data
  })
}

export function getAllEmployeeNameId(userId, token) {
  const data = {
    userId: userId,
    token: token
  }
  return request({
    method: 'post',
    url: '/employee/project',
    data: data
  })
}
