import request from '@/utils/request'

export function updatePwd(userId, oldPwd, newPwd) {
  const data = {
    userId: userId,
    oldPwd: oldPwd,
    newPwd: newPwd
  }
  return request({
    method: 'post',
    url: '/user/pwd',
    data: data
  })
}
