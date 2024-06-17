<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminGradeDetailPage></AdminGradeDetailPage>
        </div>
    </div>
</template>  
    
<script>
    import SideBar from '@/components/SideBar.vue';
    import AdminGradeDetailPage from '@/components/2/AdminGradeDetailPage.vue';
        export default {  
            name:'AdminGradeDetailView',
            components: {
                SideBar, 
                AdminGradeDetailPage
            },
        data(){
            return{
                choice: null
            };
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setPermission(2);
            this.$refs.sidebar.setHighlight(10);
        }
    }
</script>

<style scoped>
.container {
    display: flex;
    background-color: rgb(255, 255, 255);
}

va-sidebar {
    width: 250px;
}

.content {
    flex: 1;
}
</style>