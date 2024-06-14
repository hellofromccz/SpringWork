<template>
	<div class="container">
		<h1>用户管理</h1>
		<div class="content">

            <VaTabs v-model="value" style="margin-bottom: 10px;">
                <template #tabs>
                    <VaTab icon="va-plus" label="添加用户" @click="add"/>
                    <VaTab icon="va-minus" label="删除所有" @click="deleteAll"/>
                    <VaTab icon="book" label="筛选与查询" @click="select"/>
                </template>
            </VaTabs>
            <div class="tool-detail" v-if="action==1">
                <div style="display: flex;">
                    <div class="left">
                        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4 items-end">
                            <div class="add-item">
                                <div class="add-item-name">姓名 </div>
                                <input v-model="new_user.name">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">身份 </div>
                                <VaRadio v-model="new_user.status" :options="['学生', '教师']" /> 
                            </div>     
                            <div class="add-item">
                                <div class="add-item-name">性别 </div>
                                <VaRadio v-model="new_user.gender" :options="['男', '女']" /> 
                            </div>                           
                            <div class="add-item">
                                <div class="add-item-name">邮箱 </div>
                                <input v-model="new_user.email">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">电话 </div>
                                <input v-model="new_user.phone">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">住址 </div>
                                <input v-model="new_user.address">
                            </div>  
                            <div class="add-item">
                                <VaButton @click="comfirmAdd">确认</VaButton>
                            </div>                                                  
                        </div>
                    </div>
                    <!-- <div class="right">
                        <VaFileUpload v-model="new_user.photo" dropzone file-types="jpg,png" 
                        dropZoneText=""
                        uploadButtonText="上传头像"
                        style="width: 90%; height: 100%; align-self: center;"/>
                    </div>                 -->
                </div>
            </div>
            <div class="tool-detail" v-if="action==2">
                <div class="delete-item">
                    <div class="add-item-name">是否确定删除该列表中的所有用户？ </div>
                </div>

				<div class="delete-item">
                    <VaButton @click="comfirmAdd" style="width: 60px;">确认</VaButton>
                </div>

			</div>
			<div class="tool-detail" v-if="action==3">
				<div class="select-item">
					<VaInput v-model="condition.name" placeholder="请输入姓名" style="margin-right: 20px;"/>
					<VaButton @click="comfirmSelectName" style="width: 120px;">按姓名查询</VaButton>
				</div>
				<div class="select-item">
					<VaInput v-model="condition.id" placeholder="请输入id" style="margin-right: 20px;"/>
					<!-- <VaRadio style="margin-right: 20px;" v-model="condition.gender" :options="['男', '女','其它']"  /> -->
					<VaButton @click="comfirmSelectGender" style="width: 120px;">按id筛选</VaButton>
				</div>
			</div>
			<VaCard style="margin-bottom: 10px; margin-top: 10px;">
				<VaCardContent>
					<div class="va-table-responsive">
						<table class="va-table va-table--hoverable va-table--clickable">
							<thead>
								<tr>
									<th>姓名</th>
									<th>ID</th>
									<th>身份</th>
									<th>性别</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="user in users" :key="user.userId" @click="showDetail(user.userId)">
									<td>{{ user.name }}</td>
									<td>{{ user.userId }}</td>
									<td>{{ user.status }}</td>
									<td>{{ user.gender }}</td>
								</tr>
								
								
								<!-- <tr @click="showDetail(info.useId)">
									<td>{{ info.name }}</td>
									<td>{{ info.userId }}</td>
									<td>{{ info.status }}</td>
									<td>{{ info.gender }}</td>
								</tr> -->
							</tbody>
						</table>
					</div>
				</VaCardContent>
			</VaCard>

		</div>

	</div>
</template>

<script>
	import axios from '@/axios';
	export default {
		name: 'AdminUserListPage',
		computed: {
			isValid() {
				return this.new_user.name && this.new_user.status && this.new_user.email && this.new_user.phone && this
					.new_user.address;
			}
		},
		props: {
			value_uid: {
				type: Number,
				required: true
			},
		},
		data() {
			return {
				uid: this.value_uid,
				action: 0,
				users: [],
				new_user: {
					Name: '',
					Gender: '',
					Status: '',
					Email: '',
					Password: 'abc', 
					Phone: '',
					Address: '',
					Age:'21',
				},
				condition: {
					id: '',
					name:'',

				},
				info: {
                    name: "",
                    userId: null,
                    status:'',
                    password: '',
                    gender: '',
                    age: null,
                    email: "",
                    phone:"",
                    address:"",
                }
			};
		},
		watch: {
			value_uid(x) {
				this.uid = x;
			},
		},
		methods: {
			getUsersDefault() {
				// const body = {
				// 	uid: this.uid,
				// 	data: "default"
				// };
				// console.log(body);
				axios.post("/usr/get_all")
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.users = response.data;
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
						}
					})
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
							console.log(error.response.data);
							console.log(error.response.status);
							console.log(error.response.headers);
						} else if (error.request) {
							// 处理没有收到响应的情况
							console.log(error.request);
						} else {
							// 处理其他错误情况
							console.log('Error', error.message);
						}
						console.log(error.config);
					});
			},
			showDetail(id) {
				console.log('push to /admin/user/detail...');
				this.$router.push({path:`/admin/user/detail/${id}`});
			},
			add() {
				if (this.action != 1) {
					this.action = 1;
				} else {
					this.action = 0;
				}
			},
			comfirmAdd() {
				if (!this.isValid) {
					this.$alert('请填写所有必填字段后再尝试保存。', '提示', {
						confirmButtonText: '确认',
					});
					return;
				}
				let statusValue = this.new_user.status === "教师" ? "T" : "S"; // 三元运算符简化条件赋值
				const body = {
					//id: this.uid,
					name: this.new_user.name,
					status: statusValue,
					gender: this.new_user.gender,
					email: this.new_user.email,
					phone: this.new_user.phone,
    				address: this.new_user.address,
					password:'abc',
					age: '22',
				};
				console.log('这是body:',body);
				axios.post("/usr/add_user", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.getUsersDefault();
							this.new_user = {
								Name: '',
								Gender: '',
								Status: '',
								Email: '',
								Password: 'abc', 
								phone: '',
								Address: '',
								Age:'21',
							};
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
						}
					})
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
							console.log(error.response.data);
							console.log(error.response.status);
							console.log(error.response.headers);
						} else if (error.request) {
							// 处理没有收到响应的情况
							console.log(error.request);
						} else {
							// 处理其他错误情况
							console.log('Error', error.message);
						}
						console.log(error.config);
					});
			},
			uploadPhoto() {

			},
			deleteAll() {
				if (this.action != 2) {
					this.action = 2;
				} else {
					this.action = 0;
				}
			},
			select() {
				if (this.action != 3) {
					this.action = 3;
				} else {
					this.action = 0;
				}
			},
			comfirmSelectName(){
				const body = {
					//id: this.uid,
					name: this.condition.name
				};
				console.log(body);
				axios.post("/usr/get_info_by_name", body)
				// axios.post("/usr/get_all")
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.users = response.data;
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
						}
					})
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
							console.log(error.response.data);
							console.log(error.response.status);
							console.log(error.response.headers);
						} else if (error.request) {
							// 处理没有收到响应的情况
							console.log(error.request);
						} else {
							// 处理其他错误情况
							console.log('Error', error.message);
						}
						console.log(error.config);
					});
			},
			comfirmSelectGender(){
				const body = {
					userId: this.condition.id
					//data: this.condition.gender
				};
				console.log(body);
				axios.post("/usr/get_info", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.users = response.data;
							console.log("here",response.data.usr);
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
						}
					})
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
							console.log(error.response.data);
							console.log(error.response.status);
							console.log(error.response.headers);
						} else if (error.request) {
							// 处理没有收到响应的情况
							console.log(error.request);
						} else {
							// 处理其他错误情况
							console.log('Error', error.message);
						}
						console.log(error.config);
					});
			}
		},
		mounted() {
			this.uid = this.value_uid;
			this.getUsersDefault();
		}
	};
</script>

<style scoped lang="scss">
	.container {
		display: flex;
		flex-direction: column;
		background-color: rgb(255, 255, 255);
		height: 100vh;
		/* 使容器高度等于视口高度 */
	}

	h1 {
		padding: 60px;
		font-size: 24px;
	}

	.content {
		padding-left: 60px;
		padding-right: 60px;
		color: rgb(72, 72, 72);
		font-size: 16px;
	}

	.tool-detail {
		padding-top: 10px;
		padding-bottom: 10px;
		display: block;
	}

	.left {
		display: grid;

		width: 80%;
	}

	.right {
		display: flex;
		flex-direction: column;
		width: 20%;
		margin: 40px;
	}

	.photo {
		width: 96px;
		height: 144px;
		border: 1px solid #ddd;
		border-radius: 4px;
	}

	.add-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.add-item-name {
		margin-right: 10px;
	}

	.delete-item {
		margin: 20px;
		display: flex;
		flex-direction: column;
	}

	.select-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.tool :hover {
		cursor: pointer;
		background-color: aliceblue;
	}
</style>