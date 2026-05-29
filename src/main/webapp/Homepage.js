import { createApp } from "https://unpkg.com/vue@3/dist/vue.esm-browser.js";
import "https://unpkg.com/axios/dist/axios.min.js";
createApp({
    data() {
        return {
            tableNo : 1,
            pageNo : 1,
            totalPage: 1,
            stuList: [
                {
                    studentId: "",
                    firstName: "",
                    lastName: "",
                    gender: "",
                    major: "",
                    age: "",
                    birthdate: "",
                    email: "",
                    phone: "",
                },
            ],
            couList: [
                {
                    courseId: "",
                    courseName: "",
                    teacherId: "",
                    teacherFirstName: "",
                    teacherLastName: "",
                    credit: "",
                    courseTime: "",
                    status: "",
                },
            ],
            enrList: [
                {
                    enrollmentId: "",
                    courseId: "",
                    courseName: "",
                    teacherID: "",
                    teacherFirstName: "",
                    teacherLastName: "",
                    studentId: "",
                    studentFirstName: "",
                    studentLastName: "",
                    grade: "",
                    status: "",
                },
            ],
            teaList: [
                {
                    teacherId: "",
                    firstName: "",
                    lastName: "",
                    gender: "",
                    title: "",
                    age: "",
                    birthdate: "",
                    email: "",
                    phone: "",
                },
            ],
            searchForm:{
                stu: 1,
                cou: 1,
                enr: 1,
                tea: 1,
                content: '',
            },
            currentStu:{
                studentId: "",
                firstName: "",
                lastName: "",
                gender: "",
                major: "",
                age: "",
                birthdate: "",
                email: "",
                phone: "",
            },
            currentUser:{
                userId: "",
                userName: "",
                password: "",
                studentId: "",
            }
        }
    },
    created() {
        this.initialize();
        this.search();
        console.log("initialized success");
    },
    methods: {
        initialize(){
            axios.get('http://localhost:8080/StudentInformationManagementSystem/api/init').then(response => {
                this.currentStu = response.data.stu;
                this.currentUser = response.data.user;
                console.log("current user data returned");
            })
        },
        search() {
            switch (this.tableNo){
                case 1:
                    axios.get('http://localhost:8080/StudentInformationManagementSystem/api/search', {
                        params: {
                            tableNo: this.tableNo,
                            pageNo: this.pageNo,
                            attribute: this.searchForm.stu,
                            value: this.searchForm.content,
                        }
                    }).then(response => {
                        this.totalPage = response.data.totalPage;
                        this.stuList = response.data.list;
                        console.log("students data returned");
                    })
                    break;
                case 2:
                    axios.get('http://localhost:8080/StudentInformationManagementSystem/api/search', {
                        params: {
                            tableNo: this.tableNo,
                            pageNo: this.pageNo,
                            attribute: this.searchForm.cou,
                            value: this.searchForm.content,
                        }
                    }).then(response => {
                        this.totalPage = response.data.totalPage;
                        this.couList = response.data.list;
                        console.log("courses data returned");
                    })
                    break;
                case 3:
                    axios.get('http://localhost:8080/StudentInformationManagementSystem/api/search', {
                        params: {
                            tableNo: this.tableNo,
                            pageNo: this.pageNo,
                            attribute: this.searchForm.enr,
                            value: this.searchForm.content,
                        }
                    }).then(response => {
                        this.totalPage = response.data.totalPage;
                        this.enrList = response.data.list;
                        console.log("enrollments data returned");
                    })
                    break;
                case 4:
                    axios.get('http://localhost:8080/StudentInformationManagementSystem/api/search', {
                        params: {
                            tableNo: this.tableNo,
                            pageNo: this.pageNo,
                            attribute: this.searchForm.tea,
                            value: this.searchForm.content,
                        }
                    }).then(response => {
                        this.totalPage = response.data.totalPage;
                        this.teaList = response.data.list;
                        console.log("teachers data returned");
                    })
                    break;
            }
        },
        operate(course){
            axios.get('http://localhost:8080/StudentInformationManagementSystem/api/operation',{
                params: {
                    courseId: course.courseId,
                    status : course.status,
                }
            }).then(response => {
                alert(response.data.message);
                this.search();
                console.log("operation success");
            })
        },
        resetForm(){
            this.searchForm = {
                stu: 1,
                cou: 1,
                enr: 1,
                tea: 1,
                content: ''
            }
        },
        switchToStu(){
            this.tableNo = 1;
            this.pageNo = 1;
            console.log("switch to students table")
            this.resetForm();
            this.search();
        },
        switchToCou(){
            this.tableNo = 2;
            this.pageNo = 1;
            console.log("switch to courses table")
            this.resetForm();
            this.search();
        },
        switchToEnr(){
            this.tableNo = 3;
            this.pageNo = 1;
            console.log("switch to enrollments table")
            this.resetForm();
            this.search();
        },
        switchToTea(){
            this.tableNo = 4;
            this.pageNo = 1;
            console.log("switch to teachers table")
            this.resetForm();
            this.search();
        },
        nextPage(){
            if (this.pageNo >= this.totalPage) {
                alert("This is the last page");
                return;
            } else {
                this.pageNo += 1;
                this.search();
                console.log("next page")
            }
        },
        previousPage(){
            if (this.pageNo <= 1) {
                alert("This is the first page");
                return;
            } else {
                this.pageNo -= 1;
                this.search();
                console.log("previous page")
            }
        }
    }
}).mount('#containerA1');
//logout button
document.querySelector("#Logout_button").addEventListener("click", () => {
    // pop up a box to let the user confirm the logout
    if (confirm("Are you sure you want to logout?")) {
        window.location.href = "Login.jsp";
        console.log("the user logout")
    }
})