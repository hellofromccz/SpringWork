<template>
	<div class="container">
		<h1>教室管理</h1>
		<div class="content">

			<VaTabs v-model="value" style="margin-bottom: 10px;">
				<template #tabs>
					<VaTab icon="va-plus" label="添加教室" @click="add" />
					<!-- <VaTab icon="va-minus" label="删除所有" @click="deleteAll" /> -->
					<VaTab icon="book" label="筛选与查询" @click="select" />
				</template>
			</VaTabs>
			<div class="tool-detail" v-if="action==1">
				<div style="display: flex;">
					<div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4 items-end">
						<div class="add-item">
							<div class="add-item-name">校区 </div>
							<input v-model="new_classroom.campus" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">名称 </div>
							<input v-model="new_classroom.name" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">容量</div>
							<input v-model="new_classroom.capacity" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">设备信息</div>
							<VaRadio v-model="new_classroom.condition" :options="['无设备', '有多媒体设备','数字化教室']" />
						</div>
						<!-- <div class="add-item">
                                <VaButton @click="comfirmAdd">确认</VaButton>
                            </div> -->
						<div class="add-item">
							<VaButton @click="comfirmAdd" :disabled="!isValid">确认</VaButton>
						</div>
					</div>

				</div>
			</div>
			<div class="tool-detail" v-if="action==2">
				<div class="delete-item">
					<div class="add-item-name">该功能尚未实现 </div>
				</div>
			</div>
			<div class="tool-detail" v-if="action==3">
				<div class="select-item">
					<VaInput v-model="condition.name" placeholder="请输入教室名称" />
					<VaButton @click="comfirmSelect" style="width: 100px; margin-left: 10px;">确认查询</VaButton>
				</div>
			</div>
			<VaCard style="margin-bottom: 10px; margin-top: 10px;">
				<VaCardContent>
					<div class="va-table-responsive">
						<table class="va-table va-table--hoverable va-table--clickable">
							<thead>
								<tr>
									<th>ID</th>
									<th>校区</th>
									<th>名称</th>
									<th>容量</th>
									<th>设备信息</th>

								</tr>
							</thead>
							<tbody>
								<tr v-for="classroom in classrooms" :key="classroom.id" @click="showDetail(classroom)">
									<td>{{ classroom.ClassroomID }}</td>
									<td>{{ classroom.Campusinformation }}</td>
									<td>{{ classroom.Classroomname }}</td>
									<td>{{ classroom.Classroomcapacity }}</td>
									<td>{{ classroom.Special_Conditions_of_Classrooms }}</td>
									<td v-if="selected_classroom.id == classroom.id">
<!--										<VaButton size="small" color="danger" @click="deleteSelected(classroom)">删除-->
<!--                    <VaButton size="small" color="danger" @click="deleteSelected">删除&ndash;&gt;-->
                    <VaButton size="small" color="danger" @click="deleteSelected(classroom.ClassroomID)">删除</VaButton>

									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</VaCardContent>
			</VaCard>
			<VaModal v-model="showModal" ok-text="确定" :beforeOk="comfirmDeleteSelected">
				<h3 class="va-h3">
					请谨慎操作
				</h3>
				<p style="margin-bottom: 10px;">
					是否确定删除ID为{{ selected_classroom.id }}的{{ selected_classroom.campus }} {{ selected_classroom.name}}教室？
				</p>
				<p>
					在该教室进行的课程都将级联删除。操作不可撤销。
				</p>
			</VaModal>
		</div>

	</div>
</template>

<script>
	import axios from '@/axios';
	export default {
		name: 'AdminCourseListPage',
		computed: {
			isValid() {
				return this.new_classroom.campus && this.new_classroom.name && this.new_classroom.capacity && this
					.new_classroom.condition;
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
				classrooms: [],
				new_classroom: {
					id: null,
					campus: "",
					name: "",
					capacity: null,
					condition: '',
				},
				condition: {
					name: '请输入教室名称',
				},
				showModal: false,
				selected_classroom: {
					id: null,
					campus: "",
					name: "",
					capacity: null,
					condition: '',
				},
			};
		},
		methods: {
			getClassroomsDefault() {
				const body = {
					uid: this.uid,
					data: "default"
				};
				console.log(body);
				axios.get("/classrooms", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.classrooms = response.data;
              this.classrooms.forEach(classroom => {
                console.log('classroomID:', classroom.ClassroomID);
                console.log('Campusinformation:', classroom.Campusinformation);
                console.log('Classroomname:', classroom.Classroomname);
                console.log('Classroomcapacity:', classroom.Classroomcapacity);
                console.log('Special_Conditions_of_Classrooms:', classroom.Special_Conditions_of_Classrooms);
              });
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
            showDetail(classroom){
                this.selected_classroom = classroom;
                console.log('selected_classroom:', this.selected_classroom);
                this.selected_classroom = classroom;
                console.log("select changed");
                console.log(this.selected_classroom);
                
            },
            add(){
                if(this.action != 1){
                    this.action = 1;
                }else{
                    this.action = 0;
                }
            },
            comfirmAdd(){
                const body = {
                      uid: this.uid,
                      Classroomname: this.new_classroom.name,
                      Campusinformation: this.new_classroom.campus,
                      Classroomcapacity: this.new_classroom.capacity,
                      Special_Conditions_of_Classrooms: this.new_classroom.condition
                    };
                console.log(body);
                axios.post("/classrooms/addClassroom", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.new_classroom = {
                                id: null,
                                name: '',
                                campus: '',
                                capacity: null,
                                condition: '无设备'
                            }
                            this.getClassroomsDefault();
                        }else if(response.data.code == "-1"){
                            console.log(response.data.message);
                        }else{
                            console.log("unhandled code, ",response.data.code);
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
            uploadPhoto(){

            },
            deleteAll(){
                if(this.action != 2){
                    this.action = 2;
                }else{
                    this.action = 0;
                }                
            },
            select(){
                if(this.action != 3){
                    this.action = 3;
                }else{
                    this.action = 0;
                }
            },

			comfirmSelect() {
				const body = {
					//id: this.uid,
					name: this.condition.name
				};
				console.log(body);
              axios.post("/classrooms/get_info_by_name", body)
                  .then(response => {
                    console.log("得到回应", response.data);
                    if (response.data!=null)  {
                      this.classrooms = response.data; // 更新 classrooms 列表
                    } else {
                      console.log("没有找到匹配的教室");
                    }
                  })
                  .catch(error => {
                    console.log('Error', error.message);
                  });
            },
        deleteSelected(classroomId){
          console.log('classroomId:', classroomId);
          const body = {
            classroomId: classroomId
          };
          console.log(body);
          axios.post("/classrooms/delete_classroom", body)
              .then(response => {
                console.log("得到回应", response.data);
              });
        },
			},
		mounted() {
			this.getClassroomsDefault();
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