// <!--output: frontend/api/loginAppHistory.js-->

import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/loginAppHistory/save',
    method: 'post',
    data
  })
}

export function del(ids) {
  let data = { batchIdList: ids }
  return request({
    url: 'api/loginAppHistory/batch/delete',
    method: 'post',
    data: data
  })
}

export function edit(data) {
  return request({
    url: 'api/loginAppHistory/save',
    method: 'post',
    data
  })
}

export default { add, edit, del }
