<template>
  <div class="mod-form-container">
    <el-form ref="formRef" class="mod-form" size="small"
             label-position="right" :model="formData" :rules="rules">
        <el-row :gutter="15">


        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="Username:" prop="username">
            <el-input type="textarea" v-model="formData.username" class="mod-form-input" :autosize="{ minRows: 2, maxRows: 4}" placeholder=""/>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="Passwd:" prop="passwd">
            <el-input type="textarea" v-model="formData.passwd" class="mod-form-input" :autosize="{ minRows: 2, maxRows: 4}" placeholder=""/>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="Nickname:" prop="nickname">
            <el-input type="textarea" v-model="formData.nickname" class="mod-form-input" :autosize="{ minRows: 2, maxRows: 4}" placeholder=""/>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="Mobile:" prop="mobile">
            <el-input type="textarea" v-model="formData.mobile" class="mod-form-input" :autosize="{ minRows: 2, maxRows: 4}" placeholder=""/>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="Avatar:" prop="avatar">
            <el-input type="textarea" v-model="formData.avatar" class="mod-form-input" :autosize="{ minRows: 2, maxRows: 4}" placeholder=""/>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="1->启用；2->禁用:" prop="status">
            <el-switch v-model="formData.status"
                         active-text=""
                         inactive-text=""
                         :active-value="1"
                         :inactive-value="0">
              </el-switch>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="IsDelete:" prop="isDelete">
            <el-checkbox v-model="formData.isDelete"></el-checkbox>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        <el-col :md="12" :xs="24">
            <el-form-item class="mod-form-item" label-position="left" label-width="85px" label="DeleteTime:" prop="deleteTime">
            <el-date-picker v-model="formData.deleteTime" type="date" value-format="timestamp" class="mod-form-input"
                    placeholder="选择日期" format="yyyy 年 MM 月 dd 日" >
                </el-date-picker>
                <span class="mod-form-remark"></span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-container class="mod-form-bar">
            <el-button @click="()=>callback({close: true})">取消</el-button>
            <el-button v-loading="requesting" type="primary" @click="submitForm">提交</el-button>
        </el-container>
     </el-form>
  </div>
</template>
<script setup>
import {onMounted,ref} from "vue";
import {AxqStaff,getAxqStaff,createAxqStaff,updateAxqStaff } from "./api"
import {Message,MessageBox,router,parseResult} from "@/adapter";


const props = defineProps({
  value:{ type: Number, default: 0}
})


const emit = defineEmits(['callback']);

const formRef = ref(null);
let formData  = ref(new AxqStaff());
let requesting = ref(false);
let isModal = props.value;

// 设置验证表单字段的规则,取消验证请注释对应的规则

let rules = {
  // 自定义验证规则：
  // phone: [{label:"phone", validator: this.validate }]
  // private async validate(rule: any, value: string, callback: Function){
  //   const label = rule.label || rule.field;
  //   if (value === '') { callback(new Error(label + '为必填字段'))} else {callback()}
  // }
  deptId: [{required: true, message:"DeptId不能为空"},{type:"number", message:"DeptId必须为数字"}],
  jobId: [{required: true, message:"JobId不能为空"},{type:"number", message:"JobId必须为数字"}],
  isAdmin: [{required: true, message:"IsAdmin不能为空"},{type:"number", message:"IsAdmin必须为数字"}],
  status: [{required: true, message:"1->启用；2->禁用不能为空"},{type:"number", message:"1->启用；2->禁用必须为数字"}],
  isDelete: [{required: true, message:"IsDelete不能为空"},{type:"number", message:"IsDelete必须为数字"}],
};

onMounted(()=>{
  const {id} = router.currentRoute.query;
  if(id)props.value = parseInt(id);
  if(props.value)fetchFormData(props.value);
})

const callback = (arg)=> (isModal && emit("callback",arg)) || router.go(-1)

const fetchFormData = async(id) =>{
  try {
    const { data } = await getAxqStaff(id, { /* Your params here */ });
    formData.value = data;
  } catch (err) {
    console.error(err);
    Message.warning("数据加载失败:"+err.message)
  }
}

const submitForm = ()=> {
  formRef.value.validate(async valid => {
    if (!valid)return;
    if(requesting.value)return;requesting.value = true;
    let ret = await (props.value?updateAxqStaff(props.value,formData.value):createAxqStaff(formData.value))
      .finally(()=>requesting.value=false);
    const {errCode,errMsg} = parseResult(ret.data);
    if(errCode === 0){
      Message.success({message:'操作成功',duration:2000});
      callback({state:1,close:true});
    }else{
      MessageBox.alert(errMsg,"错误");
    }
  })
}
</script>