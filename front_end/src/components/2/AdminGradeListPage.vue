<template>
	<div class="container">
		<h1>课程管理</h1>
		<div class="content">
			<VaTabs v-model="value" style="margin-bottom: 10px;">
				<template #tabs>
					<VaTab icon="va-plus" label="添加成绩" @click="add" />
					<!-- <VaTab icon="va-minus" label="删除所有" @click="deleteAll" /> -->
					<VaTab icon="book" label="筛选与查询" @click="select" />
				</template>
			</VaTabs>
			<div class="tool-detail" v-if="action==1">
				<div style="display: flex; flex-direction: row;">
					<div style="width: 35%;">
						<div class="add-item">
							<div class="add-item-name">课程ID </div>
							<input v-model="new_grade.courseid" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">学生ID </div>
							<input v-model="new_grade.studentid" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">分数</div>
							<input v-model="new_grade.score" required="required">
						</div>
					</div>
				</div>
				<div class="add-item">
					<VaButton @click="comfirmAdd" :disabled="!isValid">确认</VaButton>
				</div>

			</div>
			<div class="tool-detail" v-if="action==2">
				<div class="delete-item">
					<div class="add-item-name">该功能尚不支持 </div>
					<VaButton @click="comfirmAdd" style="width: 60px; margin-left: 10px;">确认</VaButton>
				</div>
			</div>
			<div class="tool-detail" v-if="action==3">
				<div class="select-item">
					<VaInput v-model="condition" placeholder="请输入课程ID" />
					<VaButton @click="comfirmSelect" style="width: 100px; margin-left: 10px;">确认查询</VaButton>
				</div>
			</div>
			<VaCard style="margin-bottom: 10px; margin-top: 10px;">
				<VaCardContent>

					<div class="va-table-responsive">
						<table class="va-table va-table--hoverable va-table--clickable">
							<thead>
								<tr>
									<th>课程名称</th>
									<th>学生姓名</th>
									<th>分数</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="grade in grades" :key="grade.gradeid" @click="showDetail(grade.gradeid)">
									<td>{{ grade.courseName }}</td>
									<td>{{ grade.studentName }}</td>
									<td>{{ grade.score }}</td>
								</tr>
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
		name: 'AdminGradeListPage',
		computed: {
			isValid() {
				return this.new_grade.courseid && this.new_grade.studentid && this.new_grade.score!=0;
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
				new_grade: {
					courseid: '',
					studentid: '',
					score: '',
					studentName: '',
					courseName: '',
				},
				grades: [],
				condition: '',
			};
		},
		methods: {
			getGradesDefault() {
				const body = {
					uid: this.uid,
					data: "default"
				};
				console.log(body);
				console.log(this.oid)
				axios.post("/grade/get_all_grades", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.grades = response.data;
							this.grades = this.grades.map(grade => {
								return {
									gradeid: grade.gradeid,
									courseid: grade.course.courseId,
									courseName: grade.course.courseName,
									studentid: grade.student.studentid,
									studentName: grade.student.user.name,
									score: grade.grade,
								};
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
				
				const body = {
					courseid: this.new_grade.courseid,
					studentid: this.new_grade.studentid,
					score: this.new_grade.score,
				};
				console.log('这是body:',body);
				axios.post("/grade/add_grade", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data!=null) {
							this.getGradesDefault();
							this.new_grade = {
								courseid: '',
								studentid: '',
								score: '',
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
			comfirmSelect() {
				const body = {
					// uid: this.uid,
					gradeid:this.condition
				};
				console.log("这是body:", body);
				axios.post("/grade/get_grade_by_id", body)
					.then(response => {
						console.log("得到回应", response.data);
						if(response.data!=null){
							this.grades = response.data;
							this.grades = this.grades.map(grade => {
								return {
									gradeid: grade.gradeid,
									courseid: grade.course.courseId,
									courseName: grade.course.courseName,
									studentid: grade.student.studentid,
									studentName: grade.student.user.name,
									score: grade.grade,
								};
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
			showDetail(oid) {
				console.log('push to /admin/grade/detail...');
				this.$router.push({
					path: `/admin/grade/detail/${oid}`,
				});
			}
		},
		mounted() {
			this.getGradesDefault();
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
		flex-direction: row;
	}

	.select-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.list {
		padding-top: 10px;
		padding-bottom: 10px;
	}

	.item {
		border-radius: 10px;
		margin: 10px;
	}

	.item :hover {
		cursor: pointer;
		background-color: aliceblue;
	}

	.course-name {
		padding: 10px;
		font-size: 20px;
	}

	.caption {
		padding: 10px;
		font-size: 12px;
		color: rgb(176, 176, 176);
	}
</style>