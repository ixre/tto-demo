/** 如果新增编辑在新的窗口打开, 去掉create/edit/list 的hidden属性 */
export const axqRoutes = {
  path: '/axq/',
  redirect: 'staff/index',
  meta: {
    // roles: ["admin"],
    title: 'axq',
    icon: 'example'
  },
  children: [
    {
      path: 'staff/index',
      name: 'AxqStaffIndex',
      component: () => import(/* webpackChunkName: "axq_staff-index" */ './staff/index.vue'),
      meta: {
        // roles: ["admin"],
        title: 'AxqStaff',
        icon: 'list'
      }
    },
    {
      path: 'staff/detail',
      name: 'AxqStaffDetail',
      component: () => import(/* webpackChunkName: "axq_staff-detail" */ './staff/modal.vue'),
      meta: {
        // roles: ["admin"],
        title: 'AxqStaff',
        hidden: true
      }
    },
    {
      path: 'staffloginlog/index',
      name: 'AxqStaffLoginLogIndex',
      component: () => import(/* webpackChunkName: "axq_staff_login_log-index" */ './staffloginlog/index.vue'),
      meta: {
        // roles: ["admin"],
        title: 'AxqStaffLoginLog',
        icon: 'list'
      }
    },
    {
      path: 'staffloginlog/detail',
      name: 'AxqStaffLoginLogDetail',
      component: () => import(/* webpackChunkName: "axq_staff_login_log-detail" */ './staffloginlog/modal.vue'),
      meta: {
        // roles: ["admin"],
        title: 'AxqStaffLoginLog',
        hidden: true
      }
    }
  ]
}