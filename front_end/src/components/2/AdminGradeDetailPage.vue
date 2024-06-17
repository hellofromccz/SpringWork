<template>
    <div class="container">
        <h1>成绩管理 > {{ grade.name }}</h1>
        <div class="content">
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent style="display: flex; flex-direction: row;">
                    <div style="width: 92%;">
                        <div class="detail-info">
                            <div class="attribute">
                                <div class="attribute-name">课程名称</div>
                                <div class="attribute-content">{{ grade.name }}</div>
                                <!-- <input v-model="grade.name" class="attribute-content" v-if="edit"> -->
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">课程ID</div>
                                <div class="attribute-content">{{ grade.courseid }}</div>
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">学分</div>
                                <div class="attribute-content">{{ grade.credit }}</div>
                                <!-- <input v-model="grade.credit" class="attribute-content" v-if="edit"> -->
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">教师</div>
                                <div class="attribute-content">{{ grade.teacherName }}(ID:{{ grade.teacherId }})</div>
                                <!-- <input v-model="grade.teacherId" class="attribute-content" v-if="edit"> -->
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">学生</div>
                                <div class="attribute-content">{{ grade.studentName }}(ID:{{ grade.studentId }})</div>
                                <!-- <input v-model="grade.studenId" class="attribute-content" v-if="edit"> -->
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">时间</div>
                                <div class="attribute-content">{{ grade.time }}</div>
                                <!-- <input v-model="grade.time" class="attribute-content" v-if="edit"> -->
                            </div>
                            <div class="attribute">
                                <div class="attribute-name">成绩</div>
                                <div class="attribute-content" v-if="edit==false">{{ grade.grade }}</div>
                                <input v-model="grade.grade" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute-long">
                                <VaButton v-if="edit==false" @click="edit=true;" style="width: 60px;">编辑</VaButton>
                                <VaButton v-if="edit" @click="submit()" style="width: 100px;">保存修改</VaButton>
                            </div>
                        </div>
                    </div>
                    <div>
                        <VaButton size="small" color="danger" style="width: 60px;" @click="showModal=true;">删除</VaButton>
                    </div>

                </VaCardContent>             
            </VaCard>
            <VaModal
                v-model="showModal"
                ok-text="确定"
                :beforeOk="comfirmDelete"

            >
            <h3 class="va-h3">
            请谨慎操作
            </h3>
            <p style="margin-bottom: 10px;">
                    是否确定删除成绩ID为{{ grade.id }}的“{{ grade.name }}”？
                </p>
                <p>
                    该操作不可撤销。
                </p>
            </VaModal>
        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    import { mapState } from 'vuex';
    export default {
        name: 'AdminGradeDetailPage',
        data(){
            return {
                edit: false,
                showModal:false,
                grade:{
                    id:null,
                    courseid: null,
                    name: "",
                    credit: null,
                    time:"",
                    teacherId:null,
                    teacherName: "",
                    studentId:null,
                    studentName:"",  
                    grade:null,                
                }
            };
        },
        computed: {
            ...mapState(['id']), // 映射state中的id到本组件的computed属性id
            gradeId() {
                return this.$route.params.id || null; // 如果id不存在，则默认为null
            },
        },
        methods:{
            getGradeByDefault(){
                const body = {gradeid:Number(this.$route.params.oid)};
                console.log(body);
                axios.post("/grade/get_details", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.grade = {
                                id: response.data[0].gradeid,
                                courseid: response.data[0].course.courseId,
                                name: response.data[0].course.courseName,
                                credit: response.data[0].course.credit,
                                time: response.data[0].course.courseTime,
                                teacherId: response.data[0].course.courseTeacher.teacherID,
                                teacherName: response.data[0].course.courseTeacher.user.name,
                                studentId: response.data[0].student.studentid,
                                studentName: response.data[0].student.user.name,
                                grade: response.data[0].grade,
                            };
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
            submit(){
                const body = {
                    gradeid: this.$route.params.oid,
                    courseid: this.grade.courseid,
                    studentid: this.grade.studentId,
                    score: this.grade.grade
                };
                console.log("这是body",body);
                axios.post("/grade/update_grade", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.edit = false;
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
            comfirmDelete(){
                const body = {
                    gradeid: this.$route.params.oid
                };
                console.log("这是body",body);
                axios.post("/grade/delete_grade", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.$router.push("/admin/grade");
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
            }
        },
        mounted(){
            console.log('Received oid:', this.$route.params.oid);
            this.getGradeByDefault();            
        }
    };
</script>

<style scoped lang="scss">
    .container {
        display: flex;
        flex-direction: column;
        background-color: rgb(255, 255, 255);
        height: 100vh; /* 使容器高度等于视口高度 */
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

    .detail-info{

    }

    .attribute{
        margin: 20px;
        display: flex;
        flex-direction: row;
    }

    .attribute-name{
        margin-right: 10px;
        font-size: 20px;  
    }

    .attribute-content{
        font-size: 20px; 
        color: #666E75;        
    }

    .attribute-long{
        margin: 20px;
        display: flex;
        flex-direction: column;        
    }
</style>