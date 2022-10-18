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
    }
  ]
}