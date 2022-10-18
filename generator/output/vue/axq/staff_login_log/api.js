import {request} from '@/adapter'

// AxqStaffLoginLog对象
export class AxqStaffLoginLog {
    constructor({
     id = 0,
     username = '',
     passwd = '',
     ip = '',
     isDelete = 0,
     createTime = <unknown>,
     updateTime = <unknown>,
     deleteTime = <unknown>
     } = {}) {
      this.id = id;// Id
      this.username = username;// Username
      this.passwd = passwd;// Passwd
      this.ip = ip;// Ip
      this.isDelete = isDelete;// IsDelete
      this.createTime = createTime;// CreateTime
      this.updateTime = updateTime;// UpdateTime
      this.deleteTime = deleteTime;// DeleteTime
      
    }
}

export const getAxqStaffLoginLog = (id, params = {}) => request({
  url: `/api/axq/staff_login_log/${id}`,
  method: 'GET',
  params: { ...params }
})

export const queryAxqStaffLoginLogList = (params = {}) => request({
  url: '/api/axq/staff_login_log',
  method: 'GET',
  params: { ...params }
})

export const createAxqStaffLoginLog = (data = {}) => request({
  url: '/api/axq/staff_login_log',
  method: 'POST',
  data
})

export const updateAxqStaffLoginLog = (id, data = {}) => request({
  url: `/api/axq/staff_login_log/${id}`,
  method: 'PUT',
  data
})

export const deleteAxqStaffLoginLog = (id) => request({
  url: `/api/axq/staff_login_log/${id}`,
  method: 'DELETE'
})

export const batchDeleteAxqStaffLoginLog = (arr = []) => request({
  url: '/api/axq/staff_login_log',
  method: 'DELETE',
  data: arr
})

export const getPagingAxqStaffLoginLog = (page = 0, rows = 100, params = {}) => request({
  url: '/api/axq/staff_login_log/paging',
  method: 'GET',
  params: { page, rows, params }
})