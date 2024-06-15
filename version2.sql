/*
Navicat MySQL Data Transfer

Source Server         : ls8
Source Server Version : 80037
Source Host           : localhost:3308
Source Database       : version2

Target Server Type    : MYSQL
Target Server Version : 80037
File Encoding         : 65001

Date: 2024-06-16 03:36:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `ClassroomID` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ClassroomName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CampusInformation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ClassroomCapacity` int unsigned NOT NULL,
  `Special_Conditions_of_Classrooms` enum('无设备','有多媒体设备','数字化教室') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ClassroomID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('000000001', '数学教室', '主校区', '50', '有多媒体设备');
INSERT INTO `classroom` VALUES ('000000002', '东1-101', '杭州', '100', '数字化教室');
INSERT INTO `classroom` VALUES ('000000003', '东2-101', '城院', '100', '数字化教室');
INSERT INTO `classroom` VALUES ('000000004', '东2-102', '城院', '200', '无设备');
INSERT INTO `classroom` VALUES ('000000005', '教三-101', '城院', '80', '有多媒体设备');
INSERT INTO `classroom` VALUES ('000000006', '理四-102', '城院', '80', '数字化教室');
INSERT INTO `classroom` VALUES ('000000007', '教二-103', '南校', '80', '有多媒体设备');
INSERT INTO `classroom` VALUES ('000000008', '教五-104', '北校', '80', '有多媒体设备');
INSERT INTO `classroom` VALUES ('000000009', '教7-301', '城院', '120', '有多媒体设备');
INSERT INTO `classroom` VALUES ('000000010', '教7-302', '城院', '120', '数字化教室');
INSERT INTO `classroom` VALUES ('000000011', '教7-303', '城院', '120', '数字化教室');
INSERT INTO `classroom` VALUES ('000000012', '数学教室', '主校区', '50', '有多媒体设备');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Courseid` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Coursename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Credit` double unsigned NOT NULL,
  `Classroomid` int(9) unsigned zerofill NOT NULL,
  `Courseteacherid` int(9) unsigned zerofill NOT NULL,
  `Coursecapacity` int unsigned NOT NULL,
  `Number_of_Student_taking_Course` int unsigned NOT NULL,
  `Compulsory_or_not` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `Coursetime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`Courseid`) USING BTREE,
  KEY `course_ibfk_1` (`Courseteacherid`) USING BTREE,
  KEY `ClassroomID` (`Classroomid`) USING BTREE,
  KEY `Courseid` (`Courseid`) USING BTREE,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`Classroomid`) REFERENCES `classroom` (`ClassroomID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher` FOREIGN KEY (`Courseteacherid`) REFERENCES `teacher` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('000000001', 'Computer Organization', '4', '000000002', '000000001', '50', '40', '1', '周一4');
INSERT INTO `course` VALUES ('000000002', '微积分', '5', '000000008', '000000002', '40', '40', '1', '周二3');
INSERT INTO `course` VALUES ('000000003', '微积分', '5', '000000008', '000000003', '40', '40', '1', '周四1');
INSERT INTO `course` VALUES ('000000004', '线性代数', '4', '000000010', '000000004', '40', '40', '1', '周五3');
INSERT INTO `course` VALUES ('000000005', '建筑学导论', '3.5', '000000008', '000000005', '100', '0', '0', '周三1');
INSERT INTO `course` VALUES ('000000006', '建筑学导论', '3.5', '000000008', '000000001', '100', '0', '0', '周二4');
INSERT INTO `course` VALUES ('000000007', '考古学导论', '3.5', '000000008', '000000002', '50', '0', '0', '周二7');
INSERT INTO `course` VALUES ('000000008', '考古学导论', '3.5', '000000008', '000000003', '50', '0', '0', '周三6');
INSERT INTO `course` VALUES ('000000009', '考古学导论', '3.5', '000000008', '000000004', '50', '0', '0', '周四3');
INSERT INTO `course` VALUES ('000000033', '1', '1', '000000001', '000000001', '1', '0', '0', '[\"周一1\"]');
INSERT INTO `course` VALUES ('000000034', '1', '1', '000000001', '000000001', '1', '0', '0', '[\"周一1\"]');
INSERT INTO `course` VALUES ('000000035', 'aaa', '1', '000000001', '000000001', '1', '0', '0', '[\"周一2\"]');
INSERT INTO `course` VALUES ('000000036', 'bbb', '1', '000000001', '000000001', '1', '0', '0', '[\"周一3\"]');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `gradeid` int NOT NULL,
  `studentid` int DEFAULT NULL,
  `courseid` int(10) unsigned zerofill DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `grade_id` bigint NOT NULL,
  PRIMARY KEY (`gradeid`) USING BTREE,
  KEY `student_grade` (`studentid`) USING BTREE,
  KEY `course_grade` (`courseid`) USING BTREE,
  CONSTRAINT `course_grade` FOREIGN KEY (`courseid`) REFERENCES `course` (`Courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_grade` FOREIGN KEY (`studentid`) REFERENCES `student` (`Studentid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for opinion
-- ----------------------------
DROP TABLE IF EXISTS `opinion`;
CREATE TABLE `opinion` (
  `opinionid` int NOT NULL,
  `opinioncontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `userid` int(10) unsigned zerofill DEFAULT NULL,
  `isreply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `opinion_id` bigint NOT NULL,
  PRIMARY KEY (`opinionid`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of opinion
-- ----------------------------
INSERT INTO `opinion` VALUES ('1', '放学时间太迟了', '0000000001', '是', '0');
INSERT INTO `opinion` VALUES ('2', '开学时间太早了', '0000000002', '否', '0');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyid` int NOT NULL,
  `replycontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `replyfor` int NOT NULL,
  `whoreply` int(10) unsigned zerofill DEFAULT NULL,
  `reply_content` varchar(255) DEFAULT NULL,
  `opinionid` bigint NOT NULL,
  `who_reply` int(9) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`replyid`) USING BTREE,
  KEY `replyfor` (`replyfor`) USING BTREE,
  KEY `whoreply` (`whoreply`) USING BTREE,
  KEY `FKtxvdlro95o4yncsy4b65l8ex` (`who_reply`),
  CONSTRAINT `FKtxvdlro95o4yncsy4b65l8ex` FOREIGN KEY (`who_reply`) REFERENCES `user` (`UserID`),
  CONSTRAINT `replyfor` FOREIGN KEY (`replyfor`) REFERENCES `opinion` (`opinionid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `whoreply` FOREIGN KEY (`whoreply`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '学校正在考虑取消晚自习', '1', null, null, '0', null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Studentid` int NOT NULL,
  `Userid` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`Studentid`) USING BTREE,
  KEY `user_id` (`Userid`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`Userid`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for student_courses
-- ----------------------------
DROP TABLE IF EXISTS `student_courses`;
CREATE TABLE `student_courses` (
  `student_studentid` bigint NOT NULL,
  `courses_courseid` int(9) unsigned zerofill NOT NULL,
  PRIMARY KEY (`student_studentid`,`courses_courseid`),
  KEY `FKmui2xbwkb3hv3jby6lidbf9yu` (`courses_courseid`),
  CONSTRAINT `FKmui2xbwkb3hv3jby6lidbf9yu` FOREIGN KEY (`courses_courseid`) REFERENCES `course` (`Courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of student_courses
-- ----------------------------

-- ----------------------------
-- Table structure for student_select_course
-- ----------------------------
DROP TABLE IF EXISTS `student_select_course`;
CREATE TABLE `student_select_course` (
  `Courseid` int(10) unsigned zerofill DEFAULT NULL,
  `Studentid` int DEFAULT NULL,
  KEY `student_course1` (`Courseid`) USING BTREE,
  KEY `student_id` (`Studentid`) USING BTREE,
  CONSTRAINT `student_course1` FOREIGN KEY (`Courseid`) REFERENCES `course` (`Courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id` FOREIGN KEY (`Studentid`) REFERENCES `student` (`Studentid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student_select_course
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `TeacherID` int(9) unsigned zerofill NOT NULL,
  `UserID` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`TeacherID`) USING BTREE,
  KEY `user_teacher` (`UserID`) USING BTREE,
  CONSTRAINT `user_teacher` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('000000001', '0000000007');
INSERT INTO `teacher` VALUES ('000000002', '0000000008');
INSERT INTO `teacher` VALUES ('000000003', '0300000004');
INSERT INTO `teacher` VALUES ('000000004', '0300000005');
INSERT INTO `teacher` VALUES ('000000005', '0300000006');

-- ----------------------------
-- Table structure for teacher_courses
-- ----------------------------
DROP TABLE IF EXISTS `teacher_courses`;
CREATE TABLE `teacher_courses` (
  `teacher_teacherid` bigint NOT NULL,
  `courses_courseid` int(9) unsigned zerofill NOT NULL,
  PRIMARY KEY (`teacher_teacherid`,`courses_courseid`),
  KEY `FK19ie0ja5bo3ex8srjs25mfnxk` (`courses_courseid`),
  CONSTRAINT `FK19ie0ja5bo3ex8srjs25mfnxk` FOREIGN KEY (`courses_courseid`) REFERENCES `course` (`Courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of teacher_courses
-- ----------------------------

-- ----------------------------
-- Table structure for teacher_teach_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_teach_course`;
CREATE TABLE `teacher_teach_course` (
  `teacherid` bigint NOT NULL,
  `courseid` int(9) unsigned zerofill NOT NULL,
  PRIMARY KEY (`teacherid`,`courseid`),
  KEY `FKrug65apnimqnhacyew114ai8y` (`courseid`),
  CONSTRAINT `FKrug65apnimqnhacyew114ai8y` FOREIGN KEY (`courseid`) REFERENCES `course` (`Courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of teacher_teach_course
-- ----------------------------
INSERT INTO `teacher_teach_course` VALUES ('1', '000000033');
INSERT INTO `teacher_teach_course` VALUES ('1', '000000034');
INSERT INTO `teacher_teach_course` VALUES ('1', '000000035');
INSERT INTO `teacher_teach_course` VALUES ('1', '000000036');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Status` enum('SA','A','T','S') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '00000000',
  `Gender` enum('男','女','其它') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Age` int unsigned NOT NULL,
  `Phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=400000008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('000000001', '系统管理员', 'A', 'abc', '女', '21', '172-4402-4568', 'SysAdmin@gmail.com', '城院8舍130');
INSERT INTO `user` VALUES ('000000002', '教务管理员', 'A', 'abc', '女', '20', '111-2222-3333', 'Admin@outlook.com', '小河边曹西405');
INSERT INTO `user` VALUES ('000000003', '潘雨露', 'A', 'abc', '女', '20', '1371111111', 'lzh@outlook.com', '西湖8舍110');
INSERT INTO `user` VALUES ('000000004', '上官', 'A', 'abc', '男', '20', '1371112222', 'lhq@outlook.com', '龙井32舍401');
INSERT INTO `user` VALUES ('000000005', '天韵', 'A', 'abc', '男', '21', '1371113333', 'HHH@gmail.com', '杭州2舍601');
INSERT INTO `user` VALUES ('000000006', '纳西妲', 'A', 'abc', '女', '200', '999-2222-3333', 'Nahida@archon.com', '须弥智慧宫');
INSERT INTO `user` VALUES ('000000007', '卡维', 'T', 'abc', '男', '21', '1996668888', 'KW@genshin.com', '艾尔海森家');
INSERT INTO `user` VALUES ('000000008', '珐露珊', 'T', 'abc', '女', '125', '1996667777', 'faruzon@genshin.com', '住宅区1号');
INSERT INTO `user` VALUES ('000000009', '赛诺', 'SA', 'abc', '男', '20', '1996666666', 'cyno@akademiya.com', '住宅区2号');
INSERT INTO `user` VALUES ('000000010', '郑楚楚', 'S', 'abc', '女', '21', '19214323524', '1241413532@qq.com', '思睿');
INSERT INTO `user` VALUES ('300000004', 'Mike', 'T', 'abc', '男', '21', '212-324-2993', 'yuans@hotmail.com', '773 West Houston Street');
INSERT INTO `user` VALUES ('300000005', 'Mike', 'T', '8jY4s6udS1', '女', '381', '70-6318-5486', 'da1940@gmail.com', '3-15-2 Ginza, Chuo-ku');
INSERT INTO `user` VALUES ('300000006', 'Mike', 'T', 'tb9eq30pM1', '男', '850', '90-4994-1654', 'tlm@yahoo.com', '5-2-1 Higashi Gotanda, Shinagawa-ku ');
INSERT INTO `user` VALUES ('400000000', 'Mike', 'T', 'abc', '男', '78', '330-224-0366', 'tfho10@outlook.com', 'dom 32 room 101');
INSERT INTO `user` VALUES ('400000001', 'Ho Ting Fung', 'T', '6NnZZBj7yF', '男', '78', '330-224-0366', 'tfho10@outlook.com', '162 Collier Road');
INSERT INTO `user` VALUES ('400000002', 'Lai Ting Fung', 'T', 'TiOmdL8rg6', '女', '750', '755-4216-7235', 'laitf@gmail.com', '78 Xue Yuan Yi Xiang, Longgang');
INSERT INTO `user` VALUES ('400000003', 'Lui Wing Sze', 'T', 'mYdLZX3dNy', '女', '621', '838-054-9547', 'wingszel@gmail.com', '923 State Street');
INSERT INTO `user` VALUES ('400000004', '成成', 'A', 'abc', '男', '21', '1414321', '1123113', '1432141');
INSERT INTO `user` VALUES ('400000005', '成功人士', 'T', 'abc', '男', '22', '13577777777', 'cgrs@gmail.com', '杭州');
INSERT INTO `user` VALUES ('400000006', 'AAA五金建材', 'S', 'abc', '男', '22', '13777777777', 'aaawjjc@gmail.com', '中国');
INSERT INTO `user` VALUES ('400000007', 'AC', 'S', 'abc', '男', '22', '15311111111', '111@qq.com', 'Earth');
DROP TRIGGER IF EXISTS `after_course_insert`;
DELIMITER ;;
CREATE TRIGGER `after_course_insert` AFTER INSERT ON `course` FOR EACH ROW BEGIN
    INSERT INTO teacher_teach_course (TeacherID, CourseID)
    VALUES (NEW.CourseTeacherID, NEW.CourseID);
END
;;
DELIMITER ;
