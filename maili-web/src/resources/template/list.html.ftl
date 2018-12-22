<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${table.comment!}管理</title>
        <link rel="stylesheet" href="static/css/element/element.css" th:href="@{/static/css/element/element.css}">
        <link rel="stylesheet" href="static/css/common/style/layout.css" th:href="@{/static/css/common/style/layout.css}">
    </head>
<body class="gray-bg">
    <div id="app" class="app-container calendar-list-container" v-cloak>
        <div class="filter-container" style="padding-bottom: 10px;padding-top: 10px;">
            <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">搜索</el-button>
            <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加</el-button>
        </div>

        <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
                  style="width: 100%">
            <el-table-column align="center" type="index" label="序号" width="65px">
            </el-table-column>
           <#list table.fields as field>
                <el-table-column align="center" label="${field.comment}">
                    <template slot-scope="scope">
                        <span>{{scope.row.${field.propertyName}}}</span>
                    </template>
                </el-table-column>
            </#list>
            <el-table-column align="center" label="操作" fixed="right" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="pagination-container" style="padding-top: 10px;">
            <el-pagination background  style="text-align: center"
                            @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="listQuery.pageNum" :page-sizes="[10,20,30, 50]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="dataForm" :model="entity" label-position="left" label-width="100px" style='width: 400px; margin-left:50px;'>
            <#list table.fields as field>
                    <el-form-item label="${field.comment}" prop="${field.propertyName}">
                        <el-input v-model="entity.${field.propertyName}" placeholder="请输入${field.comment}"></el-input>
                    </el-form-item>
            </#list>
            </el-form>
            <div slot="footer" class="dialog-footer" align="center">
                <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确认</el-button>
                <el-button v-else type="primary" @click="updateData">确认</el-button>
                <el-button @click="dialogFormVisible = false">取消</el-button>
            </div>
        </el-dialog>
    </div>
</div>
</body>

<script src="static/js/common/vue.min.js" th:src="@{/static/js/common/vue.min.js}"></script>
<script src="static/js/common/element.js" th:src="@{/static/js/common/element.js}"></script>
<script src="static/js/jquery.min.js" th:src="@{/static/js/jquery.min.js}"></script>
<script src="static/js/common/common.js" th:src="@{/static/js/common/common.js}"></script>
<script>

    var app = new Vue({
        el: '#app',
        name: 'rolePage',
        data() {
            return {
                tableKey: 0,
                list: null,
                total: null,
                listLoading: true,
                listQuery: {
                    pageNum: 1,
                    pageSize: 20,
                },
                entity: {//临时变量，用于新增与修改
                 <#list table.fields as field>
                    ${field.propertyName}:'',
                </#list>
                },
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: '编辑',
                    create: '新增'
                },
                rules: {//表单验证
                <#list table.fields as field>
                        ${field.propertyName}: [{ required: true, message: '${field.comment}不能为空', trigger: 'change' }],
                    </#list>
                },
            }
        },
        created() {
            this.getList()
        },
        methods: {
            getList() {
                this.listLoading = true
                var _this = this;
                $.ajax({
                    method: 'GET',
                    url: 'listData',
                    data: _this.listQuery,
                    success: function (data) {
                        if(data.code==200){
                            _this.list = data.data.records;
                            _this.total = data.data.total;
                            _this.listLoading = false
                        }else{
                            window.dataErrorHandler(data)
                        }
                    }
                });
            },
            handleFilter() {
                this.listQuery.pageNum = 1
                this.getList()
            },
            handleSizeChange(val) {
                this.listQuery.pageSize = val
                this.getList()
            },
            handleCurrentChange(val) {
                this.listQuery.pageNum = val
                this.getList()
            },
            resetentity() {//重置临时数据
                this.entity = {
            <#list table.fields as field>
                    ${field.propertyName}:'',
                </#list>
                }
            },
            handleCreate() {//点击新增
                this.resetentity()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            handleUpdate(row) {//点击修改
                const _this = this;
                $.ajax({
                    method: 'GET',
                    url: 'get/'+row.id,
                    success: function (data) {
                        if(data.code==200){
                            _this.entity = Object.assign({}, data.data) // copy obj
                            _this.dialogStatus = 'update'
                            _this.dialogFormVisible = true
                            _this.$nextTick(() => {
                                _this.$refs['dataForm'].clearValidate()
                            })
                        }else{
                            window.dataErrorHandler(data)
                        }
                    }
                });
            },
            handleDelete(row) {//点击删除
                const _this = this;
                this.$confirm('确认提交吗？', '提示', {}).then(() => {
                        $.ajax({
                            method: 'POST',
                            url: 'remove/'+row.id,
                            success: function (data) {
                                if(data.code==200){
                                    _this.$message({
                                        showClose: true,
                                        message: '删除成功',
                                        type: 'success'
                                    })
                                    _this.dialogFormVisible = false
                                    _this.getList();
                                }else{
                                    window.dataErrorHandler(data)
                                }
                        }
                        });
                });
            },
            createData() {
                const _this = this;
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            $.ajax({
                                method: 'POST',
                                url: 'save',
                                data: _this.entity,
                                success: function (data) {
                                    if(data.code==200){
                                        _this.$message({
                                            showClose: true,
                                            message: '添加成功',
                                            type: 'success'
                                        })
                                        _this.dialogFormVisible = false
                                        _this.getList();
                                    } else {
                                        window.dataErrorHandler(data)
                                    }
                                }
                            });
                        })
                    }
                })
            },
            updateData() {
                const _this = this;
                _this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        _this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            $.ajax({
                                method: 'POST',
                                url: 'update',
                                data: _this.entity,
                                success: function (data) {
                                    if(data.code==200){
                                        _this.$message({
                                            showClose: true,
                                            message: '更新成功',
                                            type: 'success'
                                        })
                                        _this.dialogFormVisible = false
                                        _this.getList();
                                    } else {
                                        window.dataErrorHandler(data)
                                    }
                                }
                            });
                        })
                    }
                })
            },
        }
    })
</script>

</html>
