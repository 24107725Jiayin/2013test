<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Homepage</title>
    <link rel="stylesheet" href="Homepage.css">
</head>
<body>
<div class="title_bar">
    <h1>Student Information Management System</h1>
    <button id="Logout_button">Logout</button>
</div>
<div class="containerA" id="containerA1">
    <div class="containerB" id="containerB1">
        <p>Tables:</p>
        <button id="Students_button" v-on:click="switchToStu">Students</button>
        <button id="Courses_button" v-on:click="switchToCou">Courses</button>
        <button id="Enrollments_button" v-on:click="switchToEnr">Enrollments</button>
        <button id="Teacher_button" v-on:click="switchToTea">Teachers</button>
    </div>
    <div class="containerB" id="containerB2">
        <div class="containerC" id="containerC1">
            <form class="search" id="search_form">
                <p class="form_content">Search for:</p>
                <select name="Students_attribute" class="form_content" id="Students_attribute" v-model="searchForm.stu" v-show="tableNo == 1">
                    <option value="1">Student ID</option>
                    <option value="2">First Name</option>
                    <option value="3">Last Name</option>
                    <option value="4">Gender</option>
                    <option value="5">Major</option>
                    <option value="6">Age</option>
                    <option value="7">Birthdate</option>
                    <option value="8">Email</option>
                    <option value="9">Phone</option>
                </select>
                <select name="Courses_attribute" class="form_content" id="Courses_attribute" v-model="searchForm.cou" v-show="tableNo == 2">
                    <option value="1">Course ID</option>
                    <option value="2">Course Name</option>
                    <option value="3">Teacher ID</option>
                    <option value="4">Teacher First Name</option>
                    <option value="5">Teacher Last Name</option>
                    <option value="6">Course Credit</option>
                    <option value="7">Course Time</option>
                </select>
                <select name="Enrollments_attribute" class="form_content" id="Enrollments_attribute" v-model="searchForm.enr" v-show="tableNo == 3">
                    <option value="1">Enrollment ID</option>
                    <option value="2">Course ID</option>
                    <option value="3">Course Name</option>
                    <option value="4">Teacher ID</option>
                    <option value="5">Teacher First Name</option>
                    <option value="6">Teacher Last Name</option>
                    <option value="7">Student ID</option>
                    <option value="8">Student First Name</option>
                    <option value="9">Student Last Name</option>
                    <option value="10">Grade</option>
                    <option value="11">Status</option>
                </select>
                <select name="Teacher_attribute" class="form_content" id="Teacher_attribute" v-model="searchForm.tea" v-show="tableNo == 4">
                    <option value="1">Teacher ID</option>
                    <option value="2">First Name</option>
                    <option value="3">Last Name</option>
                    <option value="4">Gender</option>
                    <option value="5">Title</option>
                    <option value="6">Age</option>
                    <option value="7">Birthdate</option>
                    <option value="8">Email</option>
                    <option value="9">Phone</option>
                </select>
                <p class="form_content">=</p>
                <input type="text" name="value" class="form_content" v-model="searchForm.content">
                <button type="button" class="form_content" v-on:click="search">Search</button>
                <button type="button" class="form_content" v-on:click="resetForm">Reset</button>
            </form>
        </div>
        <div class="containerC" id="containerC2">
            <table class="table" id="Students_Table" v-show="tableNo == 1">
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Major</th>
                        <th>Age</th>
                        <th>Birthdate</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(stu,index) in stuList" :key="stu.studentID">
                        <td>{{stu.studentId}}</td>
                        <td>{{stu.firstName}}</td>
                        <td>{{stu.lastName}}</td>
                        <td>{{stu.gender}}</td>
                        <td>{{stu.major}}</td>
                        <td>{{stu.age}}</td>
                        <td>{{stu.birthdate}}</td>
                        <td>{{stu.email}}</td>
                        <td>{{stu.phone}}</td>
                    </tr>
                </tbody>
            </table>
            <table class="table" id="Courses_Table" v-show="tableNo == 2">
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Teacher ID</th>
                        <th>Teacher First Name</th>
                        <th>Teacher Last Name</th>
                        <th>Course Credit</th>
                        <th>Course Time</th>
                        <th>Status</th>
                        <th>Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(cou,index) in couList" :key="cou.courseID">
                        <td>{{cou.courseId}}</td>
                        <td>{{cou.courseName}}</td>
                        <td>{{cou.teacherId}}</td>
                        <td>{{cou.teacherFirstName}}</td>
                        <td>{{cou.teacherLastName}}</td>
                        <td>{{cou.credit}}</td>
                        <td>{{cou.courseTime}}</td>
                        <td>{{cou.status}}</td>
                        <td>
                            <button class="operation_button" v-if="cou.status == 'Unselected' || cou.status == 'Dropped' " v-on:click="operate(cou)">Enroll</button>
                            <button class="operation_button" v-else-if="cou.status == 'Enrolled' " v-on:click="operate(cou)">Drop</button>
                            <button class="operation_button" v-else v-on:click="operate(cou)">Unavailable</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table class="table" id="Enrollments_Table" v-show="tableNo == 3">
                <thead>
                    <tr>
                        <th>Enrollment ID</th>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Teacher ID</th>
                        <th>Teacher First Name</th>
                        <th>Teacher Last Name</th>
                        <th>Student ID</th>
                        <th>Student First Name</th>
                        <th>Student Last Name</th>
                        <th>Grade</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(enr,index) in enrList" :key="enr.enrollmentID">
                        <td>{{enr.enrollmentId}}</td>
                        <td>{{enr.courseId}}</td>
                        <td>{{enr.courseName}}</td>
                        <td>{{enr.teacherId}}</td>
                        <td>{{enr.teacherFirstName}}</td>
                        <td>{{enr.teacherLastName}}</td>
                        <td>{{enr.studentId}}</td>
                        <td>{{enr.studentFirstName}}</td>
                        <td>{{enr.studentLastName}}</td>
                        <td>{{enr.grade}}</td>
                        <td>{{enr.status}}</td>
                    </tr>
                </tbody>
            </table>
            <table class="table" id="Teachers_Table" v-show="tableNo == 4">
                <thead>
                    <tr>
                        <th>Teacher ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Title</th>
                        <th>Age</th>
                        <th>Birthdate</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(tea,index) in teaList" :key="tea.teacherID">
                        <td>{{tea.teacherId}}</td>
                        <td>{{tea.firstName}}</td>
                        <td>{{tea.lastName}}</td>
                        <td>{{tea.gender}}</td>
                        <td>{{tea.title}}</td>
                        <td>{{tea.age}}</td>
                        <td>{{tea.birthdate}}</td>
                        <td>{{tea.email}}</td>
                        <td>{{tea.phone}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="containerC" id="containerC3">
            <p class="C3_content">Page:{{pageNo}}/{{totalPage}}</p>
            <button class="C3_content" id="prev" v-on:click="previousPage">previous page</button>
            <button class="C3_content" id="next" v-on:click="nextPage">next page</button>
        </div>
    </div>
    <div class="containerB" id="containerB3">
        <img src="pic/default_profile.jpg" alt="default_profile" id='profile'>
        <p>Username:{{currentUser.username}}</p>
        <p>Student Name:{{currentStu.firstName}} {{currentStu.lastName}}</p>
        <p>Student ID:{{currentStu.studentId}}</p>
        <p>Gender:{{currentStu.gender}}</p>
        <p>Major:{{currentStu.major}}</p>
    </div>
</div>
<footer>
    <div class="foot">COMP2013J Group19 Assignment</div>
    <div class="foot">All data are fictional and unrelated to real world, only for presenting functionality.</div>
</footer>
<script src="Homepage.js" type="module"></script>
</body>
</html>
