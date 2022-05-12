<template>

    <el-dialog :visible.sync="visible" :title="dataId ? '更新' : '添加'" :close-on-click-modal="false"
        :before-close="chooseModel" :close-on-press-escape="false">

        <el-form :model="dataForm" :rules="rules" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()">
            <el-row>
                <el-col :span="15">
                    <el-form-item label="任务名 : " prop="name">
                        <el-input v-model="dataForm.name" clearable placeholder="任务名"></el-input>
                    </el-form-item>
                </el-col>

            </el-row>
            <el-row>
                <el-col :span="15">
                    <el-form-item label="类名 : " prop="classPath">
                        <el-input v-model="dataForm.classPath" clearable placeholder="类名"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="类型 : " prop="type">
                        <el-radio-group v-model="dataForm.type">
                            <el-radio :label="1">周期</el-radio>
                            <el-radio :label="2">每日</el-radio>
                            <el-radio :label="3">表达式</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row v-show="dataForm.type===1">
                <el-col :span="8">
                    <el-form-item label="周期 : " prop="periodType">
                        <el-select v-model="dataForm.periodType" placeholder="请选择">
                            <el-option v-for="item in periodTypeList" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8" :offset="4">
                    <el-form-item label="周期数 : " prop="period">
                        <el-input-number v-model="dataForm.period" :min="1" label="周期数"></el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row v-show="dataForm.type === 2">
                <el-col :span="8">
                    <el-form-item label="时 : " prop="h">
                        <el-input-number v-model="dataForm.h" :min="0" :max="23" label="时"></el-input-number>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="分 : " prop="m">
                        <el-input-number v-model="dataForm.m" :min="0" :max="23" label="分"></el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row v-show="dataForm.type === 3">
                <el-col>
                    <el-form-item label="cron表达式 : " prop="cron">
                        <el-input v-model="dataForm.cron" clearable placeholder="cron表达式"></el-input>
                        <el-link type="primary" href="https://cron.qqe2.com/" target="_blank">cron生成</el-link>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="状态 : " prop="active">
                        <el-radio-group v-model="dataForm.active">
                            <el-radio :label="true">启动</el-radio>
                            <el-radio :label="false">关闭</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="描述 : " prop="description">
                <el-input type="textarea" v-model="dataForm.description" clearable placeholder="描述"></el-input>
            </el-form-item>
        </el-form>

        <template slot="footer">
            <el-button @click="chooseModel">取消</el-button>
            <el-button type="primary" @click="dataFormSubmitHandle()">确定</el-button>
        </template>

    </el-dialog>

</template>

<script>
export default {
    data() {
        return {
            periodTypeList: [
                {
                    label: "时",
                    value: 1,
                },
                {
                    label: "分",
                    value: 2,
                },
                {
                    label: "秒",
                    value: 3,
                },
            ],
            dataForm: {
                createdTime: "",
                updatedTime: "",
                name: "",
                description: "",
                classPath: "",
                type: 1,
                h: "",
                m: "",
                periodType: 1,
                period: "",
                cron: "",
                active: true
            },
            rules: {
                name: [
                    // 任务名 校验
                    {
                        required: true,
                        message: "任务名不能为空",
                        trigger: "blur",
                    },
                ],
                description: [
                    // 描述 校验
                    {
                        required: true,
                        message: "描述不能为空",
                        trigger: "blur",
                    },
                ],
                classPath: [
                    // 类名 校验
                    {
                        required: true,
                        message: "类名不能为空",
                        trigger: "blur",
                    },
                ],
                type: [
                    // 类型 校验
                    {
                        required: true,
                        message: "类型不能为空",
                        trigger: "blur",
                    },
                ],
                h: [
                    // 时 校验
                    { required: false, message: "时不能为空", trigger: "blur" },
                ],
                m: [
                    // 分 校验
                    { required: false, message: "分不能为空", trigger: "blur" },
                ],
                periodType: [
                    // 周期类型 校验
                    {
                        required: false,
                        message: "周期类型不能为空",
                        trigger: "blur",
                    },
                ],
                period: [
                    // 周期数 校验
                    {
                        required: false,
                        message: "周期数不能为空",
                        trigger: "blur",
                    },
                ],
                cron: [
                    // CRON表达式 校验
                    {
                        required: false,
                        message: "CRON表达式不能为空",
                        trigger: "blur",
                    },
                ],
            },
        };
    },
    props: {
        visible: {
            type: Boolean,
            default: false,
        },
        dataId: {
            type: String,
            default: "",
        },
    },
    created() {
        if (this.dataId) {
            this.getInfo(); //查询数据详情
        }
    },
    methods: {
        //获取详情
        getInfo() {
            this.$http
                .get("/admin/quartz/" + this.dataId)
                .then((resp) => {
                    this.dataForm = resp.data;
                })
                .catch((err) => {});
        },
        // 表单提交
        dataFormSubmitHandle() {
            this.$refs["dataForm"].validate((valid) => {
                if (!valid) {
                    return false;
                }
                this.$http[this.dataId ? "put" : "post"](
                    "/admin/quartz/" + (this.dataId ? "update" : "save"),
                    this.dataForm
                )
                    .then((res) => {
                        if (res.status !== 200) {
                            return this.$message.error(res.msg);
                        }
                        this.$message({
                            message: res.msg,
                            type: "success",
                            duration: 500,
                            onClose: () => {
                                this.chooseModel();
                            },
                        });
                    })
                    .catch(() => {});
            });
        },
        //============关闭弹窗===========
        chooseModel() {
            this.$emit("chooseModel");
        },
    },
};
</script>

<style lang="scss" scoped>
.model-select {
    width: 100%;
}
.mod-quartz_quartz {
    .quartz-list {
        .el-input__inner,
        .el-input__suffix {
            cursor: pointer;
        }
    }
}
</style>

<style  lang="scss">
</style>


