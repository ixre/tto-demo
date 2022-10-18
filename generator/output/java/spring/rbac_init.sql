/**
该模板生成权限系统的SQL脚本

# 变量
VUE_PREFIX: 如:bz/
*/







/** 三级菜单才会用到这个层级 */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('autogen', 0, 0, '', 'autogen', 'menu', '', 0, 0, 0, 1607214871, '', '');





/* ====== insert data : axq ===== */

INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('axq', 0, (SELECT DISTINCT id FROM perm_res WHERE name='autogen' LIMIT 1), 'axq', '/axq/index', 'hamburger', '', 0, 0, 0, 1607214871, '', '');


   /* AxqStaff前端页面 */
   INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
   VALUES ('AxqStaff', 2, (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff', 'axq/staff/index', 'hamburger', '',
    0, 0, 0, 1607214871, 'AxqStaffIndex', '');

   /* 新增AxqStaff */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('新增AxqStaff(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff:create', '', 'create', '',0, 0, 0, 0, '', '');

/* 更新AxqStaff */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('更新AxqStaff(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff:update', '', 'update', '',0, 0, 0, 0, '', '');

/* 查询AxqStaff */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('查询AxqStaff(接口)', 1, (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff:get', '', 'update', '',0, 0, 0, 0, '', '');

/* 查询AxqStaff */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('AxqStaff列表(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff:list', '', 'update', '',0, 0, 0, 0, '', '');

/* 删除AxqStaff */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('删除AxqStaff(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff:delete', '', 'update', '',0, 0, 0, 0, '', '');


   /* AxqStaffLoginLog前端页面 */
   INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
   VALUES ('AxqStaffLoginLog', 2, (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log', 'axq/staff_login_log/index', 'hamburger', '',
    0, 0, 0, 1607214871, 'AxqStaffLoginLogIndex', '');

   /* 新增AxqStaffLoginLog */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('新增AxqStaffLoginLog(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log:create', '', 'create', '',0, 0, 0, 0, '', '');

/* 更新AxqStaffLoginLog */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('更新AxqStaffLoginLog(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log:update', '', 'update', '',0, 0, 0, 0, '', '');

/* 查询AxqStaffLoginLog */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('查询AxqStaffLoginLog(接口)', 1, (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log:get', '', 'update', '',0, 0, 0, 0, '', '');

/* 查询AxqStaffLoginLog */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('AxqStaffLoginLog列表(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log:list', '', 'update', '',0, 0, 0, 0, '', '');

/* 删除AxqStaffLoginLog */
INSERT INTO public.perm_res (name, res_type, pid, key, path, icon, permission, sort_num, is_external, is_hidden, create_time, component_name, cache_)
VALUES ('删除AxqStaffLoginLog(接口)', 1,  (SELECT DISTINCT id FROM perm_res WHERE name='axq' LIMIT 1),
    'axq:staff_login_log:delete', '', 'update', '',0, 0, 0, 0, '', '');