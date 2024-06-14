<template>
    <div class="container">
        <h1>用户管理 > {{  info.name  }}</h1>
        <div class="content">
            <VaCard style="padding: 20px; display: flex; flex-direction: row">
                <div style="width: 80%;">
                    <div class="list" v-if="edit==false">
                        <div class="item">
                            <div class="item-name">姓名 </div>
                            <div class="item-content">{{ info.name }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">ID </div>
                            <div class="item-content">{{ info.id }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">性别 </div>
                            <div class="item-content">{{ info.gender }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">身份 </div>
                            <div class="item-content">{{ info.status }}</div>
                        </div>                    
                        <div class="item">
                            <div class="item-name">邮箱 </div>
                            <div class="item-content">{{ info.email }}</div>
                        </div>
                        <div class="item"> 
                            <div class="item-name">电话 </div>
                            <div class="item-content">{{ info.phone }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">住址 </div>
                            <div class="item-content">{{ info.address }}</div>
                        </div>
                        <div class="item">
                            <VaButton @click="changeState" style="margin-right: 20px;" >修改信息</VaButton>
                            <VaButton preset="secondary" class="mr-6 mb-2" @click="this.showModel= true;" >
                                删除用户
                            </VaButton>
                        </div>
                    </div>

                    <div class="list" v-if="edit">
                        <div class="item">
                            <div class="item-name">姓名 </div>
                            <input v-model="info.name">
                        </div>

                        <div class="item">
                            <div class="item-name">ID </div>
                            <div class="item-content">{{ info.id }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">性别 </div>
                            <VaRadio v-model="info.gender" :options="['男', '女','其它']" />
                        </div>
                        <div class="item">
                            <div class="item-name">身份 </div>
                            <VaRadio v-model="info.status" :options="['学生', '教师']" /> 
                        </div>

                        <div class="item">
                            <div class="item-name">邮箱 </div>
                            <input v-model="info.email">
                        </div>
                        <div class="item">
                            <div class="item-name">电话 </div>
                            <input v-model="info.phone">
                        </div>
                        
                        <div class="item">
                            <div class="item-name">住址 </div>
                            <input v-model="info.address">
                        </div>

                        <div class="item">
                            <VaButton @click="submit()">保存修改</VaButton>
                        </div>
                    </div>           
                </div>
                <!-- <div style="width: 20%; padding: 20px;">
                    <img 
                    :src="url()" 
                    :style="imageStyle"
                    @error="onImageError"
                    />
                    <VaFileUpload
                        v-model="info.photo"
                        uploadButtonText="修改头像"
                        type="gallery"
                        file-types="jpg,png"
                        fileAdded="handleUpload"
                        style="align-self: center;"
                    />
                </div> -->
             
            </VaCard>
            <VaModal
                v-model="this.showModel"
                ok-text="确定"
                :beforeOk="comfirmDelete"

            >
            <h3 class="va-h3">
            请谨慎操作
            </h3>
            <p style="margin-bottom: 10px;">
                    是否确定删除ID为{{ info.id }}的“{{ info.name }}”？
                </p>
                <p v-if="info.status=='教师'">该用户为教师，其教授的所有课程也将一并删除。</p>
                <p>该操作不可撤销。</p>
            </VaModal>
        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    import { mapGetters } from 'vuex';
    export default {
        name: 'InfoPage',
        components:{
        },
        props: {
            value_uid: {
                type: Number,
                required: true
            },
        },
        computed: {
            ...mapGetters(['getId']), // 映射 getId getter 到本地计算属性
            userId() {
            return this.$route.params.id || null; // 如果id不存在，则默认为null
       },
        },
        data(){
            
            return {
                edit: false,
                uid: this.value_uid,
                //userId: this.userId,
                showModel: false,
                info: {
                    name: "",
                    id: null,
                    password:'abc',
                    age: null,
                    status:'',
                    gender: '',
                    email: "",
                    phone:"",
                    address:""
                }
            };
        },
        watch: {
            value_uid(x){
                console("change uid to ", x);
                this.uid = x;
            },
        },
        methods:{
            checkIdInRoute() {
                const id = this.$route.params.id;
                if (!id) {
                    console.warn('ID 参数缺失');
                    // 可以选择抛出错误、跳转到错误页或采取其他措施
                } else {
                    console.log('路由中的ID:', id);
                    // 继续执行后续逻辑，比如发起API请求等
                }
                },
            getInfo(){
                const body = {userId:Number(this.$route.params.oid)};
                //const body = {userId: this.$route.query.id};
                console.log(body);
                axios.post("/usr/get_info", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.info = {
                                name: response.data[0].name,
                                id: response.data[0].userId,
                                status: response.data[0].status,
                                gender: response.data[0].gender,
                                email: response.data[0].email,
                                phone: response.data[0].phone,
                                address: response.data[0].address,
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
            changeState(){
                this.edit = !(this.edit);
                if(this.edit){
                    console.log("state changeed to edit");
                }else{
                    console.log("state changed to show");
                }
            },
            
            setUid(x){
                this.uid = x;
            },
            setOid(x){
                this.oid = x;
            },
            submit(){
                this.edit = !(this.edit);
                //const body = {id: this.uid, user: this.info};
                let statusValue = this.info.status === "教师" ? "T" : "S"; // 三元运算符简化条件赋值
				const body = {
					userId: Number(this.$route.params.oid),
					name: this.info.name,
					status: statusValue,
					gender: this.info.gender,
					email: this.info.email,
					phone: this.info.phone,
    				address: this.info.address,
					password:'abc',
					age: '21',
				};
				// console.log('这是body:',body);
                //const body = { user: this.info};
                console.log(body);
                axios.post("/usr/update_user", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.getUsersDefault();
                            location.reload();
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
                let statusValue = this.info.status === "教师" ? "T" : "S"; // 三元运算符简化条件赋值
				const body = {
					userId: Number(this.$route.params.oid),
					name: this.info.name,
					status: statusValue,
					gender: this.info.gender,
					email: this.info.email,
					phone: this.info.phone,
    				address: this.info.address,
					password:'abc',
					age: '21',
				};
                console.log(body);
                axios.post("/usr/delete_user", body) // 
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data!=null){
                            this.$router.push("/admin/user");
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
            this.uid = this.value_uid;
            this.oid = this.value_oid;
            this.getInfo();
            this.checkIdInRoute();
        },
        watch: {
            '$route'(to, from) {
                this.checkIdInRoute();
            },
        }
    }
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

    .list{
        display: flex;
        flex-direction: column;
    }

    .item{
        margin: 20px;
        display: flex;
        flex-direction: row;
    }

    .item-name{
        margin-right: 10px;
        font-size: 20px;    
    }

    .item-content{
        font-size: 20px; 
        color: #666E75;
    }

    input{
        color: #666E75;
        border-color: rgb(208, 208, 208);
        border-width: 1px;
        border-radius: 5px;
    }

    .photo {
        width: 96px;
        height: 144px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

</style>