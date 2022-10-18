import {request} from '@/adapter'

// AxqStaff对象
export class AxqStaff {
    constructor({
     id = 0,
     deptId = 0,
     jobId = 0,
     isAdmin = 0,
     accessExtraJson = <unknown>,
     username = '',
     passwd = '',
     nickname = '',
     mobile = '',
     avatar = '',
     status = 0 + 1,
     isDelete = 0,
     createTime = <unknown>,
     updateTime = <unknown>,
     deleteTime = <unknown>
     } = {}) {
      this.id = id;// Id
      this.deptId = deptId;// DeptId
      this.jobId = jobId;// JobId
      this.isAdmin = isAdmin;// IsAdmin
      this.accessExtraJson = accessExtraJson;// AccessExtraJson
      this.username = username;// Username
      this.passwd = passwd;// Passwd
      this.nickname = nickname;// Nickname
      this.mobile = mobile;// Mobile
      this.avatar = avatar;// Avatar
      this.status = status;// 1->启用；2->禁用
      this.isDelete = isDelete;// IsDelete
      this.createTime = createTime;// CreateTime
      this.updateTime = updateTime;// UpdateTime
      this.deleteTime = deleteTime;// DeleteTime
      
    }
}

export const getAxqStaff = (id, params = {}) => request({
  url: `/api/axq/staff/${id}`,
  method: 'GET',
  params: { ...params }
})

export const queryAxqStaffList = (params = {}) => request({
  url: '/api/axq/staff',
  method: 'GET',
  params: { ...params }
})

export const createAxqStaff = (data = {}) => request({
  url: '/api/axq/staff',
  method: 'POST',
  data
})

export const updateAxqStaff = (id, data = {}) => request({
  url: `/api/axq/staff/${id}`,
  method: 'PUT',
  data
})

export const deleteAxqStaff = (id) => request({
  url: `/api/axq/staff/${id}`,
  method: 'DELETE'
})

export const batchDeleteAxqStaff = (arr = []) => request({
  url: '/api/axq/staff',
  method: 'DELETE',
  data: arr
})

export const getPagingAxqStaff = (page = 0, rows = 100, params = {}) => request({
  url: '/api/axq/staff/paging',
  method: 'GET',
  params: { page, rows, params }
})