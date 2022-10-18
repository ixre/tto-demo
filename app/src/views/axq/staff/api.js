import {request} from '@/adapter'

// AxqStaff对象
export class AxqStaff {
    constructor({
     Id = 0,
     DeptId = 0,
     JobId = 0,
     IsAdmin = 0,
     Username = '',
     Passwd = '',
     Nickname = '',
     Mobile = '',
     Avatar = '',
     Status = 0 + 1,
     IsDelete = 0,
     } = {}) {
      this.Id = Id;// Id
      this.DeptId = DeptId;// DeptId
      this.JobId = JobId;// JobId
      this.IsAdmin = IsAdmin;// IsAdmin
      this.Username = Username;// Username
      this.Passwd = Passwd;// Passwd
      this.Nickname = Nickname;// Nickname
      this.Mobile = Mobile;// Mobile
      this.Avatar = Avatar;// Avatar
      this.Status = Status;// 1->启用；2->禁用
      this.IsDelete = IsDelete;// IsDelete
      
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