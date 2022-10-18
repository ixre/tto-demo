<template>
<div class="app-container mod-grid-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container mod-grid-header">
        <div class="it mod-grid-header-back" v-show="false">
            <el-button class="filter-item" @click="handleBack">返回</el-button>
        </div>
        <div class="it mod-grid-header-filter">
          <el-form :inline="true">
            <el-form-item label="状态:" class="filter-item">
              <el-select v-model="queryParams.state" class="filter-select"
                         @change="queryPagingData">
                <el-option v-for="(it,i) in stateOptions" :key="i" :label="it.key" :value="it.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="关键词:" class="filter-item">
              <el-input v-model="queryParams.keyword" clearable class="filter-input"
                        placeholder="请输入关键词"/>
            </el-form-item>
            <el-form-item label="排序方式:" class="filter-item">
              <el-select v-model="queryParams.order_by" class="filter-select">
                <el-option v-for="(it,i) in sortOptions" :key="i" :value="it.value" :label="it.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item class="filter-item">
              <el-button v-permission="['admin']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">搜索</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="it mod-grid-header-create">
            <el-button v-permission="['admin']" class="filter-item" icon="el-icon-plus" @click="handleCreate">新增</el-button>
        </div>
        <div class="it mod-grid-header-del">
            <el-button v-show="selectedRows.length > 0" v-permission="['admin']" class="filter-item" type="danger" @click="handleDelete">删除</el-button>
        </div>
    </div>
    <div class="mod-grid-body">
    <!-- 表单数据 -->
    <el-table ref="table" v-loading="list.loading" :data="list.data" :height="tableHeight"
              fit :highlight-current-row="false" row-key="id" border 
              empty-text="暂无数据" @selection-change="handleSelectionChange">
        <el-table-column align="center" type="selection" width="45" fixed="left"/>
        <el-table-column type="index" width="50" label="序号"></el-table-column>
        <el-table-column width="60" align="left" label="Id" prop="id"/>
        <el-table-column align="left" label="DeptId" prop="dept_id"/>
        <el-table-column align="left" label="JobId" prop="job_id"/>
        <el-table-column width="140" align="left" label="IsAdmin">
             <template slot-scope="scope">
                <span v-if="scope.row.is_admin===1" class="green">是</span>
                <span v-else class="red">否</span>
             </template>
        </el-table-column>
        <el-table-column align="left" label="Username" prop="username"/>
        <el-table-column align="left" label="Passwd" prop="passwd"/>
        <el-table-column align="left" label="Nickname" prop="nickname"/>
        <el-table-column align="left" label="Mobile" prop="mobile"/>
        <el-table-column align="left" label="Avatar" prop="avatar"/>
        <el-table-column align="left" label="1->启用；2->禁用" prop="status"/>
        <el-table-column width="140" align="left" label="IsDelete">
             <template slot-scope="scope">
                <span v-if="scope.row.is_delete===1" class="green">是</span>
                <span v-else class="red">否</span>
             </template>
        </el-table-column>
        <el-table-column width="140" align="left" label="CreateTime" prop="create_time" :formatter="formatColTime"/>
        <el-table-column width="140" align="left" label="UpdateTime" prop="update_time" :formatter="formatColTime"/>
        <el-table-column width="140" align="left" label="DeleteTime" prop="delete_time" :formatter="formatColTime"/>
        <el-table-column align="center" width="160" label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button type="text" icon="el-icon-edit-outline" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="text" icon="el-icon-delete" v-loading="requesting" @click="handleDelete(scope)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    </div>
    <div class="mod-grid-footer">
    <!-- 分页 -->
      <el-pagination v-show="list.total>0" :total="list.total" :page-size.sync="list.rows"
        :current-page.sync="list.page" :page-sizes="[10, 20, 50, 100]"
        @current-change="queryPagingData" @size-change="(v)=>list.rows = v"
        background layout="prev, pager, next,sizes,jumper,total"></el-pagination>
    </div>
    <!-- 弹出操作框 -->
    <el-dialog width="75%" class="mod-dialog" :title="dialog.title" :visible.sync="dialog.open"  :close-on-click-modal="false" @close="()=>dialog.modal=null">
        <component v-bind:is="dialog.modal" v-model="dialog.params" @callback="refresh"></component>
    </el-dialog>
</div>
</template>
<script setup>
import {onMounted, reactive, ref, nextTick} from "vue";
import {getPagingAxqStaff,deleteAxqStaff,batchDeleteAxqStaff } from './api';
import AxqStaffModal from './modal.vue';
import {Message,MessageBox,router,parseResult,formatColTime} from "@/adapter";

// AxqStaff数据映射类
class ListModel {
    constructor() {
      this.id = 0;// Id
      this.dept_id = 0;// DeptId
      this.job_id = 0;// JobId
      this.is_admin = 0;// IsAdmin
      this.username = '';// Username
      this.passwd = '';// Passwd
      this.nickname = '';// Nickname
      this.mobile = '';// Mobile
      this.avatar = '';// Avatar
      this.status = 0;// 1->启用；2->禁用
      this.is_delete = 0;// IsDelete
    }
}
console.log("----111")
let list = reactive({loading:false,total:0, page: 1, rows: 10,data:[]});
let dialog = reactive({title:"Form",open:false,params:0,modal: null});
let requesting = ref(false);
let selectedIds = ref([]);
let selectedRows = ref([]);
let table = ref(null);
let tableHeight = ref(0);

/** 定义排序条件 */
let sortOptions = [
  {key: "默认排序", value: "id DESC"},
  {key: "按创建时间先后顺序", value: "id ASC"},
];

/** 定义状态条件(自定义条件) */
let stateOptions = [
  {key: "全部", value: -1},
  {key: "正常", value: 1},
  {key: "停用", value: 0}
];

/** 定义查询参数 */
let queryParams = reactive({
  keyword: "",
  where: "0=0",
  state: stateOptions[0].value,
  order_by: sortOptions[0].value,
});

onMounted(()=>{
    queryPagingData();
    nextTick(()=>{
      const rec = table.value.$el.getBoundingClientRect();
      tableHeight.value = document.body.clientHeight - rec.top - 30;
    })
})

// 读取分页数据
const queryPagingData = async (args)=> {
    const { data } = await getPagingAxqStaff(list.page,list.rows,queryParams)
      .finally(()=>list.loading=false);
    list.data = data.rows;
    if(data.total > 0)list.total = data.total;
}

const handleFilter = ()=>{
  list.page = 1;
  queryPagingData();
}

const handleSelectionChange = (rows)=> {
    selectedRows.value = rows;
    selectedIds.value = [];
    rows.map(row=> selectedIds.value.push(row.id));
}

// 返回
const handleBack = ()=>{
   // this.$store.dispatch('delView', this.$route)
   router.go(-1);
}

// 新增数据
const handleCreate = ()=> openForm(null,"新增AxqStaff")

// 编辑数据
const handleEdit = (row)=>openForm(row,"编辑AxqStaff")

// 打开表单
const openForm = (row,title)=>{
  const id = row?row.id:0
  
  // router.push({path:"../axq/staff/detail",query:{title,id}});

  // 使用模态框以编辑数据
  dialog.open = true
  dialog.title = title
  dialog.params = id
  dialog.modal = AxqStaffModal
}

// 参数state为true时,重置模态框并刷新数据,args接受传入的参数
const refresh = ({state = 0,close = true,data = {}})=>{
    if(close)dialog.open = false;
    if(state)queryPagingData(data);
}

const handleDelete = ({$index,row}) => {
    MessageBox.confirm('执行此操作数据无法恢复,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(async () => {
        if(requesting.value)return;requesting.value=true;
        let ret = await (row && row.id?
          deleteAxqStaff(row.id):
          batchDeleteAxqStaff(selectedIds.value))
          .finally(()=>requesting.value=false);
        let {errCode,errMsg} = parseResult(ret.data);
        if (errCode === 0) {
          Message.success({message:'删除成功',duration:2000,onClose:queryPagingData});
        }else{
          MessageBox.alert(errMsg,"提示")
        }
    }).catch((ex) => {
      ex !=="cancel" && MessageBox.alert(ex.message,"错误")
    });
}
</script>