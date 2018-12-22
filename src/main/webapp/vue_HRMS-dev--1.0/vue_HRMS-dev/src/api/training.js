import request from '@/utils/request'

export function getTrainById(userId, trainId) {
  const data = {
    userId: userId,
    trainId: trainId
  }
  return request({
    method: 'post',
    url: '/train/info',
    data: data
  })
}

export function getAllTrain(userId) {
  const data = {
    userId: userId
  }
  return request({
    method: 'post',
    url: '/train/all',
    data: data
  })
}

export function deleteTrain(userId, trainId) {
  const data = {
    userId: userId,
    trainId: trainId
  }
  return request({
    method: 'delete',
    url: '/train/delete',
    data: data
  })
}

export function addTrain(userId, trainInfo) {
  const data = {
    userId: userId,
    trainInfo: trainInfo
  }
  return request({
    method: 'post',
    url: '/train/add',
    data: data
  })
}

export function updateTrain(userId, trainInfo) {
  const data = {
    userId: userId,
    trainInfo: trainInfo
  }
  return request({
    method: 'post',
    url: '/train/update',
    data: data
  })
}

export function addTrainDemand(userId, demandInfo) {
  const data = {
    userId: userId,
    demandInfo: demandInfo
  }
  return request({
    method: 'post',
    url: '/train/demand/add',
    data: data
  })
}

export function delTrainDemand(userId, demandId) {
  const data = {
    userId: userId,
    demandId: demandId
  }
  return request({
    method: 'delete',
    url: '/train/demand/delete',
    data: data
  })
}

export function updateTrainDemand(userId, demandId) {
  const data = {
    userId: userId,
    demandId: demandId
  }
  return request({
    method: 'post',
    url: '/train/demand/update',
    data: data
  })
}

export function addTrainResource(userId, resourceInfo) {
  const data = {
    userId: userId,
    resourceInfo: resourceInfo
  }
  return request({
    method: 'post',
    url: '/train/resource/add',
    data: data
  })
}

export function updateTrainResource(userId, resourceInfo) {
  const data = {
    userId: userId,
    resourceInfo: resourceInfo
  }
  return request({
    method: 'post',
    url: '/train/resource/update',
    data: data
  })
}

export function delTrainResource(userId, resourceId) {
  const data = {
    userId: userId,
    resourceId: resourceId
  }
  return request({
    method: 'post',
    url: '/train/resource/delete',
    data: data
  })
}

export function getAllDemand(userId) {
  const data = {
    userId: userId
  }
  return request({
    method: 'post',
    url: '/train/demand/all',
    data: data
  })
}

export function getAllResources(userId) {
  const data = {
    userId: userId
  }
  return request({
    method: 'post',
    url: '/train/resource/all',
    data: data
  })
}
